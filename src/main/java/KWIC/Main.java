package KWIC;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {

//        System.out.println(args[0]);//titles
//        System.out.println(args[1]);//ignore
//        System.out.println(args[2]);//required
        InputStream infile = Main.class.getResourceAsStream("/" + args[0] + ".txt");

//        FileInputStream fileInputStream =
        InputStream infile2 = Main.class.getResourceAsStream("/" + args[1] + ".txt");
        InputStream infile3 = Main.class.getResourceAsStream("/" + args[2] + ".txt");
        File outfile = new File("output.txt");

        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        Pipe pipe5 = new Pipe();


        new Input(infile, pipe1).start();
        new Loop(pipe1, pipe2).start();
        new Sort(pipe2, pipe3).start();
        new ignore(infile2,pipe3, pipe4).start();
        new require(infile3,pipe4,pipe5 ).start();
        new Output(pipe5, outfile).start();

    }
}
