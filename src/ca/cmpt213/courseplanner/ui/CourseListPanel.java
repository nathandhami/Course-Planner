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
import ca.cmpt213.courseplanner.model.CourseDataExtractor;
import ca.cmpt213.courseplanner.model.CourseList;

@SuppressWarnings("serial")
public class CourseListPanel extends CoursePlannerPanel {
	
	private JList<String> list;
	private CourseList listCourses = new CourseList();

	public CourseListPanel(String title,CourseDataExtractor model) {
		super(title,model);
		getCoursesFromExtractor();
		
		ArrayList<Course> courses = new ArrayList<Course>();
		courses = listCourses.getCourseList();
		//courses = listCourses.getCoursesfromDepartment("CMPT");
		String listData[] = new String[courses.size()];
		
		for(int i=0; i<courses.size(); i++){
			listData[i] = courses.get(i).getFullCourseName();
		}
		
		list = new JList<String>(listData);
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modifyUserContentPanel();
	}

	public void getCoursesFromExtractor(){
		listCourses = getModel().getCourses();
		
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
