import java.io.*;
import java.text.DecimalFormat;


public class Process{

    private static int aSortedCID[];
    private static double aSortedPaidAmount[];

    public static void sortRows(int[] aCID, double[] aPaidAmount, int length) {
        if (aCID == null || aCID.length == 0) {
            return;
        }
        aSortedCID = aCID;
        aSortedPaidAmount = aPaidAmount;
        quickSort(0, length - 1);
    }

    private static void quickSort(int lowerIndex, int higherIndex) {
        int iLow = lowerIndex;
        int iHigh = higherIndex;
        int pivot = aSortedCID[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (iLow <= iHigh) {
            while (aSortedCID[iLow] < pivot) { 
                iLow++; 
            }
            while (aSortedCID[iHigh] > pivot) { 
                iHigh--; 
            }
            if (iLow <= iHigh) {
                swap(iLow, iHigh);
                iLow++;
                iHigh--;
            }
        }
        if (lowerIndex < iHigh){
            quickSort(lowerIndex, iHigh);
        }
        if (iLow < higherIndex){
            quickSort(iLow, higherIndex);
        } 
    }

    private static void swap(int iLow, int iHigh) {
        int temp = aSortedCID[iLow];
        aSortedCID[iLow] = aSortedCID[iHigh];
        aSortedCID[iHigh] = temp;
        double ptemp = aSortedPaidAmount[iLow];
        aSortedPaidAmount[iLow] = aSortedPaidAmount[iHigh];
        aSortedPaidAmount[iHigh] = ptemp;
    }


    public static void main(String args[]){
        long startTime = System.currentTimeMillis();
        String inputFileName = "/input/input.txt"; 

        //System.out.println("Input Filename " + inputFileName);
        
        File workingDirectory = new File(System.getProperty("user.dir"));
        System.out.println("Working directory " + workingDirectory.getName());
        File inputFile = new File(workingDirectory, inputFileName);
        //System.out.println("Input File " + inputFile.getAbsolutePath());
        
        String inLine = "";
        int nRows = 1500;
        int[] CID = new int[nRows];
        double[] paidAmount = new double[nRows];
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setGroupingUsed(false);
        
        int readLineCount=0;
        int tempFileCount=0;
        BufferedReader reader  = null;  
        FileWriter writer = null;
        try{
            reader = new BufferedReader(new FileReader(inputFile));
            while((inLine=reader.readLine()) != null){
                CID[readLineCount] = Integer.parseInt(inLine.substring(18, 27));
                paidAmount[readLineCount] = Double.valueOf(inLine.substring(241, 250));
                //System.out.println(CID[readLineCount] + " " + df.format(paidAmount[readLineCount]));
                readLineCount++;
                
                if(readLineCount == nRows){
                    tempFileCount++;
                    System.out.println("tempFileCount " + tempFileCount);
                    String outputTempFile = "/temp/temp"+tempFileCount+".txt";
                    File outputFile = new File(workingDirectory, outputTempFile);
                    if(!outputFile.exists()){
                        outputFile.createNewFile();
                    }
                    writer = new FileWriter(outputFile);

                    sortRows(CID, paidAmount, nRows);

                    for (int i =0; i<nRows; i++) {
                        //System.out.println(i + " " + table.get(i));
                        writer.write(CID[i] + " " + df.format(paidAmount[i]) +"\r");   
                    }
                    writer.close();
                    System.out.println("------");
                    readLineCount=0;
                }
            }

            //Last remaining lines
            if(readLineCount != 0){
                //System.out.println("readLineCount = " + readLineCount);
                tempFileCount++;
                sortRows(CID, paidAmount, readLineCount);
                String outputTempFile = "/temp/temp"+tempFileCount+".txt";
                File outputFile = new File(workingDirectory, outputTempFile);
                if(!outputFile.exists()){
                    outputFile.createNewFile();
                }
                writer = new FileWriter(outputFile);
                for (int i =0; i<readLineCount; i++) {
                    //System.out.println(i + " " + table.get(i));
                    writer.write(CID[i] + " " + df.format(paidAmount[i])+"\r");   
                }
                writer.close();
                System.out.println("------");
                readLineCount=0;
            }
        }catch(FileNotFoundException e){
            System.out.println(e.toString());
        }catch(IOException e){
            System.out.println(e.toString());
        }

        try{
            reader.close();
        }catch(IOException e){
            System.out.println(e.toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(endTime-startTime);
    }
}