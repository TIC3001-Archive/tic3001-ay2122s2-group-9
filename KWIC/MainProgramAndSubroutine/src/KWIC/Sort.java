package KWIC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Sort the Linelist by AlphabetizerComparator
public class Sort  {
    private ArrayList<String> reList = new ArrayList<>();
    private ArrayList<String> kwicList;

    public Sort(ArrayList<String> kwicList) {
        this.kwicList = kwicList;
    }

    protected ArrayList<String> transform() {
        // 升序排列
        Collections.sort(this.kwicList, new AlphabetizerComparator());
        for (String line : kwicList){
            reList.add(line);
        }
        return reList;
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
