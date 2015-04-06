package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	}
	
	public void setBorder(JPanel component){
		component.setBorder(BorderFactory.createBevelBorder(
				BevelBorder.LOWERED,
				Color.black, Color.gray));	
		
	}
	
	public void makeUserContentPanel(JPanel component){
//		userContentPanel.add(component);
		setBorder(component);
		add(component,BorderLayout.CENTER);
	}
	
	
	public Component makeLabel(String message) {
		JLabel label = new JLabel(message);
		label.setForeground(Color.BLUE);
		return label;
	}
	
	public void setComponentToFixedSize(Component component){
		Dimension prefSize = component.getPreferredSize();
		Dimension newSize = new Dimension(
		Integer.MAX_VALUE,
		(int)prefSize.getHeight());
		component.setMaximumSize(newSize);
	}
	
	public void setSize(Component component){
		Dimension prefSize = component.getPreferredSize();
		Dimension newSize = new Dimension(
		(int)prefSize.getWidth(),
		(int)prefSize.getHeight());
		component.setMaximumSize(newSize);
	}
	
	abstract void modifyUserContentPanel();

}
