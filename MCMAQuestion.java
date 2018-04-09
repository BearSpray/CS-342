//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public class MCMAQuestion extends MCQuestion {

	protected ArrayList<MCAnswer> studentList;
	protected double baseCredit;
	
	public MCMAQuestion(String s, double val, double credit) {
		super(s,val);
		baseCredit = credit;
		studentList = new ArrayList<MCAnswer>();
		return;
	}//MCMAQuestion(String, double, double)
	
	public MCMAQuestion(Scanner s) {
		super(s);
		baseCredit = s.nextDouble();
		studentList = new ArrayList<MCAnswer>();
		s.nextLine();
		
		int numOfChoices = s.nextInt();
		s.nextLine();
		
		for(int i = 0; i < numOfChoices; i++) {
			MCMAAnswer a = new MCMAAnswer(s);
			answerList.add(a);
		}
		return;
	}//MCMAQuestion(Scanner)
	
	public Answer getNewAnswer() {
		MCMAAnswer a = new MCMAAnswer(null, 0);
		return a;
	}//getNewAnswer(void)
	
	public Answer getNewAnswer(String s, double val) {
		MCMAAnswer a = new MCMAAnswer(s,val);
		return a;
	}//getNewAnswer(String, double)
	
	public void getAnswerFromStudent() {
		Scanner sc = ScannerFactory.getKeyboardScanner();	//Scans for input for an answer to MC questions
		
		String s = sc.nextLine();
		int length = s.length();
		int i = 0;
		char c;
		int index;
		
		while(i < length) {
			c = s.charAt(i);
			index = (int)(c-65);							//Type cast the char input to an index integer
			
			MCAnswer sA = answerList.get(index);			//Retrieves the corresponding answer in array and 'selects' that answer
			studentList.add(sA);
			i = i + 2;
		}
	}//getAnswerFromStudent(void)
	
	public double getValue() {
		if(studentList.size() == 0) {
			return (baseCredit * maxValue);
		}
		
		double val = 0;
		
		for(int i = 0; i < studentList.size(); i++) {
			MCAnswer a = studentList.get(i);
			val = val + super.getValue(a);
		}
		
		return val + (baseCredit * maxValue);
	}//getValue(void)
	
	public void save(PrintWriter p) {
		super.save(p);
		p.println(baseCredit);
		int size = answerList.size();
		p.println(size);
		
		for(int i = 0; i < size; i++) {
			MCAnswer a = answerList.get(i);
			a.save(p);
		}
		return;
	}//save(PrintWriter)
	
	public void saveStudentAnswers(PrintWriter p) {
		p.println("MCMAAnswer");
		
		int size = studentList.size();
		p.println(size);
		
		for(int i = 0; i < size; i++) {
			MCAnswer a = studentList.get(i);
			a.save(p);
		}
		return;
	}//saveStudentAnswers(PrintWriter)
	
	public void restoreStudentAnswers(Scanner s) {
		String answerType = s.nextLine();
		answerType = answerType.trim();
		
		if(answerType.equals("MCMAAnswer")) {
			int size = s.nextInt();
			s.nextLine();
			
			for(int i = 0; i < size; i++) {
				MCMAAnswer a = new MCMAAnswer(s);
				studentList.add(a);
			}
		}
		return;
	}//restoreStudentAnswers(Scanner);

}
