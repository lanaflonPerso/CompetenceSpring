package fr.dawan.autoquiz3000.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.QuizQuestion;
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
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<QuizTest> findByStudent(long idStudent) {
		List<QuizTest> lq = (List<QuizTest>)hibernateTemplate.find("FROM QuizTest qt WHERE qt.user.id=?", idStudent);
		return lq;
	}
}
