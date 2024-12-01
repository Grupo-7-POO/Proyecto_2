package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class testjframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLoremImpsumDolor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testjframe frame = new testjframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testjframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		txtLoremImpsumDolor = new JTextField();
		txtLoremImpsumDolor.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoremImpsumDolor.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		txtLoremImpsumDolor.setForeground(new Color(0, 0, 0));
		txtLoremImpsumDolor.setText("Lorem Impsum dolor?");
		contentPane.add(txtLoremImpsumDolor);
		txtLoremImpsumDolor.setColumns(14);
	}

}
