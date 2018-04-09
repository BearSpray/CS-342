//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public class MCSAQuestion extends MCQuestion{

	public MCSAQuestion(String s, double val) {
		super(s,val);
		return;
	}//MCSAQuestion(String, double)
	
	public MCSAQuestion(Scanner s) {
		super(s);
		int numOfChoices = s.nextInt();
		s.nextLine();
		
		for(int i = 0; i < numOfChoices; i++) {
			MCSAAnswer a = new MCSAAnswer(s);
			answerList.add(a);
		}
		return;
	}//MCSAQuestion(Scanner)
	
	public Answer getNewAnswer() {
		MCSAAnswer a = new MCSAAnswer(null, 0);
		return a;
	}//getNewAnswer(void)
	
	public Answer getNewAnswer(String s, double val) {
		MCSAAnswer a = new MCSAAnswer(s, val);
		return a;
	}//getNewAnswer(String, double)
	
	public void getAnswerFromStudent() {
		Scanner sc = ScannerFactory.getKeyboardScanner();	//Scans for input for an answer to MC questions
		String s = sc.nextLine();
		char c = s.charAt(0);
		
		int index = (int)(c-65);							//Type cast the char input to an index integer
		
		MCAnswer sA = answerList.get(index);				//Retrieves the corresponding answer in array and 'selects' that answer
		studentAnswer = sA;									//Sets the studentAnswer field to the answer they chose
	}//getAnswerFromStudent(void)
	
	public double getValue() {
		return super.getValue((MCSAAnswer)studentAnswer);
	}//getValue(void)
	
	public void save(PrintWriter p) {
		super.save(p);
		int size = answerList.size();
		p.println(size);
		
		for(int i = 0; i < size; i++) {
			MCAnswer a = answerList.get(i);
			a.save(p);
		}
		return;
	}//save(PrintWriter)

}
