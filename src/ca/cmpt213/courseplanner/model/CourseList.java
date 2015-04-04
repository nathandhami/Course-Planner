package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * Holds list of courses
 *
 */
public class CourseList implements Iterable<Course> {
	
	private ArrayList<Course> courses = new ArrayList<Course>();
	private List<CourseListObserver> courseChangeObservers = new ArrayList<CourseListObserver>();
	private List<CourseListObserver> offeringChangeObservers = new ArrayList<CourseListObserver>();
	
	public void insert(Course course){
		courses.add(course);
		notifyCourseChangeObservers();
		notifyOfferingChangeObservers();
	}
	
	public ArrayList<Course> getCourseList(){
		
		return courses;
	}
	
	public int size(){
		
		return courses.size();
	}

	@Override
	public Iterator<Course> iterator() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(courses).iterator();
	}
	
	public void addOfferingChangeObserver(CourseListObserver observer) {
		courseChangeObservers.add(observer);
	}
	
	public void addCourseChangeObserver(CourseListObserver observer) {
		offeringChangeObservers.add(observer);
	}
	
	private void notifyCourseChangeObservers() {
		for (CourseListObserver observer : courseChangeObservers) {
			observer.stateChanged();
		}
	}
	
	private void notifyOfferingChangeObservers() {
		for (CourseListObserver observer : offeringChangeObservers) {
			observer.stateChanged();
		}
	}
	
	
	
	

}
