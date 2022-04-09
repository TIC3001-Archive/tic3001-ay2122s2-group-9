package com.greek;

import com.greek.kwic.Main;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    private static final String QUIT = "q";
    private static List<String> historyResult = new ArrayList<>();
    private static List<String> searchWord = new ArrayList<>();
    private static boolean isEmptyFile = false;




    public static void main(String[] args) {
        if (args.length == 0 || args[0].isEmpty()) {
            System.err.println("未指定文件夹目录!");
            System.exit(0);
        }

        File files = new File(args[0]);
        initFileList(files);


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What do you want to search?");
            printSearchWordHistory();

            String keyword = scanner.nextLine();

            quitSystemIfInputQuitCommand(keyword);

            if (isEmptyFile || !isOneWord(keyword)) {
                printSearchResult();
                searchWord.add(keyword);
                continue;
            }

            searchWord.add(keyword);

            List<SearchResult> result = searchkeyword(files);

            historyResult.clear();
            result.forEach(Application::putInHistory);

            printSearchResult();
        }
    }

    private static List<SearchResult> searchkeyword(File files) {
        return Arrays.stream(files.listFiles())
                .filter(file -> file.getName().endsWith("txt"))
                .map(file -> callKWIC(file, searchWord.stream().collect(Collectors.joining(System.lineSeparator()))))
                .filter(Application::isValidSearchResult)
                .collect(Collectors.toList());
    }

    private static SearchResult callKWIC(File file, String require) {
        try {
            List<String> searchResult = Main.search(file, require);
            SearchResult result = new SearchResult();
            result.setFileName(file.getName());
            result.setResult(searchResult);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    private static void putInHistory(SearchResult searchResult) {
        historyResult.add(searchResult.getFileName());
        historyResult.addAll(searchResult.getResult());
    }


    private static void printSearchResult() {
        System.out.println("Search result:");
        historyResult.forEach(System.out::println);
        System.out.println("-------------");
    }

    private static void printSearchWordHistory() {
        searchWord.forEach(System.out::println);
    }

    private static boolean existText(File[] files) {
        return Arrays.asList(files)
                .stream()
                .anyMatch(file -> file.getName().endsWith("txt"));
    }

    private static void quitSystemIfInputQuitCommand(String keyword) {
        if (QUIT.equals(keyword)) {
            System.err.println("search end");
            System.exit(0);
        }
    }

    private static void initFileList(File directory) {
        if (!directory.exists()) {
            System.err.println("ListOfFiles.in does not exist");
            System.exit(0);
        }

        File[] files = directory.listFiles();
        if (files.length == 0 || !existText(files)) {
            isEmptyFile = true;
        }
    }

    private static boolean isOneWord(String keyword) {
        return keyword.split(" ").length == 1;
    }

    private static boolean isValidSearchResult(SearchResult result) {
        return null != result && null != result.getResult() && !result.getResult().isEmpty();
    }

}
