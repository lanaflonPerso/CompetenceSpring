package fr.dawan.autoquiz3000.filter;

import java.io.IOException;
import java.util.List;

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

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.QuizTest;
import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.dao.QuizDao;
import fr.dawan.autoquiz3000.dao.QuizTestDao;
import fr.dawan.autoquiz3000.dao.QuizToDoDao;

@Component("CountQuizStudent")
public class CountQuizStudent implements Filter {
	
	@Autowired
	private QuizTestDao quizTestDao;
	
	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private QuizToDoDao quizToDoDao;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
				
		List<Quiz> lq= quizDao.findEndQuiz();
		if(lq != null) {
			for (Quiz quiz : lq) {
				for (StudentClass stClass: quiz.getStClasses()) {
					for (User u: stClass.getStudents()) {
						boolean ifExist= quizToDoDao.DeleteQuizByQuizAndStudent(u.getId(), quiz.getId());
						if(ifExist) {
							QuizTest qt= new QuizTest();
							qt.setCorrectResponse(0);
							qt.setErrorResponse(0);
							qt.setUser(u);
							qt.setQuiz(quiz);
							quizTestDao.save(qt);
						}
					}
				}
				quiz.setEndDate(null);
				quiz.setStartDate(null);
				quizDao.save(quiz);
			}
		}		

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
	public void setQuizDao(QuizDao quizDao) {
		this.quizDao = quizDao;
	}
	public void setQuizTestDao(QuizTestDao quizTestDao) {
		this.quizTestDao = quizTestDao;
	}
}
