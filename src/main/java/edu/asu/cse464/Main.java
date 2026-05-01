package edu.asu.cse464;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("\n====================================");
            System.out.println("CSE 464 Project Part 3 Demo");
            System.out.println("====================================");

            String inputPath = "src/test/resources/input.dot";
            String summaryOutputPath = "graph_summary.txt";
            String dotOutputPath = "graph_output.dot";
            String pngOutputPath = "graph_output.png";

            System.out.println("\n--- Step 1: Parse input.dot and output graph files ---");

            Graph parsedGraph = DotGraphParser.parseGraph(inputPath);

            System.out.println("Parsed graph successfully:");
            System.out.println(parsedGraph);

            GraphFileManager.outputGraph(parsedGraph, summaryOutputPath);
            System.out.println("Graph summary written to: " + summaryOutputPath);

            GraphFileManager.outputDOTGraph(parsedGraph, dotOutputPath);
            System.out.println("DOT graph written to: " + dotOutputPath);

            GraphFileManager.outputGraphics(parsedGraph, pngOutputPath, "png");
            System.out.println("PNG graph written to: " + pngOutputPath);

            runBfsDemo(parsedGraph);
            runDfsDemo(parsedGraph);
            runRandomWalkDemo(parsedGraph);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void runBfsDemo(Graph graph) {
        System.out.println("\n====================================");
        System.out.println("BFS Demo: Search from a to c");
        System.out.println("BFS uses a queue and visits nodes level by level.");
        System.out.println("====================================");

        Path bfsPath = graph.GraphSearch(new Node("a"), new Node("c"), Algorithm.BFS);

        System.out.println("Final BFS path from a to c: " + bfsPath);
    }

    private static void runDfsDemo(Graph graph) {
        System.out.println("\n====================================");
        System.out.println("DFS Demo: Search from a to c");
        System.out.println("DFS uses a stack and explores deeper paths first.");
        System.out.println("====================================");

        Path dfsPath = graph.GraphSearch(new Node("a"), new Node("c"), Algorithm.DFS);

        System.out.println("Final DFS path from a to c: " + dfsPath);
    }

    private static void runRandomWalkDemo(Graph graph) {
        System.out.println("\n====================================");
        System.out.println("Random Walk Demo: Search from a to c");
        System.out.println("Random Walk randomly chooses one unvisited neighbor each step.");
        System.out.println("Some runs may find c. Some runs may reach a dead end.");
        System.out.println("Running 10 times to show randomness.");
        System.out.println("====================================");

        int successCount = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.println("\nRandom Walk Run " + i);

            Path randomPath = graph.GraphSearch(new Node("a"), new Node("c"), Algorithm.RANDOM_WALK);

            if (randomPath == null) {
                System.out.println("Final Random Walk path from a to c: null");
                System.out.println("Result: Reached a dead end before finding c.");
            } else {
                successCount++;
                System.out.println("Final Random Walk path from a to c: " + randomPath);
                System.out.println("Result: Found target node c.");
            }
        }

        System.out.println("\nRandom Walk successful runs: " + successCount + " out of 10");
    }
}