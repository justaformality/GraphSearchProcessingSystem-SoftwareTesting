package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphSearchBFSTest {

    @Test
    public void testBfsFindsPath() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");

        Path path = graph.GraphSearch(new Node("a"), new Node("d"), Algorithm.BFS);

        assertNotNull(path);
        assertEquals("a -> b -> c -> d", path.toString());
    }

    @Test
    public void testBfsReturnsNullWhenNoPathExists() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("c", "d");

        Path path = graph.GraphSearch(new Node("a"), new Node("d"), Algorithm.BFS);

        assertNull(path);
    }

    @Test
    public void testBfsReturnsSingleNodePathWhenSameSourceAndDestination() {
        Graph graph = new Graph();
        graph.addNode("a");

        Path path = graph.GraphSearch(new Node("a"), new Node("a"), Algorithm.BFS);

        assertNotNull(path);
        assertEquals("a", path.toString());
    }
}