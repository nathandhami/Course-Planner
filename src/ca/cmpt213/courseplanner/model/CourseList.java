
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
	
	public void insert(Course course){
		courses.add(course);
		
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
	
		return deps;
	}
	
	public void sortByName(){
		Collections.sort(courses,Course.CourseNameComparator);
		
	}
	
//	public ArrayList<Course> getCoursesfromDepartment(String d){
//		Department dep = new Department(CourseDataExtractor.getCourses());
//		return dep.getAllCoursesFromDeparment(d);
//	}
	
	public void displayCourses(){
		
		try {

			File file = new File("data/output_dump.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			writeToFle(bw);
			
			bw.close();
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	private void writeToFle(BufferedWriter bw) throws IOException {
		String checkName = courses.get(0).getFullCourseName();
		String checkSemester = courses.get(0).getSemesterId() + courses.get(0).getLocation();
		String content = checkName + "\n";
		bw.write(content + "\n");
		
		
		content = "\t" + courses.get(0).getSemesterId() + " in " + courses.get(0).getLocation()
				+ " by " + courses.get(0).getInstuctorName();
		bw.write(content + "\n");
		
		for(Course c: courses){
			
			String courseSemesterAndLocation = c.getSemesterId() + c.getLocation(); 
			
			
			if(c.getFullCourseName().equals(checkName)){
				
				if(courseSemesterAndLocation.equals(checkSemester)){
					
					content = "\t\t Type=" + c.getCourseType() + ", Enrollment:"
							+ c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity();
					bw.write(content + "\n");
				}
				else{
					
					content = "\t" + c.getSemesterId() + " in " + c.getLocation()
							+ " by " + c.getInstuctorName();
					bw.write(content + "\n");
					
					content = "\t\t Type=" + c.getCourseType() + ", Enrollment:"
							+ c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity();
					bw.write(content + "\n");
					
					checkSemester = c.getSemesterId() + c.getLocation();
				}
				
				
			}
			else{
				checkName = c.getFullCourseName();
				bw.write(checkName + "\n");
				content = "\t" + c.getSemesterId() + " in " + c.getLocation()
						+ " by " + c.getInstuctorName();
				bw.write(content + "\n");
				
				content = "\t\t Type=" + c.getCourseType() + ", Enrollment:"
						+ c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity();
				bw.write(content + "\n");
				
				checkSemester = c.getSemesterId() + c.getLocation();
			}
   }
	}
	
	public void addEnrollment(){
		
		String type = " ";
		
		for(int i=0; i<courses.size(); i++){
			
				if(type.equals(courses.get(i).getCourseType()+courses.get(i).getCourseAndCampus()
						+ courses.get(i).getSemesterId())){
					
					int val = Integer.parseInt(courses.get(i).getEnrollmentCapacity());
					courses.get(i-1).setEnrollmentCapacity(val);
					
					val = Integer.parseInt(courses.get(i).getEnrollmentTotal());
					courses.get(i-1).setEnrollmentTotal(val);
					
					courses.remove(i);
					i--;
				}
			
			
			type = courses.get(i).getCourseType() + courses.get(i).getCourseAndCampus()
					+ courses.get(i).getSemesterId();
		}
	}



}

