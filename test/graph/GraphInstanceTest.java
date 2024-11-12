/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public abstract class GraphInstanceTest {
    
    // This method will provide empty Graph objects in subclasses
    protected abstract Graph<String> emptyInstance();
    
    @Test
    public void testInitialVerticesEmpty() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected new graph to have no vertices", graph.vertices().isEmpty());
    }
    
    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("should add vertex", graph.add("Bilal"));
        assertTrue("graph should contain added vertex", graph.vertices().contains("Bilal"));
    }

    @Test
    public void testAddEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("Sharjeel");
        graph.add("Bilal");
        assertEquals("expected previous weight of 0", 0, graph.set("Sharjeel", "Bilal", 5));
        assertEquals("expected new weight of 5", 5, graph.set("Sharjeel", "Bilal", 10));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("Bilal");
        assertTrue("vertex Bilal should be removed", graph.remove("Bilal"));
        assertFalse("graph should not contain removed vertex", graph.vertices().contains("Bilal"));
    }
    
    @Test
    public void testvertices() {
        Graph<String> graph = emptyInstance();
        graph.add("Jalal");
        graph.add("Bilal");
        assertEquals("graph should contain 2 vertices", 2, graph.vertices().size());
    }
}
