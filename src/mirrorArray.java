import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * mirrorArray creates the object which will be filtered.
 */
public class mirrorArray {

    private int numRows, numCols, minVal, maxVal, newMin, newMax;
    private int[][] mirrorFramedAry, tempAry;
    private int[] neighborAry = new int[9];

    /**
     * mirrorArray constructor to initialize the 2D array
     * @param rows
     * @param cols
     * @param min
     * @param max
     */
    public mirrorArray(int rows, int cols, int min, int max){
        numRows = rows;
        numCols = cols;
        minVal = min;
        maxVal = max;
        newMin = newMax = (minVal+maxVal)/2;

        // mirrorFramedAry has a zero frame around the values
        mirrorFramedAry = new int[numRows + 2][numCols + 2];
        tempAry = new int[numRows + 2][numCols + 2];
    }

    /**
     * loadImage passes in the scanner to fill mirrorFramedAry with the input file.
     * @param sc
     */
    public void loadImage(Scanner sc){
        for (int i = 1; i < numRows + 1; i++) {
            for (int j = 1; j < numCols + 1; j++) {
                mirrorFramedAry[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * loadNeighbors fills in neighborAry with the neighboring pixels
     * @param row
     * @param col
     */
    private void loadNeighbors(int row, int col){

        int index=0;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                neighborAry[index++] = mirrorFramedAry[row+i][col+j];
            }
        }
    }

    /**
     * average takes the average of all the neighbor pixels
     * @return
     */
    private int average(){
        int sum = 0;
        for(int i =0;i<neighborAry.length;i++){
            sum+=neighborAry[i];

        }
        return sum/neighborAry.length;
    }

    /**
     * mirrorFramed places the averaged pixels into the tempAry
     */
    public void mirrorFramed(){
        for(int i =1;i<numRows+1;i++){
            for(int j=1;j<numCols+1;j++){
                loadNeighbors(i,j);
                tempAry[i][j] = average();

                // Keeps track of the updated min and max values
                if (tempAry[i][j] < newMin) {
                    newMin = tempAry[i][j];
                }
                if (tempAry[i][j] > newMax) {
                    newMax = tempAry[i][j];
                }
            }
        }
    }

    public void outputArray(FileWriter fw){
        try {
            fw.write(numRows + " " + numCols + " " + newMin + " " + newMax + "\n");
            for (int i = 1; i < numRows + 1; i++) {
                for (int j = 1; j < numCols + 1; j++) {
                    fw.write(tempAry[i][j] + " ");
                }
                fw.write("\n");
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
