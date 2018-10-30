package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

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
import javax.persistence.Version;

@Entity(name = "QuizQuestion")
@Table(name = "quiz_question")
public class QuizQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	
	@ManyToOne
	private Quiz quiz;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "question_id", referencedColumnName="id")
	private Set<QuizResponse> quizResponses= new LinkedHashSet<>();
	
	private int orderNum;
	
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
	public Set<QuizResponse> getQuizResponses() {
		return quizResponses;
	}
	public void setQuizResponse(Set<QuizResponse> quizResponses) {
		this.quizResponses = quizResponses;
	}
	
	//**************************************SETTERS / GETTERS PERSO************************************
	public void setQuizResponse(QuizResponse quizResponse) {
		this.quizResponses.add(quizResponse);
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	//**************************************OVERRIDE******************************************
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + orderNum;
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		result = prime * result + ((quizResponses == null) ? 0 : quizResponses.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + version;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizQuestion other = (QuizQuestion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderNum != other.orderNum)
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		if (quizResponses == null) {
			if (other.quizResponses != null)
				return false;
		} else if (!quizResponses.equals(other.quizResponses))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (version != other.version)
			return false;
		return true;
	}	
}
