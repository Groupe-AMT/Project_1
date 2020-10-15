package ch.heigvd.amt.projet1.ui.web.login;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentifcateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginCommandEndpoint",urlPatterns = "/login.do")
public class LoginCommandEndpoint extends HttpServlet {
    @Inject
    PersonDAOLocal personDAO;

    @Inject
    private ServiceRegistry serviceRegistry;
    private IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("errors");
        CurrentUserDTO currentUser= null;
        AuthentifcateCommand authentifcateCommand = AuthentifcateCommand.builder()
                .username(req.getParameter("username"))
                .clearPassword(req.getParameter("password"))
                .build();
        try {
            currentUser = identityManagementFacade.authenticate(authentifcateCommand);
            req.getSession().setAttribute("currentUser",currentUser);
            String targetUrl = (String)req.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl!=null)?targetUrl:"/questions";
            resp.sendRedirect(req.getContextPath()+targetUrl);
            return;
        }catch (AuthentificateFailedException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}
