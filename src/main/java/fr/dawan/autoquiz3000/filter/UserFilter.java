package fr.dawan.autoquiz3000.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.autoquiz3000.beans.User;



public class UserFilter {
	
    public UserFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse res= (HttpServletResponse) response;
		User user= (User) req.getSession().getAttribute("user");
		
		if(user == null) {
			String url= req.getContextPath()+"/public/connection";
			res.sendRedirect( url );
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
