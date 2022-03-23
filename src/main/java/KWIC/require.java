package KWIC;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class require extends Filter{

    private File infile;
    public require(File infile,Pipe input, Pipe output) {
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


    int requireisnull=strlist.size()==0?1:0;

        while(input.hasNextLine()) {

            int flag=requireisnull;
            line = input.readerLine();


            for(int i=0;i<strlist.size();i++){

                if(line.split(" ")[0].toUpperCase() .equals(strlist.get(i).toUpperCase())){
                    flag=1;
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
