package fr.dawan.autoquiz3000.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.StudentClass;

public class StClassDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(StudentClass stClass) {
		hibernateTemplate.save(stClass);
	}
	
	@Transactional
	public void update(StudentClass stClass) {
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
			return !hibernateTemplate.find("FROM StudentClass sc where sc.name=? AND (sc.endDate>? AND sc.startDate<? )AND sc.id!=?",sct.getName(),sct.getStartDate(),sct.getEndDate(),sct.getId()).isEmpty();
		else
			return !hibernateTemplate.find("FROM StudentClass sc where sc.name=? AND (sc.endDate>? AND sc.startDate<? )",sct.getName(),sct.getStartDate(),sct.getEndDate()).isEmpty();
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
}
