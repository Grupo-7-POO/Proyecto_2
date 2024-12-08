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
		
		JButton btnNewButton_1 = new JButton("Calificar Quiz(Reseña)");
		contentPane.add(btnNewButton_1, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JButton btnNewButton_2 = new JButton("Res_1");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_2, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_2, 10, SpringLayout.WEST, panel_1);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Res_2");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_3, 6, SpringLayout.SOUTH, btnNewButton_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_3, 0, SpringLayout.WEST, btnNewButton_2);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Res_3");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_4, 6, SpringLayout.SOUTH, btnNewButton_3);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_4, 0, SpringLayout.WEST, btnNewButton_2);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Res_4");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_5, 6, SpringLayout.SOUTH, btnNewButton_4);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_5, 0, SpringLayout.WEST, btnNewButton_2);
		panel_1.add(btnNewButton_5);
		
		JTextPane txtpnPregunta = new JTextPane();
		txtpnPregunta.setBackground(new Color(192, 192, 192));
		txtpnPregunta.setText("Pregunta:");
		panel.add(txtpnPregunta, BorderLayout.NORTH);
	}
}
