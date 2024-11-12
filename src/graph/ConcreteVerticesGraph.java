/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Static Vertex class to avoid needing an outer instance
    public static class Vertex {
        private final String label;
        private final Map<String, Integer> edges;

        public Vertex(String label) {
            this.label = label;
            this.edges = new HashMap<>();
        }

        public String getLabel() {
            return label;
        }

        public Map<String, Integer> getEdges() {
            return edges;
        }

        public void setEdge(String target, int weight) {
            if (weight == 0) {
                edges.remove(target);
            } else {
                edges.put(target, weight);
            }
        }

        @Override
        public String toString() {
            return label + edges.toString();
        }
    }

    @Override
    public boolean add(String vertex) {
        for (Vertex v : vertices) {
            if (v.getLabel().equals(vertex)) return false;
        }
        vertices.add(new Vertex(vertex));
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        Vertex srcVertex = null, tgtVertex = null;

        for (Vertex v : vertices) {
            if (v.getLabel().equals(source)) srcVertex = v;
            if (v.getLabel().equals(target)) tgtVertex = v;
        }

        if (srcVertex == null) {
            srcVertex = new Vertex(source);
            vertices.add(srcVertex);
        }
        if (tgtVertex == null) {
            tgtVertex = new Vertex(target);
            vertices.add(tgtVertex);
        }

        int previousWeight = srcVertex.getEdges().getOrDefault(target, 0);
        srcVertex.setEdge(target, weight);
        return previousWeight;
    }

    @Override
    public boolean remove(String vertex) {
        boolean removed = vertices.removeIf(v -> v.getLabel().equals(vertex));
        for (Vertex v : vertices) {
            v.setEdge(vertex, 0);
        }
        return removed;
    }

    @Override
    public Set<String> vertices() {
        Set<String> vertexLabels = new HashSet<>();
        for (Vertex v : vertices) {
            vertexLabels.add(v.getLabel());
        }
        return vertexLabels;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Graph:\n");
        for (Vertex vertex : vertices) {
            for (Map.Entry<String, Integer> edge : vertex.getEdges().entrySet()) {
                result.append(vertex.getLabel()).append(" -> ")
                      .append(edge.getKey()).append(" (")
                      .append(edge.getValue()).append(")\n");
            }
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