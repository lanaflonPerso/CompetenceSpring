package fr.dawan.autoquiz3000.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.dao.QuizToDoDao;

public class CountQuizStudent implements Filter {
	
	@Autowired
	private QuizToDoDao qtdDao;

    public CountQuizStudent() {   
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		HttpSession session= req.getSession();
		
		User user= (User) session.getAttribute("user");
		System.out.println("================ qtdDao= "+qtdDao);
		System.out.println("================ user.getId()= "+user.getId());
		int nbrQuiz= qtdDao.findNbQuizByStudent(user.getId());
		System.out.println("==============================nombre de quiz= "+nbrQuiz);
		session.setAttribute("nbrQuiz", nbrQuiz);
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
