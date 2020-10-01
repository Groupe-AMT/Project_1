package ch.heigvd.amt.projet1.presentation;

import ch.heigvd.amt.projet1.business.TestQuestion;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.persistence.InMemoryQuestionRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/questions.do")
/**
 * The QuoteServlet is the Controller in the pattern. It receives HTTP requests, decides that the
 * QuoteGenerator service can provide the model (a list of Quote objects). After invoking the
 * service and obtaining the model, it attaches the model to the request (as a parameter named 'quotes').
 * Finally, it finds the view capable of rendering the model (questions.jsp) and delegates the end of the work
 * to this component (by calling forwarding the request).
 */
public class QuestionsServlet extends javax.servlet.http.HttpServlet {
    static InMemoryQuestionRepository Qs = new InMemoryQuestionRepository();
    private TestQuestion service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new TestQuestion();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        if (Qs.getContentList().getContent() != null){
            request.setAttribute("Qs", Qs.getContentList().getContent());
        }

        request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login")!=null) {
            String subj = request.getParameter("subject_form");
            String tags = request.getParameter("tags_form");
            String cont = request.getParameter("content_form");

            Qs.getContentList().addContent(new Question("Anonymous", cont, subj, Arrays.asList(tags.split("/"))));

            if (Qs.getContentList().getContent() != null){
                request.setAttribute("Qs", Qs.getContentList().getContent());
            }

            request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);
        }

    }
}