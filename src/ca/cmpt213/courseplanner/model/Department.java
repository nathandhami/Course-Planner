package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;


/**
 * 
 * This class will show all courses from a department (NO DUPLICATES)
 * Example: CMPT is the department name
 * 			CMPT 213, CMPT 300, etc. are the courses for that department
 */
public class Department {
	
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<String> departments = new ArrayList<String>();
	
	
	public Department(CourseList courseList){
		
		this.courses = courseList.getCourseList();
	}
	
	
	public void getAllCoursesFromDeparment(String departmentName){
		
		for(int i =0; i < courses.size(); i++){
			if(courses.get(i).getSubject().equals(departmentName)){
				departments.add(courses.get(i).getSubject());
			}
		}
		
		for(int i =0; i < departments.size(); i++){
			
			String courseName = departments.get(i);
			
			for(int j =0; j < departments.size();j++){
				if(courseName.equals(departments.get(j)) && i !=j){
					departments.remove(j);
				}
			}
		}
		
		for(int i =0; i < departments.size(); i++)
		System.out.println(departments.get(i));
	}
	
	


}
