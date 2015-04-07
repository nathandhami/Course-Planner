package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ca.cmpt213.courseplanner.model.CourseDataExtractor;

public class CourseDetailPanel extends CoursePlannerPanel {
	
	private JTextArea textBoxDisplay = new JTextArea();

	public CourseDetailPanel(String title, CourseDataExtractor model) {
		super(title, model);
		// TODO Auto-generated constructor stub
		modifyUserContentPanel();
		setPreferredSize(new Dimension(200, 200));
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
		
		mainPanel.add(westPanel,BorderLayout.WEST);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel,BoxLayout.PAGE_AXIS));
		textBoxDisplay.setColumns(4);
		textBoxDisplay.setRows(10);
		textBoxDisplay.setLineWrap(true);
		textBoxDisplay.setWrapStyleWord(true);
		eastPanel.add(textBoxDisplay);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		
		makeUserContentPanel(mainPanel);
	}
	
	
}
