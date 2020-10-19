package ch.heigvd.amt.projet1.ui.web.profile;

import ch.heigvd.amt.projet1.application.ServiceRegistry;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.domain.statistic.Statistic;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfilePageEndpoint",urlPatterns = "/profile")
public class ProfilePageEndpoint extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object errors = req.getSession().getAttribute("errors");
        req.setAttribute("errors",errors);
        req.getSession().removeAttribute("errors");

        Object currentUser = req.getSession().getAttribute("currentUser");

        CurrentUserDTO user = (CurrentUserDTO) currentUser;

        Statistic stats = serviceRegistry.getStatisticFacade().getStats(user.getUsername());

        req.setAttribute("name", user.getUsername());
        req.setAttribute("stats", stats);
        req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
    }
}
