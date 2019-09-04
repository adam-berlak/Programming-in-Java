package as4;
import java.lang.Math;
public class MortgageCalculator {
	private double amortization;
	private double principle;
	private double interestRate;
        
        /** Method that sets the amortization through a variable passed as a parameter
        * @param pMonths   amortization months passed as a parameter
        */
	public void setAmortization(double pMonths){
	this.amortization = pMonths;
	}
        /** Method that sets the principle amount loaned through a variable passed as a parameter
        * @param pPrinciple   principle amount loaned passed as a parameter
        */
	public void setPrinciple(double pPrinciple){
	this.principle = pPrinciple;
	}
        /** Method that sets the interest rate through a variable passed as a parameter
        * @param pInterestRate   interest rate passed as a parameter
        */
	public void setInterestRate(double pInterestRate){
	this.interestRate = pInterestRate;
	}
        /** Method that calculates and returns the monthly payment
        * @return the monthly payment as a double
        */
	public double getMonthlyPayment(){
        double i = Math.pow(interestRate/2 + 1, 2.0/12.0) - 1;
	return principle * i / (1 - Math.pow(i + 1,-amortization));
	}
        /** Method that calculates and returns the total interest paid
        * @return the total interest paid as a double
        */
	public double getTotalInterest(){
	return getTotal() - principle;
	}
        /** Method that calculates and returns the total amount paid
        * @return the total amount paid as a double
        */
        public double getTotal(){
        return getMonthlyPayment() * amortization;
        }
        /** Method that calculates and returns the ratio between total interest and principle loaned
        * @return the ration between total interest and principle as a double
        */
	public double getRatio(){
	return getTotalInterest()/principle;
	}
        /** Method that calculates and returns the interest paid per year
        * @return interest paid per year
        */
	public double getInterestPerYear(){
	return getTotalInterest()/getAmortizationInYears();
	}
        /** Method that calculates and returns the interest paid per month
        * @return the total paid per month as a double
        */
        public double getInterestPerMonth(){
        return getTotalInterest()/amortization;
        }
        /** Method that calculates and returns the amortization in years
        * @return the amortization in years
        */
        public double getAmortizationInYears(){
        return amortization / 12;
        }
}