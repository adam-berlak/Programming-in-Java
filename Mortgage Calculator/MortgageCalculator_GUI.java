package as4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MortgageCalculator_GUI extends JFrame{
    private JTextField amortization = new JTextField("0");
    private JTextField principle = new JTextField("0");
    private JTextField interestRate = new JTextField("0");
    private JButton confirmValues = new JButton("Would you like to confirm values?");
    private JLabel amortizationPrompt = new JLabel("Enter Amortization in months");
    private JLabel princplePrompt = new JLabel("Enter Principle");
    private JLabel interestRatePrompt = new JLabel("Enter Interest Rate as a decimal value");
    private JLabel result = new JLabel("no values entered");
    private JPanel myPanel;
    
    /** Constructor that creates, organizes and updates the user interface
     * 
     */
    public MortgageCalculator_GUI(){
        
        setVisible(true);   
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(10,3,3,3));
        
        pane.add(amortizationPrompt);
        pane.add(amortization);
        pane.add(princplePrompt);
        pane.add(principle);
        pane.add(interestRatePrompt);
        pane.add(interestRate);
        pane.add(confirmValues);
        pane.add(result);
        
    }
    /** Method that sets the Event Listener
     * @param Listener   An event listener passed as a parameter
     */

    public void setEventListener(ActionListener listener){
        confirmValues.addActionListener(listener);
    }
    /** Method that gets and returns the amortization
     * @return amortization as a string
     */
    
    public String getAmortization(){
    	return amortization.getText();
    }
    /** Method that gets and returns the principle amount loaned
     * @return principle amount as a string
     */
    public String getPrinciple(){
    	return principle.getText();
    }
    /** Method that gets and returns the interest rate
     * @return interest rate as a string
     */
    public String getInterestRate(){
    	return interestRate.getText();
    }
    /** Method that sets the output text of the variable result
     * @param pResult   the result specified through a parameter
     */
    public void setOutput(String pResult){
        result.setText(pResult);
    }
}
