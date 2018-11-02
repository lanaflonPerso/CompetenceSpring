package fr.dawan.autoquiz3000.ctrl;

import fr.dawan.autoquiz3000.beans.QuizQuestion;
import fr.dawan.autoquiz3000.beans.QuizResponse;
import fr.dawan.autoquiz3000.beans.QuizTest;
import fr.dawan.autoquiz3000.beans.StudentResponse;
import fr.dawan.autoquiz3000.dao.QuizDao;

public class CtrlQuizTest extends Ctrl {
	
	private QuizDao qDao;
	private QuizTest quizTest;

	public CtrlQuizTest(QuizDao qDao, QuizTest quizTest) {
		this.qDao= qDao;
		this.quizTest= quizTest;
	}
	
	public void ctrlResponse(String[] responseStudent, int orderNum, long quizId) {
		QuizQuestion currentQst = qDao.findQuestion(quizId, orderNum);
		quizTest.addQuizQuestion(currentQst);
		
		for (QuizResponse responseForm: currentQst.getQuizResponses()) {
			boolean oneResponse= false;
			StudentResponse stResponse= new StudentResponse();
			stResponse.setIdResponse(responseForm.getId());
			
			for (String  stringResponse: responseStudent) {
				System.out.println("============= numero de quiz= "+responseForm.getId());
				long idResponse= Long.valueOf(stringResponse);

				if(responseForm.getId() == idResponse) {
					if (!oneResponse) {
						stResponse.setAnswered(true);
						oneResponse= true;
					}
					if(!responseForm.isCorrect()) {
						error= true;
					}
				} else {
					if (!oneResponse) {
						stResponse.setAnswered(false);
					}
//					this.quizTest.addStResponse(stResponse);
					if (responseForm.isCorrect()) {
						error= true;
					}
				}
				this.quizTest.addStResponse(stResponse);
			}
		}
	}

	public void save() {
		if(!error) {
			System.out.println("============= il n'y a pas d'erreur! ");
			quizTest.addCorrectResponse();
		} else {
			System.out.println("============= il y a une d'erreur! ");
			quizTest.addErrorResponse();
		}
	}
}
