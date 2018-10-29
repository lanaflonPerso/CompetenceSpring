package fr.dawan.autoquiz3000.formbeans;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class UserForm {
		private Long id;

		@NotEmpty(message = "Le prénom ne peut pas Ãªtre vide")
		@Size(max = 50,message = "Le prénom ne peut pas Ãªtre vide") 
		private String firstName;
		
		@NotEmpty(message = "Le prénom ne peut pas Ãªtre vide")
		@Size(max = 50,message = "Le prénom ne peut pas Ãªtre vide") 
		private String lastName;
		
		@NotEmpty(message = "Le prénom ne peut pas Ãªtre vide")
		@Size(max = 50,message = "Le prénom ne peut pas Ãªtre vide") 
		private String email;
		
		@NotNull(message = "La date de dÃ©but ne peut pas Ãªtre vide")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date birthdate;
	
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
			// TODO Auto-generated constructor stub
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
		
}
