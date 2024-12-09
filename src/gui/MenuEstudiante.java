package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.usuarios.Estudiante;
import sistemabase.EstadoGlobal;
import sistemabase.GeneradorActividades;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;

public class MenuEstudiante extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Estudiante usuarioActual;
	private JComboBox<LearningPath> comboBox;
	private JComboBox<LearningPath> comboBox_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MenuEstudiante window = new MenuEstudiante();
					//window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuEstudiante( Estudiante estudiante) {
		this.usuarioActual = estudiante;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		LearningPath[] learningPaths = EstadoGlobal.getLearningPaths().values().toArray( new LearningPath[EstadoGlobal.getLearningPaths().values().size()] );
		LearningPath learningPathsInscrito = usuarioActual.getLearningPathInscrito();

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Menú Estudiante");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Learning Paths Inscritos");
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Todos Los Learning Paths");
		panel_2.add(lblNewLabel_2, BorderLayout.NORTH);
		
		
		comboBox = new JComboBox<LearningPath>(); // inscritos
		comboBox.setModel(new DefaultComboBoxModel<LearningPath>(new LearningPath[] {learningPathsInscrito}));
		panel_1.add(comboBox, BorderLayout.CENTER);
		

		comboBox_1 = new JComboBox<LearningPath>();
		comboBox_1.setModel(new DefaultComboBoxModel<LearningPath>( learningPaths ));// todos
		panel_2.add(comboBox_1, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == comboBox )
		{
			
		}
		if ( e.getSource() == comboBox_1 )
		{
			
		}
		
	}

}
