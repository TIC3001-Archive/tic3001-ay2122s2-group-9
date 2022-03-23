package KWIC;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class Output extends Filter{
    private File outfile;
    public Output(Pipe input, File outfile) {
        super(input, null);
this.outfile=outfile;
    }

    @Override
    protected void transform() throws IOException {

        String line;
        PrintWriter pw = new PrintWriter(outfile);
        while(input.hasNextLine()) {

            line = input.readerLine();



            pw.write(line);
            pw.write("\n");
        }
        pw.flush();
        pw.close();

        input.closeReader();


    }
}
