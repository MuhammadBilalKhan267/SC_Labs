/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;

public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Static Edge class for better structure
    public static class Edge {
        private final String source;
        private final String target;
        private final int weight;

        public Edge(String source, String target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        public String getSource() {
            return source;
        }

        public String getTarget() {
            return target;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return source + " -> " + target + " (" + weight + ")";
        }
    }

    @Override
    public boolean add(String vertex) {
        return vertices.add(vertex);
    }

    @Override
    public int set(String source, String target, int weight) {
        // Add vertices if they don't exist
        vertices.add(source);
        vertices.add(target);

        // Check for existing edge and update weight if it exists
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                int prevWeight = edge.getWeight();
                edges.remove(edge);
                if (weight > 0) {
                    edges.add(new Edge(source, target, weight));
                }
                return prevWeight;
            }
        }

        // If no existing edge, add a new one
        if (weight > 0) {
            edges.add(new Edge(source, target, weight));
        }
        return 0; // If no edge exists, return 0
    }

    @Override
    public boolean remove(String vertex) {
        boolean removed = vertices.remove(vertex);
        // Also remove edges associated with this vertex
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        return removed;
    }

    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Graph:\n");
        for (Edge edge : edges) {
            result.append(edge.toString()).append("\n");
        }
        return result.toString();
    }
    
	@Override
	public Map<String, Integer> sources(String target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> targets(String source) {
		// TODO Auto-generated method stub
		return null;
	}
}
