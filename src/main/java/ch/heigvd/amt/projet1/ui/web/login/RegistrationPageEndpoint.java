package ch.heigvd.amt.projet1.ui.web.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationPageEndpoint",urlPatterns = "/registration")
public class RegistrationPageEndpoint extends HttpServlet {
    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Object errors = req.getSession().getAttribute("errors");
    req.setAttribute("errors",errors);
    req.getSession().removeAttribute("errors");
    req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,resp);
}
}
