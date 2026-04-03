package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphSearchMergedTest {

    @Test
    public void testGraphSearchWithBfs() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");

        Path path = graph.GraphSearch(new Node("a"), new Node("d"), Algorithm.BFS);

        assertNotNull(path);
        assertEquals("a -> b -> c -> d", path.toString());
    }

    @Test
    public void testGraphSearchWithDfs() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");

        Path path = graph.GraphSearch(new Node("a"), new Node("d"), Algorithm.DFS);

        assertNotNull(path);
        assertEquals("a -> b -> c -> d", path.toString());
    }

    @Test
    public void testGraphSearchReturnsNullWhenNoPathExists() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("x", "y");

        Path path = graph.GraphSearch(new Node("a"), new Node("y"), Algorithm.BFS);

        assertNull(path);
    }
}