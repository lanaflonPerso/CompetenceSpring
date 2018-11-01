package fr.dawan.autoquiz3000.ctrl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.dao.UserDao;

public class CtrlUser extends Ctrl {
	
	private UserDao uDao;

	private final LocalDate NOW= LocalDate.now(); 
	private final Pattern VALID_BITHDAY_REGEX= Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", Pattern.CASE_INSENSITIVE);
	private final Pattern VALID_EMAIL_ADDRESS_REGEX= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private final int LENGTH_NAME_MIN= 2;
	private final int LENGTH_NAME_MAX= 100;

	private String msgConnection;
	private String msgFirstname;
	private String msgLastname;
	private String msgEmail;
	private String msgBirthDate;
	private String msgPassword;
	private String msgToken;
	private User user;
	
	public CtrlUser(UserDao uDao) {
		this.uDao= uDao;
		user= new User();
	}
	
	public CtrlUser(String firstname, String lastname, String email, String birthDate, UserDao uDao) {
		this.uDao= uDao;
		user= new User();
		ctrlFirstname(firstname);
		user.setFirstName(firstname);
		ctrlLastname(lastname);
		user.setLastName(lastname);
		ctrlEmail(email);
		user.setEmail(email);
		Date valdBirthdate= ctrlBirthdate(birthDate);
		user.setBirthdate(valdBirthdate);
	}
	
	public void ctrlTokenAndPassword(String token, String password, String confirm) {
		ctrlToken(token);
		user.setToken(token);
		ctrlPassword(password);
		ctrlPasswordEqual(password, confirm);
		user.setPassword(MySQLPassword(password));
	}
	
	public void ctrlEmailAndPassword(String email, String password) {
		User user= uDao.findByEmailAndPassword(email, MySQLPassword(password));
		if (user == null) {
			this.user= new User();
			this.user.setEmail(email);
			msgConnection= "les identifiants ne son pas correct!";
			error= true;
		} else {
			this.user= user;
		}
	}
	
	public void ctrlPassword(String password) {
		if(password.length() < 6) {
			msgPassword= "le mot de passe doit comprendre 6 caractéres minimum";
			error= true;
		}
	}
	
	public void ctrlPasswordEqual(String password, String confirm) {
		if (!password.equals(confirm)) {
			msgPassword= "Erreur dans la confirmation du mot de passe";
			error= true;
		}
	}
	

	public void ctrlFirstname(String firstname) {
		if (firstname.length() < LENGTH_NAME_MIN || firstname.length() > LENGTH_NAME_MAX) {
			msgFirstname= "le prénom doit comprendre entre "+ LENGTH_NAME_MIN +" et "+ LENGTH_NAME_MAX +" caractères";
			this.error= true;
		}
	}
	
	public void ctrlLastname(String lastname) {
		if (lastname.length() < LENGTH_NAME_MIN || lastname.length() > LENGTH_NAME_MAX) {
			msgLastname= "le nom doit comprendre entre "+ LENGTH_NAME_MIN +" et "+ LENGTH_NAME_MAX +" caractères";
			this.error= true;
		}
	}
	
	public void ctrlEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(!matcher.find()) {
        	msgEmail= "l'adresse email n'ai pas valide";
        	this.error= true;
        }
	}
	
	public Date ctrlBirthdate(String birthdate) {
		Date result= null;
		Matcher matcher = VALID_BITHDAY_REGEX.matcher(birthdate);
		
		if(!matcher.find()) {
			msgBirthDate= "date de naissance invalide";
			error= true;
		}else {
			String[] tab= birthdate.split("-");
			int day= Integer.valueOf(tab[2]);
			int month= Integer.valueOf(tab[1]);
			int year= Integer.valueOf(tab[0]);
			if(year > NOW.getYear()-2 || year < NOW.getYear()-62) {
				msgBirthDate= "l'âge n'est pas valide";
				error= true;
			}else {
				LocalDate localDate= LocalDate.of(year, month, day);
				result= Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}
		}		
		return result;
	}
	
	public void ctrlToken(String token) {
		User user= uDao.findByToken(token);
		if (user == null) {
			msgToken= "le token ne correspont a aucun compte";
			error= true;
		} else {
			this.user= user;
		}
	}
	
	//********************Getters*****************************
	public String getMsgFirstname() {
		return msgFirstname;
	}
	public String getMsgLastname() {
		return msgLastname;
	}
	public String getMsgEmail() {
		return msgEmail;
	}
	public String getMsgBirthDate() {
		return msgBirthDate;
	}
	public User getUser() {
		return user;
	}
	public String getMsgPassword() {
		return msgPassword;
	}
	public String getMsgToken() {
		return msgToken;
	}
	public String getMsgConnection() {
		return msgConnection;
	}
	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@Override
	public String toString() {
		return "CtrlStudent [msgFirstname=" + msgFirstname + ", msgLastname=" + msgLastname + ", msgEmail=" + msgEmail
				+ ", msgBirthDate=" + msgBirthDate + "]";
	}
}
