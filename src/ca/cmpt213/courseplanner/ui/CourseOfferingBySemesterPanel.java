package ca.cmpt213.courseplanner.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CourseOfferingBySemesterPanel extends CoursePlannerPanel {

	public CourseOfferingBySemesterPanel(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		modifyUserContentPanel();
	}

	@Override
	void modifyUserContentPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.WHITE);
		setPreferredSize(new Dimension(500,500));
		makeUserContentPanel(panel);
		
	}
	
	private GridBagConstraints makeConstraints(int i ){
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = i;
		c.gridy = i;
		//Other settings on c go here.
		return c;
	}

}
