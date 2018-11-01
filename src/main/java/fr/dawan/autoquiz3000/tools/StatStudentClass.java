package fr.dawan.autoquiz3000.tools;

import fr.dawan.autoquiz3000.beans.Quiz;

public class StatStudentClass {
	
	private double averangeScore;
	private long minScore;
	private long maxScore;
	private long countQuizDone;
	private long countSkill;
	private long countFailSkill;

	private Quiz quiz;
		
	public StatStudentClass() {
	}
	 
	public double getAverangeScore() {
		return averangeScore;
	}
	public void setAverangeScore(double averangeScore) {
		this.averangeScore = averangeScore;
	}
	public long getCountQuizDone() {
		return countQuizDone;
	}
	public void setCountQuizDone(long countQuizDO) {
		this.countQuizDone = countQuizDO;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public long getMinScore() {
		return minScore;
	}

	public void setMinScore(long minScore) {
		this.minScore = minScore;
	}

	public long getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(long maxScore) {
		this.maxScore = maxScore;
	}

	public long getCountSkill() {
		return countSkill;
	}

	public void setCountSkill(long countSkill) {
		this.countSkill = countSkill;
	}
	
	public long getCountFailSkill() {
		return countFailSkill;
	}

	public void setCountFailSkill(long countFailSkill) {
		this.countFailSkill = countFailSkill;
	}
}
