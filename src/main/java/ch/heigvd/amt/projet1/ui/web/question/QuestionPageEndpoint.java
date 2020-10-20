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

@WebServlet(name = "QuestionPageEndpoint",urlPatterns = "/question")
public class QuestionPageEndpoint extends HttpServlet{

        @Inject
        private ServiceRegistry serviceRegistry;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Question> questions = serviceRegistry.getQuestionFacade().getQuestions();
            String id = request.getParameter("id");
            id = id.substring(0,id.length()-1);
            Question question=null;
            for(Question q : questions) {
                String a =q.getId().asString();
                if(q.getId().asString().equals(id)) {
                    question = q;
                    break;
                }
            }

            request.setAttribute("Q",question);
            request.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(request, response);
        }
    }

