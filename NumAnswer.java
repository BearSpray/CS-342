//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public class NumAnswer extends Answer{
	
	protected double value;

	public NumAnswer(double val) {
		value = val;
		return;
	}//NumAnswer(double);
	
	public NumAnswer(Scanner s) {
		value = s.nextDouble();
		s.nextLine();
		return;
	}//NumAnswer(Scanner)
	
	public void print() {
		System.out.println(value);
	}//print(void)
	
	public double getCredit(Answer rightAnswer) {
		double val = Math.abs(((NumAnswer)rightAnswer).value - value);
		return Math.round(val*100.0)/100.0;
	}//getCredit(Answer)
	
	public void save(PrintWriter p) {
		p.println(value);
	}//save(PrintWriter)
	
}
