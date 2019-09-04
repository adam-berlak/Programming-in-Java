// Assignment 1 by Adam Berlak, ID: 30008230,
// imports scanner, used to receive input from the user
import java.util.Scanner;
// creates a class called grades
public class Grades {

    // Declaring variables that will be used in the methods
    int mSum = 0;
    int mMax = -1;
    int mMin = -1;
    int mGrade = -1;
    int mTotal = 0;
    double mAverage = 0;
    String mCourseCode;
    
    // creates a main function that will automatically be called when the program is run
    
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        Grades grades = new Grades();

        // prompts the user for the course code and sets course code as the input
        System.out.println("Enter Course Code: ");
        String input = userInput.nextLine();
        grades.setCourseCode(input);
        
        // declares variables used in the recursion process of the while loop and prompts the user to enter grades
        boolean exit = false;
        int inputInt = -1;
        
        System.out.println("Please Enter Grades. Enter -1 to exit.");
        
        while(!exit)
        {   // prompts user for an input
            input = userInput.nextLine();
            inputInt = Integer.parseInt(input);
            // if input is -1 while loop will exit
            if (inputInt == -1)
            {    
                exit = true;
                
            }
            // checks if integer value entered is within 0 and 100
            else if (inputInt <= 0 || inputInt >= 100)
            {
                System.out.println("Incorrect value. Number out of bounds.");    
            }    
            else 
            {   // checks the entered value for any of the required properties and updates variable
                if (inputInt > grades.getMax())
                {
                    grades.setMax(inputInt);    
                } 
                if (grades.getMin() == -1 || inputInt < grades.getMin())
                {
                    grades.setMin(inputInt);    
                }
                grades.setSum(grades.getSum() + inputInt);
                grades.setTotal(grades.getTotal()+1);
                grades.setAverage((double)grades.getSum()/(double)grades.getTotal());
            }  
            
            
        }
        // prints variables after the user has exited the while loop
        System.out.println("Course Code:" + grades.getCourseCode());
        System.out.println("Grade Max:" + grades.getMax());
        System.out.println("Grade Min:" + grades.getMin());
        System.out.println("Grade Sum:" + grades.getSum());
        System.out.println("Grade Total:" + grades.getTotal());
        System.out.println("Grade Average:" + grades.getAverage());
  
    }
    // methods meant to return and update the values declared above

    public void setSum(int pSum) {
        this.mSum = pSum;
    }

    public int getSum() {
        return mSum;
    }

    public void setMax(int pMax) {
        this.mMax = pMax;
    }

    public int getMax() {
        return mMax;
    }

    public void setGrade(int pGrade) {
        this.mGrade = pGrade;
    }

    public int getGrade() {
        return mGrade;
    }

    public void setCourseCode(String pCourseCode) {
        this.mCourseCode = pCourseCode;
    }

    public String getCourseCode() {
        return mCourseCode;
    }

    public void setMin(int pMin) {
        this.mMin = pMin;
    }

    public int getMin() {
        return mMin;
    }
    public int getTotal(){
        return mTotal;
    }
    public void setTotal(int pTotal){
        this.mTotal = pTotal;
    }
    public double getAverage(){
        return mAverage;
    }
    public void setAverage(double pAverage){
        this.mAverage = pAverage;
    }
    
}
