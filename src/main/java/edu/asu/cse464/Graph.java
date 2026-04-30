package edu.asu.cse464;

import java.util.*;

public class Graph {
    private final Set<String> nodes;
    private final Set<Edge> edges;

    public Graph() {
        this.nodes = new LinkedHashSet<>();
        this.edges = new LinkedHashSet<>();
    }

    private String normalizeNodeLabel(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Node label cannot be null.");
        }

        String trimmedLabel = label.trim();

        if (trimmedLabel.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be empty.");
        }

        return trimmedLabel;
    }

    private String normalizeEndpointLabel(String label, String endpointName) {
        if (label == null) {
            throw new IllegalArgumentException(endpointName + " cannot be null.");
        }

        String trimmedLabel = label.trim();

        if (trimmedLabel.isEmpty()) {
            throw new IllegalArgumentException(endpointName + " cannot be empty.");
        }

        return trimmedLabel;
    }

    private void requireNodeExists(String label) {
        if (!nodes.contains(label)) {
            throw new IllegalArgumentException("Node does not exist: " + label);
        }
    }

    private void validateGraphSearchInput(Node src, Node dst, Algorithm algo) {
        if (src == null || dst == null) {
            throw new IllegalArgumentException("Source and destination nodes cannot be null.");
        }

        if (algo == null) {
            throw new IllegalArgumentException("Algorithm cannot be null.");
        }
    }

    public boolean addNode(String label) {
        String trimmedLabel = normalizeNodeLabel(label);
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
        String trimmedLabel = normalizeNodeLabel(label);

        requireNodeExists(trimmedLabel);

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
            String trimmedLabel = normalizeNodeLabel(label);

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
        String trimmedSrc = normalizeEndpointLabel(srcLabel, "Source");
        String trimmedDst = normalizeEndpointLabel(dstLabel, "Destination");

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

        requireNodeExists(trimmedLabel);

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

    public Path GraphSearch(Node src, Node dst, Algorithm algo) {
        validateGraphSearchInput(src, dst, algo);

        switch (algo) {
            case BFS:
                return graphSearchBFS(src, dst);
            case DFS:
                return graphSearchDFS(src, dst);
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algo);
        }
    }
    private Path graphSearchBFS(Node src, Node dst) {
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

    private Path graphSearchDFS(Node src, Node dst) {
        String srcLabel = src.getLabel();
        String dstLabel = dst.getLabel();

        if (!containsNode(srcLabel) || !containsNode(dstLabel)) {
            return null;
        }

        Set<String> visited = new HashSet<>();
        List<Node> currentPath = new ArrayList<>();

        boolean found = dfsHelper(srcLabel, dstLabel, visited, currentPath);

        if (!found) {
            return null;
        }

        return new Path(currentPath);
    }

    private boolean dfsHelper(String current, String destination, Set<String> visited, List<Node> path) {
        visited.add(current);
        path.add(new Node(current));

        if (current.equals(destination)) {
            return true;
        }

        for (String neighbor : getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                if (dfsHelper(neighbor, destination, visited, path)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
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