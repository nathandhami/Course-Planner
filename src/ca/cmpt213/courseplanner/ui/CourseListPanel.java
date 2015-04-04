package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class CourseListPanel extends CoursePlannerPanel {
	
	private JList<String> list;

	public CourseListPanel(String title) {
		super(title);
		String	listData[] =
			{
				"Item 1",
				"Item 2",
				"Item 3",
				"Item 4"
			};
		list = new JList<String>(listData);
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
//		add(list);
//		add(Box.createHorizontalGlue());
//		setLayout(new BorderLayout());


		modifyUserContentPanel();
	}

	@Override
	void modifyUserContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(list));
		panel.setPreferredSize(new Dimension(100,500));
		makeUserContentPanel(panel);
	}

}
