package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

public class StudentClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7164089657595567541L;
	private Long id;
	private String name;
	private Date startDate;
	private Date endDate;
	@OneToMany
	List<User> students;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

}
