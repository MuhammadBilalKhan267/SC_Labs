package poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    // Testing strategy
    // - Test with an empty corpus.
    // - Test with a single-word corpus.
    // - Test with a multi-word corpus:
    //   - Check case-insensitivity.
    //   - Check bridge word insertion.
    //   - Check no bridge word inserted when no path exists.
    // - Test input with:
    //   - No adjacent words needing a bridge.
    //   - Words needing multiple bridges.
    //   - Words at the start, middle, and end of the input.
    // - Test corpus and input containing "Bilal".

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyCorpus() throws IOException {
        File corpus = createTempCorpus("");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Hello Bilal!";
        String poem = poet.poem(input);

        assertEquals("Empty corpus should produce identical input", input, poem);
    }

    @Test
    public void testSingleWordCorpus() throws IOException {
        File corpus = createTempCorpus("Bilal");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Hello Bilal!";
        String poem = poet.poem(input);

        assertEquals("No bridge words possible with a single-word corpus", input, poem);
    }

    @Test
    public void testBridgeWordInsertion() throws IOException {
        File corpus = createTempCorpus("Bilal loves poetry");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Bilal poetry";
        String poem = poet.poem(input);

        assertEquals("Bilal loves poetry", poem);
    }

    @Test
    public void testNoBridgeWordWhenNoPathExists() throws IOException {
        File corpus = createTempCorpus("Hello world");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Bilal loves poetry";
        String poem = poet.poem(input);

        assertEquals("Bilal loves poetry", poem);
    }

    @Test
    public void testCaseInsensitiveCorpus() throws IOException {
        File corpus = createTempCorpus("bilal LOVES Poetry");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Bilal poetry";
        String poem = poet.poem(input);

        assertEquals("Bilal loves poetry", poem);
    }

    @Test
    public void testMultipleBridgeWords() throws IOException {
        File corpus = createTempCorpus("Bilal loves poetry and Bilal loves Java");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Bilal poetry Java";
        String poem = poet.poem(input);

        assertEquals("Bilal loves poetry Java", poem);
    }

    // Helper method to create a temporary file with the given content
    private File createTempCorpus(String content) throws IOException {
        File tempFile = Files.createTempFile("corpus", ".txt").toFile();
        Files.write(Paths.get(tempFile.toURI()), content.getBytes());
        return tempFile;
    }
}
