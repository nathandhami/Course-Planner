package ca.cmpt213.courseplanner.ui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BarGraphPanel extends CoursePlannerPanel {

	public BarGraphPanel(String title) {
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
		makeUserContentPanel(panel);
	}

}