package take.myUtility.cygwin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Grib2MainFrame extends JFrame {
	private Grib2MainController mc;

	private JPanel contentPane;
	private JTextField txtCcygwinhometaketotemptestgribbin;
	private JScrollPane scrollPane;
	JTextArea textArea;


	/**
	 * Create the frame.
	 */
	public Grib2MainFrame(Grib2MainController mc) {
		this.mc = mc;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		txtCcygwinhometaketotemptestgribbin = new JTextField();
		txtCcygwinhometaketotemptestgribbin.setText("C:\\cygwin\\home\\taketo\\temp\\test_grib2.bin");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtCcygwinhometaketotemptestgribbin, 24, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtCcygwinhometaketotemptestgribbin, 91, SpringLayout.WEST, contentPane);
		contentPane.add(txtCcygwinhometaketotemptestgribbin);
		txtCcygwinhometaketotemptestgribbin.setColumns(10);

		JLabel lblNewLabel = new JLabel("file name");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, txtCcygwinhometaketotemptestgribbin);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -17, SpringLayout.WEST, txtCcygwinhometaketotemptestgribbin);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtCcygwinhometaketotemptestgribbin, -6, SpringLayout.WEST, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, -1, SpringLayout.NORTH, txtCcygwinhometaketotemptestgribbin);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("読み込み");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 10, SpringLayout.SOUTH, txtCcygwinhometaketotemptestgribbin);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 291, SpringLayout.WEST, contentPane);
		btnNewButton_1.addActionListener(mc);
		contentPane.add(btnNewButton_1);

		scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -20, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);


	}

	public void setMc(Grib2MainController mc) {
		this.mc = mc;
	}

	public String getFileName(){
		return getTextField().getText();
	}
	public JTextField getTextField() {
		return txtCcygwinhometaketotemptestgribbin;
	}
	/**
	 * @return
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

}
