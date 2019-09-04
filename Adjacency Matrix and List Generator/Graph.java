
import java.io.*;
import java.util.*;
public class Graph {
    int totalVertices; // Contains the number specifying the total number of vertices
    int maxNeighbor = 0; // Contains the size of the largest array of neighbors among vertices
    int minNeighbor; // Contains the size of the smallest array of neighbors among vertices
    String fileName; // Contains the directory or filename of the file to be read
    ArrayList<String> mpv = new ArrayList<String>(); // Dynamic array that contains the vertices with the most amount of neighbors
    ArrayList<String> lpv = new ArrayList<String>(); // Dynamic array that contains the vertices with the least amount of neighbors
    String[][] matrix; // Two-dimensional array that represents the adjacency matrix of the graph
    Map<Integer, List<Integer>> Adjacency_List; // Hash map that maps vertices to sorted lists that contain the neighbors of the corresponding vertices
    
    public static void main(String[] args) {
        
            Graph graph = new Graph(); // Creates new graph object  
            Scanner userInput = new Scanner(System.in); // Creates new scanner object
            
            System.out.println("Enter number of vertices"); // Prompts the user to input the total number of vertices
            graph.setTotalVertices(Integer.parseInt(userInput.nextLine())); // Sets the total vertices as the users input
            graph.setMinNeighbor(graph.getTotalVertices()); // Initializes minimum neighbor value to max possible
            graph.setMatrix(new String[graph.getTotalVertices() + 1][graph.getTotalVertices() + 1]); // Initializes size of two-dimensional matrix

            System.out.println("Enter filename"); // Prompts the user to input the directory or file name
            graph.setFileName(userInput.nextLine()); // Sets fileName as user input
            
            graph.Adjacency_List = new HashMap<Integer, List<Integer>>(); // Initializes adjacency list
            for (int i = 0 ; i <= graph.totalVertices ; i++){  // Increments until list is fully initialized
                graph.Adjacency_List.put(i, new LinkedList<Integer>()); // Maps each vertices to a linked list
            }
            
            graph.initializeMatrix(); // Initializes matrix
            graph.getGraph(); // Calculates properties of graph
            
            FileWriter fileWriter = null; // Initializes fileWriter to null
            try { // trys and catches exception upon trying to create file
            fileWriter = new FileWriter("AdjacencyMatrix.CSV"); // creates new file AdjacencyMatrix.CSV
            int n = 0; // initializes 'n' to 0
            int m; // initializes 'm' to 0
            while (n < graph.getTotalVertices() + 1){ // loops until 'n' equals total amount of vertices + 1
                m = 0; // initializes 'm' to 0
                while (m < graph.getTotalVertices() + 1){ // loops until m equals total amount of vertices + 1
                    fileWriter.append(graph.getMatrix()[n][m] + ", "); // writes character at n, m onto file with a comma
                    m++; // increments 'm' by 1
                }
                fileWriter.append("\n"); // makes sure next character is written on a new line
                n++; // increments 'n' by 1
            }
            }
            catch (Exception e) { // catches error writing file
                System.out.println("error in csv file");
            }
            finally { // flushes and closes filewriter
                try {
                    fileWriter.flush();
                    fileWriter.close();
                }
                catch (IOException e){
                    System.out.println("error while flushing");
                }
            }
            try { // trys and catches exception upon trying to create file
                fileWriter = new FileWriter("AdjacencyList.CSV "); // creates new file AdjacencyList.CSV
                int n = 0; // initializes 'n' to 0
                while (n < graph.getTotalVertices()){ // loops until 'n' equals total amount of vertices
                    List<Integer> slist = graph.Adjacency_List.get(n); // Assigns slist the list associated with variable n
                    if (slist == null) { // if null do nothing
                    }    
                    else {
                        fileWriter.append(n + ""); // write vertices to file
                        for (int o : slist){ // loop through each element of sList
                            fileWriter.append(", " + o); // add each element to file
                        }
                    }    
                    fileWriter.append("\n"); // makes sure next character is written on a new line
                    n++; // increments 'n' by 1
                }  
            } catch (Exception e) { // catches error writing file
                System.out.println("error in csv file");
            }
            finally { // flushes and closes filewriter
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e){
                    System.out.println("error while flushing");
                }
            }
            graph.calcMpv(); // Creates array of vertices with largest amount of neighbors
            System.out.println("Number of neighbors for MPV: " + graph.getMaxNeighbor()); // prints largest amount of neighbors in graph
            System.out.println("MPV neighbors: "); // prints MPV neighbors:
            int i = 0; // initializes i to 0
            while (i < graph.getMpv().size()){ // loops until mpv has been fully traversed
                List<Integer> slist = graph.Adjacency_List.get(Integer.parseInt(graph.getMpv().get(i))); // assigns slist to list associated with character i of mpv
                if (slist == null) { // if empty print the following
                    System.out.println("empty"); // print empty
                }    
                else {
                    System.out.print(graph.getMpv().get(i) + ""); // print character i of mpv
                    for (int o : slist){ // print all neighbors of character i of mpv
                        System.out.print(", " + o); // print object in slist
                    }
                }    
                System.out.println(""); // ensures and next string is printed on a new line
                i++; // increment i by 1
            }
            graph.calcLpv(); // Creates array of vertices with smallest amount of neighbors
            System.out.println("Number of neighbors for LPV: " + graph.getMinNeighbor()); // prints smallest amount of neighbors in graph
            System.out.println("LPV neighbors: "); // prints LPV neighbors:
            i = 0; // initializes i to 0
            while (i < graph.getLpv().size()){ // loops until lpv has been fully traversed
                List<Integer> slist = graph.Adjacency_List.get(Integer.parseInt(graph.getLpv().get(i))); // assigns slist to list associated with character i of lpv
                if (slist == null) { // if empty print the following
                    System.out.println("empty"); // print empty
                }    
                else {
                    System.out.print(graph.getLpv().get(i) + ""); // print character i of lpv
                    for (int o : slist){ // print all neighbors of character i of lpv
                        System.out.print(", " + o); // print object in slist
                    }
                }    
                System.out.println(""); // ensures and next string is printed on a new line
                i++; // increments i by 1
            }
        }

    public void initializeMatrix(){ // initializes matrix 
        // pre-conditions: i = 0, j = 0, matrix is empty
        // post-conditions: i > totalVertices, j > totalVertices, matrix is full
        int i = 0; // initializes i to 0 
        getMatrix()[0][0] = "x"; // assigns the top left corner to the character x
        while (i <= getTotalVertices()){ // loops until i is greater than total amount of vertices
            int j = 0; // initializes j to 0
            while (j <= getTotalVertices()){ // loops until j is greater than total amount of vertices
                if (i == 0 & j != 0) getMatrix()[0][j] = Integer.toString(j - 1); // if we are in the top most row and not the left most column write j - 1 at 0, j
                else if (j == 0 & i != 0) getMatrix()[i][0] = Integer.toString(i - 1); // if we are in the left most column and not in the top row write i - 1 at i, 0
                else if (i != 0 & j != 0) getMatrix()[i][j] = "0"; // if not in top row or left most column write 0 at i, j
                j++; // increment j by 1
            }
            i++; // increment i by 1
        }
    }
    public void calcMpv(){ // creates array of vertices with the most neighbors
        // pre-conditions: i = 0, mpv is empty
        // post-conditions: i = total vertices, mpv is full
        for (int i = 0 ; i < getTotalVertices() ; i++){ // loop until all vertices have been checked
            List<Integer> slist = Adjacency_List.get(i); // assign slist to the list corresponding to vertices i
            if (slist == null) { // if list is empry do nothing
            }    
            else if (slist.size() > getMaxNeighbor()){ // if size of list is larger than largest number of neighbors so far update mpv
                setMaxNeighbor(slist.size()); // update max neighbor size
                setMpv(new ArrayList<String>()); // create new empty array for mpv
                getMpv().add(i + ""); // add vertices to array
            }
            else if (slist.size() == getMaxNeighbor()){ // if size of list is equal to size of max neighbors so far add it to the array
                getMpv().add(i + ""); // add vertices to array
            }
        }
    }
    public void calcLpv(){ // creates array of vertices with the least neighbors
    // pre-conditions: i = 0, lpv is empty
    // post-conditions: i = total vertices, lpv is full
        for (int i = 0 ; i < getTotalVertices() ; i++){ // loop until all vertices have been checked
            List<Integer> slist = Adjacency_List.get(i); // assign slist to the list corresponding to vertices i
            if (slist == null) { // if list is empry do nothing
            }    
            else if (slist.size() < getMinNeighbor()){ // if size of list is smaller than smallest number of neighbors so far update lpv
                setMinNeighbor(slist.size()); // update min neighbor size
                setLpv(new ArrayList<String>()); // create new empty array for lpv
                getLpv().add(i + ""); // add vertices to array
            }
            else if (slist.size() == getMinNeighbor()){ // if size of list is equal to size of min neighbors so far add it to the array
                getLpv().add(i + ""); // add vertices to array
            }
        }
    }
    public void getGraph(){
        // pre-conditions: j = 0, file exists and has text, number1 and number2 are empty, matrix is empty, all lists in Adjlist are empty
        // post-conditions: j = size of data read, number1 and number2 represent numbers, matrix is full, all lists in Adjlist contain all neighbors
        File file = new File(getFileName()); // read from file in getFileName
        try{ // try reading file, catch exception if error reading file
            Scanner inputStream = new Scanner(file); // create scanner to read file
            while(inputStream.hasNext()){ // loop until file has been traversed
                String data = inputStream.next(); // put data from file in variable data
                String number1 = ""; // initializes number1 to empty string
                
                int j = 0; // initialize j to 0
                while(data.charAt(j) != ','){ // loop until , is read
                    number1 = number1 + data.charAt(j); // add character to number 1
                    j++; // increment j by 1
                }
                j++; // increment j by 1 more to skip ','
                String number2 = ""; // initializes number2 to empty string
                while(j < data.length()){ // loops until data has been traversed
                    number2 = number2 + data.charAt(j); // adds character to number2
                    j++; // increments j by 1
                }
                getMatrix()[Integer.parseInt(number1) + 1][Integer.parseInt(number2) + 1] = "1"; // set position number1, number2 to 1 in matrix
                getMatrix()[Integer.parseInt(number2) + 1][Integer.parseInt(number1) + 1] = "1"; // set position number2, number1 to 1 in matrix

                List<Integer> slist = Adjacency_List.get(Integer.parseInt(number1)); // assign list to list corresponding to number1
                slist.add(Integer.parseInt(number2)); // add number2 to said list
                List<Integer> dlist = Adjacency_List.get(Integer.parseInt(number2)); // assign list to list corresponding to number2
                dlist.add(Integer.parseInt(number1)); // add number1 to said list
            }
            inputStream.close(); // close input stream
            
        } catch (FileNotFoundException e){ // catch exception if error reading file
            e.printStackTrace();
        }
    }
    public void setTotalVertices(int totalVertices) {
        this.totalVertices = totalVertices;
    }

    public int getTotalVertices() {
        return totalVertices;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMaxNeighbor(int maxNeighbor) {
        this.maxNeighbor = maxNeighbor;
    }

    public int getMaxNeighbor() {
        return maxNeighbor;
    }

    public void setMpv(ArrayList<String> mpv) {
        this.mpv = mpv;
    }

    public ArrayList<String> getMpv() {
        return mpv;
    }

    public void setMinNeighbor(int minNeighbor) {
        this.minNeighbor = minNeighbor;
    }

    public int getMinNeighbor() {
        return minNeighbor;
    }

    public void setLpv(ArrayList<String> lpv) {
        this.lpv = lpv;
    }

    public ArrayList<String> getLpv() {
        return lpv;
    }
}
