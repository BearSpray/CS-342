//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public abstract class MCAnswer extends Answer{
	
	protected String text;
	protected double creditIfSelected;
	
	protected MCAnswer(String s, double val) {
		text = s;
		creditIfSelected = val;
		return;
	}//MCAnswer(String, double)
	
	public MCAnswer(Scanner s) {
		creditIfSelected = s.nextDouble();
		text = s.nextLine();
		text = text.trim();
		return;
	}//MCAnswer(Scanner)
	
	public void print() {
		System.out.println(text);
		return;
	}//print(void)
	
	public double getCredit(Answer rightAnswer) {
		return creditIfSelected;
	}//getCredit(Answer)
	
	public void save(PrintWriter p) {
		p.println(creditIfSelected + " " + text);
		return;
	}//save(PrintWriter)

}
