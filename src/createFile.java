import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.*;

/**
 * A simple creation method to create one relation with 1,000,000 tuples
 */
public class createFile {
    public static void createRecords() throws IOException{
        PrintWriter writer = new PrintWriter(new FileWriter("src/Records.txt"));
        for(int i = 0; i < 500000; i++){
          writer.println("Ayush");
          writer.println("11223445566" );
          writer.println("\n");
        }
        writer.close();
        System.out.println("Complete");
    }

    public static void main (String args[]) {
        try{
            createRecords();
        }catch(IOException e){

        }
    }

}
