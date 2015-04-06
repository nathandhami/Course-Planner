package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.CourseDataExtractor;




public class CoursePlannerFrame extends JFrame{
	
	public CoursePlannerFrame(String title,CourseDataExtractor model){
		setTitle(title);
		setLayout(new BoxLayout(getContentPane(),BoxLayout.LINE_AXIS));
//		add(new CourseListFilterPanel("Course List Filter"));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.add(new CourseListFilterPanel("Course List Filter", model));
		panel.add(new CourseListPanel("Course List",model));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
		panel2.add(new BarGraphPanel("Statistics",model));
		panel2.add(new CourseDetailPanel("Details of Course Offering",model));
		
		add(panel);
		add(new CourseOfferingBySemesterPanel("Course Offerings By Semester",model));
		add(panel2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
//	public static void main(String args[]) {
//		new CoursePlannerFrame("FAS Course Planner");
//	}
	

}
