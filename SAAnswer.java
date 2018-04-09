//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public class SAAnswer extends Answer {

	protected String text;
	
	public SAAnswer(String s) {
		text = s;
		return;
	}//SAAnswer(String)
	
	public SAAnswer(Scanner s) {
		text = s.nextLine();
		text = text.trim();
		return;
	}//SAAnswer(Scanner)
	
	public void print() {
		System.out.println(text);
		return;
	}//print(void)
	
	public double getCredit(Answer rightAnswer) {
		if(text.equalsIgnoreCase(((SAAnswer)rightAnswer).text)) {		//Case insensitive string compare after type casting rightAnswer to a SAAnswer
			return 1;
		}
		else {
			return 0;
		}
	}//getCredit(Answer)
	
	public void save(PrintWriter p) {
		p.println(text);
	}//save(PrintWriter)

}
