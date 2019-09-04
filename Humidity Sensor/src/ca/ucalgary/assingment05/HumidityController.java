package ca.ucalgary.assingment05;

public class HumidityController extends Thread {
    private double minHumidity;
    private double maxHumidity;
    private HumiditySensor sensor;
    private Greenhouse greenhouse;

    /** Constructor that initializes the sensor, the greenhouse and max-min humidity
     * @param pSensor  Humidity Sensor passed as a parameter
     * @param pGreenhouse  Greenhouse controller passed as a parameter
     */
    public HumidityController(HumiditySensor pSensor, Greenhouse pGreenhouse) {
        this.sensor = pSensor;
        this.greenhouse = pGreenhouse;
        this.minHumidity = pGreenhouse.getMinHumidity();
        this.maxHumidity = pGreenhouse.getMaxHumidity();

    }

    /** run method that decreases and increases humidity depending on max-min humidity
     */
    public void run() {

        while (true) {
            if (sensor.getHumidity() < minHumidity) {
                greenhouse.setHumidity(greenhouse.getHumidity() + greenhouse.getHumidityRate());
                greenhouse.logCurrentConditions();
            } else if (sensor.getHumidity() > maxHumidity) {
                greenhouse.setHumidity(greenhouse.getHumidity() - greenhouse.getHumidityRate());
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
