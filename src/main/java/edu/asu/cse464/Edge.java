package edu.asu.cse464;

import java.util.Objects;

public class Edge {
    private final String src;
    private final String dst;

    public Edge(String src, String dst) {
        if (src == null || dst == null) {
            throw new IllegalArgumentException("Source and destination cannot be null.");
        }

        this.src = src.trim();
        this.dst = dst.trim();

        if (this.src.isEmpty() || this.dst.isEmpty()) {
            throw new IllegalArgumentException("Source and destination cannot be empty.");
        }
    }

    public String getSrc() {
        return src;
    }

    public String getDst() {
        return dst;
    }

    @Override
    public String toString() {
        return src + " -> " + dst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return src.equals(edge.src) && dst.equals(edge.dst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dst);
    }
}