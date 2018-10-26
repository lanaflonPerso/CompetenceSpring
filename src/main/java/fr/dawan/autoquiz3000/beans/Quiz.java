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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class Quiz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Skill skill;
	
	private int scoreToAcquireSkill;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<QuizQuestion> quizQuestions= new ArrayList<>();
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<StudentClass> stClasses;
	
	@Version
	private int version;

	//**************************************SETTERS / GETTERS******************************************
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
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public int getScoreToAcquireSkill() {
		return scoreToAcquireSkill;
	}
	public void setScoreToAcquireSkill(int scoreToAcquireSkill) {
		this.scoreToAcquireSkill = scoreToAcquireSkill;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	
	public List<StudentClass> getStClasses() {
		return stClasses;
	}
	public void setStClasses(List<StudentClass> stClasses) {
		this.stClasses = stClasses;
	}
	public List<QuizQuestion> getQuizQuestions() {
		return quizQuestions;
	}
	public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}
	
	//**************************************SETTERS / GETTERS PERSO************************************
	public void setQuizQuestion(QuizQuestion quizQuestion) {
		this.quizQuestions.add(quizQuestion);
	}
}
