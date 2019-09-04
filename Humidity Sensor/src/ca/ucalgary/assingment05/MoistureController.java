package ca.ucalgary.assingment05;

public class MoistureController extends Thread {
    private double minMoisture;
    private double maxMoisture;
    private MoistureSensor sensor;
    private Greenhouse greenhouse;

    /** Constructor that initializes the sensor, the greenhouse and max-min moistures
     * @param pSensor  Moisture Sensor passed as a parameter
     * @param pGreenhouse  Greenhouse controller passed as a parameter
     */
    public MoistureController(MoistureSensor pSensor, Greenhouse pGreenhouse) {
        this.greenhouse = pGreenhouse;
        this.minMoisture = pGreenhouse.getMinMoisture();
        this.maxMoisture = pGreenhouse.getMaxMoisture();
        this.sensor = pSensor;
    }

    /** run method that decreases and increases moisture depending on max-min moisture
     */
    public void run() {

        while (true) {
            if (sensor.getMoisture() < minMoisture) {
                greenhouse.setMoisture(greenhouse.getMoisture() + greenhouse.getMoistureRate());
                greenhouse.logCurrentConditions();
            } else if (sensor.getMoisture() > maxMoisture) {
                greenhouse.setMoisture(greenhouse.getMoisture() - greenhouse.getMoistureRate());
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
