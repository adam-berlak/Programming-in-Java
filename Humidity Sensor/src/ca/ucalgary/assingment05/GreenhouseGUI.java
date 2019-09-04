package ca.ucalgary.assingment05;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusAdapter;

import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class GreenhouseGUI extends JFrame {
    private BorderLayout layoutMain = new BorderLayout();
    private JPanel panelCenter = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JLabel statusBar = new JLabel();
    private JToolBar toolBar = new JToolBar();
    private JButton buttonOpen = new JButton();
    private JButton buttonSave = new JButton();
    private JButton buttonHelp = new JButton();
    private ImageIcon imageOpen = new ImageIcon(GreenhouseGUI.class.getResource("openfile.gif"));
    private ImageIcon imageClose = new ImageIcon(GreenhouseGUI.class.getResource("closefile.gif"));
    private ImageIcon imageHelp = new ImageIcon(GreenhouseGUI.class.getResource("help.gif"));
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JButton tempStart = new JButton();
    private JButton tempStop = new JButton();
    private Greenhouse greenhouse;
    private JTextField jTextField3 = new JTextField();
    private JTextField jTextField4 = new JTextField();
    private JLabel jLabel4 = new JLabel();
    private JTextField jTextField5 = new JTextField();
    private JTextField jTextField6 = new JTextField();
    private JLabel jLabel5 = new JLabel();
    private JTextField jTextField7 = new JTextField();
    private JTextField jTextField8 = new JTextField();
    private JTextField jTextField9 = new JTextField();
    private JButton humStart = new JButton();
    private JButton humStop = new JButton();
    private JButton moistStart = new JButton();
    private JButton moistStop = new JButton();
    private JLabel jLabel6 = new JLabel();
    private JTextField jTextField10 = new JTextField();
    private JTextField jTextField11 = new JTextField();
    private JTextField jTextField12 = new JTextField();
    private JLabel jLabel7 = new JLabel();
    private JTextField jTextField13 = new JTextField();
    private JTextField jTextField14 = new JTextField();
    private JTextField jTextField15 = new JTextField();
    private JLabel jLabel8 = new JLabel();
    /** Constructor for greenhouse gui
     */
    public GreenhouseGUI() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** Constructor for the greenhouse gui that sets the greenhouse enviroment for the gui
     * @param pGreenhouse  the greenhouse
     */
    public GreenhouseGUI(Greenhouse pGreenhouse) {
        this();
        setGreenhouse(pGreenhouse);
        initializeTextFields();
    }
    /** Method that generates the user interface 
     */
    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        this.getContentPane().setLayout( layoutMain );
        panelCenter.setLayout( null );
        panelCenter.setBounds(new Rectangle(0, 26, 800, 223));
        this.setSize(new Dimension(605, 300));
        this.setTitle( "Assignment 5 - Greenhouse Simulation" );
        this.setBounds(new Rectangle(10, 10, 800, 300));
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText( "Help" );
        menuHelpAbout.setText( "About" );
        menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        statusBar.setText( "" );
        buttonOpen.setToolTipText( "Open Simulation Log..." );
        buttonOpen.setIcon( imageOpen );
        buttonOpen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openLog_actionPerformed(e);
                }
            });
        buttonSave.setToolTipText( "Save Conditions..." );
        buttonSave.setIcon( imageClose );
        buttonSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveLog_actionPerformed(e);
                }
            });
        
        
        buttonHelp.setToolTipText( "About" );
        buttonHelp.setIcon( imageHelp );
        jLabel1.setText("Temperature:");
        jLabel1.setBounds(new Rectangle(20, 40, 80, 15));
        jLabel2.setText("Minimum");
        jLabel2.setBounds(new Rectangle(120, 15, 45, 15));
        jLabel3.setText("Maximum");
        jLabel3.setBounds(new Rectangle(215, 15, 65, 15));
        jTextField1.setBounds(new Rectangle(100, 35, 80, 20));
        jTextField1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jTextField1_actionPerformed(e);
                }
            });
        jTextField2.setBounds(new Rectangle(205, 35, 80, 20));
        
        // Buttons that start and stop temperature threads
        tempStart.setText("Start");
        tempStart.setBounds(new Rectangle(310, 35, 75, 21));
        tempStart.setToolTipText("Starting temperature controller..."); 
        tempStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tempStart_actionPerformed(e);
                }
            });
        tempStop.setText("Stop");
        tempStop.setBounds(new Rectangle(400, 35, 75, 21));
        tempStop.setToolTipText("Stopping temperature controller...");
        tempStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tempStop_actionPerformed(e);
                }
            });
        // Buttons that start and stop humidity threads
        humStart.setText("Start");
        humStart.setBounds(new Rectangle(310, 70, 75, 21));
        humStart.setToolTipText("Starting humidity controller...");
        humStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    humStart_actionPerformed(e);
                }
            });
        humStop.setText("Stop");
        humStop.setBounds(new Rectangle(400, 70, 75, 21));
        humStop.setToolTipText("Stopping humidity controller...");
        humStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    humStop_actionPerformed(e);
                }
            });
        // Buttons that start and stop moisture threads
        moistStart.setText("Start");
        moistStart.setBounds(new Rectangle(310, 105, 75, 21));
        moistStart.setToolTipText("Starting moisture controller...");
        moistStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    moistStart_actionPerformed(e);
                }
            });
        moistStop.setText("Stop");
        moistStop.setBounds(new Rectangle(400, 105, 75, 21));
        moistStop.setToolTipText("Stopping moisture controller...");
        moistStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    moistStop_actionPerformed(e);
                }
            });
        // JLabels and Texfields are given attributes
        jLabel6.setText("Sensor");
        jLabel6.setBounds(new Rectangle(510, 15, 55, 15));
        jTextField10.setBounds(new Rectangle(590, 35, 75, 20));
        jTextField10.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    jTextField10_focusLost(e);
                }
            });
        jTextField11.setBounds(new Rectangle(590, 70, 75, 20));
        jTextField11.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    jTextField11_focusLost(e);
                }
            });
        jTextField12.setBounds(new Rectangle(590, 105, 75, 20));
        jTextField12.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    jTextField12_focusLost(e);
                }
            });
        jLabel7.setText("Override");
        jLabel7.setBounds(new Rectangle(600, 15, 50, 15));
        jTextField13.setBounds(new Rectangle(680, 35, 75, 20));
        jTextField14.setBounds(new Rectangle(680, 70, 75, 20));
        jTextField15.setBounds(new Rectangle(680, 105, 75, 20));
        jLabel8.setText("Rate");
        jLabel8.setBounds(new Rectangle(700, 15, 34, 14));
        jTextField3.setBounds(new Rectangle(495, 35, 80, 20));

        jTextField4.setBounds(new Rectangle(100, 70, 80, 20));
        jLabel4.setText("Humidity:");
        jLabel4.setBounds(new Rectangle(20, 75, 60, 15));
        jTextField5.setBounds(new Rectangle(205, 70, 80, 20));
        jTextField6.setBounds(new Rectangle(495, 70, 80, 20));
        jLabel5.setText("Moisture:");
        jLabel5.setBounds(new Rectangle(20, 105, 55, 15));
        jTextField7.setBounds(new Rectangle(100, 105, 80, 20));
        jTextField8.setBounds(new Rectangle(205, 105, 80, 20));
        jTextField9.setBounds(new Rectangle(495, 105, 80, 20));

        panelCenter.add(jLabel8, null);
        panelCenter.add(jTextField15, null);
        panelCenter.add(jTextField14, null);
        panelCenter.add(jTextField13, null);
        panelCenter.add(jLabel7, null);
        panelCenter.add(jTextField12, null);
        panelCenter.add(jTextField11, null);
        panelCenter.add(jTextField10, null);
        panelCenter.add(jLabel6, null);
        panelCenter.add(moistStop, null);
        panelCenter.add(moistStart, null);
        panelCenter.add(humStop, null);
        panelCenter.add(humStart, null);
        panelCenter.add(jTextField9, null);
        panelCenter.add(jTextField8, null);
        panelCenter.add(jTextField7, null);
        panelCenter.add(jLabel5, null);
        panelCenter.add(jTextField6, null);
        panelCenter.add(jTextField5, null);
        panelCenter.add(jLabel4, null);
        panelCenter.add(jTextField4, null);
        panelCenter.add(jTextField3, null);
        panelCenter.add(tempStop, null);
        panelCenter.add(tempStart, null);
        panelCenter.add(jTextField2, null);
        panelCenter.add(jTextField1, null);
        panelCenter.add(jLabel3, null);
        panelCenter.add(jLabel2, null);
        panelCenter.add(jLabel1, null);
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        toolBar.add(buttonOpen);
        toolBar.add(buttonSave);
        toolBar.add(buttonHelp);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);

        this.getContentPane().add( panelCenter, BorderLayout.CENTER );
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }
    
    /** Actionlistener method for the temperature start button
     */
    private void tempStart_actionPerformed(ActionEvent e) {
        greenhouse.setMinTemperature(Double.parseDouble(jTextField1.getText()));
        greenhouse.setMaxTemperature(Double.parseDouble(jTextField2.getText()));
        greenhouse.setTemperatureRate(Double.parseDouble(jTextField13.getText()));
        tempStart.setEnabled(false);
        tempStop.setEnabled(true);
        
        TemperatureController tempController = new TemperatureController(greenhouse.getTempSensor(),greenhouse);
        greenhouse.setTemperatureController(tempController);  
        
        getGreenhouse().getTemperatureController().start();
    }
    /** Actionlistener method for the temperature stop button
     */
    private void tempStop_actionPerformed(ActionEvent e) {
        tempStart.setEnabled(true);
        tempStop.setEnabled(false);
        getGreenhouse().getTemperatureController(). stop();
    }
    /** Actionlistener method for the humidity start button
     */
    private void humStart_actionPerformed(ActionEvent e) {
        greenhouse.setMinHumidity(Double.parseDouble(jTextField4.getText()));
        greenhouse.setMaxHumidity(Double.parseDouble(jTextField5.getText()));
        greenhouse.setHumidityRate(Double.parseDouble(jTextField14.getText()));
        humStart.setEnabled(false);
        humStop.setEnabled(true);
        
        HumidityController humController = new HumidityController(greenhouse.getHumSensor(),greenhouse);
        greenhouse.setHumidityController(humController);  
        
        getGreenhouse().getHumidityController().start();
    }
    /** Actionlistener method for the humidity stop button
     */
    private void humStop_actionPerformed(ActionEvent e) {
        humStart.setEnabled(true);
        humStop.setEnabled(false);
        getGreenhouse().getHumidityController(). stop();
    }
    /** Actionlistener method for the moisture start button
     */
    private void moistStart_actionPerformed(ActionEvent e) {
        greenhouse.setMinMoisture(Double.parseDouble(jTextField7.getText()));
        greenhouse.setMaxMoisture(Double.parseDouble(jTextField8.getText()));
        greenhouse.setMoistureRate(Double.parseDouble(jTextField15.getText()));
        moistStart.setEnabled(false);
        moistStop.setEnabled(true);
        
        MoistureController moistController = new MoistureController(greenhouse.getMoistSensor(),greenhouse);
        greenhouse.setMoistureController(moistController);  
        
        getGreenhouse().getMoistureController().start();
    }
    /** Actionlistener method for the moisture stop button
     */
    private void moistStop_actionPerformed(ActionEvent e) {
        moistStart.setEnabled(true);
        moistStop.setEnabled(false);
        getGreenhouse().getMoistureController(). stop();
    }
    /** Actionlistener method for the save file button
     */
    private void saveLog_actionPerformed(ActionEvent e) {
        getGreenhouse().saveLogToFile(); 
    }
    /** Actionlistener method for the open file button
     */
    private void openLog_actionPerformed(ActionEvent e) {
        getGreenhouse().openSimulationLog(); 
    }  
    /** Actionlistener method for the help button
     */
    void helpAbout_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new GreenhouseGUI_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);
    }

    private void jTextField1_actionPerformed(ActionEvent e) {
    }
    /** Initializes the text fields for before a user inputs their own values
     */
    public void initializeTextFields(){
        jTextField1.setText(Double.toString(greenhouse.getMinTemperature()));
        jTextField2.setText(Double.toString(greenhouse.getMaxTemperature()));
        jTextField3.setText(Double.toString(greenhouse.getTemperature()));
        jTextField4.setText(Double.toString(greenhouse.getMinHumidity()));
        jTextField5.setText(Double.toString(greenhouse.getMaxHumidity()));
        jTextField6.setText(Double.toString(greenhouse.getHumidity()));
        jTextField7.setText(Double.toString(greenhouse.getMinMoisture()));
        jTextField8.setText(Double.toString(greenhouse.getMaxMoisture()));
        jTextField9.setText(Double.toString(greenhouse.getMoisture()));
        jTextField13.setText(Double.toString(greenhouse.getTemperatureRate()));
        jTextField14.setText(Double.toString(greenhouse.getHumidityRate()));
        jTextField15.setText(Double.toString(greenhouse.getMoistureRate()));
    }
    
    /** Set method for the greenhouse of the greenhouse GUI
     * @param greenhouse  the greenhouse environment
     */
    public void setGreenhouse(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }
     /** Get method for the greenhouse of the greenhouse GUI
      * @return the greenhouse environment
      */
    public Greenhouse getGreenhouse() {
        return greenhouse;
    }

    /** Get method for the greenhouse current temperature as displayed by the textfield
     * @return jTextfield3  the current temperature
     */
    public JTextField getTemperatureDisplay() {
        return jTextField3;
    }
    /** Get method for the greenhouse current humidity as displayed by the textfield
     * @return jTextfield6  the current humidity
     */
    public JTextField getHumidityDisplay() {
        return jTextField6;
    }
    /** Get method for the greenhouse current moisture as displayed by the textfield
     * @return jTextfield9  the current moisture
     */
    public JTextField getMoistureDisplay() {
        return jTextField9;
    }
    /** Focus event for the current temperature override text field, updates current temperature field when you tab out
     */
    private void jTextField10_focusLost(FocusEvent e) {
        greenhouse.setTemperature(Double.parseDouble(jTextField10.getText()));
    }
    /** Focus event for the current humidity override text field, updates current humidity field when you tab out
     */
    private void jTextField11_focusLost(FocusEvent e) {
        greenhouse.setHumidity(Double.parseDouble(jTextField11.getText()));
    }
    /** Focus event for the current moisture override text field, updates current moisture field when you tab out
     */
    private void jTextField12_focusLost(FocusEvent e) {
        greenhouse.setMoisture(Double.parseDouble(jTextField12.getText()));
    }
}
