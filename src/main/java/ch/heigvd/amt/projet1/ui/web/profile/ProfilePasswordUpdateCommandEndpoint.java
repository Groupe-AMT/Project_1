package ch.heigvd.amt.projet1.ui.web.profile;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.updatePassword.UpdatePasswordCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.updatePassword.UpdatePasswordFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.updateProfile.UpdateProfileCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.updateProfile.UpdateProfileFailedException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfilePasswordUpdateCommandEndpoint",urlPatterns = "/updatePass.do")
public class ProfilePasswordUpdateCommandEndpoint extends HttpServlet {
    @Inject
    private ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Object errors = req.getSession().getAttribute("errors");
        req.setAttribute("errors",errors);
        req.getSession().removeAttribute("errors");

        Object currentUser = req.getSession().getAttribute("currentUser");
        CurrentUserDTO userDTO = (CurrentUserDTO) currentUser;

        UpdatePasswordCommand updatePasswordCommand = UpdatePasswordCommand.builder()
                .prev_pass(req.getParameter("pass"))
                .new_pass(req.getParameter("newpass"))
                .build();

        try {
            serviceRegistry.getIdentityFacade().updatePassword(userDTO, updatePasswordCommand);
            req.getSession().setAttribute("errors", "Changements effectués");
            resp.sendRedirect(req.getContextPath()+"/updatePass");
            return;
        }catch (UpdatePasswordFailedException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect(req.getContextPath()+"/updatePass");
        }
    }
}
