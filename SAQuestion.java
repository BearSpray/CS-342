//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public class SAQuestion extends Question {
	
	public SAQuestion(String s, double val) {
		super(s,val);
		return;
	}//SAQuestion(String, double)
	
	public SAQuestion(Scanner s) {
		super(s);
		rightAnswer = new SAAnswer(s);
		return;
	}//SAQuestion(Scanner)
	
	public Answer getNewAnswer() {
		String s = null;
		SAAnswer a = new SAAnswer(s);
		return a;
	}//getNewAnswer(void)
	
	public Answer getNewAnswer(String s) {
		SAAnswer a = new SAAnswer(s);
		return a;
	}//getNewAnswer(String)
	
	public void getAnswerFromStudent() {
		Scanner sc = ScannerFactory.getKeyboardScanner();	//Prompts user to enter in their answer
		String s = sc.nextLine();
		s = s.trim();
		
		studentAnswer = new SAAnswer(s);					//Creates a SAAnswer object using their input and stores it in studentAnswer
		return;
	}//getAnswerFromStudent(void)
	
	public double getValue() {
		if(studentAnswer == null) {							//If not given an answer, it returns 0
			return 0;
		}
		
		double val = studentAnswer.getCredit(rightAnswer);
		
		if(val == 1) {
			return maxValue;
		}
		else {
			return 0;
		}
	}//getValue(void)
	
	public void save(PrintWriter p) {
		p.println("SAQuestion");
		p.println(maxValue);
		p.println(questionText);
		rightAnswer.save(p);
		return;
	}//save(PrintWriter)

}
