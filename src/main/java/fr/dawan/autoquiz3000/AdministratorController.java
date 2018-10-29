package fr.dawan.autoquiz3000;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;
import fr.dawan.autoquiz3000.dao.StClassDao;
import fr.dawan.autoquiz3000.dao.UserDao;
import fr.dawan.autoquiz3000.formbeans.StudentClassForm;
import fr.dawan.autoquiz3000.formbeans.UserForm;

@Controller
public class AdministratorController {
	
	@Autowired
	private StClassDao scDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/administrator/studentclass", method=RequestMethod.GET)
	public String manageStudentClass(@RequestParam("page") int page, @RequestParam("max") int max,Model model) {
		try {
			if(max==0)
				max = 20;
			if (page==0)
				page = 1;
			int pagemax=1+(int)scDao.count()/max;
			if(page>pagemax)
				page=pagemax;
			model.addAttribute("stclasses", scDao.findAll((page-1)*max, max));
			model.addAttribute("maxpage", pagemax);
			model.addAttribute("studentclass-form", new StudentClassForm());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "administrator/studentclass";
	}
	
	@RequestMapping(value = "/administrator/studentclass", method = RequestMethod.POST)
	public String addStudentClass(@Valid @ModelAttribute("studentclass-form") StudentClassForm form,BindingResult result, Model model) {
		String msg=null;
		if(result.hasErrors()) {
			model.addAttribute("errors",result);
		}else if(form.getStartDate().after(form.getEndDate())) {
			msg="La date de début doit être inférieur à la date de fin";
			model.addAttribute("message", msg);
		} else {
			StudentClass sc=null;
			if(form.getId()!=null)
				sc=scDao.findById(form.getId());
			else	
				sc=new StudentClass();
			sc.setName(form.getName());
			sc.setStartDate(form.getStartDate());
			sc.setEndDate(form.getEndDate());
			if(scDao.isExist(sc)) {
				msg="La classe existe déjà durant cette période";
				model.addAttribute("message", msg);
			}
			else {
				if(sc.getId()==null)
					scDao.save(sc);
				else
					scDao.update(sc);
				return "redirect:/administrator/studentclass?page=1&max=20";
			}
		}
		model.addAttribute("studentclass-form", form);
		model.addAttribute("stclasses", scDao.findAll());
		return "administrator/studentclass";
	}
	
	@RequestMapping(value = "/administrator/studentclass/{id}/delete")
	public String deleteStudentClass(@PathVariable("id") Long id, Model model) {
		//TODO ajouter un test si classe vide et sur Id?
		scDao.delete(id);
		return "redirect:/administrator/studentclass?page=1&max=20";
	}
	
	@RequestMapping(value = "/administrator/studentclass/{id}/update")
	public String updateStudentClass(@PathVariable("id") Long id, Model model) {
		StudentClass sc=scDao.findById(id);
		StudentClassForm scf = new StudentClassForm();
		scf.setId(sc.getId());
		scf.setName(sc.getName());
		scf.setEndDate(sc.getEndDate());
		scf.setStartDate(sc.getStartDate());
		model.addAttribute("studentclass-form", scf);
		model.addAttribute("stclasses", scDao.findAll());
		return "administrator/studentclass";
	}
	
	@RequestMapping(value = "/administrator/user" , method=RequestMethod.GET)
	public String displayUserList(@RequestParam("page") int page, @RequestParam("max") int max,Model model) {
		if(max==0)
			max = 20;
		if (page==0)
			page = 1;
		int pagemax=1+(int)userDao.count()/max;
		if(page>pagemax)
			page=pagemax;
		model.addAttribute("users", userDao.findAll((page-1)*max, max));
		model.addAttribute("maxpage",pagemax);
		return "administrator/userList";
	}

	@RequestMapping(value = "/administrator/user/{id}/delete")
	public String deleteUser(@PathVariable("id") Long id,Model model) {
		userDao.delete(id);
		return "redirect:/administrator/user?page=1&max=20";
	}
	
	@RequestMapping(value = "/administrator/user/{id}/update")
	public String showUpdateUser(@PathVariable("id") Long id,Model model) {
		model.addAttribute("user", userDao.findById(id));
		model.addAttribute("user-form", new UserForm());
		model.addAttribute("lstTypeUser",UserType.values());
		return "administrator/profilUser";
	}
	
	@RequestMapping(value = "/administrator/user", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user-form") UserForm form,BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("errors",result);
			return "administrator/userList";
		}
		User u=userDao.findById(form.getId());
		u.setFirstName(form.getFirstName());
		u.setLastName(form.getLastName());
		u.setBirthdate(form.getBirthdate());
		u.setEmail(form.getEmail());
		u.setType(form.getType());
		userDao.save(u);	
		return "administrator/userList";
	}

}
