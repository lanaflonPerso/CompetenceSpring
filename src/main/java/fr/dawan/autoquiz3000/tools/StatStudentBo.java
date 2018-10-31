package fr.dawan.autoquiz3000.tools;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.dawan.autoquiz3000.beans.QuizTest;

public class StatStudentBo {
	
	// en session
	// scoreTotal: ajout de toute les notes
	// quizTotal: nombre de quiz fait par le student
	
	private int quizTotal;
	private List<QuizTest> qTest;
	private HttpServletRequest request;

	public StatStudentBo(List<QuizTest> qTest, HttpServletRequest request) {
		this.qTest= qTest;
		this.request= request;
		
		quizTotal= qTest.size();
		request.setAttribute("quizTotal", quizTotal);
		
		average();
	}
	
	public void average() {
		int scoreTotal= 0;
		for (QuizTest quizTest : qTest) {
			scoreTotal+= quizTest.getScore();	
		}
		request.setAttribute("scoreTotal", scoreTotal);
		
		int average=100;
		if(quizTotal != 0) {
			average= scoreTotal/quizTotal;	
		}
		
		request.setAttribute("average", average);
	}
}
