package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CourseByNames;
import ca.cmpt213.courseplanner.model.CourseDataExtractor;
import ca.cmpt213.courseplanner.model.CourseDataExtractorObserver;

@SuppressWarnings("serial")
public class CourseOfferingBySemesterPanel extends CoursePlannerPanel {

	CourseByNames selectedCourse;
	ArrayList<Course> coursesBySemester = new ArrayList<Course>();
	private final int NUM_OF_COLS = 3;
	private int firstRow = 0;
	private final int NUM_OF_ROWS = 15;
	
	public CourseOfferingBySemesterPanel(String title, CourseDataExtractor model) {
		super(title,model);
		// TODO Auto-generated constructor stub
		registerAsObserver();
		modifyUserContentPanel();
	}

	private void updateCourseOffering(){
		
		selectedCourse = getModel().getSelOfferedCourse();
		coursesBySemester = selectedCourse.getIndividualCourses();
		for(Course c : coursesBySemester){
			System.out.println(c.getSemesterId());
		}
	}
	
	private void registerAsObserver(){
		getModel().addCourseChangeObserver(new CourseDataExtractorObserver() {
			
			@Override
			public void stateChanged() {
				// TODO Auto-generated method stub
				updateCourseOffering();

			}
		});
	}
	
	@Override
	void modifyUserContentPanel() {
		// TODO Auto-generated method stub
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		topPanel.setLayout(new GridBagLayout());
		
		
		topPanel.add(new JLabel("Spring"),makeConstraints(0,0));
		topPanel.add(new JLabel("Summer"), makeConstraints(1,0));
		topPanel.add(new JLabel("Fall"),makeConstraints(2,0));
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridBagLayout());
		westPanel.setBackground(Color.WHITE);
		
		for(int i =0 ; i <= NUM_OF_ROWS; i++){
			int year = 2000 + i;
		westPanel.add(new JLabel(String.valueOf(year)),makeConstraints(0,i));
		}
		
		JPanel centerPanel = new JPanel();
		
		centerPanel.setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(500,500));
		
			for(int i =0; i <= NUM_OF_ROWS; i++){
			
			for(int j =0; j < NUM_OF_COLS; j++){
				GridBagConstraints c = makeConstraints(j,i);
				c.fill = GridBagConstraints.BOTH;
				centerPanel.add(new JButton("content"),c);
				
			}
		}
			
			mainPanel.add(topPanel,BorderLayout.NORTH);
			mainPanel.add(westPanel,BorderLayout.WEST);
			mainPanel.add(centerPanel,BorderLayout.CENTER);
		makeUserContentPanel(mainPanel);
		
	}
	
	private GridBagConstraints makeConstraints(int i,int j){
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = i;
		c.gridy = j;
		
		//Top row
		if(j == firstRow){
		}
		//Other settings on c go here.
		c.weightx = 1;
		c.weighty = 1;
		
		return c;
	}

}
