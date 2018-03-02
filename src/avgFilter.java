import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * avgFilter removes noise in an image by averaging the neighbors of the pixel.
 */
public class avgFilter {

    public static void main(String[] argv) {

        File inFile = new File(argv[0]);
        File outFile = new File(argv[1]);
        Scanner sc;
        FileWriter fw;
        int numRows, numCols, minVal, maxVal;


        try {
            sc = new Scanner(inFile);
            fw = new FileWriter(outFile);

            // Reads in the header line to initialize the filtered image.
            numRows = sc.nextInt();
            numCols = sc.nextInt();
            minVal = sc.nextInt();
            maxVal = sc.nextInt();

            mirrorArray ma = new mirrorArray(numRows, numCols, minVal, maxVal);

            // Copies the array in the input file into the object.
            ma.loadImage(sc);

            // Applies the averaging filter on the image.
            ma.mirrorFramed();

            // Outputs the noise reduced image to the file.
            ma.outputArray(fw);

            sc.close();
            fw.close();
        } catch (Exception ioe) {
    
            ioe.printStackTrace();
            System.exit(1);
        }
    }
}

