package edu.asu.cse464;

public interface GraphSearchStrategy {
    Path search(Graph graph, Node src, Node dst);
}
