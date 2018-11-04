package fr.dawan.autoquiz3000;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
import fr.dawan.autoquiz3000.tools.StatStudentClass;

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
		for (StudentClass st : stclasses) {
			st.setQuizzes(null);
			st.setStudents(null);
		}
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
		HttpSession session= request.getSession();
		Quiz quiz= (Quiz) session.getAttribute("quiz");
		if(quiz != null) {
			model.addAttribute("quiz", quiz);
			return new ModelAndView("professor/viewQuiz");
		} else {
			// TODO rediriger vers une 404
		}
		return new ModelAndView("professor/viewQuiz");
	}
	
	@GetMapping("/close_quiz")
	public ModelAndView getCloseQuiz(Model model, HttpServletRequest request) {
		HttpSession session= request.getSession();
		Quiz quiz= (Quiz) session.getAttribute("quiz");
		if(quiz != null) {
			qDao.save(quiz);
			List<StudentClass> stclasses= quiz.getStClasses();
			for (StudentClass stClass : stclasses) {
				Set<User> students= stClass.getStudents();
				for (User student : students) {
					QuizToDo qtd= new QuizToDo();
					qtd.setIdQuiz(quiz.getId());
					qtd.setIdUser(student.getId());
					qtdDao.save(qtd);
				}
			}
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
			
			session.setAttribute("quiz", ctrl.getQuiz());
			session.setAttribute("nbQuestion", 0);
			return new ModelAndView("redirect:/professor/create_question");
		}
		List<StudentClass> stclasses= stDao.findAll();
		model.addAttribute("classes", stclasses);
		request.setAttribute("ctrl", ctrl);
		return new ModelAndView("professor/createQuiz");
	}
	
	@PostMapping("/create_question")
	public ModelAndView postQestion(Model model, HttpServletRequest request) {
		HttpSession session= request.getSession();
		Quiz quiz= (Quiz) session.getAttribute("quiz");
		if(quiz != null) {
			CtrlQuizQuestion ctrl= new CtrlQuizQuestion(request, quiz);
			if(!ctrl.isError()) {
				return new ModelAndView("professor/createQuestion");
			} else {
				model.addAttribute("question", ctrl.getQuestion());
				model.addAttribute("ctrl", ctrl);
				return new ModelAndView("professor/createQuestion");	
			}

		}else {
			return new ModelAndView("professor/create_quiz");
		}
	}
	
	@GetMapping("viewQuizCs/{id}")
	public String viewQuizzConsult(@PathVariable("id") Long id,Model model) {
			model.addAttribute("quiz", qDao.findById(id));
			return "professor/viewQuizdb";
	}
	
	@GetMapping("professorDashboard")
	public String viewProfessorDashboard(Model model) {
		model.addAttribute("classes", stDao.findAll());
		model.addAttribute("quizzes", qDao.findAll());
		return "professor/professorDashboard";
	}
	
	@GetMapping("studentClassDashboard/{id}")
	public String viewStudentClassDashboard(@PathVariable("id") Long id,Model model) {
		StudentClass sc=stDao.findById(id);
		List<StatStudentClass> statQuiz=stDao.getStatistic(sc);
		List<Quiz> t=qDao.findbyStudentClass(sc);
		model.addAttribute("nbStudent",sc.getStudents().size());
		model.addAttribute("classe", sc);
		model.addAttribute("usersAssigned", uDao.findByAssignedStudentClass(sc));
		model.addAttribute("quizzes",t);
		model.addAttribute("StatQuiz",statQuiz);
		return "professor/studentclassDashboard";
	}
		
	public void setcDao(StClassDao stDao) {
		this.stDao = stDao;
	}
	
	public void setqDao(QuizDao qDao) {
		this.qDao = qDao;
	}
}
