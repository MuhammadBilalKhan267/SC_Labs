package lab12;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RecursiveBinarySearchTest {
	
	@Test(expected=AssertionError.class) 
	public void testAssertionsEnabled() { 
		assert false; // make sure assertions are enabled with VM argument: -ea } // TODO tests for Commands.differentiate() and Commands.simplify() }
	}
	// Integer binary search tests
    @Test
    public void testBinarySearchRecursiveIntegers_TargetPresent() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(2, RecursiveBinarySearch.binarySearchRecursive(array, 5, 0, array.length - 1));
    }

    @Test
    public void testBinarySearchRecursiveIntegers_TargetAbsent() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(array, 6, 0, array.length - 1));
    }

    @Test
    public void testBinarySearchRecursiveIntegers_EmptyArray() {
        int[] array = {};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(array, 5, 0, array.length - 1));
    }

    @Test
    public void testBinarySearchRecursiveIntegers_NullArray() {
        int[] array = null;
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(array, 5, 0, 0));
    }

    // String binary search tests
    @Test
    public void testBinarySearchRecursiveStrings_TargetPresent() {
        String[] array = {"apple", "banana", "cherry", "date"};
        assertEquals(2, RecursiveBinarySearch.binarySearchRecursive(array, "cherry", 0, array.length - 1));
    }

    @Test
    public void testBinarySearchRecursiveStrings_TargetAbsent() {
        String[] array = {"apple", "banana", "cherry", "date"};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(array, "fig", 0, array.length - 1));
    }

    @Test
    public void testBinarySearchRecursiveStrings_CaseSensitive() {
        String[] array = {"Apple", "Banana", "Cherry"};
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(array, "apple", 0, array.length - 1));
    }

    // Find all indices tests
    @Test
    public void testFindAllIndices_MultipleOccurrences() {
        int[] array = {1, 3, 5, 5, 5, 7};
        List<Integer> result = RecursiveBinarySearch.findAllIndices(array, 5);
        assertEquals(List.of(2, 4, 3), result);
    }

    @Test
    public void testFindAllIndices_SingleOccurrence() {
        int[] array = {1, 3, 5, 7};
        List<Integer> result = RecursiveBinarySearch.findAllIndices(array, 3);
        assertEquals(List.of(1), result);
    }

    @Test
    public void testFindAllIndices_TargetAbsent() {
        int[] array = {1, 3, 5, 7};
        List<Integer> result = RecursiveBinarySearch.findAllIndices(array, 9);
        assertEquals(List.of(), result);
    }
}