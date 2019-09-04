import java.util.Scanner;
import java.util.ArrayList;

public class Multiply {
    String number1; // declares number1 as a string
    String number2; // declares number2 as a string
    String product; // declares product as a string
    ArrayList<String> interArray = new ArrayList<String>(); // creates a dynamic array names interArray
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis(); // check current time
        
        Scanner userInput = new Scanner(System.in); // creates a Scanner object used to get user input
        Multiply multiply = new Multiply(); // creates Multiply object called compare
        
        System.out.println("Enter number 1"); // prompts the user to enter the first number 
        String input = userInput.nextLine(); // creates input String object that saves the users input
        multiply.setNumber1(input); // calls setNumber1 mutator method to set variable number1
        System.out.println("Number 1 is:" + multiply.getNumber1()); // prints out the users input by calling accessor method of number1
        
        System.out.println("Enter number 2"); // prompts the user to enter the second number
        input = userInput.nextLine(); // updates input String object with new input value
        multiply.setNumber2(input); // calls setNumber2 mutator method to set variable number2
        System.out.println("Number 2 is:" + multiply.getNumber2()); // prints out the users input by calling accessor method of number2
        
        System.out.println("Product is:" + multiply.calcProduct(multiply.getNumber1(), multiply.getNumber2())); // prints product by calling calcProduct method
        short p = 0; // initializes p to zero
        while (p < multiply.getInterArray().size()){ // loops while p is smaller than the dynamic array size
            System.out.println("Partial Product No." + p + ":" + multiply.getInterArray().get(p)); // prints each partial product
            p++; // increments p by 1
        long elapsed = System.currentTimeMillis() - start; // calculate elapsed time
        System.out.println("Elapsed time: " + elapsed); // print elapsed time
        }
    }

    public String calcProduct(String pNumber1, String pNumber2){ // method that calculates and returns product of 2 numbers
        // pre-conditions: pNumber1, pNumber2, i = pNumber1.length() - 1, j
        // post-conditions: product correctly represents the product of number 1 and 2, i < 0, j < 0
        short i = (short) (pNumber1.length() - 1); // initializes i to length of number1 - 1
        short j; // declares j as a short
        short tempProduct; // declares tempProduct as short
        String product = "0"; // initializes product to "0"
        short carry; // declares carry as a short
        String inter; // declares inter as a short

        while(i >= 0){ // loops while i is greater than zero
            tempProduct = 0; // assigns 0 to tempProduct
            inter = ""; // assigns "" to inter
            carry = 0; // assings 0 to carry
            j = (short) (pNumber2.length() - 1); // initializes j to length of number2 - 1
            while(j >= 0){ // loops until j is smaller than 0
                tempProduct = (short) (Short.parseShort("" + pNumber1.charAt(i)) * Short.parseShort("" + pNumber2.charAt(j)) + carry); // multiplies the character in number1 at i with number 2 at j, and adds carry
                carry = 0; // sets carry to 0
                if (tempProduct >= 10){ // if temporary product is larger than 10 execute the following loop
                    carry = Short.parseShort("" + Short.toString(tempProduct).charAt(0)); // set carry as the second char in tempProduct
                    tempProduct = (short) (tempProduct - carry * 10); // subtract tempProduct by carry * 10
                }
                inter = Short.toString(tempProduct) + inter; // update intermediate product with new tempProduct
                if (j == 0 & carry != 0) inter = Short.toString(carry) + inter; // if j == 0 and carry is not 0 add it to the start of the intermediate product
                j--; // decrement j by 1
            }
            for (short n = 0; n < (pNumber1.length() - 1) - i; n++){ // loop that appends pNumber1.length() - 1 zeros to inter
                inter = inter + "0"; // append 0 to inter
            }
            interArray.add(inter); // adds the new intermediate product to the dynamic array
            
            String intSum = ""; // initializes intSum to ""
            short tempSum = 0; // initializes tempSum to 0
            carry = 0; // sets carry to 0
            short m = (short) (inter.length() - 1); // initializes m to inter.length() - 1
            short n = (short) (product.length() - 1); // initializes n to product.length() - 1
            while(n >= -1 | m >= -1){ // while loop that updates the product by adding the new intermediate product
                if(n >= 0 & m >= 0){ // executes if i and j are greater than or equal to 0
                    tempSum = (short) (Short.parseShort("" + inter.charAt(m)) + Short.parseShort("" + product.charAt(n)) + carry); // calculates sum of character from the two numbers
                    carry = 0; // sets carry to 0
                    if (tempSum >= 10){ // if the sum is greater than 10, set carry to 1, and subtract 10 from the sum
                        carry = 1; // sets carry to 1
                        tempSum = (short)(tempSum - 10); // subtracts tempSum by 10
                    }
                    intSum = tempSum + intSum; // update intSum by appending intSum to the new temp sum
                }
                else if (n < 0 & m >= 0){ // executes if i is less than 0 and j is greater than or equal to 0
                    tempSum = (short) (Short.parseShort("" + inter.charAt(m)) + carry); // calculates sum of character from the number2 and 0
                    carry = 0; // sets carry to 0
                    if (tempSum >= 10){ // if the sum is greater than 10, set carry to 1, and subtract 10 from the sum
                        carry = 1; // sets carry to 1
                        tempSum = (short)(tempSum - 10); // subtracts smallSum by 10
                    }
                    intSum = tempSum + intSum; // update intSum by appending intSum to the new small sum
                }
                else if (n >= 0 & m < 0){ // executes if j is less than 0 and i is greater than 0
                    tempSum = (short) (Short.parseShort("" + product.charAt(n)) + carry); // calculates sum of character from the number 1 and 0
                    carry = 0; // sets carry to 0
                    if (tempSum >= 10){ // if the sum is greater than 10, set carry to 1, and subtract 10 from the sum
                        carry = 1; // sets carry to 1
                        tempSum = (short)(tempSum - 10); // subtracts smallSum by 10
                    }
                    intSum = tempSum + intSum; // update intSum by appending intSum to the new small sum

                }
                else if (n < 0 & m < 0 & carry == 1){ // executes if i and j are 0 but carry value is 1
                    intSum = "1" + intSum; // appends intsum to 1 and updates intsum value
                }
                n--; // decrements n by 1
                m--; // decrements m by 1
            }
            product = intSum; // updates product with new intSum
            i--; // decrements i by 1
        }
        return product; // returns calculated product
    }
    
    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getNumber2() {
        return number2;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setInterArray(ArrayList<String> interArray) {
        this.interArray = interArray;
    }

    public ArrayList<String> getInterArray() {
        return interArray;
    }
}
