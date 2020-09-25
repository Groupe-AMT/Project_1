package ch.heigvd.amt.projet1.presentation;

import ch.heigvd.amt.projet1.business.Checker;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login.do")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    private Checker business;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        business = new Checker();
    }
    protected  void doGet (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("login")==null)
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/WEB-INF/views/logout.jsp").forward(request, response);

    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("login")==null) {

            session=request.getSession();
            String login = request.getParameter("uname");
            String password = request.getParameter("psw");
            boolean model = business.login(login, password);
            if(model){
                session.setAttribute("login",login);
                request.setAttribute("login", model);
                String target = (String)session.getAttribute("target");
                target = (target!=null)?target:"/Projet_1/questions";
                response.sendRedirect(target);
                return;
            }
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}