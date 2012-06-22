package take.myUtility.bumpy;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BumpyInterfaceFrame extends JFrame{

	BumpyInterfaceController bic;
	private JTextField textField;
	private JTextArea textAreaStdOut;
	private JTextArea textAreaErrOut;

	public BumpyInterfaceFrame(BumpyInterfaceController c) {
		bic = c;

		setSize(new Dimension(600, 500));
		setMinimumSize(new Dimension(300, 300));
		setTitle("Bumpy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 150, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 80, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -120, SpringLayout.SOUTH, getContentPane());
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

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 42, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 48, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 81, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 347, SpringLayout.WEST, getContentPane());
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("button");
		btnNewButton.addActionListener(bic);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 48, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, textField);
		getContentPane().add(btnNewButton);

	}

	String getCommandText(){
		return textField.getText();
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




}
