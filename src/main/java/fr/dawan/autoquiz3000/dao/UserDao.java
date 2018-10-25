package fr.dawan.autoquiz3000.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.autoquiz3000.beans.User;

public class UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void save(User user) {
		hibernateTemplate.save(user);
	}
	
	@Transactional(readOnly=true)
	public User findByEmailAndPassword(String email, String password){
		return (User) hibernateTemplate.find("FROM User u WHERE u.password=? AND u.email= ?", password, email);
	}
	
	@Transactional(readOnly=true)
	public User findByToken(String token) {
		return (User) hibernateTemplate.find("FROM User u WHERE u.token=?", token);
	}
}
