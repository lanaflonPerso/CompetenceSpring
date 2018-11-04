package fr.dawan.autoquiz3000;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;
import fr.dawan.autoquiz3000.ctrl.Ctrl;
import fr.dawan.autoquiz3000.ctrl.CtrlProfilUser;
import fr.dawan.autoquiz3000.ctrl.CtrlUser;
import fr.dawan.autoquiz3000.dao.UserDao;
import fr.dawan.autoquiz3000.formbeans.UserForm;
import fr.dawan.autoquiz3000.helper.Token;
import fr.dawan.autoquiz3000.tools.EmailTools;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserDao uDao;
	
	@GetMapping("/connection")
	public ModelAndView getConnection() {
		return new ModelAndView("public/viewConnexion");
	}
	
	@GetMapping("/deconnection")
	public ModelAndView getDeconnection(HttpServletRequest request) {
		request.getSession().invalidate();
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
	
	@GetMapping("/userprofil")
	public String getUserProfil(Model model, HttpServletRequest request) {
		model.addAttribute("user-form",new UserForm((User)request.getSession().getAttribute("user")));
		return "public/profilUser";		
	}
	
	@PostMapping("/userprofil")
	public String postUserProfil(@Valid @ModelAttribute("user-form") UserForm form,BindingResult result, Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			model.addAttribute("errors",result);
			model.addAttribute("user-form",form);
			return "public/profilUser";
		}
		else{
			User user=(User)request.getSession().getAttribute("user");
			CtrlProfilUser ctrl=new CtrlProfilUser(form,user,uDao);
			ctrl.ctrlBithDate(form.getBirthdate());
			ctrl.ctrlEmail(form.getEmail());
			if(ctrl.isReinitPassword(form.getPassword())){
				ctrl.ctrlReInitPassword(form.getPassword(), form.getConfirmPassword());
			}
			if(ctrl.isError()) {
				model.addAttribute("message", ctrl.getMsg());
				model.addAttribute("user-form", form);
				return "public/profilUser";
			}
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());
			uDao.save(user);
			request.getSession().setAttribute("user", user);
			model.addAttribute("user",user);
			return "student/home";	
		}
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
			return new RedirectView(request.getContextPath()+"/student/");
		} else {
			model.addAttribute("user", ctrl.getUser());
			return new RedirectView(request.getContextPath()+"/public/connection");
		}
	}
	
	@PostMapping("/inscription")
	public ModelAndView postInscription(@RequestParam String firstName,	@RequestParam String lastName, @RequestParam String birthdate,	@RequestParam String email,	HttpServletRequest request, Model model) {
		CtrlUser ctrl= new CtrlUser(firstName, lastName, email, birthdate, uDao);
		if(!ctrl.isError()) {
			User u= ctrl.getUser();
			u.setToken(Token.getToken());
			u.setType(UserType.STUDENT);
			uDao.save(u);
			StringBuilder sb= new StringBuilder();
			sb.append(u.getToken());
			try {
				EmailTools.getInstance().send(u.getEmail(), "inscription", sb.toString());
			} catch (Exception e) {
				// TODO logger l'erreur
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/public/token");
		} else {
			model.addAttribute("ctrl", ctrl);
			model.addAttribute("user", ctrl.getUser());
			return new ModelAndView("/public/viewInscription");
		}
	}
	
	@PostMapping("/token")
	public ModelAndView postToken(@RequestParam String token, @RequestParam String password, @RequestParam String confirm,	HttpServletRequest request, Model model) {
		CtrlUser ctrl= new CtrlUser(uDao);
		ctrl.ctrlTokenAndPassword(token, password, confirm);
		User u= ctrl.getUser();
		if(!ctrl.isError()) {
			u.setToken("");
			u.setPassword(Ctrl.MySQLPassword(password));
			uDao.save(u);
			request.getSession().setAttribute("user", u);
			return new ModelAndView("redirect:/student/");
		} else {
			model.addAttribute("ctrl", ctrl);
			u.setType(null);
			model.addAttribute("user", u);
			return new ModelAndView("/public/viewToken");
		}
	}
	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}
}
