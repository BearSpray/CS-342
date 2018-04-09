//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;
import java.time.*;

public class Exam {
	
	private String examTitle;
	private ArrayList<Question> questionList;
	
	public Exam(String s) {
		examTitle = s;
		questionList = new ArrayList<Question>();
		return;
	}//Exam(String)
	
	public Exam(Scanner s) {
		examTitle = s.nextLine();
		examTitle = examTitle.trim();
		questionList = new ArrayList<Question>();
		s.nextLine();
		
		String questionType;
		Question q;
		
		while(s.hasNextLine()) {
			s.nextLine();
			questionType = null;
			q = null;
			questionType = s.nextLine();
			questionType = questionType.trim();
			
			if(questionType.equals("MCSAQuestion")) {
				q = new MCSAQuestion(s);
			}
			else if(questionType.equals("MCMAQuestion")) {
				q = new MCMAQuestion(s);
			}
			else if(questionType.equals("SAQuestion")) {
				q = new SAQuestion(s);
			}
			else if(questionType.equals("NumQuestion")) {
				q = new NumQuestion(s);
			}
			questionList.add(q);
		}
		return;
	}//Exam(Scanner)
	
	public void print() {
		System.out.println(examTitle + "\n");
		
		for(int i = 0; i<questionList.size(); i++) {
			Question q = questionList.get(i);
			System.out.printf((i+1) + ". ");
			q.print();
			System.out.println("\n");
		}
	}//print(void)
	
	public void addQuestion(Question q) {
		questionList.add(q);
		return;
	}//addQuestion(Question)
	
	public void removeQuestion(int position) {
		questionList.remove(position);
		return;
	}//removeQuestion(int)
	
	public void reorderQuestions() {
		Collections.shuffle(questionList);	//Use collections.shuffle to reorder
		return;
	}//reorderQuestions(void)
	
	public void reorderMCAnswers(int position) {
		Question q;
		if(position >= 0) {		//Positive positions reorder the answers of that MCQuestion
			q = questionList.get(position);
			if(q instanceof MCQuestion) {
				((MCQuestion) q).reorderAnswers();
			}
		}
		else {					//Otherwise reorder all MCQuestion answers
			for(int i = 0; i<questionList.size(); i++) {
				q = questionList.get(i);
				if(q instanceof MCQuestion) {
					((MCQuestion) q).reorderAnswers();
				}
			}
		}
	}//reorderMCAnswers(int)
	
	public void getAnswerFromStudent(int position) {
		Question q = questionList.get(position);
		q.getAnswerFromStudent();
	}//getAnswerFromStudent(int)
	
	public double getValue() {
		double score = 0;
		for(int i = 0; i<questionList.size(); i++) {
			Question q = questionList.get(i);
			score = score + q.getValue();
		}
		return score;
	}//getValue(void)
	
	public void reportQuestionValues() {
		System.out.println("Question	Value");
		System.out.println("---------------------");
		for(int i = 0; i<questionList. size(); i++) {
			Question q = questionList.get(i);
			System.out.println((i+1) + "		" + q.getValue());
		}
		System.out.println("\nTotal		" + this.getValue());
	}//reportQuestionValues(void)
	
	public void save(PrintWriter p) {
		p.println(examTitle);
		LocalDate day = LocalDate.now();
		LocalTime time = LocalTime.now();
		p.println(time + " " + day);
		Question q;
		
		for(int i = 0 ; i < questionList.size(); i++) {
			p.println();
			q = questionList.get(i);
			q.save(p);
		}
	}//save(PrintWriter)
	
	public void saveStudentAnswers(PrintWriter p) {
		Question q;

		for(int i = 0; i < questionList.size(); i++) {
			p.println();
			q = questionList.get(i);
			q.saveStudentAnswers(p);
		}
	}//saveStudentAnswers(PrintWriter)
	
	public void restoreStudentAnswers(Scanner s) {
		s.nextLine();
		s.nextLine();
		s.nextLine();
		
		Question q;
		
		for(int i = 0; i < questionList.size(); i++) {
			s.nextLine();
			q = questionList.get(i);
			q.restoreStudentAnswers(s);
		}
	}//restoreStudentAnswers(Scanner)
	
}
