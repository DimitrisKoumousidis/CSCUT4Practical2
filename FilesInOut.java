import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.util.Scanner;

/**
 *
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("supply filename for input:");
        Scanner in = new Scanner(System.in); // Create a scanner object called "in"
        boolean inputSuccess = false;
        String inputPath = "";

        // Test whether the input file exists
        while (inputSuccess == false){ // While  boolean value = false
             inputPath = in.nextLine();  // Read the next line
             File inputFile = new File(inputPath);
            try {
                Scanner inFile = new Scanner(inputFile);
                inputSuccess = true; // If successfull set boolean value to true
            }catch(IOException e){ // Catch exception
                System.err.println("IOException: " + e.getMessage()
                        + "not found" + ".Try adding a new filepath");
            }
        }

        File inputFile =new File(inputPath);
        Scanner inFile = new Scanner(inputFile);
        inFile.useDelimiter("\n"); // Use a line as a delimiter
        inFile.tokens(); // Separate the lines

        System.out.println("supply filename for output:");
        Scanner out = new Scanner(System.in); // Create a scanner object called "out"
        boolean outputSuccess = false;
        String outputPath = "";

        //Test whether the output file exists
        while (outputSuccess == false){
            outputPath = out.nextLine();
            File outputFile = new File(outputPath);
            try {
                Scanner outFile = new Scanner(outputFile);
                outputSuccess = true;
            }catch(FileNotFoundException e){
                System.err.println("FileNotFoundException: " + e.getMessage()
                        + "not found" + ".Try adding a new filepath");
            }
        }



        PrintWriter writer = new PrintWriter(outputPath); // Create a printwriter object
        FilesInOut fileUtility = new FilesInOut(); /* Create an object of this class(For the
                                                       use of the string_to_date method */


        while(inFile.hasNext()){
            String line = inFile.next(); // Assign the next line
            String[] lineTokens = line.split(" "); // Split the line by spaces and assign them in an array
            String outputLine =""; // The final output string for each line

                for(int i = 0; i <= lineTokens.length-1;i++) { // For all the elements of the array

                    if(lineTokens[i].length() == 1) { // If the of the token is 1 it must be the middle name
                        lineTokens[i] = lineTokens[i].toUpperCase(); // Set the char to uppercase
                        lineTokens[i] = lineTokens[i].concat("." + "\s"); //Add a dot and a space

                        outputLine =  outputLine.concat(lineTokens[i]); // Concatenate the result
                    } else if (i == lineTokens.length-1) { //If the token is the final one(Always the date)
                        outputLine =  outputLine.concat( fileUtility.string_To_Date(lineTokens[i]) + "\n");
                        // Call string_to_date to alter the format
                    }
                    else {// For the first and last names
                        lineTokens[i] = lineTokens[i].substring(0, 1).toUpperCase() + lineTokens[i].substring(1); //Set the first char to uppercase
                        outputLine =  outputLine.concat(lineTokens[i] + "\s"); // Add a space and add to the output
                    }

                }
                writer.write(outputLine); // Write the line
            }

            System.out.println("File operation succesfull");


            inFile.close(); // Close the file reading scanner
            writer.flush(); // Flush the stream
            writer.close(); // Close the writer
    } // main
    /**
     * Takes a string of 8 number characters and returns it in the dd/mm/YYYY format
     * @param input a string with 8 numbers
     * @return The formatted string
     */
    public String string_To_Date(String input){

        String lineDate = ""; //Declare an empty string
        lineDate = lineDate.concat(input.substring(0,2));
        lineDate = lineDate.concat("/"+ input.substring(2,4)); // Manipulate the string using substring
        lineDate =  lineDate.concat("/" + input.substring(4,8));

        return lineDate; // Return the formatted array
    }

} // FilesInOut


