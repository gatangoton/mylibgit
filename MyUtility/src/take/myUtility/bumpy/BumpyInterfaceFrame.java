package take.myUtility.bumpy;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class BumpyInterfaceFrame extends JFrame{

	BumpyInterfaceController bic;
	private JTextField textField;
	private JTextArea textAreaStdOut;
	private JTextArea textAreaErrOut;
	private PanelErr panelErr;

	public BumpyInterfaceFrame(BumpyInterfaceController c) {
		bic = c;

		setSize(new Dimension(800, 700));
		setMinimumSize(new Dimension(300, 300));
		setTitle("Bumpy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);


		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(500, 200));
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 58, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, -20, SpringLayout.EAST, getContentPane());
		getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

				textField = new JTextField();
				textField.setColumns(30);
				panel.add(textField);

				JButton btnNewButton = new JButton("button");
				panel.add(btnNewButton);
				btnNewButton.addActionListener(bic);

		panelErr = new PanelErr();
		tabbedPane.addTab("New tab", null, panelErr, null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 200));
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.SOUTH, tabbedPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 80, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -20, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);

		textAreaStdOut = new JTextArea();
		scrollPane.setViewportView(textAreaStdOut);

		JScrollPane scrollPane_1 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 10, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, 0, SpringLayout.EAST, scrollPane);
		getContentPane().add(scrollPane_1);

		textAreaErrOut = new JTextArea();
		scrollPane_1.setViewportView(textAreaErrOut);

		JLabel label = new JLabel("標準出力");
		springLayout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(label);

		JLabel lblError = new JLabel("Error出力");
		springLayout.putConstraint(SpringLayout.NORTH, lblError, 0, SpringLayout.NORTH, scrollPane_1);
		springLayout.putConstraint(SpringLayout.WEST, lblError, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblError);

	}

	String getCommandText(){
		return textField.getText();
	}

	void setProgramErr(String s){
		panelErr.setText(s);
	}

	public void setStdOut(String s){
		this.textAreaStdOut.setText(s);
	}

	public void setStdOut(StringBuffer sb){
		this.setStdOut(sb.toString());
	}

	public void appendStdOut(String s){
		this.textAreaStdOut.append(s);
	}

	public void appendStdOut(StringBuffer sb){
		this.appendStdOut(sb.toString());
	}

	public void appendErrOut(String s){
		this.textAreaErrOut.append(s);
	}

	PanelErr getPanelErr(){
		return panelErr;
	}
}
