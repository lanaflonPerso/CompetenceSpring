package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class QuizResponse implements Serializable {

	private static final long serialVersionUID = 6540035082580962340L;
	private Long id;
	private String text;
	private boolean correct;

	@ManyToOne
	private QuizQuestion question;

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

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public QuizQuestion getQuestion() {
		return question;
	}

	public void setQuestion(QuizQuestion question) {
		this.question = question;
	}

}
