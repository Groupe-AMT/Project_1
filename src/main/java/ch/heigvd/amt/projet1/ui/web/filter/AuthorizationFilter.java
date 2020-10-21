package ch.heigvd.amt.projet1.ui.web.filter;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter (filterName ="AuthorizationFilter",urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        String s = request.getRequestURI();
        // do nothing if is assets or log-in/out and register pages
        if(isPublicRessource(request.getRequestURI(),request)) {

            chain.doFilter(req, rep);
            return;
        }
        //if not log redirect to login page
        CurrentUserDTO currentUser = (CurrentUserDTO)((HttpServletRequest) req).getSession().getAttribute("currentUser");

        if(currentUser==null){
            String target = request.getRequestURI();
            if(request.getQueryString()!=null)
                target+='?'+request.getQueryString();
            ((HttpServletRequest) req).getSession().setAttribute("target",target);
            ((HttpServletResponse)rep).sendRedirect( request.getContextPath()+"/login");
            return;
        }
    chain.doFilter(req, rep);


    }

    @Override
    public void destroy() {

    }
    boolean isPublicRessource(String URI,HttpServletRequest req ){
        if(URI.startsWith(req.getContextPath()+"/assets"))
            return true;
        if(URI.startsWith(req.getContextPath()+"/login"))
            return true;
        if(URI.startsWith(req.getContextPath()+"/logout"))
            return true;
        if(URI.startsWith(req.getContextPath()+"/register"))
            return true;
        if(URI.startsWith(req.getContextPath()+"/index.jsp"))
            return true;
        return false;
    }
}
