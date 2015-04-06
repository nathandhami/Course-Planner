
package ca.cmpt213.courseplanner.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	public ArrayList<String> getDepartments(){
		
		ArrayList<String> deps = new ArrayList<String>();
		
		for(int i=0; i<courses.size(); i++){
			
			if(deps.isEmpty()){
				deps.add(courses.get(i).getSubject());
			}
			else{
				if(!deps.contains(courses.get(i).getSubject())){
					deps.add(courses.get(i).getSubject());
				}
			}
		}
		
			System.out.println(deps);
		
		
		return deps;
	}
	
	public void sortByName(){
		Collections.sort(courses,Course.CourseNameComparator);
		
	}
	
	public ArrayList<Course> getCoursesfromDepartment(String d){
		Department dep = new Department(CourseDataExtractor.getCourses());
		return dep.getAllCoursesFromDeparment(d);
	}
	
	public void displayCourses(){
		
		try {

			File file = new File("data/output_dump.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			String checkName = courses.get(0).getFullCourseName();
			String checkSemester = courses.get(0).getSemesterId() + courses.get(0).getLocation();
			String content = checkName;
			System.out.println(checkName);
			bw.write(content + "\n");
			
			
			content = "\t" + courses.get(0).getSemesterId() + " in " + courses.get(0).getLocation()
					+ " by " + courses.get(0).getInstuctorName();
			System.out.println(content);
			bw.write(content + "\n");
			
			for(Course c: courses){
				
				String courseSemesterAndLocation = c.getSemesterId() + c.getLocation(); 
				
				if(c.getFullCourseName().equals(checkName)){
					
					if(courseSemesterAndLocation.equals(checkSemester)){
						
						content = "\t\t Type=" + c.getCourseType() + ", Enrollment:"
								+ c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity();
						System.out.println(content);
						bw.write(content + "\n");
					}
					else{
						
						content = "\t" + c.getSemesterId() + " in " + c.getLocation()
								+ " by " + c.getInstuctorName();
						System.out.println(content);
						bw.write(content + "\n");
						
						content = "\t\t Type=" + c.getCourseType() + ", Enrollment:"
								+ c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity();
						System.out.println(content);
						bw.write(content + "\n");
						
						checkSemester = c.getSemesterId() + c.getLocation();
					}
					
					
				}
				else{
					checkName = c.getFullCourseName();
					System.out.println("\n" + checkName);
					bw.write(checkName + "\n");
					content = "\t" + c.getSemesterId() + " in " + c.getLocation()
							+ " by " + c.getInstuctorName();
					System.out.println(content);
					bw.write(content + "\n");
					
					content = "\t\t Type=" + c.getCourseType() + ", Enrollment:"
							+ c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity();
					System.out.println(content);
					bw.write(content + "\n");
					
					checkSemester = c.getSemesterId() + c.getLocation();
				}
		   }
			
			bw.close();
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}



}

