package fr.dawan.springmvc.beans;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="t_trainings")
@XmlRootElement(name="training")
@XmlAccessorType(XmlAccessType.FIELD)
public class Training {

	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	
	@Column(nullable=false, length=250, unique=true)
	@XmlElement
	private String title;
	
	public enum TrainingStatus {
		IN_PROGRESS, TO_VALIDATE, ONLINE
	}
	
	@Enumerated(EnumType.STRING)
	@XmlElement
	private TrainingStatus status;
	
	@Column(precision=2)
	@XmlElement
	private double price;
	
	@OneToMany(mappedBy="training", cascade=CascadeType.ALL)
	@XmlTransient
	private Set<TrainingSession> sessions;

	
	
	
	public Training(int id, String title, double price) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TrainingStatus getStatus() {
		return status;
	}

	public void setStatus(TrainingStatus status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<TrainingSession> getSessions() {
		return sessions;
	}

	public void setSessions(Set<TrainingSession> sessions) {
		this.sessions = sessions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Training other = (Training) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}
