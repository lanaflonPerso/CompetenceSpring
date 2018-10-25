package fr.dawan.autoquiz3000.ctrl;

import org.springframework.beans.factory.annotation.Autowired;

import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.dao.StClassDao;

public class CtrlStClass extends Ctrl {
	
	@Autowired
	private StClassDao cDao;
	
	private StudentClass stClass;

	public void setClasse(String name) {
		StudentClass sc= cDao.findByName(name);
		if(sc == null) {
			error= true;
		}
	}

	public StClassDao getcDao() {
		return cDao;
	}
	public void setcDao(StClassDao cDao) {
		this.cDao = cDao;
	}
	public StudentClass getStClass() {
		return stClass;
	}
	public void setStClass(StudentClass stClass) {
		this.stClass = stClass;
	}
}
