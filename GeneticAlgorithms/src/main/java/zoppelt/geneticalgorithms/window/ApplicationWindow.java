package zoppelt.geneticalgorithms.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JSlider;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import zoppelt.geneticalgorithms.classes.Simulation;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class ApplicationWindow {

	private JFrame frame;
	private JSpinner crossoverSpinner;
	private JSpinner mutationSpinner;
	private JTextField fitnessFunctionField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
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
	public ApplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("# of Generations");
		lblNewLabel.setBounds(10, 20, 82, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("# of Chromosomes");
		lblNewLabel_1.setBounds(10, 54, 91, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Length of Chromosome");
		lblNewLabel_2.setBounds(10, 88, 111, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Probability of Crossover");
		lblNewLabel_3.setBounds(10, 122, 115, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Probability of Mutation");
		lblNewLabel_4.setBounds(10, 156, 108, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Selection Method");
		lblNewLabel_5.setBounds(10, 190, 82, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fitness Function");
		lblNewLabel_6.setBounds(10, 224, 82, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBounds(131, 0, 146, 261);
		frame.getContentPane().add(controlsPanel);
		controlsPanel.setLayout(null);
		
		JSpinner generationsSpinner = new JSpinner();
		generationsSpinner.setModel(new SpinnerNumberModel(50, 50, 2000, 10));
		generationsSpinner.setBounds(10, 14, 126, 20);
		controlsPanel.add(generationsSpinner);
		
		JSpinner chromosomeAmtSpinner = new JSpinner();
		chromosomeAmtSpinner.setModel(new SpinnerNumberModel(5, 5, 2000, 1));
		chromosomeAmtSpinner.setBounds(10, 48, 126, 20);
		controlsPanel.add(chromosomeAmtSpinner);
		
		JSpinner chromosomeLgthSpinner = new JSpinner();
		chromosomeLgthSpinner.setModel(new SpinnerNumberModel(1, 1, 64, 1));
		chromosomeLgthSpinner.setBounds(10, 82, 126, 20);
		controlsPanel.add(chromosomeLgthSpinner);
		
		crossoverSpinner = new JSpinner();
		crossoverSpinner.setModel(new SpinnerNumberModel(Double.valueOf(0.5), Double.valueOf(0.01), Double.valueOf(0.99), Double.valueOf(0.01)));
		crossoverSpinner.setBounds(10, 116, 126, 20);
		controlsPanel.add(crossoverSpinner);
		
		mutationSpinner = new JSpinner();
		mutationSpinner.setModel(new SpinnerNumberModel(Double.valueOf(0.001), Double.valueOf(0.001), Double.valueOf(0.0091), Double.valueOf(0.001)));
		mutationSpinner.setBounds(10, 150, 126, 20);
		controlsPanel.add(mutationSpinner);
		
		fitnessFunctionField = new JTextField();
		fitnessFunctionField.setText("15x-x^2");
		fitnessFunctionField.setBounds(10, 220, 126, 20);
		controlsPanel.add(fitnessFunctionField);
		fitnessFunctionField.setColumns(10);
		
		JComboBox selectionMethodBox = new JComboBox();
		selectionMethodBox.setModel(new DefaultComboBoxModel(new String[] {"TestMethod"}));
		selectionMethodBox.setBounds(10, 184, 126, 22);
		controlsPanel.add(selectionMethodBox);
		
		JPanel miscPanel = new JPanel();
		miscPanel.setBounds(276, 126, 158, 135);
		frame.getContentPane().add(miscPanel);
		miscPanel.setLayout(null);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(276, 0, 158, 126);
		frame.getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(null);
		
		JButton runSimButton = new JButton("Run Simulation");
		runSimButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Populate list with information from the Application Window
				List<Object> simulationParameters = getComponentInformation(controlsPanel.getComponents());	
				
				//Create simulation and run it
				Simulation sim = new Simulation();
				runSimButton.setEnabled(false);
				sim.runSimulation(simulationParameters);
				runSimButton.setEnabled(true);
				
			}
		});
		runSimButton.setBounds(34, 14, 89, 23);
		buttonsPanel.add(runSimButton);
		
		JButton loadDataButton = new JButton("Load Data");
		loadDataButton.setBounds(34, 51, 89, 23);
		buttonsPanel.add(loadDataButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(34, 88, 89, 23);
		buttonsPanel.add(clearButton);
	}
	
	//Gets component information from application window
	public List<Object> getComponentInformation(Component[] windowComponents) {
		List<Object> simulationParameters = new ArrayList<Object>();
		
		//Loop through components and gather data for simulation
		for(Component component : windowComponents) {
			if(component instanceof JSpinner) {
				JSpinner spinner = (JSpinner)component;
				simulationParameters.add(spinner.getValue());
			}
			else if(component instanceof JTextField) {
				JTextField textField = (JTextField)component;
				simulationParameters.add(textField.getText());
			}
			else if(component instanceof JComboBox) {
				simulationParameters.add("test");
				
//				JComboBox comboBox = (JComboBox)component;
//				simulationParameters.add(comboBox.getModel().getSelectedItem().toString());
//				System.out.print(comboBox.getModel().getSelectedItem().toString());
			}
		}
	
		return simulationParameters;
	}
}
