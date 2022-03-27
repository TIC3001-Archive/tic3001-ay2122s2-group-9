package KWIC;


import java.io.*;


public class Main {

    public static File generateFile(String name1, String name2, String name3) throws IOException, InterruptedException {

        InputStream infile = Main.class.getResourceAsStream("/" + name1 + ".txt");
        InputStream infile2 = Main.class.getResourceAsStream("/" + name2 + ".txt");
        InputStream infile3 = Main.class.getResourceAsStream("/" + name3 + ".txt");

        File outfile = new File("testcase/output.txt");

        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        Pipe pipe5 = new Pipe();


        new Input(infile, pipe1).start();
        new Loop(pipe1, pipe2).start();
        new Sort(pipe2, pipe3).start();
        new ignore(infile2, pipe3, pipe4).start();
        new require(infile3, pipe4, pipe5).start();
        new Output(pipe5, outfile).start();

        return outfile;
    }

}
