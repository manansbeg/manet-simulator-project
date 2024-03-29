package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Simulator extends JFrame implements ActionListener, ChangeListener {

	private static final String RADIO_BTN_RANDOM_WALK 	= "RandomWalk";
	private static final String RADIO_BTN_WAYPOINT 		= "Waypoint";
	private static final String RADIO_BTN_TELEPORT 		= "Teleport";
	private static final String RADIO_BTN_STATIC 		= "Static";
	
	private static final String LBL_END_TIME 			= "End time";
	private static final String LBL_RESOLUTION 			= "Resolution";
	private static final String LBL_START_TIME 			= "Start time";
	private static final String LBL_NUMBERS_OF_NODES	= "Numbers of nodes";
	private static final String LBL_FIELD_SIZE			= "Field size";
	
	private static final String EXIT				= "Exit";
	private static final String STOP_SIMULATION 	= "Stop simulation";
	private static final String PAUSE_SIMULATION 	= "Pause simulation";
	private static final String START_SIMULATION 	= "Start simulation";
	
	private static final String SIMULATOR_TITLE 	= "AODV protocol simulator";
	private static final String SIMULATORS_CONFIG 	= "Simulators configurations";
	private static final String SIMULATION_AREA 	= "Simulation area";
	private static final String MOBILITY_MODELS_BORDER_TITLE = "Mobility models";
	
	private static final int GRID_COLUMNS = 2;
	private static final int GRID_ROWS = 1;
	private static final int GRID_VGAP = 5;
	private static final int GRID_HGAP = 5;

	private static final String[] buttonCaptions = { START_SIMULATION, PAUSE_SIMULATION, STOP_SIMULATION, EXIT};
	private static final String[] labelsCaptions = { LBL_FIELD_SIZE, LBL_NUMBERS_OF_NODES, 
		LBL_START_TIME, LBL_RESOLUTION, LBL_END_TIME};
	private static final String[] radioCaptions	 = { RADIO_BTN_STATIC, RADIO_BTN_TELEPORT, 
		RADIO_BTN_WAYPOINT, RADIO_BTN_RANDOM_WALK};
	
	// Add action listener for buttons
	private final static Map<String, ActionListener> actionCommands = new HashMap<String, ActionListener>( ) {
		private static final long serialVersionUID = 6406256726812169345L;
		//Static initialization
		{
			// TODO: Should we add show statistic button as well?
			// Start simulation click
			put( START_SIMULATION, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO: Need collect simulation parameters and 
					// start simulation. Probably need to add inputs 
					// to collect more properties for simulation startup
				}
			});
			// Pause simulation click
			put( PAUSE_SIMULATION, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			// Stop simulation click
			put( STOP_SIMULATION, new ActionListener( )
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			//On exit click
			put( EXIT, new ActionListener( )
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame root = (JFrame)SwingUtilities.getRoot((JComponent) e.getSource( ));
					root.dispose( );
				}
			}); 
		}
	};
	
	private static final long serialVersionUID = -7230682643620581408L;
	
	public Simulator( ) {
		final JPanel jpnlMaster = new JPanel( );
		getContentPane( ).add( jpnlMaster, BorderLayout.PAGE_START);
		
		//Create panel with simulation area field
		final JPanel jpnlSimulation = createWorkingPanel( SIMULATION_AREA);
		jpnlSimulation.setBackground( Color.white);
		jpnlSimulation.setPreferredSize( new Dimension( 400,400));
		
		//Create panel with simulations controls.
		final JPanel jpnlConfig = createConfigPanel( );
		
		//Create main panel layout group manager
		final GroupLayout grpMasterPanels = new GroupLayout( jpnlMaster);
		jpnlMaster.setLayout( grpMasterPanels);
		grpMasterPanels.setHorizontalGroup( grpMasterPanels.createSequentialGroup( )
				.addGroup( grpMasterPanels.createParallelGroup(Alignment.LEADING)
						.addComponent(jpnlSimulation))
				.addGroup( grpMasterPanels.createParallelGroup(Alignment.LEADING)
						.addComponent(jpnlConfig)));
		grpMasterPanels.setVerticalGroup( grpMasterPanels.createSequentialGroup( )
				.addGroup( grpMasterPanels.createParallelGroup(Alignment.BASELINE)
						.addComponent(jpnlSimulation)
						.addComponent(jpnlConfig)));
		// Setup and make JFrame visible.
		setupInitialProperties();
		pack( );
		setVisible(true);
	}

	//TODO: Extract to additional class
	private JPanel createConfigPanel() {
		final JPanel $ = createWorkingPanel( SIMULATORS_CONFIG);
		//Create input text boxes
		final JPanel jpnlInputs = new JPanel( new GridLayout( (int)Math.ceil( (double)labelsCaptions.length/2 ), 4, GRID_HGAP, GRID_VGAP));
		createInputField(jpnlInputs);
		final JPanel jpnlMobility = new JPanel(  new BorderLayout( ));
		createMobilityRadioBtn( jpnlMobility);
		//Create buttons for control panel
		final JPanel jpnlButtons = new JPanel( new GridLayout( 1, 4, GRID_HGAP, GRID_VGAP));
		createCtrlButtons(jpnlButtons);
		final GroupLayout grpConfigPanel = new GroupLayout( $);
		$.setLayout( grpConfigPanel);
		grpConfigPanel.setHorizontalGroup( grpConfigPanel.createSequentialGroup( )
				.addGroup( grpConfigPanel.createParallelGroup( Alignment.LEADING)
						.addComponent( jpnlInputs)
						.addComponent(jpnlMobility)
						.addComponent( jpnlButtons)));
		grpConfigPanel.setVerticalGroup( grpConfigPanel.createSequentialGroup( )
						.addComponent( jpnlInputs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						          GroupLayout.PREFERRED_SIZE)
						.addComponent(jpnlMobility)
						.addComponent( jpnlButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						          GroupLayout.PREFERRED_SIZE));
		return $;
	}

	private void createMobilityRadioBtn(JPanel jpnlCenter) {
		final JPanel jpn$ = new JPanel( new GridLayout( 4, 1));
		final ButtonGroup grp$ = new ButtonGroup( );
		for( int i = 0; i < radioCaptions.length; ++i) {
			final JRadioButton $ = new JRadioButton( radioCaptions[i]);
			if ( i == 0)
				$.setSelected( true);
			$.addChangeListener( this);
			grp$.add( $);
			jpn$.add( $);
		}
		final TitledBorder t = BorderFactory.createTitledBorder( MOBILITY_MODELS_BORDER_TITLE);
		t.setTitlePosition( TitledBorder.TOP);
		jpnlCenter.add( jpn$, BorderLayout.PAGE_START);
		jpnlCenter.setBorder( t);
	}

	private void createInputField(JPanel jpnlInputs) {
		for ( int i = 0; i < labelsCaptions.length; ++i) {
			final JLabel $ = new JLabel( labelsCaptions[i]);
			jpnlInputs.add( $);
			final JTextField $txt = new JTextField( );
			jpnlInputs.add( $txt);
			$.setLabelFor( $txt);
		}
	}

	private void createCtrlButtons(JPanel jpnlButtons) {
		for ( int i = 0; i < buttonCaptions.length; ++i) {
			JButton $ = new JButton( buttonCaptions[i]);
			$.setActionCommand( buttonCaptions[i]);
			$.addActionListener( this);
			jpnlButtons.add( $);
		}
	}

	private void setupInitialProperties() {
		setDefaultCloseOperation( EXIT_ON_CLOSE);
		setTitle( SIMULATOR_TITLE);
	}

	private JPanel createWorkingPanel( String caption) {
		final JPanel $ = new JPanel( );
		final TitledBorder title = BorderFactory.createTitledBorder( caption);
		title.setTitlePosition( TitledBorder.TOP);
		$.setBorder(title);
		return $;
	}
	
	public static void main(String[] args) {
		final Simulator sim = new Simulator( );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionCommands.get( e.getActionCommand()).actionPerformed( e);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}

}
