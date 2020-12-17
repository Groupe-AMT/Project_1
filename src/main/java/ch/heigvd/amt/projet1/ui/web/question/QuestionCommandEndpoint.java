package ch.heigvd.amt.projet1.ui.web.question;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerCommand;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerException;
import ch.heigvd.amt.projet1.application.answermanagement.AnswerManagementFacade;
import ch.heigvd.amt.projet1.application.apimanagement.ApiManagementFacade;
import ch.heigvd.amt.projet1.application.apimanagement.Event;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentCommand;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentException;
import ch.heigvd.amt.projet1.application.commentmanagement.CommentManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.application.votemanagement.VoteCommand;
import ch.heigvd.amt.projet1.application.votemanagement.VoteException;
import ch.heigvd.amt.projet1.application.votemanagement.VoteManagementFacade;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import lombok.SneakyThrows;

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
import java.util.UUID;

@WebServlet(name = "QuestionCommandEndpoint",urlPatterns = "/repquestion.do")
public class QuestionCommandEndpoint extends HttpServlet {
    @Inject
    private ServiceRegistry serviceRegistry;
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        req.setCharacterEncoding("UTF-8");

        if (session.getAttribute("currentUser")!=null) {
            CurrentUserDTO currentUserDTO = (CurrentUserDTO) session.getAttribute("currentUser");

            if (req.getParameter("type") != null) {
                if (req.getParameter("vote") != null) {
                    Boolean vote = Boolean.parseBoolean(req.getParameter("vote"));
                    String s = currentUserDTO.getUsername();
                    String type = req.getParameter("type");
                    VoteCommand voteCommand = VoteCommand.builder()
                            .author(s)
                            .Id(req.getParameter("vid"))
                            .type(req.getParameter("type"))
                            .note(vote)
                            .build();
                    VoteManagementFacade voteManagementFacade = serviceRegistry.getVoteFacade();
                    ApiManagementFacade.SendVoteEvent(req); //envoi du vote à l'API de gamification
                    try {
                        voteManagementFacade.saveVote(voteCommand);
                    } catch (VoteException e) {
                        req.getSession().setAttribute("errors", List.of(e.getMessage()));
                    } finally {
                        resp.sendRedirect(req.getContextPath() + "/question?id=" + req.getParameter("id"));
                        return;
                    }
                }
            }

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
            }
            else {
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
