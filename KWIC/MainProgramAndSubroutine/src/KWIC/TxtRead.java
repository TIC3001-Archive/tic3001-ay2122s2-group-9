package KWIC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Read content from input txt
public class TxtRead {
   File infile;
    public TxtRead(File infile){
        this.infile=infile;
    }
    public ArrayList<String> read() throws FileNotFoundException {
        ArrayList<String> lineList = new ArrayList<>();

        String line;

        Scanner input = new Scanner(infile);
        while (input.hasNextLine()) {
            line = input.nextLine();
            if (!line.equals("")) {
                lineList.add(line);
            }
        }
        input.close();
        return lineList;
    }
}
