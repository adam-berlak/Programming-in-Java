package as4;
import java.awt.event.*;
public class Controller {
    
    //declares Mortgage Caluclator and GUI
    private MortgageCalculator calculator;
    private MortgageCalculator_GUI view;
    
    /** Constructor that uses inputs from user receieved from the gui and updates the gui using values from the calculator
    * @param pView the gui passed as a parameter
    * @param pCalculator the calculator passed as a parameter
    */
    public Controller(MortgageCalculator_GUI pView, MortgageCalculator pCalculator) {
        // initializes view and calculator 
        view = pView;
        calculator = pCalculator;
        
        // sets event listener for the gui
        view.setEventListener(new ActionListener(){
            
            /** Action listener method that is called when gui button is pressed
            */
            public void actionPerformed(ActionEvent e){
                view.setTitle("Button Clicked");
                // recieves inputs from gui and uses MortgageCalculator to calculate values
                double amortization = Double.parseDouble(view.getAmortization());
                System.out.println("Amortization: "+amortization);
                calculator.setAmortization(amortization);
                
                double principle = Double.parseDouble(view.getPrinciple());
                calculator.setPrinciple(principle);
                System.out.println("Principle: "+principle);
                
                double interestRate = Double.parseDouble(view.getInterestRate());
                calculator.setInterestRate(interestRate);
                System.out.println("Interest rate: "+interestRate);
                
                // updates output of the gui
                view.setOutput("Monthly Payment: " + calculator.getMonthlyPayment() + " Total Tnterest: "
                + calculator.getTotalInterest() + " Total Payment: " + calculator.getTotal()); 
                
                // prints calculated information
                System.out.println("Monthly Payment: " + calculator.getMonthlyPayment() + " Total Tnterest: "
                + calculator.getTotalInterest() + " Total Payment: " + calculator.getTotal() + " Ratio: " 
                + calculator.getRatio() + " Interest Rate per Year: " + calculator.getInterestPerYear()
                + " Interest per Month: " + calculator.getInterestPerMonth() + " Amortization in Years: " + 
                calculator.getAmortizationInYears());
            }
        });
        
    }
    /** Main method that initializes mortgageCalculator the Gui and the controller
    * @param pView the gui passed as a parameter
    * @param pCalculator the calculator passed as a parameter
    */
    public static void main(String[] args){
        MortgageCalculator calculator = new MortgageCalculator();
        MortgageCalculator_GUI view = new MortgageCalculator_GUI();
        Controller testController = new Controller(view, calculator);
    }

}

