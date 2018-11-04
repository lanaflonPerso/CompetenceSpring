package fr.dawan.autoquiz3000.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;

public class UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(User user) {
		hibernateTemplate.saveOrUpdate(user);
	}
	
	@Transactional
	public void delete(Long id) {
		hibernateTemplate.delete(hibernateTemplate.get(User.class, id));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findAll(){
		return (List<User>)hibernateTemplate.find("FROM User");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findAll(int start, int max){
		return (List<User>) hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM User")
		.setFirstResult(start).setMaxResults(max).list();
	}
	
	@Transactional(readOnly=true)
	public User findById(long id){
		return hibernateTemplate.get(User.class, id);
	}
	
	@Transactional(readOnly=true)
	public long count() {
		return (Long)hibernateTemplate.find("SELECT COUNT(u.id) FROM User u").get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public User findByEmail(String email) {
		User result= null;
		List<User> users= (List<User>) hibernateTemplate.find("FROM User u WHERE u.email= ?", email);
		if(users != null && users.size() > 0) {
			result= users.get(0);
		}
		return result;
	}
	
	@Transactional(readOnly=true)
	public User findByEmailAndPassword(String email, String password){
		User result= null;
		@SuppressWarnings("unchecked")
		List<User> users= (List<User>) hibernateTemplate.find("FROM User u WHERE u.password=? AND u.email= ?", password, email);
		if(users != null && users.size() > 0) {
			result= users.get(0);
		}
		return result;
	}
	
	@Transactional(readOnly=true)
	public User findByToken(String token) {
		User result= null;
		System.out.println("token= "+token);
		System.out.println("hibernateTemplate= "+ hibernateTemplate);
		@SuppressWarnings("unchecked")
		List<User> users= (List<User>) hibernateTemplate.find("FROM User u WHERE u.token=?", token);
		if(users != null && users.size() > 0) {
			result= users.get(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Long countByType(UserType type){
		return (Long) hibernateTemplate.find("SELECT COUNT(u.id)FROM User u WHERE u.type=?",type).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findByAssignedStudentClass(StudentClass sc){
		return (List<User>) hibernateTemplate.find("FROM User u WHERE u.studentClass=?", sc);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findByUnAssignedStudentClass(){
		return (List<User>) hibernateTemplate.find("FROM User u WHERE u.studentClass=null");
	}
}
