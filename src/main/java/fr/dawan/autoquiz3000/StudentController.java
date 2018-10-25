package fr.dawan.autoquiz3000;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {

	@GetMapping("/")
	public ModelAndView getDashboard() {
		return new ModelAndView("viewDashboard");
	}
}
