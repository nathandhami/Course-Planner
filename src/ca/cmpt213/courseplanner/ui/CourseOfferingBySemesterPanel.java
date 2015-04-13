package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CourseByNames;
import ca.cmpt213.courseplanner.model.CourseDataExtractor;
import ca.cmpt213.courseplanner.model.CourseDataExtractorObserver;

@SuppressWarnings("serial")
public class CourseOfferingBySemesterPanel extends CoursePlannerPanel {

	CourseByNames selectedCourse;
	ArrayList<Course> coursesBySemester = new ArrayList<Course>();
	private final int NUM_OF_COLS = 4;
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
		initialiseCenterPanel();
		
		registerAsObserver();
		registerAsDepartmentChangeObserver();
		modifyUserContentPanel();
	}

	private void updateCourseOffering(){
		
		selectedCourse = getModel().getSelOfferedCourse();
		coursesBySemester = selectedCourse.getIndividualCourses();

		setRows();
		
		addCenterPanel();
		modifyUserContentPanel();
	}
	
	private void registerAsObserver(){
		getModel().addCourseChangeObserver(new CourseDataExtractorObserver() {
			
			@Override
			public void stateChanged() {
				updateCourseOffering();

			}
		});
	}
	
	private void registerAsDepartmentChangeObserver(){
		getModel().addDepartmentChangeObserver(new CourseDataExtractorObserver() {
			
			@Override
			public void stateChanged() {
				// TODO Auto-generated method stub
				mainPanel.removeAll();
			}
		});
	}
	
	private void addMainPanel(){
		mainPanel.setLayout(new BorderLayout());
	}
	
	private void addTopPanel(){
		topPanel.setBackground(Color.WHITE);
		topPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints forTop = makeConstraints(0,0);
		forTop.anchor = GridBagConstraints.CENTER;
		forTop.weightx = 1;
		topPanel.add(new JLabel("Spring"),forTop);
		topPanel.add(new JLabel("Summer"), makeConstraints(1,0));
		topPanel.add(new JLabel("Fall"),makeConstraints(2,0));
	}
	
	private void initialiseCenterPanel(){
		centerPanel.setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(500,500));
	}
	
	private void addCenterPanel(){
		
		centerPanel.removeAll();	
		
			for(int i =Row_Start; i <= Row_End; i++){
			
				for(int j =0; j < NUM_OF_COLS; j++){
					c = makeConstraints(j,i);
					c.fill = GridBagConstraints.BOTH;
					JPanel add_panel = new JPanel();
					
					if(j == 0){
						addYears(i, j);
					}
					else{
						
						JLabel label = new JLabel();
						label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						centerPanel.add(label,c);
						
						add_panel.setLayout(new BoxLayout(add_panel, BoxLayout.PAGE_AXIS));
						String ShouldBeDisplayed = " ";
						
						for(int t=0; t<coursesBySemester.size(); t++){
							
							if(!(coursesBySemester.get(t).getCourseAndCampus() + 
									coursesBySemester.get(t).getSemesterId())
									.equals(ShouldBeDisplayed)){
								displayOfferings(i, j, add_panel, t);
							}
							ShouldBeDisplayed = coursesBySemester.get(t).getCourseAndCampus() +
												coursesBySemester.get(t).getSemesterId();
						}
					}

					centerPanel.add(add_panel,c);
					
				}
			}
	}

	private void addYears(int i, int j) {
		int year = 2000 + i;
		JPanel py = new JPanel();
		py.setLayout(new BorderLayout());
		py.add(new JLabel(String.valueOf(year)),BorderLayout.CENTER);
		py.setBackground(Color.white);
		GridBagConstraints gc =makeConstraints(j,i);
		gc.fill = GridBagConstraints.BOTH;
		
		gc.ipadx = 0;
		centerPanel.add(py,gc);
	}

	private void displayOfferings(int i, int j, JPanel add_panel, int t) {
		int y = Integer.parseInt(coursesBySemester.get(t).getSemesterId().substring(1, 3));
		
		if(y == i){
			
			int mon = Integer.parseInt(coursesBySemester.get(t).
						getSemesterId().substring(3, 4));
			if(mon == 1 && j == 1){
				add_panel.add(makeButton(coursesBySemester.get(t).getCourseAndCampus()
									,coursesBySemester.get(t)));
			}
			else if(mon == 4 && j == 2){
				add_panel.add(makeButton(coursesBySemester.get(t).getCourseAndCampus()
									,coursesBySemester.get(t)));
			}
			else if(mon == 7 && j == 3){
				add_panel.add(makeButton(coursesBySemester.get(t).getCourseAndCampus()
									,coursesBySemester.get(t)));
			}
			

		}
	}
	
	
	private JButton makeButton(String label, Course getCourse){
		JButton button = new JButton(label);
		setComponentToFixedSize(button);
		setupButtonActionListener(button,getCourse);
		return button;
	}
	
	private void setupButtonActionListener(JButton button, final Course getCourse){
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getModel().setChosenOfferedCourse(getCourse);
				getModel().notifyOfferingChangeObservers();
			}
		});
		
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
			mainPanel.add(centerPanel,BorderLayout.CENTER);
			makeUserContentPanel(mainPanel);
		
	}
	
	private GridBagConstraints makeConstraints(int j,int i){
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = j;
		c.gridy = i;
		
		if(c.gridx == 0){
			c.anchor = GridBagConstraints.LINE_START;
			c.weightx = 0.02;
		}
		else{
			c.weightx = 1;
			c.weighty = 1;
		}
		
		
		return c;
	}

}
