package KWIC;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class Pipe {
    private Scanner pipeReader;
    private PrintWriter pipeWriter;

    Pipe() throws IOException {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();
        pipedReader.connect(pipedWriter);
        pipeReader = new Scanner(pipedReader);
        pipeWriter = new PrintWriter(pipedWriter);
    }
    public String readerLine() {
        return pipeReader.nextLine();
    }
    public boolean hasNextLine(){
        return pipeReader.hasNext();
    }
    public void writerLine(String line) {
        pipeWriter.println(line);
    }
    public void closeReader() {
        pipeReader.close();
    }
    public void closeWriter() {
        pipeWriter.flush();
        pipeWriter.close();
    }

}
