package recursion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RecursiveFileSearchTest {

    private File tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary directory structure for testing
        tempDir = Files.createTempDirectory("testDir").toFile();

        // Creating some files and subdirectories
        new File(tempDir, "bilal.txt").createNewFile();
        new File(tempDir, "Khan.txt").createNewFile(); // Differently cased
        new File(tempDir, "lol.log").createNewFile();

        File subDir = new File(tempDir, "abc");
        subDir.mkdir();
        new File(subDir, "bilal.txt").createNewFile();
        new File(subDir, "khan.txt").createNewFile();
    }

    @Test
    public void testSingleFileCaseSensitiveFound() {
        RecursiveFileSearch caseSensitiveSearch = new RecursiveFileSearch(true, true);
        List<String> results = caseSensitiveSearch.searchFile(tempDir, "bilal.txt");
        assertEquals(2, results.size(), "Should find two instances of 'bilal.txt'");
    }

    @Test
    public void testSingleFileCaseSensitiveNotFound() {
        RecursiveFileSearch caseSensitiveSearch = new RecursiveFileSearch(true, true);
        List<String> results = caseSensitiveSearch.searchFile(tempDir, "nonexistent.txt");
        assertTrue(results.isEmpty(), "Should return an empty list for non-existent file");
    }

    @Test
    public void testSingleFileCaseInsensitiveFound() {
        RecursiveFileSearch caseInsensitiveSearch = new RecursiveFileSearch(false, false);
        List<String> results = caseInsensitiveSearch.searchFile(tempDir, "Khan.txt");
        
        // Print debug information
        System.out.println("Search results for 'Khan.txt': " + results.size());
        
        assertEquals(1, results.size(), "Should find one instance of 'Khan.txt' ignoring case");
    }
    @Test
    public void testMultipleFilesSearch() {
        RecursiveFileSearch caseInsensitiveSearch = new RecursiveFileSearch(false, true);
        Map<String, List<String>> results = caseInsensitiveSearch.searchFiles(tempDir, Arrays.asList("bilal.txt", "khan.txt"));
        assertEquals(2, results.get("bilal.txt").size(), "Should find two instances of 'bilal.txt'");
        assertEquals(2, results.get("khan.txt").size(), "Should find one instance of 'khan.txt'");
    }
    
    @Test
    public void testMultipleFilesSearchSensitive() {
        RecursiveFileSearch caseInsensitiveSearch = new RecursiveFileSearch(true, true);
        Map<String, List<String>> results = caseInsensitiveSearch.searchFiles(tempDir, Arrays.asList("bilal.txt", "khan.txt"));
        assertEquals(2, results.get("bilal.txt").size(), "Should find two instances of 'bilal.txt'");
        assertEquals(1, results.get("khan.txt").size(), "Should find one instance of 'khan.txt'");
    }

    @Test
    public void testCaseInsensitiveFindAllOccurrences() {
        RecursiveFileSearch caseInsensitiveSearch = new RecursiveFileSearch(false, true);
        Map<String, List<String>> results = caseInsensitiveSearch.searchFiles(tempDir, Arrays.asList("bilal.txt"));
        assertEquals(2, results.get("bilal.txt").size(), "Should find all instances of 'bilal.txt' ignoring case");
    }

    @Test
    public void testSingleFileCaseInsensitiveFoundWithDifferentCase() {
        RecursiveFileSearch caseInsensitiveSearch = new RecursiveFileSearch(false, true);
        List<String> results = caseInsensitiveSearch.searchFile(tempDir, "BILAL.TXT");
        assertEquals(2, results.size(), "Should find 'bilal.txt' when searching case-insensitively");
    }

    @Test
    public void testDirectoryDoesNotExist() {
        RecursiveFileSearch caseSensitiveSearch = new RecursiveFileSearch(true, true);
        File nonExistentDir = new File(tempDir, "nonExistentDir");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                caseSensitiveSearch.searchFiles(nonExistentDir, Arrays.asList("bilal.txt")),
                "Expected exception for non-existent directory");
        assertEquals("The specified path is not a directory.", thrown.getMessage());
    }

    // Argument validation tests
    @Test
    public void testArgumentValidationInsufficientArgs() {
        String[] args = {"dir"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                RecursiveFileSearch.validateArguments(args),
                "Expected exception for insufficient arguments");
        assertEquals("Usage: java RecursiveFileSearch <directory> <filename1> [<filename2> ...] [-ignoreCase | -caseSensitive] [-findAll | -findFirst]", thrown.getMessage());
    }

    @Test
    public void testArgumentValidationWithIgnoreCase() {
        String[] args = {tempDir.getAbsolutePath(), "bilal.txt", "-ignoreCase"};
        assertDoesNotThrow(() -> RecursiveFileSearch.validateArguments(args), "Should not throw exception with valid arguments");
    }

    @Test
    public void testArgumentValidationWithCaseSensitive() {
        String[] args = {tempDir.getAbsolutePath(), "bilal.txt", "-caseSensitive"};
        assertDoesNotThrow(() -> RecursiveFileSearch.validateArguments(args), "Should not throw exception with valid arguments");
    }

    @Test
    public void testArgumentValidationWithFindAll() {
        String[] args = {tempDir.getAbsolutePath(), "bilal.txt", "-findAll"};
        assertDoesNotThrow(() -> RecursiveFileSearch.validateArguments(args), "Should not throw exception with valid arguments");
    }

    @Test
    public void testArgumentValidationWithFindFirst() {
        String[] args = {tempDir.getAbsolutePath(), "bilal.txt", "-findFirst"};
        assertDoesNotThrow(() -> RecursiveFileSearch.validateArguments(args), "Should not throw exception with valid arguments");
    }
}
