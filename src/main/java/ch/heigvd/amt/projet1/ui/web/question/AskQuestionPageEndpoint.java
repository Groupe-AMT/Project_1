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

@WebServlet(name = "askQuestionEndpoint",urlPatterns = "/ask")
public class AskQuestionPageEndpoint extends HttpServlet {

        @Inject
        private ServiceRegistry serviceRegistry;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/WEB-INF/views/askQuestion.jsp").forward(request, response);
        }
}
