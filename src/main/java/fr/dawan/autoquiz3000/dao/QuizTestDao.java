package fr.dawan.autoquiz3000.dao;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<QuizTest> findByStudent(long idStudent) {
		List<QuizTest> lq = (List<QuizTest>) hibernateTemplate.find("FROM QuizTest qt WHERE qt.user.id=?", idStudent);
		return lq;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public QuizTest findByIdQuizAndIdUser(long idQuiz, long idStudent) {
		QuizTest result= null;
		List<QuizTest> lq = (List<QuizTest>) hibernateTemplate.find("FROM QuizTest qt WHERE qt.user.id=? AND qt.quiz.id= ?", idStudent, idQuiz);
		if(lq != null && lq.size() > 0) {
			result= lq.get(0);
		}
		return result;
	}
	
}
