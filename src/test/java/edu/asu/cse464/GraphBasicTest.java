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

    @Test
    public void testRemoveSingleNodeSuccessfully() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("a", "c");

        boolean removed = graph.removeNode("b");

        assertTrue(removed);
        assertFalse(graph.getNodes().contains("b"));
        assertEquals(2, graph.getNodeCount());
        assertEquals(1, graph.getEdgeCount());
        assertFalse(graph.getEdges().contains(new Edge("a", "b")));
        assertFalse(graph.getEdges().contains(new Edge("b", "c")));
        assertTrue(graph.getEdges().contains(new Edge("a", "c")));
    }

    @Test
    public void testRemoveMultipleNodesSuccessfully() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("a", "d");

        graph.removeNodes(new String[]{"b", "c"});

        assertEquals(2, graph.getNodeCount());
        assertTrue(graph.getNodes().contains("a"));
        assertTrue(graph.getNodes().contains("d"));
        assertFalse(graph.getNodes().contains("b"));
        assertFalse(graph.getNodes().contains("c"));
        assertEquals(1, graph.getEdgeCount());
        assertTrue(graph.getEdges().contains(new Edge("a", "d")));
    }

    @Test
    public void testRemoveEdgeSuccessfully() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");

        boolean removed = graph.removeEdge("a", "b");

        assertTrue(removed);
        assertEquals(3, graph.getNodeCount());
        assertEquals(1, graph.getEdgeCount());
        assertFalse(graph.getEdges().contains(new Edge("a", "b")));
        assertTrue(graph.getEdges().contains(new Edge("b", "c")));
    }

    @Test
    public void testRemoveNonExistentNodeThrowsException() {
        Graph graph = new Graph();
        graph.addNode("a");

        assertThrows(IllegalArgumentException.class, () -> graph.removeNode("x"));
    }

    @Test
    public void testRemoveMultipleNodesWithMissingNodeThrowsException() {
        Graph graph = new Graph();
        graph.addNodes(new String[]{"a", "b", "c"});

        assertThrows(IllegalArgumentException.class,
                () -> graph.removeNodes(new String[]{"a", "x"}));
    }

    @Test
    public void testRemoveNonExistentEdgeThrowsException() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");

        assertThrows(IllegalArgumentException.class,
                () -> graph.removeEdge("b", "a"));
    }
}