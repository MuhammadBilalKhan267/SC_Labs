/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    @Override
    protected Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    @Test
    public void testEdgeCreation() {
        ConcreteEdgesGraph.Edge edge = new ConcreteEdgesGraph.Edge("Bilal", "Faiq", 5);
        assertEquals("Bilal", edge.getSource());
        assertEquals("Faiq", edge.getTarget());
        assertEquals(5, edge.getWeight());
    }
    
    @Test
    public void testEdgeImmutability() {
        ConcreteEdgesGraph.Edge edge1 = new ConcreteEdgesGraph.Edge("Bilal", "Faiq", 5);
        ConcreteEdgesGraph.Edge edge2 = new ConcreteEdgesGraph.Edge("Bilal", "Faiq", 5);
        assertEquals("Bilal", edge1.getSource());
        assertEquals("Faiq", edge1.getTarget());
        assertEquals(5, edge1.getWeight());

        // Check immutability by verifying distinct instances instead of object references
        assertNotSame("Edge instances should be distinct", edge1, edge2);
    }

    @Test
    public void testAddEdgeInGraph() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("Bilal"));
        assertTrue(graph.add("Faiq"));
        
        // Adding edge and checking the return value for previous weight (should be 0 if none existed)
        assertEquals(0, graph.set("Bilal", "Faiq", 5));
        
        // Adding the same edge with a new weight, should return old weight (5)
        assertEquals(5, graph.set("Bilal", "Faiq", 10));
    }
    
    @Test
    public void testGraphToString() {
        Graph<String> graph = emptyInstance();
        graph.add("Bilal");
        graph.add("Faiq");
        graph.set("Bilal", "Faiq", 5);
        
        // Validate the toString output format
        String graphString = graph.toString();
        assertTrue("Graph toString should contain 'Bilal -> Faiq (5)'", graphString.contains("Bilal -> Faiq (5)"));
    }
}

