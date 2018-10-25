package fr.dawan.autoquiz3000;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.ctrl.CtrlQuiz;
import fr.dawan.autoquiz3000.dao.QuizDao;
import fr.dawan.autoquiz3000.dao.SkillDao;
import fr.dawan.autoquiz3000.dao.StClassDao;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private SkillDao sDao;
	
	@Autowired
	private QuizDao qDao;
	
	@Autowired
	private StClassDao stDao;
	
	@GetMapping("/create_quiz")
	public ModelAndView getQuiz(Model model) {
		List<StudentClass> stclasses= stDao.findAll();
		model.addAttribute("classes", stclasses);
		return new ModelAndView("professor/createQuiz");
	}
	
	@PostMapping("/create_quiz")
	public RedirectView postQuiz(@RequestParam String stClassName,
			@RequestParam String name, 
			@RequestParam String skill,
			@RequestParam String startDebut,
			@RequestParam String endDate,
			Model model, 
			HttpServletRequest request) {
		CtrlQuiz ctrl= new CtrlQuiz(sDao, stDao);
		ctrl.createQuiz(name, skill, startDebut, endDate, stClassName);
		System.out.println(ctrl);
		if(!ctrl.isError()) {
			qDao.save(ctrl.getQuiz());
			request.getSession().setAttribute("quiz", ctrl.getQuiz());
			return new RedirectView(request.getContextPath()+"/professor/create_question");
		}
		return new RedirectView(request.getContextPath()+"/professor/create_quiz");
	}

	public void setcDao(StClassDao stDao) {
		this.stDao = stDao;
	}
	public void setqDao(QuizDao qDao) {
		this.qDao = qDao;
	}
}
