package ch.heigvd.amt.projet1.ui.web.question;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionCommand;
import ch.heigvd.amt.projet1.application.questionmanagement.QuestionException;
import ch.heigvd.amt.projet1.domain.question.Question;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@WebServlet(name = "QuestionsPage",urlPatterns = "/questions.do")
public class QuestionsServlet<TestQuestion> extends javax.servlet.http.HttpServlet {

    @Inject
    private ServiceRegistry  serviceRegistry;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        if (session.getAttribute("currentUser")!=null) {
            String subj = request.getParameter("subject_form");
            List<String> tags = Arrays.asList(request.getParameter("tags_form").toUpperCase().split(" ").clone());
            String cont = request.getParameter("content_form");
            try{
                CurrentUserDTO currentUserDTO = (CurrentUserDTO)session.getAttribute("currentUser");
            QuestionCommand questionCommand = QuestionCommand.builder()
                    .author(currentUserDTO.getUsername())
                    .content(cont)
                    .subject(subj)
                    .tags(tags)
                    .build();

                serviceRegistry.getQuestionFacade().saveQuestion(questionCommand);
            }catch (QuestionException e){
                request.getSession().setAttribute("errors", List.of(e.getMessage()));
            }

            response.setContentType("text/html;charset=UTF-8");
            int currentPage = 1;
            if (request.getParameter("currentPage") != null)  {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }
            if (currentPage <= 0) currentPage = 1;

            int nbPerPage = 7;

            int nbQ = serviceRegistry.getStatisticFacade().getQuestionSize();
            int nbPages = (nbQ / nbPerPage);
            if (nbQ % nbPerPage > 0) { nbPages++;}

            int[] Pages = IntStream.range(1, nbPages+1).toArray();

            request.setAttribute("Pages", Pages);
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("nbPerPage", nbPerPage);

            List<Question> questions = serviceRegistry.getQuestionFacade().getPageQuestions(currentPage, nbPerPage);

            List<Integer> votes = new ArrayList<>();;
            int i = 0;
            for (Question question:questions) {
                i =0;
                i+= serviceRegistry.getVoteFacade().count(question.getId(),"question",true);
                i -=serviceRegistry.getVoteFacade().count(question.getId(),"question",false);
                votes.add(i);
            }

            request.setAttribute("Vs", votes);
            request.setAttribute("Qs",questions);
            request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
        }

    }
}