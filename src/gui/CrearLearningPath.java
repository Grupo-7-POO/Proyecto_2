package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.usuarios.Profesor;
import sistemabase.EstadoGlobal;
import sistemabase.GeneradorActividades;

import javax.swing.JLabel;
import java.util.LinkedList;
import java.util.List;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;


import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class CrearLearningPath extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JList<Actividad> listaActividades;
	private JList<Actividad> actividadesLearningPath;
	private DefaultListModel<Actividad> modelo1;
	private JTextField textField_3;
	private Profesor usuarioActual;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CrearEncuesta frame = new CrearEncuesta();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearLearningPath( Profesor profesor ) 
	{
		this.usuarioActual = profesor;
		setFont(new Font("Algerian", Font.BOLD, 12));
		setTitle("Crear Learning Path");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setBounds(10, 11, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion:");
		lblNewLabel_1.setBounds(10, 36, 78, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Duración:");
		lblNewLabel_3.setBounds(10, 61, 78, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Dificultad:");
		lblNewLabel_4.setBounds(10, 86, 78, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Actividades del Learning Path");
		lblNewLabel_6.setBounds(10, 125, 237, 14);
		contentPane.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(112, 8, 135, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 33, 135, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Principiante", "Intermedio", "Avanzado"}));
		comboBox.setBounds(112, 82, 135, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(this);
		
		btnNewButton_1 = new JButton("Generar Learning Path");
		btnNewButton_1.setBounds(10, 377, 237, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		// Informacion de actividades
		
		Actividad[] actividades = GeneradorActividades.getActividades().values().toArray( new Actividad[GeneradorActividades.getActividades().values().size()] );
		
		JPanel panel = new JPanel();
		panel.setBounds(257, 11, 217, 389);
		contentPane.add(panel);
		modelo1 = new DefaultListModel<Actividad>();
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 23, 188, 321);
		panel.add(scrollPane);
		
		listaActividades = new JList<Actividad>( actividades );
		scrollPane.setViewportView(listaActividades);     
		listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		
		JLabel lblNewLabel_9 = new JLabel("Archivo de Actividades: ");
		lblNewLabel_9.setBounds(10, 0, 166, 14);
		panel.add(lblNewLabel_9);
		
		btnNewButton = new JButton("Añadir");
		btnNewButton.setBounds(10, 355, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(112, 58, 135, 17);
		contentPane.add(textField_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 150, 237, 166);
		contentPane.add(scrollPane_1);
		
		actividadesLearningPath = new JList<Actividad>();
		scrollPane_1.setViewportView(actividadesLearningPath);
		
		btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Borrar todo");
		btnNewButton_3.setBounds(112, 327, 89, 23);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		btnNewButton_2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)     
	{
		
		if ( e.getSource() == btnNewButton_2 ) // Eliminar actividad seleccionada
		{
			int indice = actividadesLearningPath.getSelectedIndex();
			if (indice>=0) { modelo1.removeElementAt(indice); }
			else { JOptionPane.showMessageDialog(null, "Debe seleccionar un indice","Error", JOptionPane.ERROR_MESSAGE); }
		}
		
		if ( e.getSource() == btnNewButton_3 ) { modelo1.clear(); }
		
		if ( e.getSource() == btnNewButton )
		{
			modelo1.addElement( listaActividades.getSelectedValue() );
			actividadesLearningPath.setModel(modelo1);
		}
		
		if ( e.getSource() == btnNewButton_1 )
		{
			if ( textField.getText().isEmpty() || textField_1.getText().isEmpty() ||  
					textField_3.getText().isEmpty() || ( actividadesLearningPath.getModel().getSize() == 0 ) )
			{ JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE); }
			else
			{
				List<Actividad> actividades = new LinkedList<Actividad>();
			
				for ( int i = 0; i < actividadesLearningPath.getModel().getSize(); i++)
				{
					actividades.add(actividadesLearningPath.getModel().getElementAt(i));
				}
				int duracionEstimada = Integer.parseInt(textField_3.getText());
				
				LearningPath learningPathCreado = usuarioActual.crearLearningPath(textField.getText(), textField_1.getText(), (String)comboBox.getSelectedItem(), duracionEstimada, actividades); 
				EstadoGlobal.aniadirLearningPath(learningPathCreado);
				dispose();
			}
		}
	}
}
