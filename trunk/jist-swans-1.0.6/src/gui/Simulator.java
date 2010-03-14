package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Simulator extends JFrame implements ActionListener {

	private static final String LBL_END_TIME 			= "End time";
	private static final String LBL_RESOLUTION 			= "Resolution";
	private static final String LBL_START_TIME 			= "Start time";
	private static final String LBL_NUMBERS_OF_NODES	= "Numbers of nodes";
	
	private static final String EXIT				 = "Exit";
	private static final String STOP_SIMULATION 	= "Stop simulation";
	private static final String PAUSE_SIMULATION 	= "Pause simulation";
	private static final String START_SIMULATION 	= "Start simulation";
	private static final String SIMULATOR_TITLE 	= "AODV protocol simulator";
	private static final String SIMULATORS_CONFIG 	= "Simulators configurations";
	private static final String SIMULATION_AREA 	= "Simulation area";
	
	private static final int GRID_COLUMNS = 2;
	private static final int GRID_ROWS = 1;
	private static final int GRID_VGAP = 5;
	private static final int GRID_HGAP = 5;

	private static final String[] buttonCaptions = { START_SIMULATION, PAUSE_SIMULATION, STOP_SIMULATION, EXIT};
	private static final String[] labelsCaptions = { LBL_NUMBERS_OF_NODES, LBL_START_TIME, LBL_RESOLUTION, LBL_END_TIME};
	/**
	 * 
	 */
	private static final long serialVersionUID = -7230682643620581408L;
	
	public Simulator( ) {
		JPanel jpnlMaster = new JPanel( new GridLayout( GRID_ROWS, GRID_COLUMNS));
		getContentPane( ).add( jpnlMaster, BorderLayout.PAGE_START);
		
		//Create panel with simulation area field
		JPanel jpnlSimulation = createWorkinPanel( SIMULATION_AREA);
		jpnlSimulation.setBackground(Color.white);
		jpnlSimulation.setPreferredSize( new Dimension( 400,400));
		jpnlMaster.add( jpnlSimulation);
		
		//Create panel with simulations controls.
		JPanel jpnlConfig = createWorkinPanel( SIMULATORS_CONFIG);
		jpnlConfig.setLayout( new BorderLayout( ));
		jpnlMaster.add( jpnlConfig);
		
		//Create input text boxes
		JPanel jpnlInputs = new JPanel( new GridLayout( 2, 4, GRID_HGAP, GRID_VGAP));
		createInputField(jpnlInputs);
		jpnlConfig.add( jpnlInputs, BorderLayout.PAGE_START);
		
		//Create buttons for control panel
		createCtrlButtons(jpnlConfig);
		
		// Setup and make JFrame visible.
		setupInitialProperties();
		pack( );
		setVisible(true);
	}

	private void createInputField(JPanel jpnlInputs) {
		for ( int i = 0; i < labelsCaptions.length; ++i) {
			JLabel $ 	= new JLabel( labelsCaptions[i]);
			jpnlInputs.add( $);
			JTextField $txt = new JTextField( );
			jpnlInputs.add( $txt);
			$.setLabelFor( $txt);
		}
	}

	private void createCtrlButtons(JPanel jpnlConfig) {
		final JPanel jpnlButtons = new JPanel( new GridLayout( 1, 4, GRID_HGAP, GRID_VGAP));
		for ( int i = 0; i < buttonCaptions.length; ++i) {
			JButton $ = new JButton( buttonCaptions[i]);
			$.setActionCommand( buttonCaptions[i]);
			jpnlButtons.add( $);
		}
		jpnlConfig.add( jpnlButtons, BorderLayout.PAGE_END);
	}

	private void setupInitialProperties() {
		setDefaultCloseOperation( EXIT_ON_CLOSE);
		setTitle( SIMULATOR_TITLE);
	}

	private JPanel createWorkinPanel( String caption) {
		JPanel $ = new JPanel( );
		TitledBorder title = BorderFactory.createTitledBorder( caption);
		title.setTitlePosition( TitledBorder.TOP);
		$.setBorder(title);
		return $;
	}
	
	public static void main(String[] args) {
		final Simulator sim = new Simulator( );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
