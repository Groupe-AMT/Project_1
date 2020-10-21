package ch.heigvd.amt.projet1.ui.web.profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfilePasswordUpdatePageEndpoint",urlPatterns = "/updatePass")
public class ProfilePasswordUpdatePageEndpoint extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Object errors = req.getSession().getAttribute("errors");
    req.setAttribute("errors",errors);
    req.getSession().removeAttribute("errors");
    req.getRequestDispatcher("/WEB-INF/views/updatePass.jsp").forward(req,resp);
    }
}
