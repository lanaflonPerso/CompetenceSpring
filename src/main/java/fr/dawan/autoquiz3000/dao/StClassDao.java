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
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<StudentClass> findAll(){
		return (List<StudentClass>)hibernateTemplate.find("FROM StudentClass");
	}
	
	@Transactional(readOnly=true)
	public StudentClass findById(long id){
		return hibernateTemplate.get(StudentClass.class, id);
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
