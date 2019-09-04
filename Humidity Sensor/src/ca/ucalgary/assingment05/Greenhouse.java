package ca.ucalgary.assingment05;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Greenhouse {
   
    private double temperature = 18;
    private double minTemperature = 22;
    private double maxTemperature = 35;
    private double humidity = .05;
    private double minHumidity = .50;
    private double maxHumidity = .80;
    private double moisture = .5;
    private double minMoisture = .7;
    private double maxMoisture = .9;
    private TemperatureController tempController;
    private HumidityController humController;
    private MoistureController moistController;
    TemperatureSensor tempSensor;
    HumiditySensor humSensor;
    MoistureSensor moistSensor;
    public GreenhouseGUI greenhouseGUI;
    private File simulationLog = new File("simulation.log");
    private FileWriter simulationLogWriter;
    private String simulationLogStr = "";
    private double temperatureRate = 1;
    private double humidityRate = .01;
    private double moistureRate = .01;
    
    /** Greenhouse constructor that initializes a corresponding greenhouse GUI
     */
    public Greenhouse() {
        GreenhouseGUI greenhouseGUI = new GreenhouseGUI(this);
        setGreenhouseGUI(greenhouseGUI);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = greenhouseGUI.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        greenhouseGUI.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        greenhouseGUI.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        greenhouseGUI.setVisible(true);
    }
    /** Main method that creates and instance of the greenhouse, the sensors and starts them
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Greenhouse greenhouse = new Greenhouse();
      
        // sets up temperature threads
        TemperatureSensor tempSensor = new TemperatureSensor(greenhouse);
        greenhouse.setTempSensor(tempSensor);
        tempSensor.start();
        
        
        // sets up humidity threads
        HumiditySensor humSensor = new HumiditySensor(greenhouse);
        greenhouse.setHumSensor(humSensor);
        humSensor.start();
        
        // sets up moisture threads
        MoistureSensor moistSensor = new MoistureSensor(greenhouse);
        greenhouse.setMoistSensor(moistSensor);
        moistSensor.start();

    }
    /** Updates sensor text field in gui for the temperature
     * @param pMeasuredTemperature  parameter that specifies measured temperature
     */
    public void updateTemperatureDisplay(double pMeasuredTemperature){
        getGreenhouseGUI().getTemperatureDisplay().setText(Double.toString(pMeasuredTemperature));
    }
    /** Updates sensor text field in gui for the humidity
     * @param pMeasuredHumidity  parameter that specifies measured humidity
     */
    public void updateHumidityDisplay(double pMeasuredHumidity){
        getGreenhouseGUI().getHumidityDisplay().setText(Double.toString(pMeasuredHumidity));
    }
    /** Updates sensor text field in gui for the moisture
     * @param pMeasuredMoisture  parameter that specifies measured moisture
     */
    public void updateMoistureDisplay(double pMeasuredMoisture){
        getGreenhouseGUI().getMoistureDisplay().setText(Double.toString(pMeasuredMoisture));
    }
    /** Appends current conditions when called to the simulation log
     */
    public void logCurrentConditions(){
       
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        String currentTime = df.format(dateobj);

       
       String logString = currentTime + ": " + 
                          Double.toString(getMinTemperature()) + ", " + Double.toString(getMaxTemperature()) + ", " + Double.toString(getTemperature()) + ", " + 
                          Double.toString(getMinHumidity()) + ", " + Double.toString(getMaxHumidity()) + ", " + Double.toString(getHumidity()) + ", " + 
                          Double.toString(getMinMoisture()) + ", " + Double.toString(getMaxMoisture()) + ", " + Double.toString(getMoisture()) +"\r\n";
       
       setSimulationLogStr(getSimulationLogStr() + logString);
       System.out.println("Logging: ");
        System.out.println(getSimulationLogStr());
    }
    /** Writes simulation log to a file when called
     */
    public void saveLogToFile(){
        try {
            
            FileWriter logWriter = new FileWriter(getSimulationLog(), true);
            logWriter.write(getSimulationLogStr());
            System.out.println("Added to File: " );
            System.out.println(getSimulationLogStr());
            logWriter.flush();
            logWriter.close();

        } catch (IOException ioe) {
            // TODO: Add catch code
            ioe.printStackTrace();
        }
        
    }
    /** Opens simulation log from a file when called using notepad
     */
    public void openSimulationLog(){
        try {
            Runtime rs = Runtime.getRuntime();
             
                try {
                  rs.exec("notepad " + getSimulationLog().getName());
                }
                catch (IOException e) {
                  System.out.println(e);
                }  

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
    }    
    /** Get method for the current temperature
     * @return the current temperature
     */
    public double getTemperature(){
        return temperature;
    }
    /** Set method for the current temperature
     * @param pTemperature  the current temperature
     */
    public void setTemperature(double pTemperature){
        this.temperature = pTemperature;
    }
    /** Get method for the current min temperature
     * @return the current min temperature
     */
    public double getMinTemperature() {
        return minTemperature;
    }
    /** Set method for the current min temperature
     * @param pMinTemperature   the current min temperature
     */
    public void setMinTemperature(double pMinTemperature) {
        this.minTemperature = pMinTemperature;
    }
    /** Get method for the current max temperature
     * @return the current max temperature
     */
    public double getMaxTemperature() {
        return maxTemperature;
    }
    /** Set method for the current max temperature
     * @param pMaxTemperature   the current max temperature
     */
    public void setMaxTemperature(double pMaxTemperature) {
        this.maxTemperature = pMaxTemperature;
    }
    /** Get method for the current humidity
     * @return the current humidity
     */
    public double getHumidity() {
        return humidity;
    }
    /** Set method for the current humidity
     * @param pHumidity the current humidity
     */
    public void setHumidity(double pHumidity) {
        this.humidity = pHumidity;
    }
    /** Get method for the current min humidity
     * @return the current  min humidity
     */
    public double getMinHumidity() {
        return minHumidity;
    }
    /** Set method for the current min humidity
     * @param pMinHumidity  the current min humidity
     */
    public void setMinHumidity(double pMinHumidity) {
        this.minHumidity = pMinHumidity;
    }
    /** Get method for the current max humidity
     * @return the current max humidity
     */
    public double getMaxHumidity() {
        return maxHumidity;
    }
    /** Set method for the current max humidity
     * @param pMaxHumidity  the current max humidity
     */
    public void setMaxHumidity(double pMaxHumidity) {
        this.maxHumidity = pMaxHumidity;
    }
    /** Get method for the current moisture level
     * @return the current moisture level
     */
    public double getMoisture() {
        return moisture;
    }
    /** Set method for the current moisture level
     * @param pMoisture  the current moisture level
     */
    public void setMoisture(double pMoisture) {
        this.moisture = pMoisture;
    }
    /** Get method for the current min moisture level
     * @return the current min moisture level
     */
    public double getMinMoisture() {
        return minMoisture;
    }
    /** Set method for the current min moisture level
     * @param pMinMoisture  the current min moisture level
     */
    public void setMinMoisture(double pMinMoisture) {
        this.minMoisture = pMinMoisture;
    }
    /** Get method for the current max moisture level
     * @return the current max moisture level
     */
    public double getMaxMoisture() {
        return maxMoisture;
    }
    /** Set method for the current max moisture level
     * @param pMaxMoisture  the current max moisture level
     */
    public void setMaxMoisture(double pMaxMoisture) {
        this.maxMoisture = pMaxMoisture;
    }
    /** Get method for the temperature controller of the greenhouse
     * @return the temperature controller
     */
    public TemperatureController getTemperatureController() {
        return this.tempController;
    }
    /** Set method for the temperature controller of the greenhouse
     * @param pTempController   the temperature controller
     */
    public void setTemperatureController(TemperatureController pTempController) {
        this.tempController = pTempController;
    }
    /** Get method for the humidity controller of the greenhouse
     * @return the humidity controller
     */
    public HumidityController getHumidityController() {
        return this.humController;
    }
    /** Set method for the humidity controller of the greenhouse
     * @param pHumController  the humidity controller
     */
    public void setHumidityController(HumidityController pHumController) {
        this.humController = pHumController;
    }
    /** Get method for the moisture controller of the greenhouse
     * @return the moisture controller
     */
    public MoistureController getMoistureController() {
        return this.moistController;
    }
    /** Set method for the moisture controller of the greenhouse
     * @param pMoistController the moisture controller
     */
    public void setMoistureController(MoistureController pMoistController) {
        this.moistController = pMoistController;
    }
    /** Set method for the greenhouse GUI of the greenhouse
     * @param greenhouseGUI the greenhouse GUI
     */
    public void setGreenhouseGUI(GreenhouseGUI greenhouseGUI) {
        this.greenhouseGUI = greenhouseGUI;
    }
    /** Get method for the greenhouse GUI of the greenhouse
     * @return the greenhouse GUI
     */
    public GreenhouseGUI getGreenhouseGUI() {
        return greenhouseGUI;
    }
    /** Set method for the temperature sensor of the greenhouse
     * @param tempSensor the temperature sensor
     */
    public void setTempSensor(TemperatureSensor tempSensor) {
        this.tempSensor = tempSensor;
    }
    /** Get method for the temperature sensor of the greenhouse
     * @return the  temperature sensor
     */
    public TemperatureSensor getTempSensor() {
        return tempSensor;
    }
    /** Set method for the humidity sensor of the greenhouse
     * @param humSensor  the humidity sensor
     */
    public void setHumSensor(HumiditySensor humSensor) {
        this.humSensor = humSensor;
    }
    /** Get method for the humidity sensor of the greenhouse
     * @return the humidity sensor
     */
    public HumiditySensor getHumSensor() {
        return humSensor;
    }
    /** Set method for the moisture sensor of the greenhouse
     * @param moistSensor  the moisture sensor
     */
    public void setMoistSensor(MoistureSensor moistSensor) {
        this.moistSensor = moistSensor;
    }
    /** Get method for the moisture sensor of the greenhouse
     * @return the moisture sensor
     */
    public MoistureSensor getMoistSensor() {
        return moistSensor;
    }
    /** Set method for the simulation log of the greenhouse
     * @param simulationLog  the simulation log
     */
    public void setSimulationLog(File simulationLog) {
        this.simulationLog = simulationLog;
    }
    /** Get method for the simulation log of the greenhouse
     * @return the simulation log
     */
    public File getSimulationLog() {
        return simulationLog;
    }
    /** Set method for the simulation log of the greenhouse as a string
     * @param simulationLogStr  the simulation log as a string
     */
    public void setSimulationLogStr(String simulationLogStr) {
        this.simulationLogStr = simulationLogStr;
    }
    /** Get method for the simulation log of the greenhouse as a string
     * @return the simulation log as a string
     */
    public String getSimulationLogStr() {
        return simulationLogStr;
    }
    /** Set method for the simulation log writer of the greenhouse
     * @param simulationLogWriter  the simulation log writer
     */
    public void setSimulationLogWriter(FileWriter simulationLogWriter) {
        this.simulationLogWriter = simulationLogWriter;
    }
    /** Get method for the simulation log writer of the greenhouse
     * @return the simulation log writer
     */
    public FileWriter getSimulationLogWriter() {
        return simulationLogWriter;
    }
    /** Set method for the temperature rate of the greenhouse
     * @param temperatureRate  the temperature rate
     */
    public void setTemperatureRate(double temperatureRate) {
        this.temperatureRate = temperatureRate;
    }
    /** Get method for the temperature rate of the greenhouse
     * @return the temperature rate
     */
    public double getTemperatureRate() {
        return temperatureRate;
    }
    /** Set method for the humidity rate of the greenhouse
     * @param humidityRate  the humidity rate
     */
    public void setHumidityRate(double humidityRate) {
        this.humidityRate = humidityRate;
    }
    /** Get method for the humidity rate of the greenhouse
     * @return the humidity rate
     */
    public double getHumidityRate() {
        return humidityRate;
    }
    /** Set method for the moisture rate of the greenhouse
     * @param moistureRate  the moisture rate
     */
    public void setMoistureRate(double moistureRate) {
        this.moistureRate = moistureRate;
    }
    /** Get method for the moisture rate of the greenhouse
     * @return the moisture rate
     */
    public double getMoistureRate() {
        return moistureRate;
    }
}
