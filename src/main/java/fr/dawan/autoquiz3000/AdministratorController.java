package fr.dawan.autoquiz3000;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;
import fr.dawan.autoquiz3000.ctrl.CtrlProfilUser;
import fr.dawan.autoquiz3000.ctrl.CtrlUser;
import fr.dawan.autoquiz3000.dao.StClassDao;
import fr.dawan.autoquiz3000.dao.UserDao;
import fr.dawan.autoquiz3000.formbeans.EmailForm;
import fr.dawan.autoquiz3000.formbeans.StudentClassForm;
import fr.dawan.autoquiz3000.formbeans.UserForm;
import fr.dawan.autoquiz3000.helper.Token;
import fr.dawan.autoquiz3000.tools.EmailTools;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private StClassDao scDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/studentclass")
	public String manageStudentClass(@RequestParam("page") int page, @RequestParam("max") int max,Model model) {
		try {
			max=checkPageMax(max);
			int pagemax= ((userDao.count()%max>0?1:0)+(int)scDao.count()/max);
			page=checkPage(page,pagemax);
			model.addAttribute("actionButton","Ajouter");
			model.addAttribute("stclasses", scDao.findAll((page-1)*max, max));
			model.addAttribute("maxpage", pagemax);
			model.addAttribute("studentclass-form", new StudentClassForm());
		} catch (Exception e) {
			// TODO: erreur 404 ou autre!
		}
		return "administrator/studentclass";
	}
	
	@PostMapping("/studentclass")
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
				scDao.save(sc);
				return "redirect:/administrator/studentclass?page=1&max=20";
			}
		}
		model.addAttribute("actionButton", form.getId()==null?"Ajouter":"Modifier");
		model.addAttribute("studentclass-form", form);
		model.addAttribute("stclasses", scDao.findAll());
		return "administrator/studentclass";
	}
	
	@GetMapping("/studentclass/{id}/delete")
	public String deleteStudentClass(@PathVariable("id") Long id, Model model) {
		try {
			StudentClass sc=scDao.findById(id);
			if(sc.getStudents().size()==0)
				scDao.delete(id);
		} catch (Exception e) {
			//TODO erreur 404
		}
		return "redirect:/administrator/studentclass?page=1&max=20";
	}
	
	@GetMapping("/studentclass/{id}/update")
	public String updateStudentClass(@PathVariable("id") Long id, Model model) {
		try {
			StudentClass sc=scDao.findById(id);
			StudentClassForm scf = new StudentClassForm();
			scf.setId(sc.getId());
			scf.setName(sc.getName());
			scf.setEndDate(sc.getEndDate());
			scf.setStartDate(sc.getStartDate());
			model.addAttribute("actionButton","Modifier");
			model.addAttribute("studentclass-form", scf);
			model.addAttribute("stclasses", scDao.findAll());
		} catch (Exception e) {
			//TODO erreur 404
		}
		return "administrator/studentclass";
	}

	@GetMapping("/assignStudent/{id}")
	public String assignStudentClass(@PathVariable("id") Long id, Model model) {
		try {
			StudentClass sc=scDao.findById(id);
			model.addAttribute("assignedusers",userDao.findByAssignedStudentClass(sc));
			model.addAttribute("unasssignedusers",userDao.findByUnAssignedStudentClass());
			model.addAttribute("classe",sc);
		} catch (Exception e) {
			//TODO erreur 404
		}
		return "administrator/assignStudent";
	}
	
	@GetMapping("/assignStudent/{iduser}/delete/{idclass}")
	public String assignStudentClassDelete(@PathVariable("iduser") Long iduser,@PathVariable("idclass") Long idclass, Model model) {
		try {
			User u=userDao.findById(iduser);
			StudentClass sc=scDao.findById(idclass);
			sc.getStudents().remove(idclass);
			u.setStudentClass(null);
			userDao.save(u);
		} catch (Exception e) {
			//TODO erreur 404
		}
		return "redirect:/administrator/assignStudent/"+idclass;
	}
	
	@GetMapping("/assignStudent/{iduser}/add/{idclass}")
	public String assignStudentClassAdd(@PathVariable("iduser") Long iduser,@PathVariable("idclass") Long idclass, Model model) {
		try {
			StudentClass sc=scDao.findById(idclass);
			User u=userDao.findById(iduser);
			u.setStudentClass(sc);
			sc.getStudents().add(u);
			userDao.save(u);
		} catch (Exception e) {
			//TODO erreur 404
			e.printStackTrace();
		}
		return "redirect:/administrator/assignStudent/"+idclass;
	}
	
	@GetMapping("/user")
	public String displayUserList(@RequestParam("page") int page, @RequestParam("max") int max,Model model) {
		max=checkPageMax(max);
		int pagemax= ((userDao.count()%max>0?1:0)+(int)userDao.count()/max);
		page=checkPage(page,pagemax);
		model.addAttribute("users", userDao.findAll((page-1)*max, max));
		model.addAttribute("maxpage",pagemax);
		model.addAttribute("countadmin",userDao.countByType(UserType.ADMINISTRATOR));
		return "administrator/userList";
	}

	@GetMapping("/user/{id}/delete")
	public String deleteUser(@PathVariable("id") Long id,Model model) {
		try {
			userDao.delete(id);
		} catch (Exception e) {
			//TODO erreur 404
			e.printStackTrace();
		}
		return "redirect:/administrator/user?page=1&max=20";
	}
	
	@GetMapping("/user/{id}/update")
	public String displayUpdateUser(@PathVariable("id") Long id,Model model) {
		try {
			User user=userDao.findById(id);
			UserForm form=new UserForm(user);
			model.addAttribute("user-form", form);
			model.addAttribute("lstTypeUser",UserType.values());
		} catch (Exception e) {
			//TODO erreur 404
		}
		return "administrator/profilUser";
	}
	
	@PostMapping("/user")
	public String updateUser(@Valid @ModelAttribute("user-form") UserForm form,BindingResult result, Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			model.addAttribute("errors",result);
			model.addAttribute("user-form",form);
			return "administrator/profilUser";
		}
		else{
			User u=userDao.findById(form.getId());
			CtrlProfilUser ctrl=new CtrlProfilUser(form,u,userDao);
			ctrl.ctrlBithDate(form.getBirthdate());
			ctrl.ctrlEmail(form.getEmail());
			ctrl.ctrlCheckStillOneAdmin(form.getType());
			if(ctrl.isReinitPassword(form.getPassword())){
				ctrl.ctrlReInitPassword(form.getPassword(), form.getConfirmPassword());
			}
			if(ctrl.isError()) {
				model.addAttribute("message", ctrl.getMsg());
				model.addAttribute("user-form", form);
				return "administrator/profilUser";
			}
			u.setFirstName(form.getFirstName());
			u.setLastName(form.getLastName());
			u.setType(form.getType());
			userDao.save(u);
			if(((User)request.getSession().getAttribute("user")).getId()==u.getId())
				request.getSession().setAttribute("user", u);
			model.addAttribute("user",u);
			return "redirect:/administrator/user?page=1&max=20";	
		}
	}
	
	@GetMapping("/add_students")
	public String AddStudent() {
		return "administrator/addStudents";
	}
	
	@PostMapping("/add_students")
	public String AddStudent(@RequestParam String listStudent, Model model) {
		List<User> usersOK= new ArrayList<>();
		List<User> usersNoOK= new ArrayList<>();
		String[] ligne= listStudent.split("\n");
		for (String string : ligne) {
			String[] tabUser= string.split(";");
			CtrlUser ctrl= new CtrlUser(tabUser[0], tabUser[1], tabUser[2], tabUser[3], userDao);
			ctrl.ctrlStClass(tabUser[4], scDao);
			ctrl.ctrlType(tabUser[5]);
			
			User newUser=ctrl.getUser();
			if(!ctrl.isError()) {
				//ajouter token + envoie mail
				newUser.setToken(Token.getToken());		
				userDao.save(newUser);
				
//				try {
//					StringBuilder sb= new StringBuilder();
//					sb.append(newUser.getToken());
//					EmailTools.sendEmail(newUser.getEmail(), "inscription", sb.toString());
//				} catch (Exception e) {
//					// TODO logger l'erreur
//					e.printStackTrace();
//				}
				
				usersOK.add(newUser);
			} else {
				usersNoOK.add(newUser);
			}
		}
		model.addAttribute("usersOK", usersOK);
		model.addAttribute("usersNoOK", usersNoOK);
		return "administrator/addStudents";
	}
	
	@GetMapping("/configuresmtp")
	public String viewConfigureSmtp(Model model) {
		EmailTools email=EmailTools.getInstance();
		EmailForm form=new EmailForm();
		form.setSmtpServer(email.getSmtpServer());
		form.setEmailSender(email.getEmailSender());
		form.setPort(email.getPort());
		form.setEmailUser(email.getUser());
		form.setEmailPassword(email.getPassword());
		model.addAttribute("email-form", form);
		return "administrator/configureSmtp";
	}
	
	@PostMapping("/configuresmtp")
	public String configureSmtp(@Valid @ModelAttribute("email-form") EmailForm form,BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("errors",result);
			return "administrator/configureSmtp";
		}
		EmailTools email=EmailTools.getInstance();
		email.setEmailSender(form.getEmailSender());
		email.setSmtpServer(form.getSmtpServer());
		email.setPort(form.getPort());
		email.setPassword(form.getEmailPassword());
		email.setUser(form.getEmailUser());
		email.saveProperty();
		return "administrator/configureSmtp";
	}
	
	private int checkPageMax(int max) {
		if(max==0)
			max = 20;
		if(max>100)
			max=100;
		return max;
	}
	
	private int checkPage(int page,int maxPage) {
		if (page==0)
			page = 1;
		if(page>maxPage)
			page=maxPage-1;
		return page;
	}
}

