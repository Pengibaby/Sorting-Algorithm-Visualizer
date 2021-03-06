import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.JLabel;

public class MainWindow {

	private JFrame frame;
	private Visual_window newVisual;
	private String[] listOfSorts;
	private int dropDownSelection = 0;
	private int numberOfBars = 50;
	private int speed = 4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//MainWindow window = new MainWindow();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Sorting Algorithm Visualizer");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		newVisual = new Visual_window(numberOfBars);
		
		JComboBox comboBox = new JComboBox();
		listOfSorts = new String[5];
		listOfSorts[0] = "Bubble Sort";
		listOfSorts[1] = "Bucket Sort";
		listOfSorts[2] = "Insertion Sort";
		listOfSorts[3]= "Merge Sort";
		listOfSorts[4]= "Selection Sort";
		comboBox.setModel(new DefaultComboBoxModel(listOfSorts));
		comboBox.setBounds(10, 20, 200, 50);
		frame.getContentPane().add(comboBox);
		
		JButton startButton = new JButton("Start");
		startButton.setBounds(230, 20, 230, 50);
		frame.getContentPane().add(startButton);
		
		JButton resetButton = new JButton("Reset Array");
		resetButton.setBounds(479, 20, 230, 50);
		frame.getContentPane().add(resetButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 81, 1244, 589);
		frame.getContentPane().add(tabbedPane);
		tabbedPane.add(newVisual);
		
		JLabel numberOfBarsLabel = new JLabel("Number Of Bars");
		numberOfBarsLabel.setBounds(785, 19, 100, 14);
		frame.getContentPane().add(numberOfBarsLabel);
		
		JSlider numberOfBars_Slider = new JSlider(25, 200, 50);
		numberOfBars_Slider.setBounds(731, 40, 200, 50);
		numberOfBars_Slider.setMajorTickSpacing(25);
		numberOfBars_Slider.setMinorTickSpacing(5);
		numberOfBars_Slider.setPaintTicks(true);
		numberOfBars_Slider.setPaintLabels(true);
		numberOfBars_Slider.setSnapToTicks(true);
		frame.getContentPane().add(numberOfBars_Slider);
		
		JSlider speedSlider = new JSlider(1, 8, 4);
		speedSlider.setBounds(959, 40, 200, 50);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setSnapToTicks(true);
		frame.getContentPane().add(speedSlider);
		
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				speed = ((JSlider) arg0.getSource()).getValue();
				newVisual.setSpeed(speed);
			}
		});
		
		numberOfBars_Slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				numberOfBars = ((JSlider) arg0.getSource()).getValue();
				newVisual.resetArray(numberOfBars);
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dropDownSelection = comboBox.getSelectedIndex();
				resetButton.setEnabled(true);
			}
		});
		
		SortingAlgorithms allAlgorithms = new SortingAlgorithms();
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetButton.setEnabled(false);
				startButton.setEnabled(false);
				numberOfBars_Slider.setEnabled(false);
				if(dropDownSelection == 0) {
					allAlgorithms.bubbleSort(newVisual, numberOfBars);
				} else if(dropDownSelection == 1){
					allAlgorithms.bucket_sort(newVisual, numberOfBars);
				} else if(dropDownSelection == 2){
					allAlgorithms.insertion_sort(newVisual, numberOfBars);
				} else if(dropDownSelection == 3) {
					allAlgorithms.merge_sort(newVisual, numberOfBars);
				} else if(dropDownSelection == 4) {
					allAlgorithms.selection_sort(newVisual, numberOfBars);
				}
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newVisual.resetArray(numberOfBars);
				startButton.setEnabled(true);
				numberOfBars_Slider.setEnabled(true);
			}
		});
	}
}
