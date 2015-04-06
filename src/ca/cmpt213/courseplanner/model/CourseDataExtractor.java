
package ca.cmpt213.courseplanner.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import ca.cmpt213.courseplanner.ui.CourseListPanel;
import ca.cmpt213.courseplanner.ui.CoursePlannerFrame;

/**
 * 
 * Extract input from excel file and store data in business logic
 * MAIN MODEL
 */
public class CourseDataExtractor {

	private CourseList courses = new CourseList();
	private ArrayList<String> allDeps = new ArrayList<String>();
	private ArrayList<Course> coursesOffered = new ArrayList<Course>();
	private ArrayList<CourseByNames> singleCourses = new ArrayList<CourseByNames>();
	
	public void courseDataExtractorInit(){

		loadCoursesFromExcelFile();
		allDeps = courses.getDepartments();
		dumpModel();
		for(String s:allDeps){
			joinSameCourses(s);
		}
	}

	public void loadCoursesFromExcelFile() {

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
	
	
	public void dumpModel(){
		
		courses.sortByName();
		courses.displayCourses();
		
	}
	
	public ArrayList<Course> getCoursesFromDepartment(String d){
		Department dep = new Department(getCourses());
		return dep.getAllCoursesFromDeparment(d);
	}
	
	public CourseList getCourses(){
		return courses;
	}
	
	public ArrayList<CourseByNames> getSingleCourses(){
		return singleCourses;
	}
	
	
	public ArrayList<String> getDepartmentNames(){
		return allDeps;
	}
	
	private void joinSameCourses(String dep) {
        
        ArrayList<String> done = new ArrayList<String>();
        coursesOffered = getCoursesFromDepartment(dep);
        
        for(int i=0; i<coursesOffered.size(); i++){
                
                if(done.isEmpty() || !done.contains(coursesOffered.get(i).getFullCourseName())){
                        CourseByNames c = new CourseByNames(coursesOffered.get(i).getFullCourseName());
                        c.addCourses(coursesOffered);
                        singleCourses.add(c);
                        done.add(coursesOffered.get(i).getFullCourseName());
                }
                
        }
	}
        
        
        
}
	
	
