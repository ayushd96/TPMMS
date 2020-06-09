import java.io.*;
import java.nio.file.Path;

/**
 * Class of relation which control two relations
 */
public class RelationFile {

    static int mainMemorySize = 5000000; // Initially set as 5MB = 5000000B
    static int blockSize = 4000; // 4KB for each block
    static int o = 0;

    int totalFileTuple;
    int totalFileBlock;
    int noOfMMBlock;
    int noOfSublists;
    int noOfTuplesPerBlock = 1280; // 1280 tuples are stored in one block
    int blocksRead;
    int blocksWritten;

    String file;

    /**
     * Constructor
     * @param file path of file
     * @param totalFileTuple tuples of file
     */
    public RelationFile(String file, int totalFileTuple){
        this.blocksRead = 0;
        this.blocksWritten = 0;

        this.file = file;

        this.totalFileTuple = totalFileTuple;
        this.totalFileBlock = this.totalFileTuple/this.noOfTuplesPerBlock;
        this.noOfSublists = (this.totalFileBlock*this.blockSize)/this.mainMemorySize;

        //in case there are reminder that is left when divided
        if(((this.totalFileBlock*this.blockSize)% this.mainMemorySize) != 0){
            ++noOfSublists;
        }

        this.noOfMMBlock = this.mainMemorySize/this.blockSize+1;
    }

    public void setMainMemorySize(int mainMemorySize){
        this.mainMemorySize = mainMemorySize;
    }

    public int getNoOfSublists() {
        return noOfSublists;
    }

    public static int getMainMemorySize() {
        return mainMemorySize;
    }





}