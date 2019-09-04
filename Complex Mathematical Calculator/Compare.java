import java.util.Scanner;
// creates public class 'Compare'
public class Compare {
    // declares variables
    String number1;
    String number2;
    String greater;
    String sum;
    String difference;
    // creates main method that is called upon creation of Compare object
    public static void main(String[] args){
        
        Scanner userInput = new Scanner(System.in); // creates a Scanner object used to get user input
        Compare compare = new Compare(); // creates Compare object called compare
        
        System.out.println("Enter number 1"); // prompts the user to enter the first number 
        String input = userInput.nextLine(); // creates input String object that saves the users input
        compare.setNumber1(input); // calls setNumber1 mutator method to set variable number1
        System.out.println("Number 1 is:" + compare.getNumber1()); // prints out the users input by calling accessor method of number1
        
        System.out.println("Enter number 2"); // prompts the user to enter the second number
        input = userInput.nextLine(); // updates inout String object with new input value
        compare.setNumber2(input); // calls setNumber2 mutator method to set variable number2
        System.out.println("Number 2 is:" + compare.getNumber2()); // prints out the users input by calling accessor method of number2
        
        compare.calcGreater(compare.getNumber1(), compare.getNumber2()); // calls calcGreater method with the two numbers as parameters
        compare.calcSum(compare.getNumber1(), compare.getNumber2()); // calls calcSum method with the two numbers as parameters
        if (compare.number1 == compare.getGreater()){ // if statment that checks if number1 is the greater number
            compare.calcDifference(compare.getNumber1(), compare.getNumber2()); // if so, subtract number2 from number 1 by calling calcDifference
        }
        else { // else statment executed if number1 is not the greater number
            compare.calcDifference(compare.getNumber2(), compare.getNumber1()); // subtract number1 from number 2 by calling calcDifference
        }
        System.out.println("Greater number is:" + compare.getGreater()); // print out the greater number by calling greater accessor method
        System.out.println("Sum number is:" + compare.getSum()); // print out the sum by calling sum accessor method
        System.out.println("Difference number is:" + compare.getDifference()); // print out difference by calling difference accessor method
    }
    public void calcSum(String pNumber1, String pNumber2){ // method that calculates the sum of two numbers taken as parameters
        // pre-conditions: pNumber1, pNumber2, intSum = "", decSum = "", smallSum = "", carryValue = "", i = pNumber1.length() - 1, and j = pNumber2.length() - 1
        // post-conditions: i < 0, j < 0, totalSum correctly represents the sum of number1 and number2
        String intSum = ""; // declares and initializes intSum, the sum of the integer portion of the number
        String decSum = ""; // declares and initializes decSum, the sum of the decimals portion of the number
        short smallSum = 0; // declares and initializes smallSum, a temporary sum value of two small numbers
        short carryValue = 0; // declares and initializes carryValue, a number that is carried
        short i = (short) (pNumber1.length() - 1); // declares and initializes i, the loop variant used for accessing elements of pNumber1
        short j = (short) (pNumber2.length() - 1); // declares and initializes j, the loop variant used for accessing elements of pNumber2
        while(i > pNumber1.indexOf(".")| j > pNumber2.indexOf(".")){ // while statement that loops while the loop variants are greater than the index of "."
            if ((i - pNumber1.indexOf(".")) == (j - pNumber2.indexOf("."))){ // executes if i and j are the same distance from "."
                smallSum = (short) (Short.parseShort("" + pNumber1.charAt(i)) + Short.parseShort("" + pNumber2.charAt(j)) + carryValue); // calculates sum of two numbers
                carryValue = 0; // sets carryValue to 0
                if (smallSum > 10){ // if the sum is greater than 10, set carryValue to 1, and subtract 10 from the sum
                    carryValue = 1; // sets carryValue to 1
                    smallSum = (short)(smallSum - 10); // subtracts smallSum by 10
                }
                decSum = smallSum + decSum; // update decSum by appending decSum to the new sum
                i--; // decrement i by 1
                j--; // decrement j by 1
            }
            else if ((i - pNumber1.indexOf(".")) > (j - pNumber2.indexOf("."))){ // executes if i is farther from "." than j
                smallSum = (short) (Short.parseShort("" + pNumber1.charAt(i)) + carryValue); // calculates sum of character in number 1 at i with 0
                carryValue = 0; // sets carryValue to 0
                if (smallSum > 10){ // if the sum is greater than 10, set carryValue to 1, and subtract 10 from the sum
                    carryValue = 1; // sets carryValue to 1
                    smallSum = (short)(smallSum - 10); // subtracts smallSum by 10
                }
                decSum = smallSum + decSum; // update decSum by appending decSum to the new small sum
                i--; // decrement i by 1
            }
            else if ((i - pNumber1.indexOf(".")) < (j - pNumber2.indexOf("."))){ // executes if j is farther from "." than i 
                smallSum = (short) (Short.parseShort("" + pNumber2.charAt(j)) + carryValue); // calculates sum of character in number 2 at j with 0
                carryValue = 0; // sets carryValue to 0
                if (smallSum > 10){ // if the sum is greater than 10, set carryValue to 1, and subtract 10 from the sum
                    carryValue = 1; // sets carryValue to 1
                    smallSum = (short)(smallSum - 10); // subtracts smallSum by 10
                }
                decSum = smallSum + decSum; // update decSum by appending decSum to the new small sum
                j--; // decrement j by 1
            }
        }
        
        i = (short) (pNumber1.indexOf(".") - 1); // updates i to be the index of the number before "."
        j = (short) (pNumber2.indexOf(".") - 1); // updates j to be the index of the number before "."
       
        while(i >= -1 | j >= -1){ // while statement that loops while the loop variants are greater than of equal to 0
            if(i >= 0 & j >= 0){ // executes if i and j are greater than 0
                smallSum = (short) (Short.parseShort("" + pNumber1.charAt(i)) + Short.parseShort("" + pNumber2.charAt(j)) + carryValue); // calculates sum of character from the two numbers
                carryValue = 0; // sets carryValue to 0
                if (smallSum > 10){ // if the sum is greater than 10, set carryValue to 1, and subtract 10 from the sum
                    carryValue = 1; // sets carryValue to 1
                    smallSum = (short)(smallSum - 10); // subtracts smallSum by 10
                }
                intSum = smallSum + intSum; // update intSum by appending intSum to the new small sum
            }
            else if (i < 0 & j >= 0){ // executes if i is less than 0 and j is greater than 0
                smallSum = (short) (Short.parseShort("" + pNumber2.charAt(j)) + carryValue); // calculates sum of character from the number 2 and 0
                carryValue = 0; // sets carryValue to 0
                if (smallSum > 10){ // if the sum is greater than 10, set carryValue to 1, and subtract 10 from the sum
                    carryValue = 1; // sets carryValue to 1
                    smallSum = (short)(smallSum - 10); // subtracts smallSum by 10
                }
                intSum = smallSum + intSum; // update intSum by appending intSum to the new small sum
            }
            else if (i >= 0 & j < 0){ // executes if j is less than 0 and i is greater than 0
                smallSum = (short) (Short.parseShort("" + pNumber1.charAt(i)) + carryValue); // calculates sum of character from the number 1 and 0
                carryValue = 0; // sets carryValue to 0
                if (smallSum > 10){ // if the sum is greater than 10, set carryValue to 1, and subtract 10 from the sum
                    carryValue = 1; // sets carryValue to 1
                    smallSum = (short)(smallSum - 10); // subtracts smallSum by 10
                }
                intSum = smallSum + intSum; // update intSum by appending intSum to the new small sum

            }
            else if (i == -1 & j == -1 & carryValue == 1){ // executes if i and j are 0 but carry value is 1
                intSum = "1" + intSum; // appends intsum to 1 and updates intsum value
            }
            i--; // decrements i by 1
            j--; // decrements j by 1
        }

        String totalSum = intSum + "." + decSum; // combines decSum and intSum together to creater the total sum
        setSum(totalSum); // sets sum by calling mutator method of sum
    }
    public void calcDifference(String pNumber1, String pNumber2){ // method that calculates the difference of two numbers taken as parameters
        // pre-conditions: pNumber1, pNumber2, intSum = "", decSum = "", smallSum = "", carryValue = "", i = pNumber1.length() - 1, and j = pNumber2.length() - 1
        // post-conditions: i < 0, j < 0, totalSum correctly represents the sum of number1 and number2
        String intDifference = ""; // declares and initializes intSum, the sum of the integer portion of the number
        String decDifference = ""; // declares and initializes decSum, the sum of the decimals portion of the number
        short smallDifference = 0; // declares and initializes smallSum, a temporary sum value of two small numbers
        short carryValue = 0; // declares and initializes carryValue, a number that is carried
        short i = (short) (pNumber1.length() - 1); // declares and initializes i, the loop variant used for accessing elements of pNumber1
        short j = (short) (pNumber2.length() - 1); // declares and initializes j, the loop variant used for accessing elements of pNumber2
            while(i > pNumber1.indexOf(".")| j > pNumber2.indexOf(".")){ // while statement that loops while the loop variants are greater than the index of "."
                if ((i - pNumber1.indexOf(".")) == (j - pNumber2.indexOf("."))){ // executes if i and j are the same distance from "."
                    smallDifference = (short) (Short.parseShort("" + pNumber1.charAt(i)) - Short.parseShort("" + pNumber2.charAt(j)) - carryValue); // calculates difference of two numbers
                    carryValue = 0; // sets carryValue to 0
                    if (smallDifference < 0){ // if the difference is less than 0, set carryValue to 1, and add 10 to the difference
                        carryValue = 1; // sets carryValue to 1
                        smallDifference = (short)(10 + smallDifference); // adds 10 to smallDifference
                    }
                    decDifference = smallDifference + decDifference; // update decDifference by appending decDifference to the new difference
                    i--; // decrement i by 1
                    j--; // decrement j by 1
                }
                else if ((i - pNumber1.indexOf(".")) > (j - pNumber2.indexOf("."))){ // executes if i is farther from "." than j
                    smallDifference = (short) (Short.parseShort("" + pNumber1.charAt(i)) - carryValue); // calculates difference of character in number 1 at i with 0
                    carryValue = 0; // sets carryValue to 0
                    if (smallDifference < 0){ // if the difference is less than 0, set carryValue to 1, and add 10 to the difference
                        carryValue = 1; // sets carryValue to 1
                        smallDifference = (short)(smallDifference + 10); // adds 10 to smallDifference
                    }
                    decDifference = smallDifference + decDifference; // update decDifference by appending decDifference to the new difference
                    i--; // decrement i by 1
                }
                else if ((i - pNumber1.indexOf(".")) < (j - pNumber2.indexOf("."))){ // executes if i is farther from "." than j
                    smallDifference = (short) (0 - Short.parseShort("" + pNumber2.charAt(j)) - carryValue + 10); // calculates difference of character in number 2 at j with 0
                    carryValue = 1; // sets carryValue to 1
                    decDifference = smallDifference + decDifference; // update decDifference by appending decDifference to the new difference
                    j--; // decrement j by 1
                }
            }
            
            i = (short) (pNumber1.indexOf(".") - 1); // updates i to be the index of the number before "."
            j = (short) (pNumber2.indexOf(".") - 1); // updates j to be the index of the number before "."
           
            while(i >= 0 | j >= 0){ // while statement that loops while the loop variants are greater than of equal to 0
                if(i >= 0 & j >= 0){ // executes if i and j are greater than 0
                    smallDifference = (short) (Short.parseShort("" + pNumber1.charAt(i)) - Short.parseShort("" + pNumber2.charAt(j)) - carryValue); // calculates difference of character from the two numbers
                    carryValue = 0; // sets carryValue to 0
                    if (smallDifference < 0){ // if the difference is less than 0, set carryValue to 1, and add 10 to the difference
                        carryValue = 1; // sets carryValue to 1
                        smallDifference = (short)(smallDifference + 10); // adds 10 to smallDifference
                    }
                    intDifference = smallDifference + intDifference; // update intDifference by appending intDifference to the new difference
                }
                else if (i >= 0 & j < 0){ // executes if j is less than 0 and i is greater than 0
                    smallDifference = (short) (Short.parseShort("" + pNumber1.charAt(i)) - carryValue); // calculates difference of character from number 1 with 0
                    carryValue = 0;
                    if (smallDifference < 0){
                        carryValue = 1;
                        smallDifference = (short)(smallDifference + 10);
                    }
                    intDifference = smallDifference + intDifference; // update intDifference by appending intDifference to the new difference      
                }
                i--; // decrement i by 1
                j--; // decrement j by 1
            }

    
        String totalDifference = intDifference + "." + decDifference; // combines decDifference and intDifference to get total difference
        setDifference(totalDifference); // sets difference by calling mutator method of difference
    }
    public void calcGreater(String pNumber1, String pNumber2){
        // pre-conditions: i = 0, done = false, pNumber1, pNumber2
        // post-conditions: i, done, greater
        short i = 0; // initializes and declares i
        boolean done = false; // initializes and declares done

            if ((short) pNumber1.indexOf(".") > (short) pNumber2.indexOf(".")){ // if the integer portion of number 1 is greater set it as greater
                setGreater(pNumber1); // set number 1 as greater with the mutator method
            }
            if ((short) pNumber2.indexOf(".") > (short) pNumber1.indexOf(".")){ // if the integer portion of number 2 is greater set it as greater
                setGreater(pNumber2); // set number 2 as greater with the mutator method
            }
            if ((short) pNumber1.indexOf(".") == (short) pNumber2.indexOf(".")){ // executes if the integer portions of the numbers are the same size
                char charNumb1; // declares charNumb1
                char charNumb2; // declares charNumb2
                short end = (short)pNumber1.length(); // sets end as the length of number 1
                if ((short)pNumber1.length() < (short)pNumber2.length()) end = (short) pNumber2.length(); // if number 1 is smaller than number 2 set number 2 as end
                
                while (i < end & !done){ // executes if i is smaller then end and not done
                    charNumb1 = 0; // initializes charNumb1 to 0
                    charNumb2 = 0; // initializes charNumb2 to 0
                    if (pNumber1.length() - 1 >= i){ // execute if i is not out of bounds for number 1
                        charNumb1 = pNumber1.charAt(i); // set charNumb1 as the char at index i for number 1
                    }
                    if (pNumber2.length() - 1 >= i){ // execute if i is not out of bounds for number 2
                        charNumb2 = pNumber2.charAt(i); // set charNumb1 as the char at index i for number 2
                    }
                    if (charNumb1 == '.' | charNumb2 == '.'){ // execute if decimal is found for the numbers
                        System.out.println("decimal found"); // print decimal found
                    }
                    else if (charNumb1 > charNumb2){ // if decimal is not found and charNumb1 is bigger than charNumb2 set number 1 as greater
                        setGreater(pNumber1); done = true; // sets greater as number 1 with mutator method
                    }
                    else if (charNumb2 > charNumb1){ // if decimal is not found and charNumb2 is bigger than charNumb1 set number 2 as greater
                        setGreater(pNumber2); done = true; // sets greater as number 2 with mutator method
                    }
                    i++; // increment i by 1
                }
            }
    }
    
    public void setNumber1(String number1){
        this.number1 = number1;
    }

    public String getNumber1(){
        return number1;
    }
    public void setNumber2(String number2){
        this.number2 = number2;
    }

    public String getNumber2(){
        return number2;
    }
    public void setGreater(String greater){
        this.greater = greater;
    }
    public String getGreater(){
        return this.greater;
    }
    public void setSum(String pSum){
        this.sum = pSum;
    }
    public String getSum(){
        return this.sum;
    }
    public void setDifference(String pDifference){
        this.difference = pDifference;
    }
    public String getDifference(){
        return this.difference;
    }
}
