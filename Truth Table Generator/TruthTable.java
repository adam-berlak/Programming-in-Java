
import java.util.*;
import java.lang.Math.*;
public class TruthTable{
    String expression; // declares a string that will contain the users inputed expression
    ArrayList<String> variables = new ArrayList<String>(); // creates a dynamic array that stores all variables in the expression
    ArrayList<String> duplicates = new ArrayList<String>(); // creates a dynamic array that stores all duplicates in the expression
    Map<String, String> subExpressions = new HashMap<String, String>(); // creates a dictionary that maps sub expressions to a number
    Map<String, String> truthValue = new HashMap<String, String>(); // creates a dictionary that maps sub expressions to a truth value
    String[][] table; // creates a 2-dimensional array that represents the truth table
    
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); // creates a Scanner object used to get user input
        TruthTable truthTable = new TruthTable(); // creates TruthTables object called truthTable
        
        System.out.println("Enter your logical expression"); // prompts the user to enter their logical expression
        String input = userInput.nextLine(); // creates input String object that saves the users input
        truthTable.setExpression(input); // sets expression as the users input

        truthTable.getTruthTable(truthTable.getExpression()); // call getTruthTable method that generates a truth table with the expression inputed
        
        System.out.println("Set of independent variables:"); // prints out text 'Set of independent variables:'
        short n = 0; // declares and initializes n to 0
        while (n < truthTable.getVariables().size()){ // loops until 'n' is equal to the size of the array of variables
            System.out.println(truthTable.getVariables().get(n)); // prints out the variable at index 'n' in the array
            n++; // increments 'n' by 1
        }
        System.out.println("Set of logical subexpressions and logical expression:"); // prints out text 'Set of logical subexpressions and logical expression:'
        n = 0; // initializes 'n' to 0
        while (n < truthTable.getSubExpressions().size()){ // loops until 'n' is equal to the size of the dictionary of sub-expressions
            System.out.println("LEO" + n + ": " + truthTable.getSubExpressions().get("LEO" + n)); // prints out the sub-expression assigned to "LEO" + 'n'
            n++; // increments 'n' by 1
        }
        System.out.println("Truth Table:"); // prints out text 'Truth Table:'
        n = 0; // initializes 'n' to 0
        short m; // initializes 'm' to 0
        while (n < (int)Math.pow(2, truthTable.getVariables().size())+1){ // loops until 'n' equals 2^(amount of variables)
            m = 0; // initializes 'm' to 0
            while (m < truthTable.getVariables().size() + truthTable.getSubExpressions().size()){ // loops until m equals the amount of sub-expressions + variables
                System.out.print(" | " + truthTable.getTable()[n][m]); // prints out the truth-value/expression corresponding to the position n,m
                m++; // increments 'm' by 1
            }
            System.out.println(""); // ensures and next string is printed on a new line
            n++; // increments 'n' by 1
        }
    }

    public String evaluateExpression(String pExpression){ // creates evaluateExpression method that evaluates the inputed expressions truth value
        // pre-conditions: i = 0, sub-expression = "", form = "", temp is empty, result = "F", stack contains "("
        // post-conditions: i = pExpression.length(), sub-expression = "", form = operator, temp contains operands, result contains truth value, stack is empty
        char character; // declares character
        int i = 0; // initializes 'i' to 0
        String subExpression = ""; // initializes subExpression to an empty string
        String form = ""; // initializes form to an empty string
        ArrayList<String> temp = new ArrayList<String>(); // creates a dynamic array called temp
        String result = "F"; // initializes result to F
        Stack count = new Stack(); // creates stack that keeps track of parenthasis
        count.push("("); // ensures size of count is 1 so first open bracket is skipped
        
        while (i < pExpression.length()){ // loops through the inputed expression until i equals length of expression
            character = pExpression.charAt(i); // initializes character to the char in pExpression at index i
            if (character == '(' & !count.empty()) count.pop(); // if an open bracket is read and count is not empty, make count empty
            else if (character == '(' & count.empty()){ // if an open bracket is read loop through the respective sub-expression and add it to the array
                count.push(character); // make count size 1 to represent 1 non closed parenthesis
                subExpression = subExpression + pExpression.charAt(i); // add character to subExpression string
                while (!count.empty()){ // loop through the respective sub-expression
                    i++; // increment i by 1
                    subExpression = subExpression + pExpression.charAt(i); // add character to sub-expression
                    if (pExpression.charAt(i) == '(') count.push(character); // if character read is an open bracket at 1 to size of stack
                    if (pExpression.charAt(i) == ')') count.pop(); // iff character read is a closed bracket remove 1 from size of stack
                }
                temp.add(subExpression); // add sub-expression to the array
                subExpression = ""; // initialize subExpression to 0
            }
            if (Character.isLetter(character)) temp.add(character + ""); // if the character is a letter at it to the array of immediate sub-expressions temp
            if (character == '+' | character == '*' | character == '-'){ // if the character is an operator set that to the form of the expression
                form = character + ""; // set form as the character
            }
            i++; // increment i by 1
        }
        if (getDuplicates().contains(temp.get(0))) getTruthValue().put(temp.get(0), evaluateExpression(temp.get(0))); // if sub-expression is a duplicate evaluate its truth here
        if (!form.equals("-") && getDuplicates().contains(temp.get(1))) getTruthValue().put(temp.get(1), evaluateExpression(temp.get(1))); // if sub-expression is a duplicate evalue its truth here

        if (form.equals("-") & getTruthValue().get(temp.get(0)).equals("F")) result = "T"; // if the form is a negation, get the truth value of the sub-expression and assign result accordingly
        else if (form.equals("+") && (getTruthValue().get(temp.get(0)).equals("T")| getTruthValue().get(temp.get(1)).equals("T"))) result = "T"; // if form is or, get truth of sub-expressions and assign result accordingly
        else if (form.equals("*") && (getTruthValue().get(temp.get(0)).equals("T") & getTruthValue().get(temp.get(1)).equals("T"))) result = "T"; // if form is and, get truth of sub-expressions and assign result accordingly
        return result; // return the result
    }
    public void getSubExpressions(String pExpression){ // method that stores all sub-expressions of a given expression
        // pre-conditions: i = 0, sub-expression = "", expressionCount = -1, stack is empty
        // post-conditions: i = pExpression.length(), sub-expression = "", expressionCount reflects amount of expressions - 1, stack is empty
        char character; // declares char
        String subExpression = ""; // initializes subExpresion to empty string
        int expressionCount = -1; // initializes expression count to -1
        Stack count = new Stack(); // creates stack counter that keeps track of parenthesis
        int i = 0; // initializes i to 0
        int n; // declares n
            
        while (i < pExpression.length()){ // while loop that loops through the inputed expression
            character = pExpression.charAt(i); // assing character at i in expression to character
            if (Character.isLetter(character) & !variables.contains(character + "")) variables.add(character + ""); // if character is letter and not in variable list add it to variables
            if (character == '('){ // if character is open bracket find respective sub expression and add it to the dictionary
                expressionCount = expressionCount + 1; // increment expression count by 1
                n = i; // assign the value of i to n
                count.push(character); // ensure size of count stack is 1
                subExpression = subExpression + pExpression.charAt(n); // add character to sub expression
                while (!count.empty()){ // loop through sub expression until there are an equal amount of closing parenthisis as open in the stack, i.e. stack = 0
                    n++; // increment n by 1
                    subExpression = subExpression + pExpression.charAt(n); // add character to sub-expression
                    if (pExpression.charAt(n) == '(') count.push(character); // if character is open bracket add it to stack
                    if (pExpression.charAt(n) == ')') count.pop(); // if character is closed bracket remove character from stack
                }
                if (findExpression(subExpression).equals("none")) getSubExpressions().put("LEO" + expressionCount, subExpression); // if sub-expression is not in dictionary add it
                else getDuplicates().add(subExpression); // if sub-expression is in dictionary, add it to duplicates array
                subExpression = ""; // initializes subExpression to empty string
            }
            i++; // increment i by 1
        }
    }
    public String findExpression(String pExpression){ // search algorithm that finds number assignment of expression in dictionary
        // pre-conditions: i = 0, result = none
        // post-conditions: i = getSubExpressions().size() or result != none
        int i = 0; // initializes i to 0
        String result = "none"; // initializes result to none
        while (i < getSubExpressions().size() && !getSubExpressions().get("LEO" + i).equals(pExpression)){ // loop until i equals the number of sub-expressions
            i++; // increment i by 1
        }
        if (i < getSubExpressions().size()) result = "LEO" + i; // if the sub-expression at i in the array matches the parameter save number to result
        return result; // return result
    }
    public void getTruthTable(String pExpression){ // method that creates table for an expression
        // pre-conditions: i = 0, table is empty, character = F
        // post-condtions: i = Math.pow(2, getVariables().size()) + 1), table is full
        char character; // declares character
        int i; // declares i
        int n; // declares n
        getSubExpressions(pExpression); // finds sub-expressions of inputed expression and updates global dictionary
        setTable(new String[(int)Math.pow(2, getVariables().size()) + 1][getVariables().size() + getSubExpressions().size()]); // initialize size of array
        character = 'F'; // initialize character to F
        i = 0; // initialize i to 0
        while (i < (int)(Math.pow(2, getVariables().size()) + 1)){ // loops until all rows of the array have been traversed
            int m = getVariables().size() + getSubExpressions().size() - 1; // initializes m to size of variables list + size of expressions dictionary
            while(m > -1 & i == 0){ // sets up first row of table as the variables and expressions
                if (m >= getVariables().size()){ // if m is larger or equal to size of variables set up expressions
                    getTable()[0][m] = "LEO" + Integer.toString((getSubExpressions().size() - 1) - (getVariables().size() + getSubExpressions().size() - 1 - m)); // put sub-expression to position m in row 0 of array
                }
                if (m < getVariables().size()){ // if size is smaller than size of variables set up variables
                    getTable()[0][m] = getVariables().get(m); // put variable to position m in row 0 of array
                }
                m--; // decrement m by 1
            }
            String binary = Integer.toBinaryString(i - 1); // converts i - 1 to binary, i.e. row 3: i - 1 = 01
            n = binary.length() - 1; // initalizes n to length of binary string
            m = getVariables().size() - 1; // initializes m to size of variables array
            while (m > -1 & i > 0){ // converts each character of string to T or F depending on if 1, or 0 is read
                if (n < 0) character = 'F'; // if binary string has been completely traversed set character to F
                else if (binary.charAt(n) == '1') character = 'T'; // if n is 1 set character to true
                else if (binary.charAt(n) == '0') character = 'F'; // if n is 0 set character to false
                getTable()[i][m] = character + ""; // save T or F to corresponding place in array
                getTruthValue().put(getTable()[0][m], character + "");  // assign variable of same column to the truth value of character
                n--; // decrement n by 1
                m--; // decrement m by 1
            }
            m = getVariables().size() + getSubExpressions().size() - 1; // initialize m to size of variables + subExpressions array and dictionary
            while (m > getVariables().size() - 1 & i > 0){ // finds truth of each sub-Expression and puts it on the table
                getTable()[i][m] = evaluateExpression(getSubExpressions().get(getTable()[0][m])); // evaluates truth of expression in column m and puts it in its corresponding section
                getTruthValue().put(getSubExpressions().get(getTable()[0][m]), getTable()[i][m]); // assign expression of same colum to the truth value that was calculated
                m--; // decrement m by 1
            }
            i++; // increment i by 1
        }
    }
    
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setVariables(ArrayList<String> variables) {
        this.variables = variables;
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public void setSubExpressions(Map<String, String> subExpressions) {
        this.subExpressions = subExpressions;
    }

    public Map<String, String> getSubExpressions() {
        return subExpressions;
    }

    public void setTruthValue(Map<String, String> truthValue) {
        this.truthValue = truthValue;
    }

    public Map<String, String> getTruthValue() {
        return truthValue;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }

    public String[][] getTable() {
        return table;
    }

    public void setDuplicates(ArrayList<String> duplicates) {
        this.duplicates = duplicates;
    }

    public ArrayList<String> getDuplicates() {
        return duplicates;
    }
}
