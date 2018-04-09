//Kevin Fong
//kfong8

import java.io.*;
import java.util.*;

public class ExamBuilder {
	
	public void menu() {
		System.out.println("Options");
		System.out.println("--------------------------------------");
		System.out.println("1. Load a saved exam from a file");
		System.out.println("2. Add questions");
		System.out.println("3. Remove questions");
		System.out.println("4. Reorder questions, and/or answers");
		System.out.println("5. Print the exam");
		System.out.println("6. Save the exam");
		System.out.println("7. Quit\n");
	}
	
	public Exam option1(Exam m) throws FileNotFoundException {
		Scanner sc = ScannerFactory.getKeyboardScanner();
		System.out.printf("Enter the name of the exam file: ");
		String fileName = sc.nextLine();
		fileName = fileName.trim();
		
		File file = new File(fileName);
		Scanner fileSC = new Scanner(file);
		m = new Exam(fileSC);
		return m;
	}
	
	public Exam option2(Exam m) {
		Scanner sc = ScannerFactory.getKeyboardScanner();
		if(m == null) {
			System.out.printf("Enter the title for the exam: ");
			String examTitle = sc.nextLine();
			m = new Exam(examTitle);			
		}
		
		System.out.printf("What type of question would you like to create: MCSAQuestion(1), MCMAQuestion(2), SAQuestion(3), NumQuestion(4)?: ");
		String questionType = sc.nextLine();
		int type = Integer.parseInt(questionType);
		
		System.out.printf("Enter the value of the question: ");
		String inputValue = sc.nextLine();
		double value = Double.parseDouble(inputValue);
		
		System.out.printf("Enter the question text: ");
		String questionText = sc.nextLine();
		
		Question q = null;
		Answer a;
		String choiceInput;
		int numOfChoices;
		char c;
		
		switch(type) {
		case 1: q = new MCSAQuestion(questionText, value);
				System.out.printf("Enter the number of answer choices you want to include: ");
				choiceInput = sc.nextLine();
				numOfChoices = Integer.parseInt(choiceInput);

				for (int i = 0; i < numOfChoices; i++) {
					c = (char)(i+65);
					System.out.printf("Enter the credit value and the text for answer " + c);
					double credit = sc.nextDouble();
					String answerText = sc.nextLine();
					answerText = answerText.trim();
					a = new MCSAAnswer(answerText, credit);
					((MCQuestion)q).addAnswer((MCAnswer)a);
				}
				break;
		case 2: System.out.printf("Enter the base credit for the question: ");
				String baseInput = sc.nextLine();
				double baseCredit = Double.parseDouble(baseInput);
				q = new MCMAQuestion(questionText, value, baseCredit);
		
				System.out.printf("Enter the number of answer choices you want to include: ");
				choiceInput = sc.nextLine();
				numOfChoices = Integer.parseInt(choiceInput);

				for (int i = 0; i < numOfChoices; i++) {
					c = (char)(i+65);
					System.out.printf("Enter the credit value and the text for answer " + c);
					double credit = sc.nextDouble();
					String answerText = sc.nextLine();
					answerText = answerText.trim();
					a = new MCMAAnswer(answerText, credit);
					((MCQuestion)q).addAnswer((MCAnswer)a);
				}
				break;
		case 3: q = new SAQuestion(questionText, value);
				System.out.printf("Enter the correct answer: ");
				String answerSA = sc.nextLine();
				answerSA = answerSA.trim();
				a = new SAAnswer(answerSA);
				q.setRightAnswer(a);
				break;
		case 4: System.out.printf("Enter the tolerance value: ");
				String toleranceInput = sc.nextLine();
				double tolerance = Double.parseDouble(toleranceInput);
				q = new NumQuestion(questionText, value, tolerance);
				System.out.printf("Enter the correct answer: ");
				String answerNum = sc.nextLine();
				double numValue = Double.parseDouble(answerNum);
				a = new NumAnswer(numValue);
				q.setRightAnswer(a);
				break;
		}
		
		m.addQuestion(q);
		return m;
	}
	
	public Exam option3(Exam m) {
		Scanner sc = ScannerFactory.getKeyboardScanner();
		System.out.printf("Which question do you want to delete?: ");
		String input = sc.nextLine();
		int choice = Integer.parseInt(input);
		m.removeQuestion((choice-1));
		return m;
	}
	
	public Exam option4(Exam m) {
		Scanner sc = ScannerFactory.getKeyboardScanner();
		System.out.printf("Would you like to reorder the questions(1), answers for MCQuestions(2), or both(3)?: ");
		String input = sc.nextLine();
		int option = Integer.parseInt(input);
		switch(option) {
		case 1: m.reorderQuestions();
				break;
		case 2: m.reorderMCAnswers(-1);
				break;
		case 3: m.reorderQuestions();
				m.reorderMCAnswers(-1);
				break;
		}
		
		return m;
	}
	
	public void option5(Exam m) {
		m.print();
	}
	
	public void option6(Exam m) throws FileNotFoundException {
		Scanner sc = ScannerFactory.getKeyboardScanner();
		System.out.printf("Enter the name of the file to export to: ");
		String fileName = sc.nextLine();
		fileName = fileName.trim();
		
		PrintWriter p = new PrintWriter(fileName);
		m.save(p);
		p.close();
	}
	
	public static void main(String[]args) throws FileNotFoundException {
		ExamBuilder a = new ExamBuilder();
		Exam m = null;
		
		while(true) {
			a.menu();
			Scanner sc = ScannerFactory.getKeyboardScanner();
			System.out.printf("Enter an option: ");
			String input = sc.nextLine();
			int option = Integer.parseInt(input);
			
			switch(option) {
			case 1: m = a.option1(m);
					break;
			case 2: m = a.option2(m);
					break;
			case 3: m = a.option3(m);
					break;
			case 4: m = a.option4(m);
					break;
			case 5: a.option5(m);
					break;
			case 6: a.option6(m);
					break;
			case 7: System.exit(0);
					break;
			}
			System.out.println();
		}
	}
}
