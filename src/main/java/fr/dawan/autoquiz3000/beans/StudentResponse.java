package fr.dawan.autoquiz3000.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student_response")
@PrimaryKeyJoinColumn(name="id")
public class StudentResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long idResponse;
	private boolean answered;
	
	//**************************************SETTERS / GETTERS******************************************
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getIdResponse() {
		return idResponse;
	}
	public void setIdResponse(long idResponse) {
		this.idResponse = idResponse;
	}
	public boolean isAnswered() {
		return answered;
	}
	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	@Override
	public String toString() {
		return "StudentResponse [id=" + id + ", idResponse=" + idResponse + ", answered=" + answered + "]";
	}
}
