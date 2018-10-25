package fr.dawan.springmvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.dawan.springmvc.formbeans.LoginForm;

@Controller
public class LoginController {

	@RequestMapping("/authenticate")//@requestMapping(value="/autenticate", method=RequestMethod.GET)
	public ModelAndView showLogin() {
			Map<String,	Object> model = new HashMap<String,	Object>();
			LoginForm lf = new LoginForm("admin@dawan.fr","yourPassword");
			model.put("login-form", lf);
			return new ModelAndView("login", model);
	}
	
//	@RequestMapping("/authenticate")//@requestMapping(value="/autenticate", method=RequestMethod.GET)
//	public String showLogin(Model model) {
//			LoginForm lf = new LoginForm("admin@dawan.fr","yourPassword");
//			model.addAttribute("loginForm", lf);
//			return "login";
//	}
	
	@RequestMapping(value="/check-login", method=RequestMethod.POST)
	public ModelAndView checkLogin(HttpServletRequest request,
		@Valid @ModelAttribute("login-form")LoginForm form, BindingResult result) {
		Map<String,	Object> model = new HashMap<String,	Object>();
		
		if(result.hasErrors()) {
			model.put("errors",result);
			model.put("login-form", form);
			return new ModelAndView("login",model);
		}
		
		//TODO check user in database
		if(form.getUsername().equals("admin@dawan.fr") && form.getPassword().equals("admin")) {
			
			request.getSession().setAttribute("isConnected", true);
			request.getSession().setAttribute("username", form.getUsername());
			return new ModelAndView("espace-admin");
			
		}else {
			model.put("login-form", form);
			model.put("msg", "Error : incorrect login or password !");
			return new ModelAndView("login",model);
		}
		
	}
}
