package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class Quiz implements Serializable {

	private static final long serialVersionUID = -2094818590398320844L;
	private Long id;
	private String name;
	@ManyToOne
	private Skill skill;

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

}
