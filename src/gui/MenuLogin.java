package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import modelo.usuarios.Usuario;
import sistemabase.EstadoGlobal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MenuLogin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnCrearUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstadoGlobal.cargarSistemaGlobal();
					MenuLogin frame = new MenuLogin();
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
	public MenuLogin() 
	{	
		setTitle("Inicio de Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inicio de Sesión");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 264, 14);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Iniciar Sesión");
		btnNewButton.setBounds(10, 127, 116, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.setBounds(158, 127, 116, 23);
		contentPane.add(btnCrearUsuario);
		btnCrearUsuario.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setBounds(10, 44, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña:");
		lblNewLabel_1_1.setBounds(10, 87, 67, 14);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 84, 153, 20);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(87, 41, 153, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btnNewButton) // INICIAR SESION
		{
			String login = textField.getText();
			String password = new String ( passwordField.getPassword() );
			
			if ( ( login.isEmpty() ) || ( password.isEmpty() ) )  { JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE); }
			else 
			{
				if ( EstadoGlobal.validarLogin(login, password) )
				{					
					Usuario usuario = (Usuario) EstadoGlobal.getUsuario( login );
					if (  usuario instanceof Profesor ) 
					{
						Profesor profesor = (Profesor) usuario; 
						MenuProfesor menuProfesor = new MenuProfesor( profesor );
						menuProfesor.setVisible(true);
						dispose();
					}
					else if ( usuario instanceof Estudiante)
					{
						Estudiante estudiante = (Estudiante) usuario;
						MenuEstudiante menuEstudiante = new MenuEstudiante( estudiante );
						menuEstudiante.setVisible(true);
						dispose();
	   				}
				}  
				else { JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE); }
			}
		}
		
		if (e.getSource() == btnCrearUsuario )
		{
			CrearUsuario nuevoUsuario = new CrearUsuario();
			nuevoUsuario.setVisible(true);
		}
	}
}