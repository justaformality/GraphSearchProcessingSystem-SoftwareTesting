package edu.asu.cse464;

import java.util.Objects;

public class Node {
    private final String label;

    public Node(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Node label cannot be null.");
        }

        this.label = label.trim();

        if (this.label.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be empty.");
        }
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return label.equals(node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}