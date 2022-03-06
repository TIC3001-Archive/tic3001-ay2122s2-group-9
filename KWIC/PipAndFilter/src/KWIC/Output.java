package KWIC;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Output extends Filter{
    private File outfile;
    public Output(Pipe input, File outfile) {
        super(input, null);
        this.outfile = outfile;
    }

    @Override
    protected void transform() throws IOException {
        PrintWriter pw = new PrintWriter(outfile);
        String line;
        System.out.println("please enter the words that you want to ignore: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String [] strList = str.split(",");

        while(input.hasNextLine()) {
            int flag=1;
            line = input.readerLine();
            for(int i=0;i<strList.length;i++){
                if(line.split(" ")[0] .equals(strList[i])){
                    flag=0;
                }
            }
            if(flag==1){
                pw.write(line);
                pw.write("\n");
            }
        }
        pw.flush();
        pw.close();
        input.closeReader();
    }
}
