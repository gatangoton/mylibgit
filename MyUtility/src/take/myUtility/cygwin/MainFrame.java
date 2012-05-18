package take.myUtility.cygwin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	private MainController mc;

	private JPanel contentPane;
	private JTextField textField;
	private JTextPane textPane;
	private JTextPane textPane_1;


	/**
	 * Create the frame.
	 */
	public MainFrame(MainController mc) {
		this.mc = mc;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 24, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 91, SpringLayout.WEST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("file name");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -17, SpringLayout.WEST, textField);
		contentPane.add(lblNewLabel);

		textPane = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPane, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textPane, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPane, 500, SpringLayout.WEST, contentPane);
		contentPane.add(textPane);

		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, -1, SpringLayout.NORTH, textField);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("読み込み");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 291, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -17, SpringLayout.NORTH, textPane);
		btnNewButton_1.addActionListener(mc);
		contentPane.add(btnNewButton_1);

		textPane_1 = new JTextPane();
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, textPane_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPane_1, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textPane_1, 5, SpringLayout.EAST, textPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textPane_1, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPane_1, -10, SpringLayout.EAST, contentPane);
		contentPane.add(textPane_1);
	}

	public void setMc(MainController mc) {
		this.mc = mc;
	}

	public String getFileName(){
		return getTextField().getText();
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextPane getTextPane() {
		return textPane;
	}
	public JTextPane getTextPane_1() {
		return textPane_1;
	}
}
