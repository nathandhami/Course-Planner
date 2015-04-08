
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
	private ArrayList<String> coursesForDep = new ArrayList<String>();
	private ArrayList<CourseByNames> singleCourses = new ArrayList<CourseByNames>();
	private CourseByNames selOfferedCourse;
	private List<CourseDataExtractorObserver> courseChangeObservers = new ArrayList<CourseDataExtractorObserver>();
	private List<CourseDataExtractorObserver> offeringChangeObservers = new ArrayList<CourseDataExtractorObserver>();
	private List<CourseDataExtractorObserver> departmentChangeObservers = new ArrayList<CourseDataExtractorObserver>();

	private Course chosenOfferedCourse;
	
	public void courseDataExtractorInit(){

		loadCoursesFromExcelFile();
		courses.sortByName();
		allDeps = courses.getDepartments();
		courses.addEnrollment();
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
	
	public void setSingleCoursesForDepartment(String d){
        
		coursesForDep.clear();
        
        for(int i=0; i<singleCourses.size(); i++){
                
                if(singleCourses.get(i).getCourseName().contains(d)){
                        coursesForDep.add(singleCourses.get(i).getCourseName());
                }
        }
        
        
  
	}
	
	public void excludeGradCourses(){
		
		for(int i=0; i<singleCourses.size(); i++){
			
			for(int j=0; j<coursesForDep.size(); j++){
				
				if(singleCourses.get(i).getCourseName().equals(coursesForDep.get(j))){
					
					int num = getStringValue(singleCourses.get(i).getIndividualCourses().get(0)
							.getCatalogId());
					
					
					if(num >= 500 ){
						
						coursesForDep.remove(j); 
						j--;
					}
					
					
				}
			}
		}
	}

	
	public void excludeUnderGradCourses(){
		
		for(int i=0; i<singleCourses.size(); i++){
			
			for(int j=0; j<coursesForDep.size(); j++){
				
				if(singleCourses.get(i).getCourseName().equals(coursesForDep.get(j))){
					
					int num = getStringValue(singleCourses.get(i).getIndividualCourses().get(0)
							.getCatalogId());
					
					if(num < 500){
						coursesForDep.remove(j);
						j--;
					}
					
					
				}
			}
		}
	}
	
	
	public void setChosenOfferedCourse(Course chosenOfferedCourse){
		
		this.chosenOfferedCourse = chosenOfferedCourse;
	}
	
	public Course getChosenOfferedCourse(){
		return chosenOfferedCourse;
	}
	
	
	private int getStringValue(String str) {
		 return Integer.valueOf("0" + str.replaceAll("(\\d*).*", "$1"));
	}

	public ArrayList<String> getCouresForDep(){
		return coursesForDep;
	}
	
	public CourseByNames getSelOfferedCourse(){
		return selOfferedCourse;
		
	}
	
	public void setSelOfferedCourse(String s){
		
		for(int i=0; i<singleCourses.size(); i++){
			
			if(singleCourses.get(i).getCourseName().equals(s)){
				selOfferedCourse = singleCourses.get(i);
				break;
			}
		}
		
	}

	
	/*
	 * OBSERVER METHODS
	 */
	public void addDepartmentChangeObserver(CourseDataExtractorObserver observer) {
		departmentChangeObservers.add(observer);
	}
	public void addOfferingChangeObserver(CourseDataExtractorObserver observer) {
		offeringChangeObservers.add(observer);
	}
	
	public void addCourseChangeObserver(CourseDataExtractorObserver observer) {
		
		courseChangeObservers.add(observer);
	}
	
	public void notifyDepartmentChangeObservers() {
		for (CourseDataExtractorObserver observer : departmentChangeObservers) {
			observer.stateChanged();
		}
	}
	
	public void notifyCourseChangeObservers() {
		
		for (CourseDataExtractorObserver observer : courseChangeObservers) {
			observer.stateChanged();
			
		}
	}
	
	public void notifyOfferingChangeObservers() {
		for (CourseDataExtractorObserver observer : offeringChangeObservers) {
			observer.stateChanged();
		}
	}
        
        
        
}
	
	
