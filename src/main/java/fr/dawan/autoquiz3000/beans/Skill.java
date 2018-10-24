package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class Skill implements Serializable {
	private static final long serialVersionUID = 4978044425830426432L;

	private Long id;
	private String name;
	private SkillStatus status;
	@ManyToOne
	private User user;

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

	public SkillStatus getStatus() {
		return status;
	}

	public void setStatus(SkillStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
