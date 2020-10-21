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
import java.util.List;
import java.util.stream.IntStream;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "QuestionsPageEndpoint",urlPatterns = "/questions")
public class QuestionsPageEndpoint extends HttpServlet {

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

            int nbPerPage = 7;

            int nbPages = serviceRegistry.getStatisticFacade().getQuestionSize() / nbPerPage;
            if (nbPages % nbPerPage > 0) { nbPages++;}

            int[] Pages = IntStream.range(1, nbPages+1).toArray();

            request.setAttribute("Pages", Pages);
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("nbPerPage", nbPerPage);

            List<Question> questions = serviceRegistry.getQuestionFacade().getPageQuestions(currentPage, nbPerPage);

            request.setAttribute("Qs",questions);
            request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
        }
}
