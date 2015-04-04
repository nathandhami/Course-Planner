package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class CoursePlannerFrame extends JFrame{
	
	public void setComponentToFixedSize(Component component){
		Dimension prefSize = component.getPreferredSize();
		Dimension newSize = new Dimension(
		Integer.MAX_VALUE,
		(int)prefSize.getHeight());
		component.setMaximumSize(newSize);
	}
	
	public CoursePlannerFrame(String title){
		
		setTitle(title);
		setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
		add(new CourseListFilterPanel("Course List Filter Panel"));
		add((new CourseListPanel("Course List")));
//		panel1.add(new CourseListFilterPanel("Course List Filter Panel"));
//		panel2.add(new CourseListFilterPanel("Course List Filter Panel"));
//		panel2.add(new CourseListFilterPanel("Course List Filter Panel"));
//		
//		add(panel1);
//		add(panel2);
		
//		add(new CourseListFilterPanel("Course List Filter Panel"),BorderLayout.NORTH);
//		add(new CourseListFilterPanel("Course List Filter Panel"),BorderLayout.WEST);
//		add(new CourseListFilterPanel("Course List Filter Panel"),BorderLayout.EAST);
//		add(new CourseListFilterPanel("Course List Filter Panel"),BorderLayout.SOUTH);
//		add(new CourseListPanel("Course List"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	
	public static void main(String args[]) {
		new CoursePlannerFrame("FAS Course Planner");
	}


}
