package edu.asu.cse464;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Path {
    private final List<Node> nodes;

    public Path(List<Node> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("Path nodes cannot be null or empty.");
        }

        this.nodes = new ArrayList<>(nodes);
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    @Override
    public String toString() {
        return nodes.stream()
                .map(Node::toString)
                .collect(Collectors.joining(" -> "));
    }

    public String toDetailedString(){
        return "Path{nodes=" + nodes.stream()
                .map(node -> "Node{" + node.getLabel() + "}")
                .collect(Collectors.joining(",", "[","]")) + "}";
    }
}