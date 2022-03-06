package KWIC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public void loop(String line) {

    }

    public static void main(String[] args) throws FileNotFoundException {
        // Words to ignore :
        System.out.println("please enter the words that you want to ignore:");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        scan.close();


        File infile = new File("Paste your Absolute path here ");
        File outfile = new File("Paste your Absolute path here");

        ArrayList<String> result;
        ArrayList<String> lineList ;

        // Read content from input file
        lineList=new TxtRead(infile).read();

        //Loop given content
        result = new Loop(lineList).transform();

        //Sorted the Loop content
        result = new Sort(result).transform();

        //Filter ignore content and save output to txt
        new Filter(str,result,outfile).filter();



    }

}
