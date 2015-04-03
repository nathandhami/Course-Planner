package ca.cmpt213.courseplanner.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Extract input from excel file and store data in business logic
 *
 */
public class CourseDataExtractor {

	private static CourseList courses = new CourseList();
//	private static CourseOffering courseOffering;
	private static ArrayList<Course> coursesOffered = new ArrayList<Course>();

	public static void main(String args[]) {

		loadCoursesFromExcelFile();
		

	}

	public static void loadCoursesFromExcelFile() {

		final int semesterId= 0;
		final int subject = 1;
		final int catalogId  = 2;
		final int location  = 3;
		final int enrollmentCapacity  = 4;
		final int enrollmentTotal  = 5;
		final int instructorName  = 6;
		final int courseType = 7;
		final int finalColumnIndex = 8;
		final int instructorColumnIndex = 6;

		File excelFile = new File("data/course_data_2015.csv");

		try {
			Scanner scanner = new Scanner(excelFile);

			while (scanner.hasNextLine()) {

				ArrayList<String> words;

				String line = scanner.nextLine();
				
				boolean rowHasMultipleInstructors = !line.contains("\"");
	
				if (rowHasMultipleInstructors) {
					words = new ArrayList<String>(Arrays.asList(line.split(",", finalColumnIndex)));
					for (int i = 0; i < words.size(); i++) {
						System.out.println(words.get(i));
					}
					System.out.println(words.size());

					System.out.println();
				} else {
					words = new ArrayList<String>(Arrays.asList(line.split(",", finalColumnIndex - 1)));
					for (int i = 0; i < words.size(); i++) {

						if (i == instructorColumnIndex) {
							int index = words.get(i).lastIndexOf("\"");

							int firstIndexOfCourseTypeString = index + 2;
							
							String componentCode = words.get(i).substring(firstIndexOfCourseTypeString);

							int lastIndexOfInstructorString = index + 1;

							words.set(i, words.get(i).substring(0, lastIndexOfInstructorString)
											         .replaceAll("\"", ""));

							words.add(componentCode);

						}

						System.out.println(words.get(i));

					}

					System.out.println(words.size());

					System.out.println();

				}

				courses.insert(new Course(words.get(semesterId), words
						.get(subject), words.get(catalogId), words
						.get(location), words.get(enrollmentCapacity), words
						.get(enrollmentTotal), words.get(instructorName), words
						.get(courseType)));

			}
			scanner.close();

			System.out.println(courses.size());
		}

		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	
//	public void dumpModel(){
//		coursesOffered = courses.getCourseList();
//		
//		for(int i = 0; i < courses.size();i++){
//			
//			if(coursesOffered.get(i).equals("cmpt"
//		}
//		
//		
//	}
	
	
	
}
