package ca.ucalgary.assingment05;

public class TemperatureSensor extends Thread{
    private double temperature;
    private Greenhouse greenhouse;
    /** Constructor that initializes greenhouse and temperature
     * @param pGreenhouse  Greenhouse controller passed as a parameter
     */
    public TemperatureSensor(Greenhouse pGreenhouse) {
        this.greenhouse = pGreenhouse;
        this.temperature = pGreenhouse.getTemperature();
    }
    /** run method that reads greenhouse temperature every 3 seconds
     */
    public void run(){
        while (true) {
            this.temperature = greenhouse.getTemperature();
            System.out.println("Current temperature: " + getTemperature());
            greenhouse.updateTemperatureDisplay(getTemperature());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                // TODO: Add catch code
                ie.printStackTrace();
            }
        }
    }
    /** method that gets and returns temperature from the sensor
     * @return temperature as read by the sensor
     */
    public double getTemperature(){
        return temperature;
    }
}
