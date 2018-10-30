package fr.dawan.autoquiz3000.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.QuizQuestion;

public class QuizQuestionDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(QuizQuestion question) {
		hibernateTemplate.saveOrUpdate(question);
	}
}
