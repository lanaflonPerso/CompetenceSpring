package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;

import javax.persistence.ManyToOne;
import javax.persistence.Version;

public class QuizQuestion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8900964905632257532L;
	private Long id;
	private String text;
	@ManyToOne
	private Quiz quiz;
	@Version
	private int version;
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
	
	

}
