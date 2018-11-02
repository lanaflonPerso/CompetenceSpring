<<<<<<< HEAD
package fr.dawan.autoquiz3000;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import fr.dawan.autoquiz3000.ctrl.Ctrl;
import fr.dawan.autoquiz3000.ctrl.CtrlUser;
import fr.dawan.autoquiz3000.dao.StClassDao;
import fr.dawan.autoquiz3000.dao.UserDao;
import fr.dawan.autoquiz3000.formbeans.StudentClassForm;
import fr.dawan.autoquiz3000.formbeans.UserForm;
import fr.dawan.autoquiz3000.helper.Token;

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
			e.printStackTrace();
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
		//TODO ajouter v�rification id
		StudentClass sc=scDao.findById(id);
		if(sc.getStudents().size()==0)
			scDao.delete(id);
		return "redirect:/administrator/studentclass?page=1&max=20";
	}
	
	@GetMapping("/studentclass/{id}/update")
	public String updateStudentClass(@PathVariable("id") Long id, Model model) {
		//TODO ajouter v�rification id
		StudentClass sc=scDao.findById(id);
		StudentClassForm scf = new StudentClassForm();
		scf.setId(sc.getId());
		scf.setName(sc.getName());
		scf.setEndDate(sc.getEndDate());
		scf.setStartDate(sc.getStartDate());
		model.addAttribute("actionButton","Modifier");
		model.addAttribute("studentclass-form", scf);
		model.addAttribute("stclasses", scDao.findAll());
		return "administrator/studentclass";
	}
	
	@GetMapping("/assignStudent/{id}")
	public String assignStudentClass(@PathVariable("id") Long id, Model model) {
		//TODO ajouter v�rification id
		StudentClass sc=scDao.findById(id);
		model.addAttribute("assignedusers",userDao.findByAssignedStudentClass(sc));
		model.addAttribute("unasssignedusers",userDao.findByUnAssignedStudentClass());
		model.addAttribute("classe",sc);
		return "administrator/assignStudent";
	}
	
	@GetMapping("/assignStudent/{iduser}/delete/{idclass}")
	public String assignStudentClassDelete(@PathVariable("iduser") Long iduser,@PathVariable("idclass") Long idclass, Model model) {
		User u=userDao.findById(iduser);
		StudentClass sc=scDao.findById(idclass);
		sc.getStudents().remove(u);
		u.setStudentClass(null);
		userDao.save(u);
		return "redirect:/administrator/assignStudent/"+idclass;
	}
	
	@GetMapping("/assignStudent/{iduser}/add/{idclass}")
	public String assignStudentClassAdd(@PathVariable("iduser") Long iduser,@PathVariable("idclass") Long idclass, Model model) {
		StudentClass sc=scDao.findById(idclass);
		User u=userDao.findById(iduser);
		u.setStudentClass(sc);
		sc.getStudents().add(u);
		userDao.save(u);
		return "redirect:/administrator/assignStudent/"+idclass;
	}
	
	@GetMapping("/user")
	public String displayUserList(@RequestParam("page") int page, @RequestParam("max") int max,Model model) {
		max=checkPageMax(max);
		int pagemax= ((userDao.count()%max>0?1:0)+(int)userDao.count()/max);
		page=checkPage(page,pagemax);
		model.addAttribute("users", userDao.findAll((page-1)*max, max));
		model.addAttribute("maxpage",pagemax);
		return "administrator/userList";
	}

	@GetMapping("/user/{id}/delete")
	public String deleteUser(@PathVariable("id") Long id,Model model) {
		userDao.delete(id);
		return "redirect:/administrator/user?page=1&max=20";
	}
	
	@GetMapping("/add_students")
	public String AddStudent() {
		return "administrator/addStudents";
	}
	
	@GetMapping("/auser/{id}/update")
	public String showUpdateUser(@PathVariable("id") Long id,Model model) {
		//TODO ajouter v�rification id
		User user=userDao.findById(id);
		UserForm form=new UserForm(user);
		model.addAttribute("user-form", form);
		model.addAttribute("lstTypeUser",UserType.values());
		return "administrator/profilUser";
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
	
	@PostMapping("/user")
	public String updateUser(@Valid @ModelAttribute("user-form") UserForm form,BindingResult result, Model model) {
		String msg=null;
		if(result.hasErrors()) {
			model.addAttribute("errors",result);
		}
		else{
			// vérififer l'id
			User u=userDao.findById(form.getId());
			if(form.getBirthdate().before(Date.from(LocalDate.now().minusYears(62).atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
				msg="La date de naissance n'est pas valide";
				
			}
			else {
				User tstExist=userDao.findByEmail(form.getEmail());
				if(tstExist==null || !tstExist.getId().equals(u.getId())) {
					msg="L'email existe déjà";
				}
				else {
					u.setFirstName(form.getFirstName());
					u.setLastName(form.getLastName());
					u.setBirthdate(form.getBirthdate());
					u.setEmail(form.getEmail());
					u.setType(form.getType());
					if(form.getPassword().length()>0)
					{
						if(form.getPassword().length()<6) {
							msg="Le mot de passe doit contenir au moins 6 caractères";
							model.addAttribute("message", msg);
							model.addAttribute("user-form", form);
							return "administrator/profilUser";
						}
						else if(!form.getConfirmPassword().equals(form.getPassword())) {
							msg="Le mot de passe est diférent de la confirmation";
							model.addAttribute("message", msg);
							model.addAttribute("user-form", form);
							return "administrator/profilUser";
						}
						u.setPassword(Ctrl.MySQLPassword(form.getPassword()));
					}
					userDao.save(u);	
					return "redirect:/administrator/user?page=1&max=20";
				}
			}
		}
		model.addAttribute("message", msg);
		model.addAttribute("user-form", form);
		return "administrator/profilUser";
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

