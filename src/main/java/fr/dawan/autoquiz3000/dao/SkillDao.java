package fr.dawan.autoquiz3000.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.Skill;
import fr.dawan.autoquiz3000.beans.User;

public class SkillDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(User user) {
		hibernateTemplate.save(user);
	}
	
	@Transactional(readOnly=true)
	public Skill findByName(String name){
		Skill result= null;
		@SuppressWarnings("unchecked")
		List<Skill> skills= (List<Skill>) hibernateTemplate.find("FROM Skill s WHERE s.name=?", name);
		if(skills != null && skills.size() > 0) {
			result= skills.get(0);
		}
		return result;
	}
}
