package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;




public class CoursePlannerFrame extends JFrame{
	
	public CoursePlannerFrame(String title){
		
		setTitle(title);
		setLayout(new BorderLayout());
		add(new CourseListFilterPanel("Course List Filter Panel"),BorderLayout.NORTH);
		add(new CourseListPanel("Course List"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	
	public static void main(String args[]) {
		new CoursePlannerFrame("FAS Course Planner");
	}


}
