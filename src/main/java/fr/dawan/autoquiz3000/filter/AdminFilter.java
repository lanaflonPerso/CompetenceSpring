package fr.dawan.autoquiz3000.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminFilter implements Filter {
    public AdminFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse res= (HttpServletResponse) response;
		boolean isAdmin= false;
		
		if(req.getSession().getAttribute("isAdmin") != null) {
			isAdmin= (Boolean) req.getSession().getAttribute("isAdmin");
		}
		
		if(!isAdmin) {
			String url= req.getContextPath()+"/public/connection/professor";
			res.sendRedirect( url );
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
