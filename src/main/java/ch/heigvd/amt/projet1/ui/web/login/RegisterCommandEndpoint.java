package ch.heigvd.amt.projet1.ui.web.login;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;


import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterCommandEndpoint",urlPatterns = "register.do")
public class RegisterCommandEndpoint extends HttpServlet {
    @EJB(beanName="PersonDAO")
    PersonDAOLocal personDAO;

    @Inject
    private ServiceRegistry  serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("errors");
        RegisterCommand registerCommand = RegisterCommand.builder()
                .username(req.getParameter("username"))
                .firstname(req.getParameter("firstname"))
                .lastname(req.getParameter("lastname"))
                .email(req.getParameter("email"))
                .clearPassword(req.getParameter("password"))
                .build();
        IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityFacade();
        try {
            identityManagementFacade.register(registerCommand);
            req.getRequestDispatcher("login.do").forward(req,resp);
            return;
        }catch (RegisterFailedException e){
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect(req.getContextPath()+"/register");
            return;
        }
    }
}
