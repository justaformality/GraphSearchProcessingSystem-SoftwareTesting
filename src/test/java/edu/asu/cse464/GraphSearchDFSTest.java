package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphSearchDFSTest {

    @Test
    public void testDfsFindsPath() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");

        Path path = graph.GraphSearch(new Node("a"), new Node("d"));

        assertNotNull(path);
        assertEquals("a -> b -> c -> d", path.toString());
    }

    @Test
    public void testDfsReturnsNullWhenNoPathExists() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("c", "d");

        Path path = graph.GraphSearch(new Node("a"), new Node("d"));

        assertNull(path);
    }

    @Test
    public void testDfsReturnsSingleNodePathWhenSameSourceAndDestination() {
        Graph graph = new Graph();
        graph.addNode("a");

        Path path = graph.GraphSearch(new Node("a"), new Node("a"));

        assertNotNull(path);
        assertEquals("a", path.toString());
    }
}