package fr.dawan.autoquiz3000.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.QuizToDo;

public class QuizToDoDao {
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(QuizToDo quiztoDo) {
		hibernateTemplate.saveOrUpdate(quiztoDo);
	}
	
	@Transactional
	public int findNbQuizByStudent(long idUser) {
		System.out.println("====================hibernateTemplate= "+hibernateTemplate);
		@SuppressWarnings("unchecked")
		List<Long> quizIds= (List<Long>) hibernateTemplate.find("FROM QuizToDo qtd WHERE qtd.idUser= ?", idUser);
		return quizIds.size();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Quiz> findByStudent(long idUser, QuizDao qDao) {
		List<Quiz> quizs= new ArrayList<>();
		List<QuizToDo> quizIds= (List<QuizToDo>) hibernateTemplate.find("FROM QuizToDo qtd WHERE qtd.idUser= ?", idUser);
		System.out.println("================================ quizs= "+quizIds);
		for (QuizToDo qtd: quizIds) {
			System.out.println("==================================== qtd.getIdQuiz()= "+qtd.getIdQuiz());
			quizs.add(qDao.findById(qtd.getIdQuiz()));
		}
		return quizs;		
	}
}
