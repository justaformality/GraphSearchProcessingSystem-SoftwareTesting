package edu.asu.cse464;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("\n--- Parse and output graph files ---");
            String inputPath = "src/test/resources/input1.dot";
            String summaryOutputPath = "graph_summary.txt";
            String dotOutputPath = "graph_output.dot";
            String pngOutputPath = "graph_output.png";

            Graph parsedGraph = DotGraphParser.parseGraph(inputPath);

            System.out.println("Parsed graph successfully:");
            System.out.println(parsedGraph);

            GraphFileManager.outputGraph(parsedGraph, summaryOutputPath);
            System.out.println("Graph summary written to: " + summaryOutputPath);

            GraphFileManager.outputDOTGraph(parsedGraph, dotOutputPath);
            System.out.println("DOT graph written to: " + dotOutputPath);

            GraphFileManager.outputGraphics(parsedGraph, pngOutputPath, "png");
            System.out.println("PNG graph written to: " + pngOutputPath);


            System.out.println("\n--- Remove API Demo ---");
            Graph graph = new Graph();

            graph.addEdge("a", "b");
            graph.addEdge("b", "c");
            graph.addEdge("c", "d");
            graph.addEdge("a", "d");

            System.out.println("Original graph:");
            System.out.println(graph);

            graph.removeEdge("a", "d");
            System.out.println("After removing edge a -> d:");
            System.out.println(graph);

            graph.removeNode("b");
            System.out.println("After removing node b:");
            System.out.println(graph);

            Graph multiRemoveGraph = new Graph();
            multiRemoveGraph.addEdge("x", "y");
            multiRemoveGraph.addEdge("y", "z");
            multiRemoveGraph.addEdge("z", "w");

            System.out.println("Before removing multiple nodes:");
            System.out.println(multiRemoveGraph);

            multiRemoveGraph.removeNodes(new String[]{"y", "z"});
            System.out.println("After removing nodes y and z:");
            System.out.println(multiRemoveGraph);


            System.out.println("\n--- Graph Search Demo ---");
            Graph searchGraph = new Graph();
            searchGraph.addEdge("n1", "n2");
            searchGraph.addEdge("n2", "n3");
            searchGraph.addEdge("n3", "n4");
            searchGraph.addEdge("n1", "n5");

            Path bfsPath = searchGraph.GraphSearch(new Node("n1"), new Node("n4"), Algorithm.BFS);
            Path dfsPath = searchGraph.GraphSearch(new Node("n1"), new Node("n4"), Algorithm.DFS);

            System.out.println("BFS path from n1 to n4: " + bfsPath);
            System.out.println("DFS path from n1 to n4: " + dfsPath);

            Path noPath = searchGraph.GraphSearch(new Node("n4"), new Node("n1"), Algorithm.BFS);
            System.out.println("Path from n4 to n1 using BFS: " + noPath);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
