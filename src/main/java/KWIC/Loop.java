package KWIC;

import java.util.ArrayList;


public class Loop extends Filter{
    private ArrayList<String> wordlist = new ArrayList<>();
    private ArrayList<String> linelist = new ArrayList<>();

    public Loop(Pipe input, Pipe output) {
        super(input, output);
    }

    @Override
    protected void transform() {
        String line = "";

        while(input.hasNextLine()) {
            line = input.readerLine();
            // 单词拆分
            String[] words = line.split(" ");
            for (String word : words) {
                wordlist.add(word);
            }

            //单词重组
            this.regroup();
            for (int i = 0; i < linelist.size(); i++) {
                output.writerLine(linelist.get(i));
            }

            wordlist.clear();
            linelist.clear();
            line = "";
        }
        input.closeReader();
        output.closeWriter();
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
