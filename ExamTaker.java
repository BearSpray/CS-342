//Kevin Fong
//kfong8

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ExamTaker {
	
	public static void main(String[]args) throws FileNotFoundException {
		Scanner sc = ScannerFactory.getKeyboardScanner();
		System.out.printf("Enter your name: ");
		String name = sc.nextLine();
		
		System.out.println();
		System.out.printf("Enter the name of the exam file: ");
		String fileName = sc.nextLine();
		fileName = fileName.trim();
		
		File file = new File(fileName);
		Scanner fileSC = new Scanner(file);
		Exam test = new Exam(fileSC);
		test.print();
		
		while(true) {
			System.out.printf("Enter the number of the question you would like to answer or change(enter 'quit' to finish): ");
			String input = sc.nextLine();
			input = input.trim();
			
			if(input.equals("quit")) {
				break;
			}
			
			int questionNum = Integer.parseInt(input);
			System.out.printf("Enter your answer for Question " + questionNum + ": ");
			test.getAnswerFromStudent((questionNum - 1));
		}
		
		System.out.printf("Enter the name of the file to export answers to: ");
		String exportFile = sc.nextLine();
		exportFile = exportFile.trim();
		
		PrintWriter p = new PrintWriter(exportFile);
		
		p.println(name);
		p.println(fileName);
		
		LocalDate day = LocalDate.now();
		LocalTime time = LocalTime.now();
		p.println(time + " " + day);
		
		test.saveStudentAnswers(p);
		p.close();
	}
	
}
