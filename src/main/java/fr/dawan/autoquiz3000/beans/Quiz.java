package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity(name = "Quiz")
@Table(name = "quiz")
@PrimaryKeyJoinColumn(name="id")
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
	@JoinColumn(name = "quiz_id", referencedColumnName="id")
	private Set<QuizQuestion> quizQuestions= new LinkedHashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<StudentClass> stClasses= new ArrayList<>();
	
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
	public Set<QuizQuestion> getQuizQuestions() {
		return quizQuestions;
	}
	public void setQuizQuestions(Set<QuizQuestion> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}
	
	//**************************************SETTERS / GETTERS PERSO************************************
	public void setQuizQuestion(QuizQuestion quizQuestion) {
		this.quizQuestions.add(quizQuestion);
	}
	
	public int countQuestion() {
		return this.quizQuestions.size();
	}
	
	//**************************************OVERRIDE******************************************
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + scoreToAcquireSkill;
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + ((stClasses == null) ? 0 : stClasses.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Quiz other = (Quiz) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quizQuestions == null) {
			if (other.quizQuestions != null)
				return false;
		} else if (!quizQuestions.equals(other.quizQuestions))
			return false;
		if (scoreToAcquireSkill != other.scoreToAcquireSkill)
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		if (stClasses == null) {
			if (other.stClasses != null)
				return false;
		} else if (!stClasses.equals(other.stClasses))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name + ", skill=" + skill + ", scoreToAcquireSkill=" + scoreToAcquireSkill
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", quizQuestions=" + quizQuestions
				+ ", stClasses=" + stClasses + ", version=" + version + "]";
	}
}
