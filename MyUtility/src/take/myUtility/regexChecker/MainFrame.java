package take.myUtility.regexChecker;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class MainFrame extends JFrame {
	private MainController controller;

	private JTextField tfRegex;
	private JTextField tfString;
	private JTextArea tpResult;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public String getRegex(){
		return tfRegex.getText();
	}

	public String getString(){
		return tfString.getText();
	}

	public void showResult(String s){
		tpResult.setText(s);
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		controller = new MainController(this);

		setTitle("正規表現checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JLabel lblNewLabel = new JLabel("正規表現");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 45, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("検索文字列");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 19, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		getContentPane().add(lblNewLabel_1);

		tfRegex = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, tfRegex, 32, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, tfRegex, 215, SpringLayout.EAST, lblNewLabel);
		getContentPane().add(tfRegex);
		tfRegex.setColumns(10);

		tfString = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, tfRegex, -16, SpringLayout.NORTH, tfString);
		springLayout.putConstraint(SpringLayout.NORTH, tfString, -3, SpringLayout.NORTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, tfString, 0, SpringLayout.WEST, tfRegex);
		springLayout.putConstraint(SpringLayout.EAST, tfString, 0, SpringLayout.EAST, tfRegex);
		getContentPane().add(tfString);
		tfString.setColumns(10);

		JButton btnMatch = new JButton("matches");
		btnMatch.addActionListener(controller);
		springLayout.putConstraint(SpringLayout.NORTH, btnMatch, 38, SpringLayout.SOUTH, tfString);
		springLayout.putConstraint(SpringLayout.WEST, btnMatch, 114, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnMatch);

		JButton btnFind = new JButton("find");
		btnFind.addActionListener(controller);
		springLayout.putConstraint(SpringLayout.NORTH, btnFind, 38, SpringLayout.SOUTH, tfString);
		springLayout.putConstraint(SpringLayout.WEST, btnFind, 48, SpringLayout.EAST, btnMatch);
		getContentPane().add(btnFind);

		tpResult = new JTextArea();
		springLayout.putConstraint(SpringLayout.WEST, tpResult, 50, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tpResult, -50, SpringLayout.EAST, getContentPane());
		tpResult.setFont(new Font("Monospaced", Font.PLAIN, 12));
		springLayout.putConstraint(SpringLayout.NORTH, tpResult, 20, SpringLayout.SOUTH, btnMatch);
		springLayout.putConstraint(SpringLayout.SOUTH, tpResult, -10, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(tpResult);
	}
}
