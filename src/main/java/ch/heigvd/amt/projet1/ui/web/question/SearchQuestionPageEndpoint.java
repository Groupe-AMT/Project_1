package ch.heigvd.amt.projet1.ui.web.question;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.domain.question.Question;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@WebServlet(name = "SearchQuestionsPageEndpoint",urlPatterns = "/search")
public class SearchQuestionPageEndpoint extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int currentPage = 1;
        if (request.getParameter("currentPage") != null)  {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        if (currentPage <= 0) currentPage = 1;

        List<String> Tags = Arrays.asList(request.getParameter("tags_form").toUpperCase().split(" ").clone());

        int nbPerPage = 7;

        int nbQ = serviceRegistry.getStatisticFacade().getQuestionSize();
        int nbPages = (nbQ / nbPerPage);
        if (nbQ % nbPerPage > 0) { nbPages++;}

        int[] Pages = IntStream.range(1, nbPages+1).toArray();

        request.setAttribute("Pages", Pages);
        request.setAttribute("nbPages", nbPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("nbPerPage", nbPerPage);

        List<Question> questions = serviceRegistry.getQuestionFacade().getFilteredPageQuestions(currentPage, nbPerPage, Tags);

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
