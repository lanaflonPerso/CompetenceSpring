package fr.dawan.autoquiz3000.ctrl;

import java.util.Date;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.Skill;
import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.dao.SkillDao;
import fr.dawan.autoquiz3000.dao.StClassDao;

public class CtrlQuiz extends Ctrl {
	
	private SkillDao sDao;
	private StClassDao cDao;
	
	private final int LENGTH_NAME_MIN= 2;
	private final int LENGTH_NAME_MAX= 100;

	private Quiz quiz;
	private String msgDateDebut;
	private String msgDateFin;
	private String msgName;
	private String msgCompetence;
	private String msgStClass;
	
	public CtrlQuiz(SkillDao sDao, StClassDao cDao) {
		this.sDao= sDao;
		this.cDao= cDao;
	}
	
	public void createQuiz(String name, String skill, String startDebut, String endDate, String stClassName) {
		quiz= new Quiz();
		ctrlName(name);
		quiz.setName(name);
		Date valDateDebut= ctrlDate(startDebut);
		Date valDateFin= ctrlDate(endDate);
		ctrlTwoDate(valDateDebut, valDateFin);
		quiz.setStartDate(valDateDebut);
		quiz.setEndDate(valDateFin);
		Skill objectSkill= ctrlSkill(skill);
		quiz.setSkill(objectSkill);
		StudentClass stClass= ctrlStClass(stClassName);
	//	quiz.setStClass(stClass);
	}
	
	private Skill ctrlSkill(String name) {
		System.out.println("sDao= "+sDao);
		Skill result= sDao.findByName(name);
		if (result == null) {
			result= new Skill();
			result.setName(name);
		}
		return result;
	}

	public void ctrlName(String name) {
		if (name.length() < LENGTH_NAME_MIN || name.length() > LENGTH_NAME_MAX) {
			msgName= "l'intitulé doit comprendre entre "+ LENGTH_NAME_MIN +" et "+ LENGTH_NAME_MAX;
			this.error= true;
		}
	}

	public Date ctrlDate(String date) {
		Date result= StringToDate( date);
		if(result == null) {
			error= true;	
		} 	
		return result;
	}

	public void ctrlTwoDate(Date begin, Date end) {
		Date transit= null;
		if (begin.compareTo(end) > 0) {
            transit= end;
            end= begin;
            begin= transit;            
        }
	}
	
	public StudentClass ctrlStClass(String id) {
		StudentClass sc= cDao.findById(Long.valueOf(id));
		if(sc == null) {
			sc= new StudentClass();
			error= true;
			msgStClass= "la classe n'existe pas!";
		}
		return sc;
	}

	//********************Getters / Setters******************
	public Quiz getQuiz() {
		return quiz;
	}
	public String getMsgDateDebut() {
		return msgDateDebut;
	}
	public String getMsgDateFin() {
		return msgDateFin;
	}
	public String getMsgName() {
		return msgName;
	}
	public String getMsgCompetence() {
		return msgCompetence;
	}
	public String getMsgStClass() {
		return msgStClass;
	}
	
	public void setsDao(SkillDao sDao) {
		this.sDao = sDao;
	}
	public void setcDao(StClassDao cDao) {
		this.cDao = cDao;
	}

	@Override
	public String toString() {
		return "CtrlQuiz [sDao=" + sDao + ", cDao=" + cDao + ", LENGTH_NAME_MIN=" + LENGTH_NAME_MIN
				+ ", LENGTH_NAME_MAX=" + LENGTH_NAME_MAX + ", quiz=" + quiz + ", msgDateDebut=" + msgDateDebut
				+ ", msgDateFin=" + msgDateFin + ", msgName=" + msgName + ", msgCompetence=" + msgCompetence
				+ ", msgStClass=" + msgStClass + ", isError()=" + isError() + "]";
	}
}
