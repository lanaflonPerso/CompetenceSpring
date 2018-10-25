package fr.dawan.springmvc.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.springmvc.beans.Product;
import fr.dawan.springmvc.beans.Training;
import fr.dawan.springmvc.beans.TrainingSession;

public class TrainingDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Training> findAllTrainings(){
		return (List<Training>)hibernateTemplate.find("SELECT DISTINCT(tr) FROM Training tr JOIN FETCH tr.sessions ts");
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Training> findAllBasicInfosTrainings(){
		return (List<Training>)hibernateTemplate.find("SELECT new Training(tr.id, tr.title, tr.price) FROM Training tr");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Training findBasicInfosTrainingById(int trainingId){
		return ((List<Training>)hibernateTemplate.find("SELECT new Training(tr.id, tr.title, tr.price) FROM Training tr WHERE tr.id=?",trainingId)).get(0);
	}
   	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Training findTrainingById(int trainingId){
		return ((List<Training>)hibernateTemplate.find("FROM Training tr  JOIN FETCH tr.sessions ts WHERE tr.id=?",trainingId)).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<TrainingSession> findSessionsByTrainingId(int trainingId){
		return (List<TrainingSession>)hibernateTemplate.find("FROM TrainingSession ts WHERE ts.training.id=?", trainingId);
	}
   	//Qcm (id, sujet, plusieurs questions)
	//Question(id, enonce, plusieurs réponses)
	//Reponse (id, texte, correcte)
	
	@Transactional(readOnly=true)
	public TrainingSession findSession(int trainingSessionId) {
		return hibernateTemplate.get(TrainingSession.class, trainingSessionId);
	}
	
	@Transactional
	public void removeTraining(int id) {
		hibernateTemplate.delete(hibernateTemplate.get(Training.class, id));
	}
	
	
	
	
	
	
	
}
