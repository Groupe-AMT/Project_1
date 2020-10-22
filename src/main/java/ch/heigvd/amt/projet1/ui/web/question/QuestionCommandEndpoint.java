package ch.heigvd.amt.projet1.ui.web.question;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerCommand;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerException;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerManagementFacade;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentCommand;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentException;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "QuestionCommandEndpoint",urlPatterns = "/question.do")
public class QuestionCommandEndpoint extends HttpServlet {
    @Inject
    private ServiceRegistry serviceRegistry;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        req.setCharacterEncoding("UTF-8");

        if (session.getAttribute("currentUser")!=null) {
            CurrentUserDTO currentUserDTO = (CurrentUserDTO) session.getAttribute("currentUser");

            if (req.getParameter("type") != null) {
                CommentCommand commentCommand = CommentCommand.builder()
                        .author(currentUserDTO.getUsername())
                        .content(req.getParameter("answer"))
                        .type(req.getParameter("type"))
                        .Id(req.getParameter("ida"))
                        .build();
                CommentManagementFacade commentManagementFacade = serviceRegistry.getCommentFacade();
                try {
                    commentManagementFacade.saveComment(commentCommand);
                } catch (CommentException e) {
                    req.getSession().setAttribute("errors", List.of(e.getMessage()));
                    resp.sendRedirect(req.getContextPath() + "/question?id=" + req.getParameter("id"));
                }finally {
                    resp.sendRedirect(req.getContextPath() + "/question?id=" + req.getParameter("id"));
                    return;
                }
            } else {
                AnswerCommand answerCommand = AnswerCommand.builder()
                        .author(currentUserDTO.getUsername())
                        .content(req.getParameter("answer"))
                        .questionId(req.getParameter("id"))
                        .build();
                AnswerManagementFacade answerManagementFacade = serviceRegistry.getAnswerFacade();
                try {
                    answerManagementFacade.saveAnswer(answerCommand);

                    resp.sendRedirect(req.getContextPath() + "/question?id=" + req.getParameter("id"));
                    return;
                } catch (AnswerException e) {
                    req.getSession().setAttribute("errors", List.of(e.getMessage()));
                    resp.sendRedirect(req.getContextPath() + "/question?id=" + req.getParameter("id"));
                    return;
                }
            }
        }
    }
}
