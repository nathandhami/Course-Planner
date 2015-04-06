package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.CourseDataExtractor;

@SuppressWarnings("serial")
public class CourseListFilterPanel extends CoursePlannerPanel{
	
	private static JComboBox<String> comboBox;
	private JCheckBox checkBox1 = new JCheckBox("Include undergrad courses");
	private JCheckBox checkBox2 = new JCheckBox("Include grad courses");
	private JButton button = new JButton("Update Course List");
	private JPanel filterPanel = new JPanel();
	private Vector<String> options = new Vector<String>();
	
	public CourseListFilterPanel(String title) {
		super(title);
		
		getAllDepartments();
		comboBox =  new JComboBox<String>(options);
		modifyUserContentPanel();
		whichDepartment();
		
	}

	public void getAllDepartments(){
		
		ArrayList<String> lst = new ArrayList<String>();
		lst = CourseDataExtractor.getDepartmentNames();
		
		for(int i=0; i<lst.size(); i++){
			options.add(lst.get(i));
		}
	}
	
	public static String whichDepartment(){
		//need to get department from filter and pass it to courseListPanel
		String selectedDep = (String) comboBox.getSelectedItem();
		System.out.println(selectedDep);
		return null;
	}
	
	@Override
	void modifyUserContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
		panel.add(new JLabel("Department "));
		panel.add(comboBox);
		setComponentToFixedSize(panel);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(checkBox1,BorderLayout.WEST);
		panel2.add(checkBox2,BorderLayout.SOUTH);
		setComponentToFixedSize(button);
		filterPanel.setLayout(new BoxLayout(filterPanel,BoxLayout.PAGE_AXIS));
		filterPanel.add(panel);
		filterPanel.add(panel2);
		filterPanel.add(button);
		Dimension prefSize = filterPanel.getPreferredSize();
		makeUserContentPanel(filterPanel);
	}
	
	

}
