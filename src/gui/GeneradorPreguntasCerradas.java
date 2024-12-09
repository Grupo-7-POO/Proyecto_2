package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.preguntas.Opcion;
import modelo.preguntas.PreguntaCerrada;
import sistemabase.GeneradorPreguntas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

public class GeneradorPreguntasCerradas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton btnNewButton;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
	private ButtonGroup opcionesActividades;
	public PreguntaCerrada pregunta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneradorPreguntasCerradas frame = new GeneradorPreguntasCerradas();
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
	public GeneradorPreguntasCerradas() 
	{
		setModal(true);
		setTitle("Generador Preguntas Cerradas");
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enunciado de la Pregunta:");
		lblNewLabel.setBounds(10, 11, 215, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 36, 414, 58);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Explicación de la Pregunta:");
		lblNewLabel_1.setBounds(10, 105, 162, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 130, 414, 58);
		contentPane.add(textField_1);
		
		lblNewLabel_2 = new JLabel("Opcion A)");
		lblNewLabel_2.setBounds(10, 199, 55, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Opcion B)");
		lblNewLabel_3.setBounds(10, 224, 55, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Opcion C)");
		lblNewLabel_4.setBounds(10, 249, 55, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Opcion D)");
		lblNewLabel_5.setBounds(10, 274, 55, 14);
		contentPane.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(75, 196, 246, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(75, 221, 246, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(75, 246, 246, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(75, 271, 246, 20);
		contentPane.add(textField_5);
		
		btnNewButton = new JButton("Generar Pregunta");
		btnNewButton.setBounds(10, 309, 162, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener( this );
		
		rdbtnNewRadioButton = new JRadioButton("Correcta");
		rdbtnNewRadioButton.setBounds(327, 195, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener( this );
		
		rdbtnNewRadioButton_1 = new JRadioButton("Correcta");
		rdbtnNewRadioButton_1.setBounds(327, 220, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addActionListener( this );
		
		rdbtnNewRadioButton_2 = new JRadioButton("Correcta");
		rdbtnNewRadioButton_2.setBounds(327, 245, 109, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.addActionListener( this );
		
		rdbtnNewRadioButton_3 = new JRadioButton("Correcta");
		rdbtnNewRadioButton_3.setBounds(327, 270, 109, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.addActionListener( this );
		
		opcionesActividades = new ButtonGroup();
		opcionesActividades.add(rdbtnNewRadioButton_3);
		opcionesActividades.add(rdbtnNewRadioButton_2);
		opcionesActividades.add(rdbtnNewRadioButton_1);
		opcionesActividades.add(rdbtnNewRadioButton);
	}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
        

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == btnNewButton )
		{
			if ( textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() 
					|| textField_3.getText().isEmpty() || textField_4.getText().isEmpty() || textField_5.getText().isEmpty())
			{ JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE); }
			else 
			{
				if ( getSelectedButtonText( opcionesActividades ) == null) { JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE); }
				else
				{
					JTextField[] respuestas = new JTextField[] { textField_5, textField_4, textField_3, textField_2 };
					int i = 0;
					List<Opcion> opciones = new LinkedList<>();
					for (Enumeration<AbstractButton> buttons = opcionesActividades.getElements(); buttons.hasMoreElements();) 
					{
			            AbstractButton button = buttons.nextElement();
			            
			            Opcion opcionActual = new Opcion( respuestas[i].getText(), button.isSelected());
			            i ++;
			            opciones.add(opcionActual);
					}
		            PreguntaCerrada preguntaNueva = GeneradorPreguntas.generarPreguntaCerrada(textField.getText(), textField_1.getText(), opciones);
		            this.pregunta = preguntaNueva;
				}
			}
		}
	}
	}
