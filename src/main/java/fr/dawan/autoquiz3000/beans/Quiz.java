package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class Quiz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Skill skill;
	
	private int scoreToAcquireSkill;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@OneToMany(cascade=CascadeType.ALL)
	private List<QuizQuestion> quizQuestions;
	@ManyToOne
	private StudentClass stClass;
	
	@Version
	private int version;

	
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
	public StudentClass getStClass() {
		return stClass;
	}
	public void setStClass(StudentClass stClass) {
		this.stClass = stClass;
	}
	public List<QuizQuestion> getQuizQuestions() {
		return quizQuestions;
	}
	public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}
	
	// **********GETTER/SETTER PERSO**********
	public void setQuizQuestion(QuizQuestion quizQuestion) {
		this.quizQuestions.add(quizQuestion);
	}
}
