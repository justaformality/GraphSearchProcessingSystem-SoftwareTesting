package edu.asu.cse464;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GraphFileManager {

    public static void outputGraph(Graph graph, String filepath) throws IOException {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        if (filepath == null || filepath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            writer.print(graph.toString());
        }

    }

    public static void outputDOTGraph(Graph graph, String filepath) throws IOException {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        if (filepath == null || filepath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            writer.println("digraph G {");

            for (String node : graph.getNodes()) {
                writer.println("    " + node + ";");
            }

            for (Edge edge : graph.getEdges()) {
                writer.println("    " + edge.getSrc() + " -> " + edge.getDst() + ";");
            }

            writer.println("}");
        }
    }

    public static void outputGraphics(Graph graph, String filepath, String format) throws IOException, InterruptedException {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        if (filepath == null || filepath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        if (format == null || format.trim().isEmpty()) {
            throw new IllegalArgumentException("Format cannot be null or empty.");
        }

        String trimmedFormat = format.trim().toLowerCase();

        if (!trimmedFormat.equals("png")) {
            throw new IllegalArgumentException("Only PNG format is supported.");
        }

        File tempDotFile = File.createTempFile("graph_temp", ".dot");

        try {
            outputDOTGraph(graph, tempDotFile.getAbsolutePath());

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "dot",
                    "-T" + trimmedFormat,
                    tempDotFile.getAbsolutePath(),
                    "-o",
                    filepath
            );

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new IOException("Graphviz failed with exit code: " + exitCode);
            }
        } finally {
            tempDotFile.delete();
        }
    }
}
