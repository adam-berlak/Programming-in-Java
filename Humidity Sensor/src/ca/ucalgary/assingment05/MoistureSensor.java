package ca.ucalgary.assingment05;

public class MoistureSensor extends Thread{
    private double moisture;
    private Greenhouse greenhouse;
    /** Constructor that initializes greenhouse and moisture
     * @param pGreenhouse  Greenhouse controller passed as a parameter
     */
    public MoistureSensor(Greenhouse pGreenhouse) {
        this.greenhouse = pGreenhouse;
        this.moisture = pGreenhouse.getHumidity();
    }
    /** run method that reads greenhouse moisture every 3 seconds
     */
    public void run() {
        while (true) {
            this.moisture = greenhouse.getMoisture();
            System.out.println("Current moisture: " + getMoisture());
            greenhouse.updateMoistureDisplay(getMoisture());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                // TODO: Add catch code
                ie.printStackTrace();
            }
        }
    }
    /** method that gets and returns moisture from the sensor
     * @return moisture as read by the sensor
     */
    public double getMoisture(){
        return moisture;
    }
}