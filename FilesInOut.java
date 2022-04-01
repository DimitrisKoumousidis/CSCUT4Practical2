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
        Scanner in = new Scanner(System.in);
        boolean inputSuccess = false;
        String testInputPath = "";

        while (inputSuccess == false){ // Test whether the input file exists
             testInputPath = in.nextLine();
             File inputFile = new File(testInputPath);
            try {
                Scanner inFile = new Scanner(inputFile);
                inputSuccess = true;
            }catch(IOException e){
                System.err.println("IOException: " + e.getMessage()
                        + "not found" + ".Try adding a new filepath");
            }
        }

        String  inputFileName = testInputPath;
        File inputFile =new File(inputFileName) ;
        Scanner inFile = new Scanner(inputFile);
        inFile.useDelimiter("\n");
        inFile.tokens();

        System.out.println("supply filename for output:");
        Scanner out = new Scanner(System.in);
        boolean outputSuccess = false;
        String outputPath = "";

        while (outputSuccess == false){ // Test whether the output file exists
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



        PrintWriter writer = new PrintWriter(outputPath);
        FilesInOut fileUtility = new FilesInOut();


        while(inFile.hasNext()){
            String line = inFile.next();
            String[] lineTokens = line.split(" ");
            String outputLine ="";

                for(int i = 0; i <= lineTokens.length-1;i++) {

                    if(lineTokens[i].length() == 1) {
                        lineTokens[i] = lineTokens[i].toUpperCase();
                        lineTokens[i] = lineTokens[i].concat("." + "\s");

                        outputLine =  outputLine.concat(lineTokens[i]);
                    } else if (i == lineTokens.length-1) {
                        outputLine =  outputLine.concat( fileUtility.string_To_Date(lineTokens[i]) + "\n");
                    }
                    else {
                        lineTokens[i] = lineTokens[i].substring(0, 1).toUpperCase() + lineTokens[i].substring(1);
                        outputLine =  outputLine.concat(lineTokens[i] + "\s");
                    }

                }
                writer.write(outputLine);
            }

            System.out.println("File operation succesfull");


            inFile.close();
            writer.flush();
            writer.close();

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


