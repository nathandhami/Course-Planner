package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

// not sure.. in progress
// check out page 9 in assignment description.
public class CourseOffering {
	
	private CourseList courses;
	private String courseName;
	private ArrayList<Course> courseOfferings = new ArrayList<Course>();
	
	public CourseOffering(CourseList courses,String courseName){
		this.courseName = courseName;
		this.courses = courses;
	}
	
	public void getAllOfferingsByCourseName(){
		courseOfferings = courses.getCourseList();
		
	}

}
