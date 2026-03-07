package edu.asu.cse464;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DotGraphParser {

    public static Graph parseGraph(String filepath) throws IOException {
        if (filepath == null || filepath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        String content = Files.readString(Path.of(filepath)).trim();

        if (!content.startsWith("digraph")) {
            throw new IllegalArgumentException("Invalid DOT file: must start with 'digraph'.");
        }

        int openBraceIndex = content.indexOf('{');
        int closeBraceIndex = content.lastIndexOf('}');

        if (openBraceIndex == -1 || closeBraceIndex == -1 || closeBraceIndex <= openBraceIndex) {
            throw new IllegalArgumentException("Invalid DOT file: missing or misplaced braces.");
        }

        String body = content.substring(openBraceIndex + 1, closeBraceIndex).trim();

        Graph graph = new Graph();

        if (body.isEmpty()) {
            return graph;
        }

        String[] statements = body.split(";");

        for (String statement : statements) {
            String trimmedStatement = statement.trim();

            if (trimmedStatement.isEmpty()) {
                continue;
            }

            if (trimmedStatement.contains("->")) {
                String[] parts = trimmedStatement.split("->");

                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid edge statement: " + trimmedStatement);
                }

                String src = parts[0].trim();
                String dst = parts[1].trim();

                graph.addEdge(src, dst);
            } else {
                graph.addNode(trimmedStatement);
            }
        }

        return graph;
    }
}