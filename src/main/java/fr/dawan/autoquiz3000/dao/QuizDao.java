package fr.dawan.autoquiz3000.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.QuizQuestion;
import fr.dawan.autoquiz3000.beans.QuizResponse;
import fr.dawan.autoquiz3000.beans.QuizTest;
import fr.dawan.autoquiz3000.beans.StudentClass;

public class QuizDao {
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(Quiz quiz) {
		hibernateTemplate.saveOrUpdate(quiz);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Quiz> findAll(){
		return (List<Quiz>) hibernateTemplate.find("From Quiz");
	}
	
	@Transactional(readOnly=true)
	public Quiz findById(long id){
		return hibernateTemplate.get(Quiz.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public QuizQuestion findQuestion(long quizId, int orderNum){
		List<QuizQuestion> lq = (List<QuizQuestion>)hibernateTemplate.find("FROM QuizQuestion qst WHERE qst.quiz.id=? AND qst.orderNum=?", quizId, orderNum);
		if(lq!=null && lq.size()>0)
			return lq.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<QuizResponse> findReponses(long questionId){
		return (List<QuizResponse>)hibernateTemplate.find("FROM QuizResponse rep WHERE rep.question.id=?", questionId);
	}
	
	@Transactional(readOnly=true)
	public long nbQuestions(long quizId) {
		return (Long)hibernateTemplate.find("SELECT COUNT(qst.id) FROM QuizQuestion qst WHERE qst.quiz.id=?",quizId).get(0);
	}
	
	@Transactional(readOnly=true)
	public QuizResponse findResponseById(long id){
		return hibernateTemplate.get(QuizResponse.class, id);
	}
	
	@Transactional
	public void saveQuizTest(QuizTest quizTest) {
		hibernateTemplate.saveOrUpdate(quizTest);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Quiz> findbyStudentClass(StudentClass sc){
		return (List<Quiz>)hibernateTemplate.find("SELECT DISTINCT q FROM Quiz q INNER JOIN q.stClasses st WHERE st=?",sc);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Quiz> findbyNameContaining(String name){
		return (List<Quiz>) hibernateTemplate.find("FROM Quiz q WHERE q.name LIKE '%?%' AND q.endDate <  NOW()  ", name);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Quiz findbyName(String name){
		Quiz result= null;
		List<Quiz> lq= (List<Quiz>) hibernateTemplate.find("FROM Quiz q WHERE q.name= ?", name);
		if(lq != null && lq.size() > 0 ) {
			result= lq.get(0);
		}
		return result;
	}
}
