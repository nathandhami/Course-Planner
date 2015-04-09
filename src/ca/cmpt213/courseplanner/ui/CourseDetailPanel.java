package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CourseByNames;
import ca.cmpt213.courseplanner.model.CourseDataExtractor;
import ca.cmpt213.courseplanner.model.CourseDataExtractorObserver;

public class CourseDetailPanel extends CoursePlannerPanel {
	
	private JTextArea textBoxDisplay = new JTextArea();
	JPanel southPanel = new JPanel();
	private ArrayList<CourseByNames> allCourses = new ArrayList<CourseByNames>();
	private ArrayList<String> sectionDetail = new ArrayList<String>();
	private ArrayList<String> enrollDetail = new ArrayList<String>();

	public CourseDetailPanel(String title, CourseDataExtractor model) {
		super(title, model);
		allCourses = getModel().getSingleCourses();
		southPanel.setLayout(new GridLayout(0,2));
		modifyUserContentPanel();
		setPreferredSize(new Dimension(160, 160));
		setMaximumSize(new Dimension(200, 200));
		registerAsOfferingChangeObserver();
		registerAsCourseChangeObserver();
	}

	@Override
	void modifyUserContentPanel() {
		// TODO Auto-generated method stub
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel,BoxLayout.PAGE_AXIS));
//		panel.setBackground(Color.WHITE);
		westPanel.add(new JLabel("Course Name:"));
		westPanel.add(new JLabel("Semester:"));
		westPanel.add(new JLabel("Location:"));
		westPanel.add(new JLabel("Instructors:"));
		westPanel.add(new JLabel("Section Type:"));
		
		mainPanel.add(westPanel,BorderLayout.WEST);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel,BoxLayout.PAGE_AXIS));
		textBoxDisplay.setColumns(4);
		textBoxDisplay.setRows(1);
		textBoxDisplay.setLineWrap(true);
		textBoxDisplay.setWrapStyleWord(true);
		eastPanel.add(textBoxDisplay);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		makeUserContentPanel(mainPanel);
	}
	
	
	private void registerAsOfferingChangeObserver(){
		
		getModel().addOfferingChangeObserver(new CourseDataExtractorObserver() {
			
			@Override
			public void stateChanged() {
				// TODO Auto-generated method stub
				updateTextArea();
			}
		});
	}
	
	private void registerAsCourseChangeObserver(){
		getModel().addCourseChangeObserver(new CourseDataExtractorObserver() {
			
			@Override
			public void stateChanged() {
				// TODO Auto-generated method stub
				textBoxDisplay.setText(" ");
			}
		});
	}
	
	
	private void updateTextArea(){
		
		Course courseDisplay = getModel().getChosenOfferedCourse();

		textBoxDisplay.setText( courseDisplay.getFullCourseName() + "\n" +
								courseDisplay.getSemesterId() + "\n" +
								courseDisplay.getLocation() + "\n" +
								courseDisplay.getInstuctorName());
				
		sectionDetail.removeAll(sectionDetail);
		enrollDetail.removeAll(enrollDetail);
		southPanel.removeAll();
		getSectionDetail(courseDisplay);
		
		for(int i=sectionDetail.size() - 1; i>=0; i--){
			JLabel jDetail = new JLabel(sectionDetail.get(i));
			southPanel.add(jDetail);
			jDetail = new JLabel(enrollDetail.get(i));
			southPanel.add(jDetail);
			
		}
		System.out.println(sectionDetail);
	}
	
	private void getSectionDetail(Course c){
		
		ArrayList<Course> sameCourses = new ArrayList<Course>();
		
		for(int i=0; i<allCourses.size(); i++){
			if(allCourses.get(i).getCourseName()
					.equals(c.getFullCourseName())){
					
				sameCourses = allCourses.get(i).getIndividualCourses();
				break;
			}
		}
		
		for(int i=0; i<sameCourses.size(); i++){
			
			System.out.println(sameCourses.get(i));
			if(sameCourses.get(i).getSemesterId()
					.equals(c.getSemesterId()) && sameCourses.get(i).getCourseAndCampus()
					.equals(c.getCourseAndCampus())){
				
				String s = sameCourses.get(i).getCourseType() + ":";
				
				String e = sameCourses.get(i).getEnrollmentTotal() + "/" +
							sameCourses.get(i).getEnrollmentCapacity();
				
				sectionDetail.add(s);
				enrollDetail.add(e);
			}
		}
	}
}
