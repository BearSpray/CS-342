//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public abstract class Answer{
	
	protected Answer() {
	}//Answer(void);
	
	public Answer(Scanner s) {
	}//Answer(Scanner)
	
	public abstract void print();//abstract print(void)
	
	public abstract double getCredit(Answer rightAnswer);//abstract getCredit(Answer)
	
	public abstract void save(PrintWriter p);//abstract save(PrintWriter)

}
