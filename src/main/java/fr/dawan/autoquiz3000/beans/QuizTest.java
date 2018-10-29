package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "quiz_test")
public class QuizTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;
	
	@OneToMany
	@JoinColumn(name = "question_id")
	private List<QuizQuestion> QuizQuestions= new ArrayList<>();
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "QuizTest_id", referencedColumnName="id")
	private List<StudentResponse> stResponse= new ArrayList<>();

	private int score;
	private int errorResponse;
	private int correctResponse;

	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Version
	private int version;
	
	//**************************************SETTERS / GETTERS PERSO************************************
	public void addQuizQuestion(QuizQuestion quizQuestion) {
		this.QuizQuestions.add(quizQuestion);
	}
	public void addStResponse(StudentResponse stResponse) {
		this.stResponse.add(stResponse);
	}
	
	public void addCorrectResponse() {
		this.correctResponse++;
	}
	
	public void addErrorResponse() {
		this.errorResponse++;
	}
	
	//**************************************SETTERS / GETTERS******************************************
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(int errorResponse) {
		this.errorResponse = errorResponse;
	}
	public int getCorrectResponse() {
		return correctResponse;
	}
	public void setCorrectResponse(int correctResponse) {
		this.correctResponse = correctResponse;
	}
	public List<StudentResponse> getStResponse() {
		return stResponse;
	}
	public void setStResponse(List<StudentResponse> stResponse) {
		this.stResponse = stResponse;
	}
	public List<QuizQuestion> getQuizQuestions() {
		return QuizQuestions;
	}
	public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
		QuizQuestions = quizQuestions;
	}
}
