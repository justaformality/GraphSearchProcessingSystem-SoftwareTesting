package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphBasicTest {

    @Test
    public void testAddSingleNode() {
        Graph graph = new Graph();

        boolean result = graph.addNode("a");

        assertTrue(result);
        assertEquals(1, graph.getNodeCount());
        assertTrue(graph.getNodes().contains("a"));
    }

    @Test
    public void testAddDuplicateNode() {
        Graph graph = new Graph();

        boolean firstAdd = graph.addNode("a");
        boolean secondAdd = graph.addNode("a");

        assertTrue(firstAdd);
        assertFalse(secondAdd);
        assertEquals(1, graph.getNodeCount());
    }

    @Test
    public void testAddMultipleNodes() {
        Graph graph = new Graph();

        graph.addNodes(new String[]{"a", "b", "c"});

        assertEquals(3, graph.getNodeCount());
        assertTrue(graph.getNodes().contains("a"));
        assertTrue(graph.getNodes().contains("b"));
        assertTrue(graph.getNodes().contains("c"));
    }

    @Test
    public void testAddSingleEdge() {
        Graph graph = new Graph();

        boolean result = graph.addEdge("a", "b");

        assertTrue(result);
        assertEquals(2, graph.getNodeCount());
        assertEquals(1, graph.getEdgeCount());
        assertTrue(graph.getEdges().contains(new Edge("a", "b")));
    }

    @Test
    public void testAddDuplicateEdge() {
        Graph graph = new Graph();

        boolean firstAdd = graph.addEdge("a", "b");
        boolean secondAdd = graph.addEdge("a", "b");

        assertTrue(firstAdd);
        assertFalse(secondAdd);
        assertEquals(2, graph.getNodeCount());
        assertEquals(1, graph.getEdgeCount());
    }

    @Test
    public void testDirectedEdgeDifference() {
        Graph graph = new Graph();

        graph.addEdge("a", "b");
        graph.addEdge("b", "a");

        assertEquals(2, graph.getEdgeCount());
        assertTrue(graph.getEdges().contains(new Edge("a", "b")));
        assertTrue(graph.getEdges().contains(new Edge("b", "a")));
    }

    @Test
    public void testAddNodeRejectsNull() {
        Graph graph = new Graph();

        assertThrows(IllegalArgumentException.class, () -> graph.addNode(null));
    }

    @Test
    public void testAddNodeRejectsBlank() {
        Graph graph = new Graph();

        assertThrows(IllegalArgumentException.class, () -> graph.addNode("   "));
    }

    @Test
    public void testToStringFormat() {
        Graph graph = new Graph();
        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a", "b");

        String expected =
                "Number of nodes: 2" + System.lineSeparator() +
                        "Nodes: [a, b]" + System.lineSeparator() +
                        "Number of edges: 1" + System.lineSeparator() +
                        "Edges:" + System.lineSeparator() +
                        "a -> b" + System.lineSeparator();

        assertEquals(expected, graph.toString());
    }
}