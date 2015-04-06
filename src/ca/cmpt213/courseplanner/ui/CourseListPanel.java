package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CourseByNames;
import ca.cmpt213.courseplanner.model.CourseDataExtractor;
import ca.cmpt213.courseplanner.model.CourseList;
import ca.cmpt213.courseplanner.model.CourseListObserver;

@SuppressWarnings("serial")
public class CourseListPanel extends CoursePlannerPanel {
	
	private JList<String> list;
	private ArrayList<CourseByNames> listCourses = new ArrayList<CourseByNames>();

	public CourseListPanel(String title,CourseDataExtractor model) {
		super(title,model);
		getCoursesFromExtractor();

		
		String listData[] = new String[listCourses.size()];
		
		for(int i=0; i<listCourses.size(); i++){
			listData[i] = listCourses.get(i).getCourseName();
		}
		
		list = new JList<String>(listData);
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modifyUserContentPanel();
		registerAsObserver();
	}

	public void getCoursesFromExtractor(){
		listCourses = getModel().getSingleCourses();
		
	}
	
	private void updateCourseList(){
		
	}
	
	private void registerAsObserver(){
		getModel().getCourses().addCourseChangeObserver(new CourseListObserver() {
			
			@Override
			public void stateChanged() {
				// TODO Auto-generated method stub
				updateCourseList();
			}
		});
	}
	
	
	@Override
	void modifyUserContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(200,400));
		panel.add(new JScrollPane(list));
		makeUserContentPanel(panel);
	}

}
