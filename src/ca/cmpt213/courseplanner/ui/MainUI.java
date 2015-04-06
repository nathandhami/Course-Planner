package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CourseDataExtractor;

/**
 * 
 * This is the main ui class that initiates model and frame
 *
 */
public class MainUI {

	public static void main(String[] args) {
		
		CourseDataExtractor model = new CourseDataExtractor();
		
		model.loadCoursesFromExcelFile();
		
//		model.dumpModel();
		
		new CoursePlannerFrame("FAS Course Planner",model);

	}

}
