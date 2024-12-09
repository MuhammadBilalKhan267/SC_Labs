package lab12;
import java.util.*;

/*
 * Class for recursive binary search on string and integer arrays
 */
public class RecursiveBinarySearch {

	/**
     * Recursive binary search for integers.
     * @param array The sorted array to search.
     * @param target The target integer to find.
     * @param low The starting index of the search range.
     * @param high The ending index of the search range.
     * @return The index of the target, or -1 if not found.
     */
    public static int binarySearchRecursive(int[] array, int target, int low, int high) {
        if (array == null || array.length == 0) {
            return -1; // Handle null or empty array
        }

        if (low > high) {
            return -1; // Base case: target not found
        }

        int mid = low + (high - low) / 2; // Avoid overflow
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, high);
        } else {
            return binarySearchRecursive(array, target, low, mid - 1);
        }
    }

    /**
     * Recursive binary search for strings.
     * @param array The sorted array to search.
     * @param target The target string to find.
     * @param low The starting index of the search range.
     * @param high The ending index of the search range.
     * @return The index of the target, or -1 if not found.
     */
    public static int binarySearchRecursive(String[] array, String target, int low, int high) {
        if (array == null || array.length == 0) {
            return -1;
        }

        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        int comparison = target.compareTo(array[mid]);
        if (comparison == 0) {
            return mid;
        } else if (comparison > 0) {
            return binarySearchRecursive(array, target, mid + 1, high);
        } else {
            return binarySearchRecursive(array, target, low, mid - 1);
        }
    }

    /**
     * Finds all indices of a target in a sorted array.
     * @param array The sorted array to search.
     * @param target The target integer to find.
     * @return A list of indices where the target is found.
     */
    public static List<Integer> findAllIndices(int[] array, int target) {
        List<Integer> indices = new ArrayList<>();
        findAllIndicesHelper(array, target, 0, array.length - 1, indices);
        return indices;
    }

    private static void findAllIndicesHelper(int[] array, int target, int low, int high, List<Integer> indices) {
        if (array == null || array.length == 0 || low > high) {
            return;
        }

        int mid = low + (high - low) / 2;
        if (array[mid] == target) {
            indices.add(mid);
            System.out.print(mid);
            findAllIndicesHelper(array, target, low, mid - 1, indices); // Search left
            findAllIndicesHelper(array, target, mid + 1, high, indices); // Search right
        } else if (array[mid] < target) {
            findAllIndicesHelper(array, target, mid + 1, high, indices);
        } else {
            findAllIndicesHelper(array, target, low, mid - 1, indices);
        }
    }
}