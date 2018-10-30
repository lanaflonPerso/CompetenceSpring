package fr.dawan.autoquiz3000;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.QuizToDo;
import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.ctrl.CtrlQuiz;
import fr.dawan.autoquiz3000.ctrl.CtrlQuizQuestion;
import fr.dawan.autoquiz3000.dao.QuizDao;
import fr.dawan.autoquiz3000.dao.QuizToDoDao;
import fr.dawan.autoquiz3000.dao.SkillDao;
import fr.dawan.autoquiz3000.dao.StClassDao;
import fr.dawan.autoquiz3000.dao.UserDao;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private SkillDao sDao;
	
	@Autowired
	private QuizDao qDao;
	
	@Autowired
	private StClassDao stDao;
	
	@Autowired
	private QuizToDoDao qtdDao;
	
	@Autowired
	private UserDao uDao;
	
	@GetMapping("/create_quiz")
	public ModelAndView getQuiz(Model model) {
		List<StudentClass> stclasses= stDao.findAll();
		model.addAttribute("classes", stclasses);
		return new ModelAndView("professor/createQuiz");
	}
	
	@GetMapping("/create_question")
	public ModelAndView getQuestion(Model model, HttpServletRequest request) {
		HttpSession session= request.getSession();
		Quiz quiz= (Quiz) session.getAttribute("quiz");
		
		request.getSession().setAttribute("quiz", quiz);
		return new ModelAndView("professor/createQuestion");
	}
	
	@GetMapping("/view_quiz")
	public ModelAndView getViewQuiz(Model model, HttpServletRequest request) {
		Quiz quiz= (Quiz) request.getSession().getAttribute("quiz");
		if(quiz != null) {
			model.addAttribute("nbQuestion", quiz.getQuizQuestions().size());
			model.addAttribute("quiz", quiz);
			return new ModelAndView("professor/viewQuiz");
		} else {
			// TODO rediriger vers une 404
		}
		return new ModelAndView("professor/viewQuiz");
	}
	
	@GetMapping("/close_quiz")
	public ModelAndView getCloseQuiz(Model model, HttpServletRequest request) {
		Quiz quiz= (Quiz) request.getSession().getAttribute("quiz");
		if(quiz != null) {
			List<StudentClass> stclasses= quiz.getStClasses();
			for (StudentClass stClass : stclasses) {
				List<User> students= stClass.getStudents();
				for (User student : students) {
					QuizToDo qtd= new QuizToDo();
					qtd.setIdQuiz(quiz.getId());
					qtd.setIdUser(student.getId());
					qtdDao.save(qtd);
				}
			}
			qDao.save(quiz);
			return new ModelAndView("professor/viewQuiz");
		} else {
			// TODO rediriger vers une 404
		}
		return new ModelAndView("professor/viewQuiz");
	}
	
	@PostMapping("/create_quiz")
	public ModelAndView postQuiz(@RequestParam String stClassName,
			@RequestParam String name, 
			@RequestParam String skill,
			@RequestParam String startDebut,
			@RequestParam String endDate,
			@RequestParam String scoreToAcquireSkill,
			Model model, 
			HttpServletRequest request) {
		CtrlQuiz ctrl= new CtrlQuiz(sDao, stDao);
		ctrl.createQuiz(name, skill, startDebut, endDate, stClassName, scoreToAcquireSkill);
		if(!ctrl.isError()) {
			HttpSession session= request.getSession();
			
			qDao.save(ctrl.getQuiz());
			session.setAttribute("quiz", ctrl.getQuiz());
			session.setAttribute("nbQuestion", 0);
			return new ModelAndView("redirect:/professor/create_question");
		}
		List<StudentClass> stclasses= stDao.findAll();
		model.addAttribute("classes", stclasses);
		request.setAttribute("ctrl", ctrl);
		System.out.println("=========================== ctrl= "+ctrl);
		return new ModelAndView("professor/createQuiz");
	}
	
	@PostMapping("/create_question")
	public RedirectView postQestion(Model model, HttpServletRequest request) {
		HttpSession session= request.getSession();
		Quiz quiz= (Quiz) session.getAttribute("quiz");
		if(quiz != null) {
			CtrlQuizQuestion ctrl= new CtrlQuizQuestion(request, quiz);
			if(!ctrl.isError()) {
				System.out.println("=============================== on enregistre le quiz");
			}
			
		}else {
			//TODO faire redirection si il n'y a pas de quiz en session
		}
		return new RedirectView(request.getContextPath()+"/professor/create_question");
	}

	@GetMapping(value = "/studentClassDashboard/{id}")
	public String viewStudentClassDashboard(@PathVariable("id") Long id,Model model) {
		StudentClass sc=stDao.findById(id);
		model.addAttribute("classe", sc);
		model.addAttribute("usersAssigned", uDao.findByAssignedStudentClass(sc));
		model.addAttribute("quizs",qDao.findbyStudentClass(sc));
		return "professor/studentclassDashboard";
	}
		
	public void setcDao(StClassDao stDao) {
		this.stDao = stDao;
	}
	public void setqDao(QuizDao qDao) {
		this.qDao = qDao;
	}
}
