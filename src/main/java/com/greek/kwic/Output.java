package com.greek.kwic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class Output extends Filter {
    private File outfile;

    public Output(Pipe input, File outfile) {
        super(input, null);
        this.outfile = outfile;
    }

    @Override
    protected void transform() throws IOException {
//        long start = System.currentTimeMillis();
        String line;
        PrintWriter pw = new PrintWriter(outfile);
        while (input.hasNextLine()) {

            line = input.readerLine();


            pw.write(line);
            pw.write("\n");
        }
        pw.flush();
        pw.close();

        input.closeReader();
//        long end = System.currentTimeMillis();
//        System.out.println("执行时间:" + (end - start));
    }
}
