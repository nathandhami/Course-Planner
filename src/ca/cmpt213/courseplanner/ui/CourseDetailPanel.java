package ca.cmpt213.courseplanner.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CourseDetailPanel extends CoursePlannerPanel{

	public CourseDetailPanel(String title) {
		super(title);
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
