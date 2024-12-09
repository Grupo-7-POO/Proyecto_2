package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.actividades.Actividad;
import modelo.actividades.Examen;
import modelo.preguntas.PreguntaAbierta;
import modelo.usuarios.Profesor;
import sistemabase.GeneradorActividades;

import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.text.ParseException;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class CrearExamen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;
	private JComboBox<Actividad> comboBox_1;
	private JComboBox<Actividad> comboBox_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JList<Actividad> listaPrerequisitos;
	private JList<Actividad> listaSeguimiento;
	private JList<PreguntaAbierta> listaPreguntas;
	private DefaultListModel<Actividad> modelo1;
	private DefaultListModel<Actividad> modelo2;
	private DefaultListModel<PreguntaAbierta> modelo3;
	private JScrollPane scrollPane_1;
	private JButton btnNewButton_2_1;
	private JButton btnNewButton_3_1;
	private JTextField textField_3;
	private JTextField txtDdmmyyyy;
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
	public CrearExamen( Profesor profesor ) 
	{
		this.usuarioActual = profesor;
		setFont(new Font("Algerian", Font.BOLD, 12));
		setTitle("Crear Examen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 11, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion:");
		lblNewLabel_1.setBounds(10, 36, 78, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Objetivo:");
		lblNewLabel_2.setBounds(10, 61, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Duración:");
		lblNewLabel_3.setBounds(10, 86, 78, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Dificultad:");
		lblNewLabel_4.setBounds(10, 111, 78, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha Limite:");
		lblNewLabel_5.setBounds(10, 136, 96, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Actividades PreRequisito:");
		lblNewLabel_6.setBounds(10, 161, 170, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Actividad Seguimiento:");
		lblNewLabel_7.setBounds(10, 186, 142, 14);
		contentPane.add(lblNewLabel_7);
		
		textField = new JTextField();
		textField.setBounds(112, 8, 135, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 33, 135, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 58, 135, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Principiante", "Intermedio", "Avanzado"}));
		comboBox.setBounds(112, 107, 135, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(this);
		
		btnNewButton = new JButton("Generar Preguntas");
		btnNewButton.addActionListener( this );
		btnNewButton.setBounds(10, 343, 142, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Generar Examen");
		btnNewButton_1.setBounds(10, 377, 142, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		// Informacion de actividades
		
		Actividad[] actividades = GeneradorActividades.getActividades().values().toArray( new Actividad[GeneradorActividades.getActividades().values().size()] );
	
		
		comboBox_1 = new JComboBox<Actividad>();
		comboBox_1.setModel(new DefaultComboBoxModel<Actividad>( actividades )); // Obtener actividades del MAPA
		comboBox_1.setBounds(168, 157, 79, 22);
		contentPane.add(comboBox_1);
		comboBox_1.addActionListener(this);

		
		comboBox_2 = new JComboBox<Actividad>();
		comboBox_2.setModel(new DefaultComboBoxModel<Actividad>( actividades )); // Obtener actividades del MAPA
		comboBox_2.setBounds(168, 182, 79, 22);
		contentPane.add(comboBox_2);
		comboBox_2.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBounds(257, 11, 217, 389);
		contentPane.add(panel);
		modelo1 = new DefaultListModel<Actividad>();
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 23, 188, 62);
		panel.add(scrollPane);
		
		listaPrerequisitos = new JList<Actividad>( );
		scrollPane.setViewportView(listaPrerequisitos);
		listaPrerequisitos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		
		JLabel lblNewLabel_9 = new JLabel("Actividades de Pre Requisito:");
		lblNewLabel_9.setBounds(10, 0, 166, 14);
		panel.add(lblNewLabel_9);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 155, 188, 22);
		panel.add(scrollPane_1);
		
		listaSeguimiento = new JList<Actividad>( );
		scrollPane_1.setViewportView(listaSeguimiento);
		modelo2 = new DefaultListModel<Actividad>();
		
		JLabel lblNewLabel_10 = new JLabel("Actividad de Seguimiento:");
		lblNewLabel_10.setBounds(10, 130, 166, 14);
		panel.add(lblNewLabel_10);
		
		btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.setBounds(10, 96, 89, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		btnNewButton_3 = new JButton("Borrar todo");
		btnNewButton_3.setBounds(109, 96, 89, 23);
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		
		btnNewButton_4 = new JButton("Borrar");
		btnNewButton_4.setBounds(10, 188, 89, 23);
		panel.add(btnNewButton_4);
		btnNewButton_4.addActionListener(this);

		
		JLabel lblNewLabel_11 = new JLabel("Preguntas: ");
		lblNewLabel_11.setBounds(10, 222, 188, 14);
		panel.add(lblNewLabel_11);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(10, 247, 188, 89);
		panel.add(scrollPane_2);
		
		listaPreguntas = new JList<PreguntaAbierta>();
		scrollPane_2.setViewportView(listaPreguntas);
		modelo3 = new DefaultListModel<PreguntaAbierta>();
		
		btnNewButton_2_1 = new JButton("Borrar");
		btnNewButton_2_1.setBounds(10, 347, 89, 23);
		panel.add(btnNewButton_2_1);
		btnNewButton_2_1.addActionListener(this);
		
		btnNewButton_3_1 = new JButton("Borrar todo");
		btnNewButton_3_1.setBounds(109, 347, 89, 23);
		panel.add(btnNewButton_3_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(112, 83, 135, 20);
		contentPane.add(textField_3);
		
		txtDdmmyyyy = new JTextField();
		txtDdmmyyyy.setText("dd/MM/yyyy");
		txtDdmmyyyy.setColumns(10);
		txtDdmmyyyy.setBounds(112, 133, 135, 20);
		contentPane.add(txtDdmmyyyy);
		btnNewButton_3_1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)     
	{
		if ( e.getSource() == comboBox_1 ) // buscador actividades de prerequisito
		{
			modelo1.addElement( (Actividad) comboBox_1.getSelectedItem() );
			listaPrerequisitos.setModel(modelo1);
		}
		
		if ( e.getSource() == btnNewButton_2 ) // Eliminar PReRequisito seleccionado
		{
			int indice = listaPrerequisitos.getSelectedIndex();
			if (indice>=0) { modelo1.removeElementAt(indice); }
			else { JOptionPane.showMessageDialog(null, "Debe seleccionar un indice","Error", JOptionPane.ERROR_MESSAGE); }
		}
		
		if ( e.getSource() == btnNewButton_3 ) { modelo1.clear(); } // Eliminar Todos los PReRequisitos
		
		if ( e.getSource() == btnNewButton_4 ){  modelo2.clear(); } // Eliminar Seguimiento
		
		if ( e.getSource() == comboBox_2 ) // Buscador actividad seguimiento
		{
			modelo2.clear();
			modelo2.addElement( (Actividad) comboBox_2.getSelectedItem() );
			listaSeguimiento.setModel(modelo2);
		}
		
		if ( e.getSource() == btnNewButton ) // Boton Generar Preguntas
		{
			GeneradorPreguntasAbiertas pregunta = new GeneradorPreguntasAbiertas();
			pregunta.setVisible(true);
			modelo3.addElement( pregunta.getPregunta() );
			System.out.println( pregunta.getPregunta() );
			listaPreguntas.setModel(modelo3);
		}	
		
		if ( e.getSource() == btnNewButton_2_1 ) // Eliminar pregunta seleccionada
		{
			int indice = listaPreguntas.getSelectedIndex();
			if (indice>=0) { modelo3.removeElementAt(indice); }
			else { JOptionPane.showMessageDialog(null, "Debe seleccionar un indice","Error", JOptionPane.ERROR_MESSAGE); }
		}
		
		if ( e.getSource() == btnNewButton_3_1 ) { modelo3.clear(); } // Eliminar todas las preguntas
		
		if ( e.getSource() == btnNewButton_1 )
		{
			if ( textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || 
					textField_3.getText().isEmpty() || txtDdmmyyyy.getText().isEmpty() )
			{ JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE); }
			else
			{
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date fechaLimite = null;
				List<Actividad> actividadesPre = new LinkedList<Actividad>();
				List<PreguntaAbierta> preguntasExamen = new LinkedList<PreguntaAbierta>();
				Actividad actividadSeguimiento = null;
				try 
				{
					
					for ( int i = 0; i < listaPrerequisitos.getModel().getSize(); i++)
					{
						actividadesPre.add(listaPrerequisitos.getModel().getElementAt(i));
					}
					
					if ( listaSeguimiento.getModel().getSize() > 0 ) { actividadSeguimiento = listaSeguimiento.getModel().getElementAt(0);}
					
					for ( int i = 0; i < listaPreguntas.getModel().getSize(); i++)
					{
						preguntasExamen.add(listaPreguntas.getModel().getElementAt(i));
					}
					fechaLimite = formatter.parse( txtDdmmyyyy.getText() );
					double duracionEstimada = Double.parseDouble(textField_3.getText());
					
					//Examen examenNuevo = GeneradorActividades.generarExamen(nombre, descripcion, objetivo, nivelDificultad, duracion, actividadesPre, actividadSeguimiento, fechaLimite, preguntasExamen);
					
					Examen examenNuevo = GeneradorActividades.generarExamen(textField.getText(), textField_1.getText(), textField_2.getText(), 
					(String)comboBox.getSelectedItem(), duracionEstimada, actividadesPre, actividadSeguimiento, fechaLimite, preguntasExamen);
					
					usuarioActual.aniadirActividadCreada( examenNuevo );
					dispose();
				} 
				catch (ParseException e1) 
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error de formato en los campos llenados", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
