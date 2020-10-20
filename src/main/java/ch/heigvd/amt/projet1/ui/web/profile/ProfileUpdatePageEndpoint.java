package ch.heigvd.amt.projet1.ui.web.profile;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileUpdatePageEndpoint",urlPatterns = "/updateProfile")
public class ProfileUpdatePageEndpoint extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object errors = req.getSession().getAttribute("errors");
        req.setAttribute("errors",errors);
        req.getSession().removeAttribute("errors");

        Object currentUser = req.getSession().getAttribute("currentUser");
        CurrentUserDTO user = (CurrentUserDTO) currentUser;

        req.setAttribute("name", user.getUsername());
        req.setAttribute("firstname", user.getFirstname());
        req.setAttribute("lastname", user.getLastname());
        req.setAttribute("email", user.getEmail());
        req.getRequestDispatcher("/WEB-INF/views/updateProfile.jsp").forward(req,resp);
    }
}