//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public abstract class Question {
	
	protected String questionText;
	protected Answer rightAnswer;
	protected Answer studentAnswer;
	protected double maxValue;
	
	protected Question(String s, double val) {
		questionText = s;
		maxValue = val;
		studentAnswer = null;
		return;
	}//Question(String, double);
	
	public Question(Scanner s) {
		maxValue = s.nextDouble();
		s.nextLine();
		questionText = s.nextLine();
		questionText = questionText.trim();
		studentAnswer = null;
		return;
	}//Question(Scanner)
	
	public void print() {
		System.out.println(questionText);
	}//print(void)
	
	public void setRightAnswer(Answer a) {
		rightAnswer = a;
	}//setRightAnswer(Answer)
	
	public abstract Answer getNewAnswer();//abstract getNewAnswer(void)
	
	public abstract void getAnswerFromStudent();//abstract getAnswerFromStudent(void)
	
	public abstract double getValue();//abstract getValue(void)
	
	public abstract void save(PrintWriter p);//abstract save(PrintWriter)
	
	public void saveStudentAnswers(PrintWriter p) {
		int type = 0;
		
		if(this instanceof MCSAQuestion) {
			p.println("MCSAAnswer");
			type = 1;
		}
		else if(this instanceof SAQuestion) {
			p.println("SAAnswer");
			type = 2;
		}
		else if(this instanceof NumQuestion) {
			p.println("NumAnswer");
			type = 3;
		}
		
		if(studentAnswer != null) {
			studentAnswer.save(p);
		}
		else {
			switch(type) {
				case 1: p.println("0.0	Not Answered");
						break;
				case 2: p.println("Not Answered");
						break;
				case 3: p.println("-999.99");
						break;
			}
		}
		return;
	}//saveStudentAnswers(PrintWriter)
	
	public void restoreStudentAnswers(Scanner s) {
		String answerType = s.nextLine();
		answerType = answerType.trim();

		if(answerType.equals("MCSAAnswer")) {
			studentAnswer = new MCSAAnswer(s);
		}
		else if(answerType.equals("SAAnswer")) {
			studentAnswer = new SAAnswer(s);
		}
		else if(answerType.equals("NumAnswer")) {
			studentAnswer = new NumAnswer(s);
		}
		return;
	}//restoreStudentAnswers(Scanner)

}
