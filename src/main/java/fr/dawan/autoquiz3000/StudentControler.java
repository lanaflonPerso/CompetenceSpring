package fr.dawan.autoquiz3000;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.QuizQuestion;
import fr.dawan.autoquiz3000.beans.QuizResponse;
import fr.dawan.autoquiz3000.beans.QuizTest;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.ctrl.CtrlQuizTest;
import fr.dawan.autoquiz3000.dao.QuizDao;
import fr.dawan.autoquiz3000.dao.QuizTestDao;

@Controller
@RequestMapping("/student")
public class StudentControler {
	
	@Autowired
	private QuizDao qDao;
	
	@Autowired
	private QuizTestDao qtDao;

	@GetMapping("/")
	public ModelAndView getDashboard() {
		return new ModelAndView("viewDashboard");
	}
	
	@GetMapping("/quiz/{id}")
	public ModelAndView getQuiz(@PathVariable(value="id") String id, Model model) {
		try {
			model.addAttribute("quiz", qDao.findById(Long.valueOf(id)));
			return new ModelAndView("/student/viewQuiz");
		} catch (Exception e) {
			// TODO: erreur 404 ou autre!
		}
		// TODO erreur 404
		return new ModelAndView("fourOFour");
	}
	
	private ModelAndView displayQuestion(long quizId, int orderNum, Model model) {
		QuizQuestion currentQst = qDao.findQuestion(quizId, orderNum);
		model.addAttribute("currentQst",currentQst);
		
		List<QuizResponse> responses = qDao.findReponses(currentQst.getId());
		model.addAttribute("responses", responses);

		return new ModelAndView("/student/viewQuiz");
	}
	
	/* Déroulement d'un quiz */
	@GetMapping("/quiz/{id}/start")
	public ModelAndView startQuiz(@PathVariable(value="id") String id, Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Quiz quiz = qDao.findById(Long.valueOf(id));
			
			QuizTest quizTest= new QuizTest();
			quizTest.setStartDate(new Date());
			quizTest.setUser((User)session.getAttribute("user"));
			
			session.setAttribute("quizName", quiz.getName());
			session.setAttribute("quizId", quiz.getId());
			session.setAttribute("quizTest", quizTest);
			
			session.setAttribute("orderNum", 1);
			session.setAttribute("score", 0);
			session.setAttribute("qstNb", qDao.nbQuestions(quiz.getId()));
			return displayQuestion(quiz.getId(), 1, model);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: erreur 404 ou autre!
		}
		// TODO erreur 404
		return new ModelAndView("fourOFour");
	}
	
	
	@GetMapping("/quiz/next-question")
	public ModelAndView NextQuestionInQuiz(Model model, HttpServletRequest request) {
		String[] responseStudent= request.getParameterValues("response");
		HttpSession session = request.getSession();
		QuizTest quizTest = (QuizTest)session.getAttribute("quizTest");
		int orderNum = (Integer)session.getAttribute("orderNum");
		long quizId = (Long)session.getAttribute("quizId");
		long qstNb = (Long)session.getAttribute("qstNb");
		int score = (Integer)session.getAttribute("score");
		
		CtrlQuizTest ctrl= new CtrlQuizTest(qDao, quizTest);
		ctrl.ctrlResponse(responseStudent, orderNum, quizId);
		ctrl.save();
		if(orderNum<qstNb) {
			orderNum++;
			session.setAttribute("orderNum", orderNum);
			return displayQuestion(quizId, orderNum, model);
		}else {
			score= (quizTest.getCorrectResponse()*100)/(quizTest.getCorrectResponse()+quizTest.getErrorResponse());
			quizTest.setScore(score);
			
			qtDao.save(quizTest);
			new ModelAndView("quiz-result"); //pour afficher le score ${sessionScope.score}
		}
		return null;
	}
	
	//***************************************************************A effacer
	@GetMapping("/test")
	public ModelAndView getQuiz() {	
		return new ModelAndView("student/home");
	}
}
