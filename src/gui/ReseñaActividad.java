package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;

public class ReseñaActividad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReseñaActividad frame = new ReseñaActividad();
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
	public ReseñaActividad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Reseña");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JSlider slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(10);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(10);
		contentPane.add(slider, BorderLayout.CENTER);
		
		JTextPane txtpnEspacioParaEscribir = new JTextPane();
		txtpnEspacioParaEscribir.setText("Espacio para escribir reseña");
		contentPane.add(txtpnEspacioParaEscribir, BorderLayout.WEST);
		
		JButton btnNewButton = new JButton("Subir reseña");
		btnNewButton.setBackground(new Color(0, 0, 255));
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Dificultad: ");
		panel.add(lblNewLabel_1, BorderLayout.WEST);
		
		textField = new JTextField();
		panel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(199);
		panel.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(33);
		panel.add(verticalStrut_1, BorderLayout.SOUTH);
	}

}
