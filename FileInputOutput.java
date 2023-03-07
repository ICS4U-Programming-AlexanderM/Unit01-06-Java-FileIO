import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
* This program reads and outputs to text files.
*
* @author  Alexander Matheson
* @version 1.0
* @since   2023-03-07
*/

public final class FileInputOutput {
    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */
    private FileInputOutput() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Print messages.
    *
    * @param args Unused
    */
    public static void main(String[] args) {
        try {
            // Choose a file to get input from.
            final File input = new File("input.txt");
            final Scanner scanInput = new Scanner(input);
            // Choose (or create) a file to print output to.
            final FileWriter output = new FileWriter("output.txt");
            while (scanInput.hasNextLine()) {
                // Create variable to be used in error checking.
                boolean error = false;
                // Variables for loops.
                int sum = 0;
                int counter = 0;
                // Split new line by whitespace.
                final String[] fromFile = scanInput.nextLine().split(" ");
                while (counter < fromFile.length) {
                    try {
                        // Convert strings to integers and add them.
                        sum += Integer.parseInt(fromFile[counter]);
                    } catch (NumberFormatException err) {
                        // Checking if the line is empty.
                        if (fromFile[counter].isEmpty()) {
                            output.write("Error: Empty line found.\n");
                        } else {
                            // For when a non-integer is found on the line.
                            output.write("Error: \"" + fromFile[counter]
                                        + "\" is not a valid integer.\n");
                        }
                        // Variable to indicate if an error has occurred.
                        error = true;
                        // Stop adding elements on this line
                        // if an error is encountered.
                        break;
                    }
                    // Add one to the counter.
                    counter++;
                }
                // If no error has occurred.
                if (!error) {
                    // Output sum of all integers on the line to output file.
                    output.write(sum + "\n");
                }
            }
            // Close writer.
            output.close();

        } catch (IOException err) {
            // For when no input file is found.
            System.err.println("Error: " + err.getMessage());
        }
    }
}
