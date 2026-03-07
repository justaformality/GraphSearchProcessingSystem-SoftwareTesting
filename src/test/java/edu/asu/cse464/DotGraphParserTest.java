package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DotGraphParserTest {

    @Test
    public void testParseValidDotFile() throws IOException {
        String filepath = Path.of("src", "test", "resources", "input1.dot").toString();

        Graph graph = DotGraphParser.parseGraph(filepath);

        assertEquals(3, graph.getNodeCount());
        assertEquals(2, graph.getEdgeCount());
        assertTrue(graph.getNodes().contains("a"));
        assertTrue(graph.getNodes().contains("b"));
        assertTrue(graph.getNodes().contains("c"));
        assertTrue(graph.getEdges().contains(new Edge("a", "b")));
        assertTrue(graph.getEdges().contains(new Edge("b", "c")));
    }

    @Test
    public void testParseEmptyDotFile() throws IOException {
        String filepath = Path.of("src", "test", "resources", "empty.dot").toString();

        Graph graph = DotGraphParser.parseGraph(filepath);

        assertEquals(0, graph.getNodeCount());
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    public void testParseInvalidDotFile() {
        String filepath = Path.of("src", "test", "resources", "invalid.dot").toString();

        assertThrows(IllegalArgumentException.class, () -> DotGraphParser.parseGraph(filepath));
    }

    @Test
    public void testParseRejectsBlankPath() {
        assertThrows(IllegalArgumentException.class, () -> DotGraphParser.parseGraph("   "));
    }
}