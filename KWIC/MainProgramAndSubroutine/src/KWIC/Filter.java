package KWIC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Filter line which start with ignore words from sorted linelist
public class Filter {
    String str;
    ArrayList<String> re;
    File outfile;
    String[] strList;

    public Filter(String str,
    ArrayList<String> re,
    File outfile){
        this.str=str;
        this.re=re;
        this.outfile=outfile;
    }

    public void filter() throws FileNotFoundException {
        PrintWriter output = new PrintWriter(outfile);
        if (str != "") {
            strList = str.split(",");

            for (int j = 0; j < strList.length; j++) {
                for (int i=0;i<re.size();i++) {
                    if (strList[j].equals(re.get(i).split(" ")[0])) {
                        re.remove(i);
                    }
                }
            }
            for (int i = 0; i < re.size(); i++) {
                output.println(re.get(i));
            }
        } else {
            for (int i = 0; i < re.size(); i++) {
                output.println(re.get(i));
            }
        }

        output.flush();
        output.close();

    }
}
