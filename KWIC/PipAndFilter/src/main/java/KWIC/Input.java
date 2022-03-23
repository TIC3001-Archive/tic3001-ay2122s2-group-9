package KWIC;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Input extends Filter{
    private File infile;
    public Input(File infile, Pipe output) {
        super(null, output);
        this.infile = infile;
    }

    @Override
    protected void transform() throws IOException {
        Scanner sc = new Scanner(infile);
        String line;
        while (sc.hasNext()){
            line = sc.nextLine();
            output.writerLine(line);
        }
        output.closeWriter();
        sc.close();
    }
}
