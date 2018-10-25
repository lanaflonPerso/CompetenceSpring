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
import fr.dawan.autoquiz3000.dao.UserDao;

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
	
	@PostMapping("/connection")
	public RedirectView postConnection(@RequestParam String password, @RequestParam String email, Model model, HttpServletRequest request) {
		System.out.println(Ctrl.MySQLPassword(password)+" "+email);
		User u= uDao.findByEmailAndPassword(email, Ctrl.MySQLPassword(password));
		if(u != null) {
			if (u.getType() == UserType.PROFESSOR) {
				request.getSession().setAttribute("isProfessor", true);
			}else if (u.getType() == UserType.ADMINISTRATOR) {
				request.getSession().setAttribute("isProfessor", true);
				request.getSession().setAttribute("isAdmin", true);
			}
			request.getSession().setAttribute("user", u);
			model.addAttribute("user", u);
			return new RedirectView(request.getContextPath()+"/student");
		} else {
			User user= new User();
			user.setEmail(email);
			model.addAttribute("user", u);
			return new RedirectView(request.getContextPath()+"/public/connection");
		}
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}
}
