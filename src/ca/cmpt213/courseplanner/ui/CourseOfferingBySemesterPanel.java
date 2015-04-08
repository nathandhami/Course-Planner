package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CourseByNames;
import ca.cmpt213.courseplanner.model.CourseDataExtractor;
import ca.cmpt213.courseplanner.model.CourseDataExtractorObserver;
import ca.cmpt213.courseplanner.model.Semester;

@SuppressWarnings("serial")
public class CourseOfferingBySemesterPanel extends CoursePlannerPanel {

	CourseByNames selectedCourse;
	ArrayList<Course> coursesBySemester = new ArrayList<Course>();
	private final int NUM_OF_COLS = 3;
	private int Row_Start = 0;
	private int Row_End = 15;
	
	private JPanel mainPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	
	private GridBagConstraints c = new GridBagConstraints();
	
	public CourseOfferingBySemesterPanel(String title, CourseDataExtractor model) {
		super(title,model);

		addMainPanel();
		addTopPanel();
		addWestPanel();
		initialiseCenterPanel();
		
		registerAsObserver();
		modifyUserContentPanel();
	}

	private void updateCourseOffering(){
		
		selectedCourse = getModel().getSelOfferedCourse();
		coursesBySemester = selectedCourse.getIndividualCourses();
		for(int i =0; i < coursesBySemester.size();i++){
			System.out.println("Course Offering " + (i+1) + ": " +  coursesBySemester.get(i));
		}
		
		System.out.println("Number of offerings: " + coursesBySemester.size());
//		for(int i=0; i<coursesBySemester.size(); i++){
//			if(!coursesBySemester.get(i).getCourseType().equals("LEC")){
//				coursesBySemester.remove(i);
//			}
//		}
		setRows();
		
		for(Course c : coursesBySemester){
			System.out.println(c.getSemesterId());
		}
		addWestPanel();
		addCenterPanel();
		modifyUserContentPanel();
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
	
	private void addMainPanel(){
		mainPanel.setLayout(new BorderLayout());
	}
	
	private void addTopPanel(){
		topPanel.setBackground(Color.WHITE);
		topPanel.setLayout(new GridBagLayout());
		
		
		topPanel.add(new JLabel("Spring"),makeConstraints(0,0));
		topPanel.add(new JLabel("Summer"), makeConstraints(1,0));
		topPanel.add(new JLabel("Fall"),makeConstraints(2,0));
	}
	
	private void addWestPanel(){
		westPanel.removeAll();
		westPanel.setLayout(new GridBagLayout());
		westPanel.setBackground(Color.WHITE);
		
		for(int i =Row_Start ; i <= Row_End; i++){
			int year = 2000 + i;
			westPanel.add(new JLabel(String.valueOf(year)),makeConstraints(0,i));
		}
	}
	
	private void initialiseCenterPanel(){
		centerPanel.setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(500,500));
		
		for(int i=Row_Start; i <= Row_End; i++){
			
			for(int j=0; j<NUM_OF_COLS; j++){
			    c = makeConstraints(j,i);
				c.fill = GridBagConstraints.BOTH;

			}
		}
	}
	
	private void addCenterPanel(){
		
		centerPanel.removeAll();	
		
			for(int i =Row_Start; i <= Row_End; i++){
			
				for(int j =0; j < NUM_OF_COLS; j++){
					 c = makeConstraints(j,i);
					c.fill = GridBagConstraints.BOTH;
					
					JLabel label = new JLabel();
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					centerPanel.add(label,c);
					
					JPanel add_panel = new JPanel();
					add_panel.setLayout(new BoxLayout(add_panel, BoxLayout.PAGE_AXIS));
					
					for(int t=0; t<coursesBySemester.size(); t++){
						
						int y = Integer.parseInt(coursesBySemester.get(t).getSemesterId().substring(1, 3));
						
						if(y == i){
							
							int mon = Integer.parseInt(coursesBySemester.get(t).
										getSemesterId().substring(3, 4));
							if(mon == 1 && j == 0){
								add_panel.add(makeButton(coursesBySemester.get(t).getSemesterId(),coursesBySemester.get(t)));
//								System.out.println("adding button to i="+i+" j="+j);
							}
							else if(mon == 4 && j == 1){
								add_panel.add(makeButton(coursesBySemester.get(t).getCourseAndCampus(),coursesBySemester.get(t)));
//								System.out.println("adding button to i="+i+" j="+j);
							}
							else if(mon == 7 && j == 2){
								add_panel.add(makeButton(coursesBySemester.get(t).getCourseAndCampus(),coursesBySemester.get(t)));
//								System.out.println("adding button to i="+i+" j="+j);
							}
							
				
						}
					}
					
					
					centerPanel.add(add_panel,c);
//					c.fill = GridBagConstraints.HORIZONTAL;
					
				}
			}
	}
	
	
	private JButton makeButton(String label, Course getCourse){
		JButton button = new JButton(label);
		setComponentToFixedSize(button);
		setupButtonActionListener(button,getCourse);
		return button;
	}
	
	private void setupButtonActionListener(JButton button, Course getCourse){
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getModel().setChosenOfferedCourse(getCourse);
				getModel().notifyOfferingChangeObservers();
			}
		});
		
	}
	
	private JPanel returnGridPanel(String label){
		
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
	        JButton button = new JButton(label);
	        setComponentToFixedSize(button);
	        panel.add(button);
	        return panel;

	}
	
	private void setRows(){
		
		Row_Start = Integer.parseInt(coursesBySemester.get(0).getSemesterId().substring(1, 3));
		Row_End = Integer.parseInt(coursesBySemester.get(coursesBySemester.size()-1)
										.getSemesterId().substring(1, 3));
	}
	
	@Override
	void modifyUserContentPanel() {
			
			mainPanel.add(topPanel,BorderLayout.NORTH);
			mainPanel.add(westPanel,BorderLayout.WEST);
			//centerPanel.show();
			centerPanel.setVisible(true);
			mainPanel.add(centerPanel,BorderLayout.CENTER);
			makeUserContentPanel(mainPanel);
		
	}
	
	private GridBagConstraints makeConstraints(int j,int i){
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = j;
		c.gridy = i;
		
		//Other settings on c go here.
		c.weightx = 1;
		c.weighty = 1;
		
		return c;
	}

}
