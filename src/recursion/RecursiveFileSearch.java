package recursion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursiveFileSearch {

    private boolean caseSensitive;
    private boolean findAll;

    public RecursiveFileSearch(boolean caseSensitive, boolean findAll) {
        this.caseSensitive = caseSensitive;
        this.findAll = findAll;
    }

    public static void main(String[] args) {
        validateArguments(args);
    }

    public static void validateArguments(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java RecursiveFileSearch <directory> <filename1> [<filename2> ...] [-ignoreCase | -caseSensitive] [-findAll | -findFirst]");
        }

        File directory = new File(args[0]);
        List<String> filenames = new ArrayList<>();
        boolean caseSensitive = true;
        boolean findAll = false;

        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            if (arg.equalsIgnoreCase("-ignoreCase")) {
                caseSensitive = false;
            } else if (arg.equalsIgnoreCase("-caseSensitive")) {
                caseSensitive = true;
            } else if (arg.equalsIgnoreCase("-findAll")) {
                findAll = true;
            } else if (arg.equalsIgnoreCase("-findFirst")) {
                findAll = false;
            } else {
                filenames.add(arg);
            }
        }

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a directory.");
        }

        RecursiveFileSearch search = new RecursiveFileSearch(caseSensitive, findAll);
        Map<String, List<String>> searchResults = search.searchFiles(directory, filenames);

        searchResults.forEach((filename, paths) -> {
            if (paths.isEmpty()) {
                System.out.println("File not found: " + filename);
            } else {
                System.out.println("Total occurrences found for \"" + filename + "\": " + paths.size());
                paths.forEach(System.out::println);
            }
        });
    }

    public Map<String, List<String>> searchFiles(File directory, List<String> filenames) {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a directory.");
        }

        Map<String, List<String>> results = new HashMap<>();
        for (String filename : filenames) {
            results.put(filename, searchFile(directory, filename));
        }
        return results;
    }

    public List<String> searchFile(File directory, String searchTerm) {
        List<String> matches = new ArrayList<>();
        searchFileRecursive(directory, searchTerm, matches);
        return matches;
    }

    private void searchFileRecursive(File directory, String searchTerm, List<String> matches) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFileRecursive(file, searchTerm, matches);
                } else {
                    String filename = caseSensitive ? file.getName() : file.getName().toLowerCase();
                    String term = caseSensitive ? searchTerm : searchTerm.toLowerCase();

                    if (filename.equals(term)) {
                    	if (matches.size()>0 && !findAll) {
                            return; // Stop search after first match
                        }
                        matches.add(file.getAbsolutePath());
                        
                    }
                }
            }
        }
    }
}
