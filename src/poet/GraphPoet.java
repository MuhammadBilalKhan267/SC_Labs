package poet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * A graph-based poetry generator.
 */
public class GraphPoet {

    // Internal representation of the graph as an adjacency map
    private final Map<String, Map<String, Integer>> graph = new HashMap<>();

    // Abstraction function:
    //   Each key in the graph map is a word (vertex).
    //   Each value is a map where keys are adjacent words and values are edge weights.
    // Representation invariant:
    //   - The graph map and all its keys and values are non-null.
    //   - Edge weights are positive integers.
    // Safety from rep exposure:
    //   - The graph is private and never exposed directly to clients.

    /**
     * Create a new poet with the graph from the corpus.
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        List<String> lines = Files.readAllLines(corpus.toPath());
        String content = String.join(" ", lines);
        buildGraph(content);
        checkRep();
    }

    // Build the graph from the given corpus content.
    private void buildGraph(String content) {
        String[] words = content.toLowerCase().split("\\s+");
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            // Update graph for the current pair of words
            graph.putIfAbsent(w1, new HashMap<>());
            Map<String, Integer> neighbors = graph.get(w1);
            neighbors.put(w2, neighbors.getOrDefault(w2, 0) + 1);
        }
    }

    // Check representation invariant.
    private void checkRep() {
        assert graph != null;
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            assert entry.getKey() != null;
            Map<String, Integer> neighbors = entry.getValue();
            assert neighbors != null;
            for (Map.Entry<String, Integer> edge : neighbors.entrySet()) {
                assert edge.getKey() != null;
                assert edge.getValue() > 0;
            }
        }
    }

    /**
     * Generate a poem.
     *
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            result.append(words[i]);

            if (i < words.length - 1) {
                String w1 = words[i].toLowerCase();
                String w2 = words[i + 1].toLowerCase();
                String bridge = findBridgeWord(w1, w2);

                if (bridge != null) {
                    result.append(" ").append(bridge);
                }
            }

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // Finds the bridge word with the maximum weight between two words.
    private String findBridgeWord(String w1, String w2) {
        Map<String, Integer> neighbors = graph.getOrDefault(w1, Collections.emptyMap());
        String bestBridge = null;
        int maxWeight = 0;

        for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
            String bridge = entry.getKey();
            int weight1 = entry.getValue();
            int weight2 = graph.getOrDefault(bridge, Collections.emptyMap()).getOrDefault(w2, 0);
            int totalWeight = weight1 + weight2;

            if (weight2 > 0 && totalWeight > maxWeight) {
                maxWeight = totalWeight;
                bestBridge = bridge;
            }
        }

        return bestBridge;
    }

    @Override
    public String toString() {
        return "GraphPoet{" + "graph=" + graph + '}';
    }
}
