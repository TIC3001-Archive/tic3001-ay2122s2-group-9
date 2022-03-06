package KWIC;

import java.util.ArrayList;

//Loop content by removing the first word and appending it at the end of the line
// Return all data to Linelist
public class Loop {
    private ArrayList<String> wordlist = new ArrayList<>();
    private ArrayList<String> linelist = new ArrayList<>();
    private ArrayList<String> lines;

    public Loop(ArrayList<String> lines) {
        this.lines = lines;
    }

    protected ArrayList<String> transform() {
        String line = "";

        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            String[] words = line.split(" ");
            for (String word : words) {
                wordlist.add(word);
            }
            this.regroup();
            wordlist.clear();
            line = "";
        }
        return linelist;
    }
    protected void regroup() {
        for (int i = 0; i < wordlist.size(); i++) {
            String reline = "";

            for (int j = 0; j < wordlist.size(); j++) {
                if (j == 0) {
                    reline += wordlist.get(j);
                } else {
                    reline += " " + wordlist.get(j);
                }

            }
            linelist.add(reline);
            String word = wordlist.remove(0);
            wordlist.add(word);
        }

    }
}
