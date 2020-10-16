package ch.heigvd.amt.projet1.ui.web;

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
import java.util.Arrays;
import java.util.List;

@WebServlet("/questions.do")
public class QuestionsServlet<TestQuestion> extends javax.servlet.http.HttpServlet {

    @Inject
    private ServiceRegistry  serviceRegistry;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions = serviceRegistry.getQuestionFacade().getQuestions();
        request.setAttribute("Qs",questions);
        request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("currentUser")!=null) {
            String subj = request.getParameter("subject_form");
            List<String> tags = Arrays.asList(request.getParameter("tags_form").split("/").clone());
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

            List<Question> questions = serviceRegistry.getQuestionFacade().getQuestions();
            request.setAttribute("Qs",questions);
            request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
        }

    }
}