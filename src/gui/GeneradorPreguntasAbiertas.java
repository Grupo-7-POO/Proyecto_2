package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.preguntas.PreguntaAbierta;
import sistemabase.GeneradorPreguntas;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GeneradorPreguntasAbiertas extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	public PreguntaAbierta pregunta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GeneradorPreguntasAbiertas dialog = new GeneradorPreguntasAbiertas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GeneradorPreguntasAbiertas() 
	{
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnNewButton = new JButton("Generar Pregunta");
			btnNewButton.setBounds(10, 199, 162, 23);
			contentPanel.add(btnNewButton);
			btnNewButton.addActionListener(this);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(10, 130, 414, 58);
			contentPanel.add(textField);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Explicación de la Pregunta:");
			lblNewLabel_1.setBounds(10, 105, 162, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(10, 36, 414, 58);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblNewLabel = new JLabel("Enunciado de la Pregunta:");
			lblNewLabel.setBounds(10, 11, 192, 14);
			contentPanel.add(lblNewLabel);
		}
	}
	
	public PreguntaAbierta getPregunta()
	{
		return this.pregunta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == btnNewButton )
		{
			if ( textField.getText().isEmpty() || textField_1.getText().isEmpty() )
			{
				JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				PreguntaAbierta preguntaNueva = GeneradorPreguntas.generarPreguntaAbierta(textField.getText(), textField_1.getText());
				this.pregunta = preguntaNueva;
				dispose();
			}
		}
	}

}
