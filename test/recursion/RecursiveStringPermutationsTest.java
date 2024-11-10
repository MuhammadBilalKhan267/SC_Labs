package recursion;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;

public class RecursiveStringPermutationsTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	 @Test
    public void testEmptyString() {
        List<String> result = RecursiveStringPermutations.generatePermutations("", true);
        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }

    @Test
    public void testNullString() {
        List<String> result = RecursiveStringPermutations.generatePermutations(null, true);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleCharacterString() {
        List<String> result = RecursiveStringPermutations.generatePermutations("B", true);
        assertEquals(1, result.size());
        assertEquals("B", result.get(0));
    }

    @Test
    public void testTwoCharacterString() {
        List<String> result = RecursiveStringPermutations.generatePermutations("BK", true);
        assertEquals(2, result.size());
        assertTrue(result.contains("BK"));
        assertTrue(result.contains("KB"));
    }

    @Test
    public void testDuplicateCharactersWithIncludeDuplicates() {
        List<String> result = RecursiveStringPermutations.generatePermutations("BKK", true);
        assertEquals(6, result.size());
        assertTrue(result.contains("KKB"));
        assertTrue(result.contains("KBK"));
        assertTrue(result.contains("BKK"));
    }

    @Test
    public void testDuplicateCharactersWithoutIncludeDuplicates() {
        List<String> result = RecursiveStringPermutations.generatePermutations("BKK", false);
        assertEquals(3, result.size());
        assertTrue(result.contains("KKB"));
        assertTrue(result.contains("KBK"));
        assertTrue(result.contains("BKK"));
    }

    @Test
    public void testLargerString() {
        List<String> result = RecursiveStringPermutations.generatePermutations("MBK", true);
        assertEquals(6, result.size());
        assertTrue(result.contains("MBK"));
        assertTrue(result.contains("MKB"));
        assertTrue(result.contains("BMK"));
        assertTrue(result.contains("BKM"));
        assertTrue(result.contains("KMB"));
        assertTrue(result.contains("KBM"));
    }
}
