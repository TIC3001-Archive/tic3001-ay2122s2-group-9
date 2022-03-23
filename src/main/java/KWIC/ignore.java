package KWIC;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ignore extends Filter{

    private File infile;
    public ignore(File infile,Pipe input, Pipe output) {
        super( input, output);

        this.infile=infile;
    }

    @Override
    protected void transform() throws IOException {

        String line;


        Pipe pipein = new Pipe();
        new Input(infile,pipein).transform();

        List<String> strlist=new ArrayList<String>();

            while(pipein.hasNextLine())
               strlist.add(pipein.readerLine());



            while(input.hasNextLine()) {

            int flag=1;
            line = input.readerLine();


            for(int i=0;i<strlist.size();i++){

                if(line.split(" ")[0].toUpperCase() .equals(strlist.get(i).toUpperCase())){
                    flag=0;
                }
            }

            if(flag==1){
                output.writerLine(line);

            }

        }
        output.closeWriter();
        input.closeReader();
    }
}
