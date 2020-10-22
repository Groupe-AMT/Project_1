package ch.heigvd.amt.projet1.ui.web.question;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.comment.Comment;
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
            Question question=null;
            for(Question q : questions) {
                if(q.getId().asString().equals(id)) {
                    question = q;
                    break;
                }
            }
            if(question==null)
                request.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(request, response);

            List<Answer> answers = serviceRegistry.getAnswerFacade().getRelatedAnswer(question.getId());
            List<List<Comment>> comments = new ArrayList<>();
            List<Integer> vote = new ArrayList<>();
            List<Comment>c =serviceRegistry.getCommentFacade().getRelatedComment(question.getId(),"question");
            int i =0;
            i+= serviceRegistry.getVoteFacade().count(question.getId(),"question",true);
            i -=serviceRegistry.getVoteFacade().count(question.getId(),"question",false);
            vote.add(i);
            comments.add(c);

            for (Answer answer:answers) {
                comments.add(serviceRegistry.getCommentFacade().getRelatedComment(answer.getId(),"answer"));
                i =0;
                i+= serviceRegistry.getVoteFacade().count(answer.getId(),"answer",true);
                i -=serviceRegistry.getVoteFacade().count(answer.getId(),"answer",false);
                vote.add(i);
            }


            request.setAttribute("Q",question);
            request.setAttribute("As",answers);
            request.setAttribute("Cs",comments);
            request.setAttribute("Vs",vote);
            request.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(request, response);
        }
    }

