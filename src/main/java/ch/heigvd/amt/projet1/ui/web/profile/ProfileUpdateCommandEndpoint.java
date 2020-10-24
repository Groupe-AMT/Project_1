package ch.heigvd.amt.projet1.ui.web.profile;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
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

@WebServlet(name = "ProfileUpdateCommandEndpoint",urlPatterns = "/updateProfile.do")
public class ProfileUpdateCommandEndpoint extends HttpServlet {
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

        UpdateProfileCommand updateProfileCommand = UpdateProfileCommand.builder()
                .username(req.getParameter("username"))
                .firstname(req.getParameter("firstname"))
                .lastname(req.getParameter("lastname"))
                .email(req.getParameter("email"))
                .build();

        try {
            currentUser = serviceRegistry.getIdentityFacade().updateProfile(userDTO, updateProfileCommand);
            req.getSession().setAttribute("currentUser",currentUser);
            req.getSession().setAttribute("errors", "Changements effectués");
            resp.sendRedirect(req.getContextPath()+"/updateProfile");
            return;
        }catch (UpdateProfileFailedException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect(req.getContextPath()+"/updateProfile");
        }
    }
}
