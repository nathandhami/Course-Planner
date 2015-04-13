package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.CourseDataExtractor;

@SuppressWarnings("serial")
public class CoursePlannerFrame extends JFrame{
	
	public CoursePlannerFrame(String title,CourseDataExtractor model){
		setTitle(title);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1300,720));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.add(new CourseListFilterPanel("Course List Filter", model));
		panel.add(new CourseListPanel("Course List",model));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
		panel2.add(new BarGraphPanel("Statistics",model));
		panel2.add(new CourseDetailPanel("Details of Course Offering",model));
		
		add(panel,BorderLayout.WEST);
		add(new CourseOfferingBySemesterPanel("Course Offerings By Semester",model),BorderLayout.CENTER);
		add(panel2,BorderLayout.EAST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	

}
