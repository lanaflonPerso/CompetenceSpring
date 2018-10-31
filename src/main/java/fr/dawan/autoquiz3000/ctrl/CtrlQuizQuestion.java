package fr.dawan.autoquiz3000.ctrl;

import javax.servlet.http.HttpServletRequest;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.QuizQuestion;
import fr.dawan.autoquiz3000.beans.QuizResponse;
import fr.dawan.autoquiz3000.dao.QuizQuestionDao;

public class CtrlQuizQuestion extends Ctrl {
	private final int LENGTH_TITLE_QUESTION_MIN= 10;
	private final int LENGTH_TITLE_QUESTION_MAX= 3000;
	
	private QuizQuestion question;
	private Quiz quiz;
	private int nbrChoix;
	private String msgTitleQuestion;
	private String msgQuestion;

	public CtrlQuizQuestion(HttpServletRequest request, Quiz quiz) {
		question= new QuizQuestion();
		this.quiz= quiz;
		ctrlTitleQuestion(request.getParameter("titleQuestion"));
		question.setText(request.getParameter("titleQuestion"));
		ctrlChoix(request);
		int nbQestion= quiz.countQuestion();
		question.setOrderNum(nbQestion+1);
		this.quiz.setQuizQuestion(question);
	}
	
	private void ctrlChoix(HttpServletRequest request) {
		boolean goodResponse= false;
		
		ctrlNbrChoix(request.getParameter("nbrChoix"));
		if(!error) {
			for (int i = 1; i < nbrChoix+1; i++) {
				QuizResponse res= new QuizResponse();
				res.setText(request.getParameter("choix"+i));
	
				if(request.getParameter("checkChoix"+i) != null) {
					res.setCorrect(true);
					goodResponse= true;
				} else {
					res.setCorrect(false);
				}
				System.out.println("======================== response= "+res );
				question.setQuizResponse(res);
			}
		}
		
		if (!goodResponse) {
			msgQuestion= "il doit avoir une bonne reponse dans le questionnaire!";
			error= true;
		}
	}
	
	private void ctrlNbrChoix(String nbr) {
		try {
			nbrChoix= Integer.valueOf(nbr);
		} catch (Exception e) {
			error= true;
		}
	}
	
	private void ctrlTitleQuestion(String titleQuestion) {
		if (titleQuestion.length() < LENGTH_TITLE_QUESTION_MIN || titleQuestion.length() > LENGTH_TITLE_QUESTION_MAX) {
			msgTitleQuestion= "doit avoir entre "+LENGTH_TITLE_QUESTION_MIN+" et "+LENGTH_TITLE_QUESTION_MAX;
			error= true;
		}
	}

	//********************Getters / Setters******************
	public Quiz getQuiz() {
		return quiz;
	}
	public int getNbrChoix() {
		return nbrChoix;
	}
	public String getMsgTitleQuestion() {
		return msgTitleQuestion;
	}
	public String getMsgQuestion() {
		return msgQuestion;
	}

	//********************Override***************************
	@Override
	public String toString() {
		return "CtrlQuizQuestion [question=" + question + ", quiz=" + quiz + ", nbrChoix=" + nbrChoix
				+ ", msgTitleQuestion=" + msgTitleQuestion + ", msgQuestion=" + msgQuestion + ", isError()=" + isError()
				+ "]";
	}
}
