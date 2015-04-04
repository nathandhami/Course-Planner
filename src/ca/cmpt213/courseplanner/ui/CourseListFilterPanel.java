package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CourseListFilterPanel extends CoursePlannerPanel{
	
	private JComboBox<String> comboBox;
	private Checkbox checkBox1 = new Checkbox("Include undergrad courses");
	private Checkbox checkBox2 = new Checkbox("Include grad courses");
	private JButton button = new JButton("Update Course List");
	private JPanel filterPanel = new JPanel();

	public CourseListFilterPanel(String title) {
		super(title);
		Vector<String> options = new Vector<String>();
		options.add("First");
		options.add("Second");
		comboBox =  new JComboBox<String>(options);
		comboBox.setName("DDD");
		modifyUserContentPanel();
	}

	@Override
	void modifyUserContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
		panel.add(new JLabel("Department "));
		panel.add(comboBox);
		setComponentSizeToFixedVerticalSize(panel);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
		panel2.add(checkBox1);
		panel2.add(checkBox2);
		setComponentSizeToFixedVerticalSize(button);
		filterPanel.setLayout(new BoxLayout(filterPanel,BoxLayout.PAGE_AXIS));
		filterPanel.add(panel);
		filterPanel.add(panel2);
		filterPanel.add(button);
		makeUserContentPanel(filterPanel);
	}
	
	

}
