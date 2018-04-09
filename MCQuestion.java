//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public abstract class MCQuestion extends Question{
	
	protected ArrayList<MCAnswer> answerList;
	
	protected MCQuestion(String s, double val) {
		super(s, val);
		answerList = new ArrayList<MCAnswer>();
		return;
	}//MCQuestion(String, double);
	
	protected MCQuestion(Scanner s) {
		super(s);
		answerList = new ArrayList<MCAnswer>();
		return;
	}//MCQuestion(Scanner)
	
	public void print() {
		System.out.println(questionText + "\n");
		
		for(int i = 0; i<answerList.size(); i++) {	//Prints all the MC answers below the question
			char letter = (char)(i+65);				//Type cast to a char to show the letter that corresponds to the answer
			
			MCAnswer a = answerList.get(i);
			
			System.out.printf("	" + letter + ". ");
			a.print();
			System.out.println();
		}
		return;
	}//print(void)
	
	public void addAnswer(MCAnswer a) {
		answerList.add(a);
		return;
	}//addAnswer(MCAnswer)
	
	public void reorderAnswers() {
		Collections.shuffle(answerList);	//Shuffles the MCAnswer objects in the array
		return;
	}//reorderAnswers(void)
	
	public double getValue(MCAnswer a) {
		if(a == null) {						//If not given an answer, it returns 0
			return 0;
		}
		
		double val = a.getCredit(rightAnswer);
		return (val * maxValue);
	}//getValue(MCAnswer)
	
	public void save(PrintWriter p) {
		if(this instanceof MCSAQuestion) {
			p.println("MCSAQuestion");
		}
		else if(this instanceof MCMAQuestion) {
			p.println("MCMAQuestion");
		}
		p.println(maxValue);
		p.println(questionText);
		return;
	}//save(PrintWriter)

}
