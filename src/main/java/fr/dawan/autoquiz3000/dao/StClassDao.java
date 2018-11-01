package fr.dawan.autoquiz3000.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.tools.StatStudentClass;

public class StClassDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(StudentClass stClass) {
		hibernateTemplate.saveOrUpdate(stClass);
	}

	@Transactional
	public void delete(Long id) {
		hibernateTemplate.delete(hibernateTemplate.get(StudentClass.class, id));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<StudentClass> findAll(){
		return (List<StudentClass>)hibernateTemplate.find("FROM StudentClass");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<StudentClass> findAll(int start, int max){
		return (List<StudentClass>) hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM StudentClass")
		.setFirstResult(start).setMaxResults(max).list();
	}
	
	@Transactional(readOnly=true)
	public StudentClass findById(long id){
		return hibernateTemplate.get(StudentClass.class, id);
	}

	@Transactional(readOnly=true)
	public long count() {
		return (Long)hibernateTemplate.find("SELECT COUNT(c.id) FROM StudentClass c").get(0);
	}
	
	@Transactional(readOnly=true)
	public boolean isExist(StudentClass sct) {
		if(sct.getId()!=null)
			return !hibernateTemplate.find("FROM StudentClass sc WHERE sc.name=? AND (sc.endDate>? AND sc.startDate<? )AND sc.id!=?",sct.getName(),sct.getStartDate(),sct.getEndDate(),sct.getId()).isEmpty();
		else
			return !hibernateTemplate.find("FROM StudentClass sc WHERE sc.name=? AND (sc.endDate>? AND sc.startDate<? )",sct.getName(),sct.getStartDate(),sct.getEndDate()).isEmpty();
	}
	
	@Transactional(readOnly=true)
	public StudentClass findByName(String name) {
		StudentClass result= null;
		@SuppressWarnings("unchecked")
		List<StudentClass> scs= (List<StudentClass>) hibernateTemplate.find("FROM StudentClass sc WHERE sc.name=?", name);
		if(scs != null && scs.size() > 0) {
			result= scs.get(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<StatStudentClass> getStatistic(StudentClass sc){
		List<StatStudentClass> result=new ArrayList<>();
		List<Object[]> resultDb=(List<Object[]>) hibernateTemplate.find("SELECT q,COUNT(qt.id), AVG(qt.score),MIN(qt.score),MAX(qt.score) FROM QuizTest qt INNER JOIN qt.quiz q INNER JOIN q.stClasses stc where stc=? GROUP BY q",sc);
		for(Object[] obj: resultDb) { 
			long a=sc.getStudents().size()-((long)obj[1]);
			 StatStudentClass stat=new StatStudentClass();
			 stat.setQuiz((Quiz) obj[0]);
			 stat.setCountQuizDO(a);
			 stat.setAverangeScore((double) obj[2]);
			 stat.setMinScore((int) obj[3]);
			 stat.setMaxScore((int) obj[4]);
			 Long countSkilByQuiz= (Long) hibernateTemplate.find("SELECT COUNT(qt.id) FROM QuizTest qt INNER JOIN qt.quiz q INNER JOIN q.stClasses stc where stc=? AND q=? AND qt.score>q.scoreToAcquireSkill",sc,(Quiz) obj[0]).get(0);
			 stat.setCountSkill(countSkilByQuiz);
			 stat.setCountFailSkill((long)sc.getStudents().size()-a-countSkilByQuiz);
			 result.add(stat);
		}
		return result;
	}
	
}
