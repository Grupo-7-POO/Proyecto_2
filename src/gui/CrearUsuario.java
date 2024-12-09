package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import sistemabase.EstadoGlobal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

public class CrearUsuario extends JDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JCheckBox chckbxNewCheckBox;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JPasswordField passwordField;
	private char predeterminado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuario frame = new CrearUsuario();
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
	public CrearUsuario() 
	{
		setModal(true);
		setTitle("Crear Usuario");
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Creación de Usuario");
		lblNewLabel.setBounds(10, 11, 136, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setBounds(10, 36, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		lblNewLabel_2.setBounds(10, 61, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario:");
		lblNewLabel_3.setBounds(10, 86, 74, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contraseña:");
		lblNewLabel_4.setBounds(10, 111, 74, 14);
		contentPane.add(lblNewLabel_4);
		
		rdbtnNewRadioButton = new JRadioButton("Estudiante");
		rdbtnNewRadioButton.setBounds(208, 132, 96, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo de Usuario:");
		lblNewLabel_5.setBounds(10, 136, 95, 14);
		contentPane.add(lblNewLabel_5);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Profesor");
		rdbtnNewRadioButton_1.setBounds(111, 132, 95, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		textField = new JTextField();
		textField.setBounds(111, 36, 155, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(111, 58, 155, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(111, 83, 155, 20);
		contentPane.add(textField_2);
		
		ButtonGroup opciones = new ButtonGroup();
		opciones.add(rdbtnNewRadioButton);
		opciones.add(rdbtnNewRadioButton_1);
		
		btnNewButton = new JButton("Crear Usuario");
		btnNewButton.setBounds(10, 177, 136, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(272, 107, 32, 23);
		contentPane.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(111, 108, 155, 20);
		contentPane.add(passwordField);
		predeterminado = passwordField.getEchoChar();

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == btnNewButton )
		{
			String contrasena = new String ( passwordField.getPassword() );
			
			if ( textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || contrasena.isEmpty( ))
			{ JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE); }
			else
			{
				if ( rdbtnNewRadioButton.isSelected() )
				{
					Estudiante nuevoEstudiante = new Estudiante( textField.getText(), textField_1.getText(), textField_2.getText(), contrasena );
					EstadoGlobal.aniadirUsuario( nuevoEstudiante );	
				}
				else if ( rdbtnNewRadioButton_1.isSelected() )
				{
					Profesor nuevoProfesor = new Profesor( textField.getText(), textField_1.getText(), textField_2.getText(), contrasena );
					EstadoGlobal.aniadirUsuario( nuevoProfesor );
				}
				else { JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE); }
			} 
			dispose();
		}
		
		if ( chckbxNewCheckBox.isSelected() ) { passwordField.setEchoChar((char) 0); }
		else 								  { passwordField.setEchoChar( predeterminado ); }
		
		
	}
}