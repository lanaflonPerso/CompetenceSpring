package fr.dawan.autoquiz3000.formbeans;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import fr.dawan.autoquiz3000.beans.UserType;

public class UserForm {
		private Long id;

		@NotEmpty(message = "Le pr�nom ne peut pas être vide")
		@Size(max = 50,message = "Le pr�nom ne peut pas être vide") 
		private String firstName;
		
		@NotEmpty(message = "Le nom ne peut pas être vide")
		@Size(max = 50,message = "Le nom ne peut pas être vide") 
		private String lastName;
		
		@NotEmpty(message = "Le email ne peut pas être vide")
		@Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="L' email n'ai pas valide")
		private String email;
		
		@NotNull(message = "La date de naissance ne peut pas être vide")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date birthdate;
		
		private UserType type;

		public UserForm(Long id, String firstName, String lastName, String email, Date birthdate) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.birthdate = birthdate;
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

		
}
