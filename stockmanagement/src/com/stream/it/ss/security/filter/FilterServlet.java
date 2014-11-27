package com.stream.it.ss.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterServlet implements Filter{

    private FilterConfig filterConfig = null;
	
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        // Skip filter URL pattern
        String loginPage    = "/login";
        String userLogonId 	= "userAuthentication";
        
        /*** Check Login. ***/
        HttpSession session = request.getSession();

        try{

        	if(request.getRequestURI().indexOf(loginPage) < 0&& request.getRequestURI().indexOf(loginPage) < 0 && session.getAttribute(userLogonId) == null){
	        	
	        	if(request.getRequestURI().contains("/login") || request.getRequestURI().contains("/javax.faces") || request.getRequestURI().contains("/resources/")){
	        		chain.doFilter(request, response);	

	        		return;
				}else{
					response.sendRedirect("/" + filterConfig.getServletContext().getServletContextName()+loginPage);

					return;
				}          
	        }
	        
	        request.setCharacterEncoding("UTF-8");        
	        chain.doFilter(request, response);

        }catch(Exception e){
        	e.printStackTrace();
        }
    }

    public void destroy() {
        this.filterConfig = null;
    }


}
