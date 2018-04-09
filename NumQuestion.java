//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public class NumQuestion extends Question {
	
	protected double toleranceValue;
	
	public NumQuestion(String s, double val, double tolerance) {
		super(s, val);
		toleranceValue = tolerance;
		return;
	}//NumQuestion(String, double, double);
	
	public NumQuestion(Scanner s) {
		super(s);
		rightAnswer = new NumAnswer(s);
		toleranceValue = s.nextDouble();
		s.nextLine();
		return;
	}//NumQuestion(Scanner)
	
	public Answer getNewAnswer() {
		NumAnswer a = new NumAnswer(0);
		return a;
	}//getNewAnswer(void)
	
	public Answer getNewAnswer(double val) {
		NumAnswer a = new NumAnswer(val);
		return a;
	}//getNewAnswer(double)
	
	public void getAnswerFromStudent() {
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String s = sc.nextLine();
		double val = Double.parseDouble(s);
		
		studentAnswer = new NumAnswer(val);
		return;
	}//getAnswerFromStudent(void)
	
	public double getValue() {
		if(studentAnswer == null) {
			return 0;
		}
		double val = studentAnswer.getCredit(rightAnswer);
		
		if(val <= toleranceValue) {
			return maxValue;
		}
		else {
			return 0;
		}
	}//getValue(void);
	
	public void save(PrintWriter p) {
		p.println("NumQuestion");
		p.println(maxValue);
		p.println(questionText);
		rightAnswer.save(p);
		p.println(toleranceValue);
		return;
	}//save(PrintWriter)
	
}
