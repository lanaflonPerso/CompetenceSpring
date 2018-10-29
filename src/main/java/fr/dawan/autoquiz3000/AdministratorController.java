package fr.dawan.autoquiz3000;

import java.util.List;

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
import fr.dawan.autoquiz3000.dao.StClassDao;
import fr.dawan.autoquiz3000.dao.UserDao;
import fr.dawan.autoquiz3000.formbeans.StudentClassForm;

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
				max = 50;
			if (page==0)
				page = 1;
			int start =(page-1)*max;
			model.addAttribute("stclasses", scDao.findAll(start, max));
			boolean suivExist = (page*max)< scDao.count();
			model.addAttribute("suivExist", suivExist);
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
				return "redirect:/administrator/studentclass?page=1&max=50";
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
		return "redirect:/administrator/studentclass?page=1&max=50";
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
	
	@RequestMapping(value = "/professor/studentclass/{id}")
	public String dashboardStudentClass(@PathVariable("id") Long id, Model model) {
		StudentClass sc=scDao.findById(id);
		List<User> userAssigned=userDao.findByStudentClass(sc);
		model.addAttribute("userAssigned", userAssigned);
		model.addAttribute("classe", sc);
		return "professor/studentclassDashboard";
	}
	
	@RequestMapping(value = "/administrator/assignStudent/{id}")
	public String assignStudentClass(@PathVariable("id") Long id, Model model) {
		StudentClass sc=scDao.findById(id);
		List<User> user=userDao.findAll(); //TODO a modifier
		model.addAttribute("user", user);
		model.addAttribute("classe", sc);
		return "administrator/assignStudent";
	}
	
}
