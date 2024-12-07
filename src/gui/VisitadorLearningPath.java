package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JMenu;
import java.awt.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class VisitadorLearningPath {

	private JFrame frame;
	private JTextField txtVisualizadorLearningPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisitadorLearningPath window = new VisitadorLearningPath();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisitadorLearningPath() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtVisualizadorLearningPath = new JTextField();
		txtVisualizadorLearningPath.setHorizontalAlignment(SwingConstants.CENTER);
		txtVisualizadorLearningPath.setText("Visualizador Learning Path");
		txtVisualizadorLearningPath.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(txtVisualizadorLearningPath, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(txtVisualizadorLearningPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
		);
		
		JMenu mnNewMenu = new JMenu("Actividades");
		scrollPane.setRowHeaderView(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Actividad");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Actividad");
		mnNewMenu.add(mntmNewMenuItem);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Actividad_x");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Entregar Actividad");
		panel.add(btnNewButton, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción actividad:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("Calificar Actividad");
		panel.add(btnNewButton_1, BorderLayout.EAST);
		
		JButton btnNewButton_2 = new JButton("Calificar Learning Path");
		scrollPane.setColumnHeaderView(btnNewButton_2);
		frame.getContentPane().setLayout(groupLayout);
	}
}
