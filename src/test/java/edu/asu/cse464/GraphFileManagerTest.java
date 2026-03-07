package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GraphFileManagerTest {

    @Test
    public void testOutputGraphWritesExpectedContent() throws IOException {
        Graph graph = new Graph();
        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a", "b");

        Path tempFile = Files.createTempFile("graph_output", ".txt");

        GraphFileManager.outputGraph(graph, tempFile.toString());

        String actualContent = Files.readString(tempFile);
        String expectedContent = graph.toString();

        assertEquals(expectedContent, actualContent);

        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testOutputGraphRejectsNullGraph() {
        assertThrows(IllegalArgumentException.class, () ->
                GraphFileManager.outputGraph(null, "somefile.txt"));
    }

    @Test
    public void testOutputGraphRejectsBlankPath() {
        Graph graph = new Graph();

        assertThrows(IllegalArgumentException.class, () ->
                GraphFileManager.outputGraph(graph, "   "));
    }

    @Test
    public void testOutputDOTGraphWritesExpectedContent() throws IOException {
        Graph graph = new Graph();
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");

        Path tempFile = Files.createTempFile("graph_output", ".dot");

        GraphFileManager.outputDOTGraph(graph, tempFile.toString());

        String actualContent = Files.readString(tempFile);
        String expectedContent =
                "digraph G {" + System.lineSeparator() +
                        "    a;" + System.lineSeparator() +
                        "    b;" + System.lineSeparator() +
                        "    c;" + System.lineSeparator() +
                        "    a -> b;" + System.lineSeparator() +
                        "    b -> c;" + System.lineSeparator() +
                        "}" + System.lineSeparator();

        assertEquals(expectedContent, actualContent);

        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testOutputDOTGraphRejectsNullGraph() {
        assertThrows(IllegalArgumentException.class, () ->
                GraphFileManager.outputDOTGraph(null, "graph.dot"));
    }

    @Test
    public void testOutputDOTGraphRejectsBlankPath() {
        Graph graph = new Graph();

        assertThrows(IllegalArgumentException.class, () ->
                GraphFileManager.outputDOTGraph(graph, "   "));
    }

    @Test
    public void testOutputGraphicsCreatesPngFile() throws IOException, InterruptedException {
        Graph graph = new Graph();
        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a", "b");

        Path tempFile = Files.createTempFile("graph_image", ".png");

        GraphFileManager.outputGraphics(graph, tempFile.toString(), "png");

        assertTrue(Files.exists(tempFile));
        assertTrue(Files.size(tempFile) > 0);

        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testOutputGraphicsRejectsNullGraph() {
        assertThrows(IllegalArgumentException.class, () ->
                GraphFileManager.outputGraphics(null, "graph.png", "png"));
    }

    @Test
    public void testOutputGraphicsRejectsBlankPath() {
        Graph graph = new Graph();

        assertThrows(IllegalArgumentException.class, () ->
                GraphFileManager.outputGraphics(graph, "   ", "png"));
    }

    @Test
    public void testOutputGraphicsRejectsUnsupportedFormat() {
        Graph graph = new Graph();

        assertThrows(IllegalArgumentException.class, () ->
                GraphFileManager.outputGraphics(graph, "graph.jpg", "jpg"));
    }
}
