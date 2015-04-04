package ca.cmpt213.courseplanner.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.Semester;

public class CoursePlannerTest {

	@Test
	public void testSemester() {
		Semester testSemester = new Semester("1151");
		String expectedResult =  "Spring";
		assertEquals(expectedResult,testSemester.getSemesterMonth());
		
		expectedResult =  "Summer";
		testSemester = new Semester("1154");
		assertEquals(expectedResult,testSemester.getSemesterMonth());
		
		expectedResult =  "Fall";
		testSemester = new Semester("1157");
		assertEquals(expectedResult,testSemester.getSemesterMonth());
		
		expectedResult =  "2015";
		testSemester = new Semester("1157");
		assertEquals(expectedResult,testSemester.getSemesterYear());
		
		expectedResult =  "2022";
		testSemester = new Semester("1227");
		assertEquals(expectedResult,testSemester.getSemesterYear());
		
		expectedResult =  "2000";
		testSemester = new Semester("1007");
		assertEquals(expectedResult,testSemester.getSemesterYear());
		
		expectedResult =  "2005";
		testSemester = new Semester("1057");
		assertEquals(expectedResult,testSemester.getSemesterYear());
		
		expectedResult =  "2010";
		testSemester = new Semester("1107");
		assertEquals(expectedResult,testSemester.getSemesterYear());
	}
	
	@Test
	public void testCourse(){
		Course course = new Course("1151","CMPT","300","SURREY","30","40","Fraser","LAB");
		String expectedResult = "CMPT 300";
		assertEquals(expectedResult,course.getFullCourseName());
	}

}
