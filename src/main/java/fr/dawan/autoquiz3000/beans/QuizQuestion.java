package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class QuizQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	@ManyToOne(cascade = CascadeType.ALL)
	private Quiz quiz;
	@OneToMany(cascade=CascadeType.ALL)
	private List<QuizResponse> quizResponses= new ArrayList<>();
	
	@Version
	private int version;
	//**************************************SETTERS / GETTERS******************************************
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<QuizResponse> getQuizResponses() {
		return quizResponses;
	}
	public void setQuizResponse(List<QuizResponse> quizResponses) {
		this.quizResponses = quizResponses;
	}
	
	//**************************************SETTERS / GETTERS PERSO************************************
	public void setQuizResponse(QuizResponse quizResponse) {
		this.quizResponses.add(quizResponse);
	}
}
