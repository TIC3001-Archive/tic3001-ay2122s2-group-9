package com.greek.kwic;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class Main {

//    public static File generateFile(String name1, String name2, String name3) throws IOException, InterruptedException {
//
//        InputStream infile = Main.class.getResourceAsStream("/" + name1 + ".txt");
//        InputStream infile2 = Main.class.getResourceAsStream("/" + name2 + ".txt");
//        InputStream infile3 = Main.class.getResourceAsStream("/" + name3 + ".txt");
//
//        File outfile = new File("testcase/output.txt");
//
//        Pipe pipe1 = new Pipe();
//        Pipe pipe2 = new Pipe();
//        Pipe pipe3 = new Pipe();
//        Pipe pipe4 = new Pipe();
//        Pipe pipe5 = new Pipe();
//
//
//        new Input(infile, pipe1).start();
//        new Loop(pipe1, pipe2).start();
//        new Sort(pipe2, pipe3).start();
//        new ignore(infile2, pipe3, pipe4).start();
//        new require(infile3, pipe4, pipe5).start();
//        new Output(pipe5, outfile).start();
//
//        return outfile;
//    }

    public static List<String> search(File titleFile, String requireFile) throws IOException {
        InputStream infile = new FileInputStream(titleFile);
        InputStream infile3 = new ByteArrayInputStream(requireFile.getBytes(StandardCharsets.UTF_8));

        File outfile = new File("testcase/output.txt");

        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe5 = new Pipe();


        new Input(infile, pipe1).start();
        new Loop(pipe1, pipe2).start();
        new Sort(pipe2, pipe3).start();
        new require(infile3, pipe3, pipe5).start();

        new Output(pipe5, outfile).start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Files.readAllLines(outfile.toPath());
    }


}
