package fr.dawan.autoquiz3000;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return new ModelAndView("viewConnexion");
	}
	
	@PostMapping("/connection")
	public RedirectView postConnection(@RequestBody User user, Model model) {
		
		User u= uDao.findByEmailAndPassword(user.getEmail(), Ctrl.MySQLPassword(user.getPassword()));
		if(u != null) {
			if (u.getType() == UserType.PROFESSOR) {
				model.addAttribute("isProfessor", true);
			}else if (u.getType() == UserType.ADMINISTRATOR) {
				model.addAttribute("isAdmin", true);
			}
			model.addAttribute("user", user);
		}
		return new RedirectView("/student");
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}
}
