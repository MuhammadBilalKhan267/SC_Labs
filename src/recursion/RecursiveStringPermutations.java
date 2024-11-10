package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
	
public class RecursiveStringPermutations {
	
    public static List<String> generatePermutations(String str, boolean includeDuplicates) {
        List<String> permutations = new ArrayList<>();
        if (str == null) return permutations;
        if (str.isEmpty()) {
            permutations.add("");
            return permutations;
        }
        if (includeDuplicates) {
            generatePermutationsHelper(str, "", permutations);
        } else {
            Set<String> uniquePermutations = new HashSet<>();
            generatePermutationsHelper(str, "", uniquePermutations);
            permutations.addAll(uniquePermutations);
        }
        return permutations;
    }

    private static void generatePermutationsHelper(String str, String prefix, List<String> result) {
        if (str.isEmpty()) {
            result.add(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                generatePermutationsHelper(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i), result);
            }
        }
    }

    private static void generatePermutationsHelper(String str, String prefix, Set<String> result) {
        if (str.isEmpty()) {
            result.add(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                generatePermutationsHelper(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i), result);
            }
        }
    }

}
