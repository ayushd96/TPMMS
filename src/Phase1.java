import java.io.*;
import java.util.*;
public class Phase1 {
	public String filename;  // Input file to be Read and Sorted.
	BufferedReader br = null;
	BufferedReader br2 = null;
	BufferedReader[] br3 = null;
	FileWriter fw = null;
	File outputFile = null;
	int totalNumOfTuples = 0;
	int Phase1OutputFile = 0;
	int readIO = 0;
	int writeIO = 0; // To calculate Number of Disk I/Os.
	int blockSize; // Size of a block i.e 15 tuples (4KB).
	int numOfBlocks; // B(R) i.e 6667 blocks.
	int numOfBlocksInMem; // number of blocks we can fit in Memory. i.e 1280 blocks at a time.
	int numOfSublists; // Number of Sublists. i.e 5
	int inputBuffers; // Input Buffers
	int noOfTuples = 0;
	
	RelationFile RF = null;
	
	public Phase1(RelationFile RF) {
		this.RF = RF; // Instantiating file name.
	}
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("desktop\\input.txt")); // Get the input file.
			 int[] ClientID = new int[RF.noOfMMBlock*RF.noOfTuplesPerBlock];
			 int[] claimNumber = new int[RF.noOfMMBlock*RF.noOfTuplesPerBlock];
			 double [] AmountPaid = new double[RF.noOfMMBlock*RF.noOfTuplesPerBlock];
	            String[] content = new String[RF.noOfMMBlock*RF.noOfTuplesPerBlock];
	            int [] index = new int [RF.noOfMMBlock*40];
	            

	            int noOfTuples = 0; // use to count tuple number that already read

	            RF.blocksWritten = (RF.noOfMMBlock-3)*1000/4*4/5;
	
			int numOfTuples = 0; // Initially tuples read is 0 and is basically used to count number of tuples read.
			
			String input;
			while((input = br.readLine()) != null) {
				ClientID[numOfTuples] = Integer.parseInt(input.substring(0,9));
				claimNumber[numOfTuples] = Integer.parseInt(input.substring(0,8));
				AmountPaid[numOfTuples] = Double.parseDouble(input.substring(0,9));
				numOfTuples++;
				if(numOfTuples==RF.noOfMMBlock*RF.noOfTuplesPerBlock) {
					readIO++;
					SortAlgo.quickSort(ClientID,noOfTuples, totalNumOfTuples);
					
					outputFile = new File("src/PhraseOutput/outputEMPphase1-"+String.valueOf(writeIO)+".txt");
					if(!outputFile.exists()) {
						outputFile.createNewFile();
					}
					fw = new FileWriter(outputFile.getAbsolutePath(), false);
					for(int i=0; i< noOfTuples;i++) {
						fw.write(String.valueOf(ClientID[i] + content[claimNumber[i]] + "\r"));
					}
					fw.close();
					writeIO++;
					totalNumOfTuples += noOfTuples;
					numOfTuples = 0;
				}
			}
			if(numOfTuples > 0) {
				readIO++;
				SortAlgo.quickSort(ClientID, noOfTuples, totalNumOfTuples);
			    outputFile = new File("src/PhraseOutput/outputEMPphase1-"+String.valueOf(writeIO)+".txt");
			    if (!outputFile.exists()) {
                    outputFile.createNewFile();
                }
                fw = new FileWriter(outputFile.getAbsoluteFile(),false);
                for (int i=0;i<noOfTuples;i++){
                    fw.write(String.valueOf(ClientID[i])+content[claimNumber[i]]+"\r");
                }
                fw.close();
                totalNumOfTuples += noOfTuples;
                writeIO++;
			}
			 br.close();
		        Phase1OutputFile = writeIO;
		        System.out.println("Phase1: Total record is "+totalNumOfTuples+", each IO "+RF.blocksWritten+" blocks "+RF.blocksWritten*40+" tuples, ReadIO = "+readIO+" WriteIO = "+writeIO+", divide into "+Phase1OutputFile+" files");
		        System.gc();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (br != null)br.close();
	            if (fw != null)fw.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
