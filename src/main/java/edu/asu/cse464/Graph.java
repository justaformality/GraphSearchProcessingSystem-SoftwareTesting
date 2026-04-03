package edu.asu.cse464;

import java.util.LinkedHashSet;
import java.util.Set;

public class Graph {
    private final Set<String> nodes;
    private final Set<Edge> edges;

    public Graph() {
        this.nodes = new LinkedHashSet<>();
        this.edges = new LinkedHashSet<>();
    }

    public boolean addNode(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Node label cannot be null.");
        }

        String trimmedLabel = label.trim();

        if (trimmedLabel.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be empty.");
        }

        return nodes.add(trimmedLabel);
    }

    public void addNodes(String[] labels) {
        if (labels == null) {
            throw new IllegalArgumentException("Node label array cannot be null.");
        }

        for (String label : labels) {
            addNode(label);
        }
    }

    public boolean addEdge(String srcLabel, String dstLabel) {
        addNode(srcLabel);
        addNode(dstLabel);

        Edge edge = new Edge(srcLabel, dstLabel);
        return edges.add(edge);
    }

    public Set<String> getNodes() {
        return new LinkedHashSet<>(nodes);
    }

    public Set<Edge> getEdges() {
        return new LinkedHashSet<>(edges);
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Number of nodes: ").append(getNodeCount()).append(System.lineSeparator());
        sb.append("Nodes: ").append(nodes).append(System.lineSeparator());
        sb.append("Number of edges: ").append(getEdgeCount()).append(System.lineSeparator());
        sb.append("Edges:").append(System.lineSeparator());

        for (Edge edge : edges) {
            sb.append(edge).append(System.lineSeparator());
        }

        return sb.toString();
    }
}