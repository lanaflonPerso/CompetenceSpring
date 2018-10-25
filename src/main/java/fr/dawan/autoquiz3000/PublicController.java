package fr.dawan.autoquiz3000;

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

import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;
import fr.dawan.autoquiz3000.ctrl.Ctrl;
import fr.dawan.autoquiz3000.ctrl.CtrlUser;
import fr.dawan.autoquiz3000.dao.UserDao;
import fr.dawan.autoquiz3000.helper.Token;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserDao uDao;
	
	@GetMapping("/connection")
	public ModelAndView getConnection() {
		return new ModelAndView("public/viewConnexion");
	}
	
	@GetMapping("/inscription")
	public ModelAndView getInscription() {
		return new ModelAndView("public/viewInscription");
	}
	
	@GetMapping("/token")
	public ModelAndView getToken() {
		return new ModelAndView("public/viewToken");
	}
	
	@PostMapping("/connection")
	public RedirectView postConnection(@RequestParam String password, @RequestParam String email, Model model, HttpServletRequest request) {
		CtrlUser ctrl= new CtrlUser(uDao);
		ctrl.ctrlEmailAndPassword(email, password);
		if(!ctrl.isError()) {
			if (ctrl.getUser().getType() == UserType.PROFESSOR) {
				request.getSession().setAttribute("isProfessor", true);
			}else if (ctrl.getUser().getType() == UserType.ADMINISTRATOR) {
				request.getSession().setAttribute("isProfessor", true);
				request.getSession().setAttribute("isAdmin", true);
			}
			request.getSession().setAttribute("user", ctrl.getUser());
			return new RedirectView(request.getContextPath()+"/student");
		} else {
			model.addAttribute("user", ctrl.getUser());
			return new RedirectView(request.getContextPath()+"/public/connection");
		}
	}
	
	@PostMapping("/inscription")
	public RedirectView postInscription(@RequestParam String firstName,	@RequestParam String lastName, @RequestParam String birthdate,	@RequestParam String email,	HttpServletRequest request, Model model) {
		CtrlUser ctrl= new CtrlUser(firstName, lastName, email, birthdate, uDao);
		if(!ctrl.isError()) {
			User u= ctrl.getUser();
			u.setToken(Token.getToken());
			uDao.save(u);
			// TODO envoyer un mail
			return new RedirectView(request.getContextPath()+"/public/token");
		} else {
			model.addAttribute("ctrl", ctrl);
			model.addAttribute("user", ctrl.getUser());
			return new RedirectView(request.getContextPath()+"/public/inscription");
		}
	}
	
	@PostMapping("/token")
	public RedirectView postToken(@RequestParam String token, @RequestParam String password, @RequestParam String confirm,	HttpServletRequest request, Model model) {
		CtrlUser ctrl= new CtrlUser(uDao);
		ctrl.ctrlTokenAndPassword(token, password, confirm);
		User u= ctrl.getUser();
		if(!ctrl.isError()) {
			u.setToken("");
			u.setPassword(Ctrl.MySQLPassword(password));
			uDao.save(u);
			request.getSession().setAttribute("user", u);
			return new RedirectView(request.getContextPath()+"/student");
		} else {
			model.addAttribute("user", u);
			return new RedirectView(request.getContextPath()+"/public/token");
		}
	}
	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}
}
