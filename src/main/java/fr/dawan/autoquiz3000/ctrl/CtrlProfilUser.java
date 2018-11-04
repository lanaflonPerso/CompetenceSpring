package fr.dawan.autoquiz3000.ctrl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;
import fr.dawan.autoquiz3000.dao.UserDao;
import fr.dawan.autoquiz3000.formbeans.UserForm;

public class CtrlProfilUser extends Ctrl{
	private String msg;
	private UserDao uDao;
	private UserForm form;
	private User user;
	
	public CtrlProfilUser (UserForm form,User user,UserDao uDao)
	{
		this.form=form;
		this.user=user;
		this.uDao=uDao;
	}
	
	public void ctrlBithDate(Date birthdate) {
		if(birthdate.before(Date.from(LocalDate.now().minusYears(62).atStartOfDay(ZoneId.systemDefault()).toInstant())))
		{
			msg="La date de naissance n'est pas valide";
			error=true;
		}
		else {
			user.setBirthdate(birthdate);
		}
	}
	
	public void ctrlEmail(String email) {
		User tstExist=uDao.findByEmail(email);
		if(tstExist!=null && !tstExist.getId().equals(user.getId())) {
			error=true;
			msg="L'email existe déjà";
		}
		else {
			user.setEmail(email);
		}
	}
	
	public boolean isReinitPassword(String password) {
		return password.length()>0;
	}
	
	public void ctrlReInitPassword(String password,String confimPassword) {
		if(form.getPassword().length()<6) {
			msg="Le mot de passe doit contenir au moins 6 caractères";
			error=true;
		}else if(!password.equals(confimPassword)) {
					msg="Le mot de passe est diférent de la confirmation";
					error=true;
		}else {
			user.setPassword(Ctrl.MySQLPassword(form.getPassword()));

		}
	}
	
	public void ctrlCheckStillOneAdmin(UserType type) {
		if(type!=UserType.ADMINISTRATOR && uDao.countByType(UserType.ADMINISTRATOR)<=1){
			msg="Il doit rester un administrateur au minimum";
			error=true;
		}	
	}

	public String getMsg() {
		return msg;
	}
}
