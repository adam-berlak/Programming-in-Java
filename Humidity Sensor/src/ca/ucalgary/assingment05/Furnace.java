package ca.ucalgary.assingment05;

public class Furnace extends Thread {

    private double temperature = 14.0;
    private GreenhouseGUI greenhouseGUI = null;

    public void run() {
        System.out.println("Hello from a thread!");
        //if ( temperature < jTextField1)
        
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
    
    public double measureTemperature() {
        return temperature;
    }

    public void setGreenhouseGUI(GreenhouseGUI greenhouse) {
        this.greenhouseGUI = greenhouse;
    }

    public GreenhouseGUI getGreenhouseGUI() {
        return greenhouseGUI;
    }
}
