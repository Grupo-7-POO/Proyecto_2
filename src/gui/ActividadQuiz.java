package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JRadioButton;

public class ActividadQuiz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActividadQuiz frame = new ActividadQuiz();
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
	public ActividadQuiz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Quiz");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, BorderLayout.WEST);
		
		JButton btnNewButton = new JButton("Entregar");
		btnNewButton.setBackground(new Color(0, 0, 255));
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Calificar Encuesta");
		panel_2.add(btnNewButton_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Dificultad:");
		panel_2.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Res 1");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 10, SpringLayout.WEST, panel_1);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Res 2");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton_1, 6, SpringLayout.SOUTH, rdbtnNewRadioButton);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton_1, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Res 3");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton_2, 6, SpringLayout.SOUTH, rdbtnNewRadioButton_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton_2, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		panel_1.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Res 4");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton_3, 6, SpringLayout.SOUTH, rdbtnNewRadioButton_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton_3, 10, SpringLayout.WEST, panel_1);
		panel_1.add(rdbtnNewRadioButton_3);
		
		JTextPane txtpnPregunta = new JTextPane();
		txtpnPregunta.setBackground(new Color(192, 192, 192));
		txtpnPregunta.setText("Pregunta:");
		panel.add(txtpnPregunta, BorderLayout.NORTH);
	}
}
