package ca.cmpt213.courseplanner.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CourseDataExtractor {

	CourseList courses = new CourseList();

	public static void main(String args[]) {

		loadCoursesFromExcelFile();
//		List studentList = getStudentsListFromExcel();

	}

	public static void loadCoursesFromExcelFile() {
		
		 String semesterId;
		 String subject;
		 String catalogId;
		 String location;
		 int enrollmentCapacity;
		 int enrollmentTotal;
		 String instuctorName;
		 String courseType;

		File excelFile = new File("data/course_data_2015.csv");
		try{
			Scanner scanner = new Scanner(excelFile);
			scanner.useDelimiter(",");
			
			while(scanner.hasNext()){
				System.out.print(scanner.next() + "|");
				
			}
			scanner.close();
		}
		
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
	}

}
