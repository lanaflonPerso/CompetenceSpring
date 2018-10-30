package fr.dawan.autoquiz3000.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "quiz_todo")
@PrimaryKeyJoinColumn(name="id")
public class QuizToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long idUser;
	private long idQuiz;
	
	public QuizToDo() { }
	public QuizToDo(long idUser, long idQuiz) {
		this.idUser= idUser;
		this.idQuiz= idQuiz;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdQuiz() {
		return idQuiz;
	}
	public void setIdQuiz(long idQuiz) {
		this.idQuiz = idQuiz;
	}
	
}
