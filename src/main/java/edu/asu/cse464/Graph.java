package edu.asu.cse464;

import java.util.*;

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

    public boolean removeNode(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Node label cannot be null.");
        }

        String trimmedLabel = label.trim();

        if (trimmedLabel.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be empty.");
        }

        if (!nodes.contains(trimmedLabel)) {
            throw new IllegalArgumentException("Node does not exist: " + trimmedLabel);
        }

        edges.removeIf(edge ->
                edge.getSrc().equals(trimmedLabel) || edge.getDst().equals(trimmedLabel));

        return nodes.remove(trimmedLabel);
    }

    public void removeNodes(String[] labels) {
        if (labels == null) {
            throw new IllegalArgumentException("Node label array cannot be null.");
        }

        List<String> trimmedLabels = new ArrayList<>();

        for (String label : labels) {
            if (label == null) {
                throw new IllegalArgumentException("Node label cannot be null.");
            }

            String trimmedLabel = label.trim();

            if (trimmedLabel.isEmpty()) {
                throw new IllegalArgumentException("Node label cannot be empty.");
            }

            if (!nodes.contains(trimmedLabel)) {
                throw new IllegalArgumentException("Node does not exist: " + trimmedLabel);
            }

            trimmedLabels.add(trimmedLabel);
        }

        for (String trimmedLabel : trimmedLabels) {
            removeNode(trimmedLabel);
        }
    }

    public boolean removeEdge(String srcLabel, String dstLabel) {
        if (srcLabel == null || dstLabel == null) {
            throw new IllegalArgumentException("Source and destination cannot be null.");
        }

        String trimmedSrc = srcLabel.trim();
        String trimmedDst = dstLabel.trim();

        if (trimmedSrc.isEmpty() || trimmedDst.isEmpty()) {
            throw new IllegalArgumentException("Source and destination cannot be empty.");
        }

        if (!nodes.contains(trimmedSrc)) {
            throw new IllegalArgumentException("Source node does not exist: " + trimmedSrc);
        }

        if (!nodes.contains(trimmedDst)) {
            throw new IllegalArgumentException("Destination node does not exist: " + trimmedDst);
        }

        Edge edge = new Edge(trimmedSrc, trimmedDst);

        if (!edges.contains(edge)) {
            throw new IllegalArgumentException("Edge does not exist: " + edge);
        }

        return edges.remove(edge);
    }

    public boolean containsNode(String label) {
        if (label == null) {
            return false;
        }

        return nodes.contains(label.trim());
    }

    public boolean containsEdge(String srcLabel, String dstLabel) {
        if (srcLabel == null || dstLabel == null) {
            return false;
        }

        return edges.contains(new Edge(srcLabel.trim(), dstLabel.trim()));
    }

    public Set<String> getNeighbors(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Node label cannot be null.");
        }

        String trimmedLabel = label.trim();

        if (!nodes.contains(trimmedLabel)) {
            throw new IllegalArgumentException("Node does not exist: " + trimmedLabel);
        }

        Set<String> neighbors = new LinkedHashSet<>();

        for (Edge edge : edges) {
            if (edge.getSrc().equals(trimmedLabel)) {
                neighbors.add(edge.getDst());
            }
        }

        return neighbors;
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

    public Path GraphSearch(Node src, Node dst) {
        if (src == null || dst == null) {
            throw new IllegalArgumentException("Source and destination nodes cannot be null.");
        }

        String srcLabel = src.getLabel();
        String dstLabel = dst.getLabel();

        if (!containsNode(srcLabel) || !containsNode(dstLabel)) {
            return null;
        }

        if (srcLabel.equals(dstLabel)) {
            return new Path(List.of(new Node(srcLabel)));
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.offer(srcLabel);
        visited.add(srcLabel);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);

                    if (neighbor.equals(dstLabel)) {
                        return buildPath(srcLabel, dstLabel, parent);
                    }
                }
            }
        }

        return null;
    }

    private Path buildPath(String srcLabel, String dstLabel, Map<String, String> parent) {
        List<Node> pathNodes = new ArrayList<>();
        String current = dstLabel;

        while (current != null) {
            pathNodes.add(new Node(current));
            if (current.equals(srcLabel)) {
                break;
            }
            current = parent.get(current);
        }

        Collections.reverse(pathNodes);

        if (!pathNodes.get(0).getLabel().equals(srcLabel)) {
            return null;
        }

        return new Path(pathNodes);
    }
}