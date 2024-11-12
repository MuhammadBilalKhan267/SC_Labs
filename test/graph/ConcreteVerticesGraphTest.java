/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    @Override
    protected Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    @Test
    public void testVertexCreation() {
        ConcreteVerticesGraph.Vertex vertex = new ConcreteVerticesGraph.Vertex("Khan");
        assertEquals("Khan", vertex.getLabel());
        assertTrue("expected no edges initially", vertex.getEdges().isEmpty());
    }

    @Test
    public void testAddVertexInGraph() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("Bilal"));
        assertTrue(graph.vertices().contains("Bilal"));
        assertFalse("should not add duplicate vertex", graph.add("Bilal"));
    }

    @Test
    public void testAddAndSetEdgesInGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("Ali");
        graph.add("Talal");
        assertEquals("expected previous weight of 0", 0, graph.set("Ali", "Talal", 5));
        assertEquals("expected updated weight of 5", 5, graph.set("Ali", "Talal", 10));
        assertEquals("expected removal of edge", 10, graph.set("Ali", "Talal", 0));
    }

    @Test
    public void testRemoveVertexInGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("Alpha");
        graph.add("Beta");
        graph.set("Alpha", "Beta", 5);
        assertTrue("should remove vertex Alpha", graph.remove("Alpha"));
        assertFalse("Alpha should no longer be in graph", graph.vertices().contains("Alpha"));
    }

    @Test
    public void testGraphToString() {
        Graph<String> graph = emptyInstance();
        graph.add("Alpha");
        graph.add("Beta");
        graph.set("Alpha", "Beta", 5);
        String expectedString = "Graph:\nAlpha -> Beta (5)\n";
        assertEquals("graph should represent edges correctly", expectedString, graph.toString());
    }
}
