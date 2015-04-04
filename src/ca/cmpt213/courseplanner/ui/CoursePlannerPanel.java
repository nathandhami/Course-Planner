package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public abstract class CoursePlannerPanel extends JPanel{
	
	private String title;
	
	
	public CoursePlannerPanel(String title){
		
		this.title = title;
		
		setLayout(new BorderLayout());
		
		add(makeLabel(title),BorderLayout.NORTH);
		
		setBorder(BorderFactory.createBevelBorder(
				BevelBorder.LOWERED,
				Color.black, Color.gray));
		
		
	}
	
	
	public Component makeLabel(String message) {
		JLabel label = new JLabel(message);
		return label;
	}

}
