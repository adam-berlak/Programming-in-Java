package ca.ucalgary.assingment05;

public class TemperatureController extends Thread {
    private double minTemperature;
    private double maxTemperature;
    private TemperatureSensor sensor;
    private Greenhouse greenhouse;

    /** Constructor that initializes the sensor, the greenhouse and max-min temperatures
     * @param pSensor  Temperature Sensor passed as a parameter
     * @param pGreenhouse  Greenhouse controller passed as a parameter
     */
    public TemperatureController(TemperatureSensor pSensor, Greenhouse pGreenhouse) {
        this.sensor = pSensor;
        this.greenhouse = pGreenhouse;
        minTemperature = pGreenhouse.getMinTemperature();
        maxTemperature = pGreenhouse.getMaxTemperature();
    }

    /** run method that decreases or increases temperature depending on max-min temperatures
     */
    public void run() {


        while (true) {
            if (sensor.getTemperature() < minTemperature) {
                greenhouse.setTemperature(greenhouse.getTemperature() + greenhouse.getTemperatureRate());
                greenhouse.logCurrentConditions();

            }

            else if (sensor.getTemperature() > maxTemperature) {
                greenhouse.setTemperature(greenhouse.getTemperature() - greenhouse.getTemperatureRate());
                greenhouse.logCurrentConditions();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                // TODO: Add catch code
                ie.printStackTrace();
            }
        }
    }
}
