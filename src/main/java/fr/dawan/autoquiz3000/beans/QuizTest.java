package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;

public class QuizTest implements Serializable {

	private static final long serialVersionUID = -574979381726301067L;

	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Quiz quiz;

	private int score;

	private Date creationDate;

	private List<QuizResponse> responses;

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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<QuizResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<QuizResponse> responses) {
		this.responses = responses;
	}

}
