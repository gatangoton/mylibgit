package take.myUtility.cygwin;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private MainController mc;

	private JPanel contentPane;
	private JTextField textField;
	JTextArea textArea;
	JTextArea textArea_1;

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
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 18, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, textField);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, -1, SpringLayout.NORTH, textField);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("読み込み");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 291, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -340, SpringLayout.SOUTH, contentPane);
		btnNewButton_1.addActionListener(mc);
		contentPane.add(btnNewButton_1);

		textArea = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textArea, 9, SpringLayout.SOUTH, btnNewButton_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, textArea, 42, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textArea, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textArea, -350, SpringLayout.EAST, contentPane);
		contentPane.add(textArea);

		textArea_1 = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textArea_1, 9, SpringLayout.SOUTH, btnNewButton_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, textArea_1, 20, SpringLayout.EAST, textArea);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textArea_1, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textArea_1, -5, SpringLayout.EAST, contentPane);
		contentPane.add(textArea_1);
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

	public MainController getMc() {
		return mc;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextArea getTextArea_1() {
		return textArea_1;
	}
}
