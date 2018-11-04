package fr.dawan.autoquiz3000.formbeans;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;

public class UserForm {
		private Long id;

		@NotEmpty(message = "Le prénom ne peut pas être vide")
		@Size(min=2,max = 100,message = "Le prénom doit être compris entre 2 et 100 caractères") 
		private String firstName;
		
		@NotEmpty(message = "Le nom ne peut pas être vide")
		@Size(min=2,max = 50,message = "Le nom doit être compris entre 2 et 100 caractères") 
		private String lastName;
		
		@NotEmpty(message = "L' email ne peut pas être vide")
		@Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="L' email n'ai pas valide")
		private String email;
		
		@NotNull(message = "La date de naissance ne peut pas être vide")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date birthdate;
		
		private UserType type;

		private String password;

		private String confirmPassword;

		public UserForm(User user) {
			this.id = user.getId();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.email = user.getEmail();
			this.birthdate = user.getBirthdate();
			this.type=user.getType();
		}

		public UserForm() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getBirthdate() {
			return birthdate;
		}

		public void setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
		}
		
		
		public UserType getType() {
			return type;
		}

		public void setType(UserType type) {
			this.type = type;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
}
