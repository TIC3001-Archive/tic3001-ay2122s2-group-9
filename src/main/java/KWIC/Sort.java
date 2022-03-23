package KWIC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Sort extends Filter {
    private ArrayList<String> kwicList = new ArrayList<>();
    public Sort(Pipe input, Pipe output) {
        super(input, output);
    }

    @Override
    protected void transform() throws IOException {

        while (input.hasNextLine()){
            kwicList.add(input.readerLine());
        }
        Collections.sort(this.kwicList, new AlphabetizerComparator());
        for (String line : kwicList){
            output.writerLine(line);
        }
        input.closeReader();
        output.closeWriter();
    }
    private class AlphabetizerComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1 == null && o2 == null) {
                throw new NullPointerException();
            }
            int compareValue = 0;
            for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
                char o1c = o1.toLowerCase().charAt(i); //忽略大小写
                char o2c = o2.toLowerCase().charAt(i); //忽略大小写
                compareValue = o1c - o2c;
                if (compareValue != 0) {
                    break;
                }
            }
            return compareValue;

        }

    }
}
