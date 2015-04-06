package ca.cmpt213.courseplanner.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.CourseDataExtractor;

public class CourseDetailPanel extends CoursePlannerPanel{

	public CourseDetailPanel(String title, CourseDataExtractor model) {
		super(title,model);
		// TODO Auto-generated constructor stub
		modifyUserContentPanel();
		setPreferredSize(new Dimension(200,200));
	}

	@Override
	void modifyUserContentPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.WHITE);
		makeUserContentPanel(panel);
	}

}
