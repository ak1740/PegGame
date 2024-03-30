/* TEXT READER CLASS
 * STEP 5
 * takes a file and creates a game board based on the question requirments
 * Aliya, Leen, Mariam, Zoya
 */
package PegGame.main;
 
import java.io.BufferedReader;// used to read text from a character-input stream
import java.io.IOException; // exception class for input/output operations
import java.io.InputStream; // superclass representing an input stream of bytes
import java.io.InputStreamReader; // bridge from byte streams to character streams
 
 
public class TextReader {
    private static char[][] board; // Class-level variable to store the board array
 
    public static char[][] readArrayFromFile(String filename) {
        try (InputStream inputStream = TextReader.class.getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Read the first line to determine the dimensions
            int size = Integer.parseInt(br.readLine());
 
            // Create the square array with the specified size
            board = new char[size][size];
 
            // read the rest of the file to fill the array
            String line;
            int rowIndex = 0;
            int colIndex = 0;
            while ((line = br.readLine()) != null && rowIndex < size) {
                for (char c : line.toCharArray()) {
                    // check if the character represents a peg or an empty hole
                    if (c == 'o' || c == '.') {
                        board[rowIndex][colIndex] = c;
                        // print an error message for invalid characters in the file
                    } else {
                        System.err.println("Invalid value in file: " );
                        return null;
                    }
                    colIndex++;
                    if (colIndex == size) {
                        // move to the next row when reaching the end of a row
                        rowIndex++;
                        colIndex = 0;
                    }
                }
            }
            return board;
        } catch (IOException | NumberFormatException e) {
             // handle exceptions related to input/output operations/number formatting
 
            e.printStackTrace();// print the stack trace if an exception occurs
            return null;
        }
    }
 
    // Method to retrieve the board array
    public static char[][] getBoard() {
        return board;
    }
 
<<<<<<< HEAD
}
=======
}
>>>>>>> c7b41a252e30af32857b969cb287b9ad3c91a6dd
