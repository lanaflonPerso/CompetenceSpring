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
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.dao.QuizToDoDao;

@Component("CountQuizStudent")
public class CountQuizStudent implements Filter {

	@Autowired
	private QuizToDoDao quizToDoDao;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("user");
		int nbrQuiz = quizToDoDao.findNbQuizByStudent(user.getId());
		session.setAttribute("nbrQuiz", nbrQuiz);

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext());
		quizToDoDao = springContext.getBean(QuizToDoDao.class);
	}

	public void setQuizToDoDao(QuizToDoDao quizToDoDao) {
		this.quizToDoDao = quizToDoDao;
	}
}
