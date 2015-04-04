package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;

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

		modifyUserContentPanel();
	}

	@Override
	void modifyUserContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(list));
		makeUserContentPanel(panel);
	}

}
