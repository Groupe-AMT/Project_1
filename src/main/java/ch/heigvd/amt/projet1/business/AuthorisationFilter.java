package ch.heigvd.amt.projet1.business;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter (filterName ="AuthorisationFilter",urlPatterns = "/*")
public class AuthorisationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        // do nothing if is assets or log-in/out and register pages
        if(isPublicRessource(((HttpServletRequest) req).getRequestURI())) {
            chain.doFilter(req, rep);
            return;
        }
        //if not log redirect to login page
        String person = (String)request.getSession().getAttribute("login");
        if(person==null){
            String target = request.getRequestURI();
            if(request.getQueryString()!=null)
                target+='?'+request.getQueryString();
            ((HttpServletRequest) req).getSession().setAttribute("target",target);
            ((HttpServletResponse)rep).sendRedirect("/Projet_1/login");
            return;
        }
    chain.doFilter(req, rep);


    }

    @Override
    public void destroy() {

    }
    boolean isPublicRessource(String URI){
        if(URI.startsWith("/Projet_1/assets"))
            return true;
        if(URI.startsWith("/Projet_1/login"))
            return true;
        if(URI.startsWith("/Projet_1/logout"))
            return true;
        if(URI.startsWith("/Projet_1/register"))
            return true;
        if(URI.startsWith("/Projet_1/index.jsp"))
            return true;
        return false;
    }
}
