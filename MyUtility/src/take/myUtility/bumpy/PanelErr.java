package take.myUtility.bumpy;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelErr extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public PanelErr() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		add(scrollPane);

		textArea = new JTextArea();
		textArea.setRows(10);
		scrollPane.setViewportView(textArea);

	}

	void setText(String s){
		textArea.append(s);
	}
}
