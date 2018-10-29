package fr.dawan.autoquiz3000.formbeans;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentClassForm {

    private Long id;

	@NotEmpty(message = "Le nom de la classe ne peut pas être vide")
	@Size(max = 50,message = "La nom de la classe ne peut pas être vide") 
	private String name;
	
	@NotNull(message = "La date de début ne peut pas être vide")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@NotNull(message = "La date de début ne peut pas être vide")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentClassForm() {
		super();
	}

	public StudentClassForm(String name,Date startDate, Date endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
