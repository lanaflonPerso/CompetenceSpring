package fr.dawan.autoquiz3000.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.QuizTest;

public class QuizTestDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(QuizTest quiz) {
		hibernateTemplate.save(quiz);
	}
}
