package ca.ucalgary.assingment05;

public class HumiditySensor extends Thread{
    private double humidity;
    private Greenhouse greenhouse;
    /** Constructor that initializes greenhouse and humidity
     * @param pGreenhouse  Greenhouse controller passed as a parameter
     */
    public HumiditySensor(Greenhouse pGreenhouse) {
        this.greenhouse = pGreenhouse;
        this.humidity = pGreenhouse.getHumidity();
    }
    /** run method that reads greenhouse humidity every 3 seconds
     */
    public void run() {
        while (true) {
            this.humidity = greenhouse.getHumidity();
            System.out.println("Current humidity: " + getHumidity());
            greenhouse.updateHumidityDisplay(getHumidity());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                // TODO: Add catch code
                ie.printStackTrace();
            }
        }
    }
    /** method that gets and returns humidity from the sensor
     * @return humidity as read by the sensor
     */
    public double getHumidity(){
        return humidity;
    }
}
