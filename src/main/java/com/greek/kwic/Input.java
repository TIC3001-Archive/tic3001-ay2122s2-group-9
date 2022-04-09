package com.greek.kwic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class Input extends Filter{
    private InputStream inputStream;

    public Input(InputStream inputStream, Pipe output) {
        super(null, output);
        this.inputStream = inputStream;
    }

    @Override
    protected void transform(){
        Scanner sc = new Scanner(inputStream,"UTF-8");
        String line;
        while (sc.hasNext()){
            line = sc.nextLine();
            output.writerLine(line);
        }
        output.closeWriter();
        sc.close();
    }
}
