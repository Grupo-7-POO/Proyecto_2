package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextPane;
import java.awt.Color;

public class ActividadRecursoEducativo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActividadRecursoEducativo frame = new ActividadRecursoEducativo();
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
	public ActividadRecursoEducativo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Nombre de recurso");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Entregar");
		btnNewButton.setBackground(new Color(0, 0, 255));
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JTextPane txtpnDescripcin = new JTextPane();
		sl_panel.putConstraint(SpringLayout.NORTH, txtpnDescripcin, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, txtpnDescripcin, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, txtpnDescripcin, -78, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, txtpnDescripcin, -10, SpringLayout.EAST, panel);
		txtpnDescripcin.setText("Descripción:");
		panel.add(txtpnDescripcin);
		
		JLabel lblNewLabel_1 = new JLabel("URL");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, txtpnDescripcin);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 68, SpringLayout.SOUTH, txtpnDescripcin);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, 120, SpringLayout.WEST, panel);
		panel.add(lblNewLabel_1);
	}
}
