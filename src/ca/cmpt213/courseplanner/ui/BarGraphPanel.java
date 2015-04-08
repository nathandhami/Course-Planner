package ca.cmpt213.courseplanner.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.cmpt213.bargraph.BarGraphIcon;
import ca.cmpt213.bargraph.BarGraphModel;
import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CourseDataExtractor;
import ca.cmpt213.courseplanner.model.CourseDataExtractorObserver;
import ca.cmpt213.courseplanner.model.Semester;

public class BarGraphPanel extends CoursePlannerPanel {
	
	private BarGraphIcon icon1;
	private BarGraphIcon icon2;
	private BarGraphModel graphModel1;
	private BarGraphModel graphModel2;
	ArrayList<Course> coursesBySemester = new ArrayList<Course>();
	private JLabel courseLabel;

	private JPanel panel = new JPanel();
	public BarGraphPanel(String title,CourseDataExtractor model) {
		super(title,model);
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(200,200));
		initializeGraphModel();
		modifyUserContentPanel();
		registerAsObserver();
	}

	@Override
	void modifyUserContentPanel() {
		// TODO Auto-generated method stub
		 panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		icon1 = new BarGraphIcon(graphModel1,200,200);
		icon2= new BarGraphIcon(graphModel2,200,200);
		courseLabel = new JLabel("Course: ");
		panel.add(courseLabel);
		panel.add(new JLabel(" "));
		panel.add(new JLabel("Semester Offerings:"));
		panel.add( new JLabel(icon1));
		panel.add(new JLabel(" "));
		panel.add(new JLabel("Campus Offerings:"));
		panel.add(new JLabel(icon2));
		
		makeUserContentPanel(panel);
	}
	
	
	
	private void initializeGraphModel(){
		int data1[] = {0,0,0};
		int data2[] = {0,0,0,0};
		String months[] = {"Spring","Summer","Fall"};
		String campuses[] = {"Bby","Sry","Van","Other"};
		graphModel1 = new BarGraphModel(data1,months);
		graphModel2 = new BarGraphModel(data2,campuses);
	}
	
	private void registerAsObserver(){
		getModel().addCourseChangeObserver(new CourseDataExtractorObserver() {
			
			@Override
			public void stateChanged() {
				// TODO Auto-generated method stub
				updateUi();
				makeUserContentPanel(panel);
			}
		});
	}
	
	private void updateUi(){
		courseLabel.setText("Course: " + getModel().getSelOfferedCourse().getCourseName());
		coursesBySemester = getModel().getSelOfferedCourse().getIndividualCourses();
		int springNum = 0;
		int summerNum = 0;
		int fallNum = 0;
		
		for(int i =0; i < coursesBySemester.size();i++){
			Course course = coursesBySemester.get(i);
			Semester currentSemester = new Semester(course.getSemesterId());
			
			if(currentSemester.getSemesterMonth().equals("Spring")){
				springNum++;
			}
			else if(currentSemester.getSemesterMonth().equals("Fall")){
				fallNum++;
			}
			else if(currentSemester.getSemesterMonth().equals("Summer")){
				summerNum++;
			}
		}
		
		int numOfSurreyCourses = 0;
		int numOfVancouverCourses = 0;
		int numOfBurnabyCourses = 0;
		int numOfOtherCourses = 0;
		
		for(int i =0; i < coursesBySemester.size();i++){
			Course course = coursesBySemester.get(i);
			
			if(course.getLocation().equals("SURREY")){
				numOfSurreyCourses++;
			}
			else if(course.getLocation().equals("HRBRCNTR")){
				numOfVancouverCourses++;
			}
			else if(course.getLocation().equals("BURNABY")){
				numOfBurnabyCourses++;
			}
			else {
				numOfOtherCourses++;
			}
		}
		
		int newData1[] = {springNum,summerNum,fallNum};
		int newData2[] = {numOfBurnabyCourses,numOfSurreyCourses,numOfVancouverCourses,numOfOtherCourses};
		graphModel1.setData(newData1);
		graphModel2.setData(newData2);
		
	}

}
