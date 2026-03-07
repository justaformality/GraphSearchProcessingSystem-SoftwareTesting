package edu.asu.cse464;

public class Main {

    public static void main(String[] args) {
        try {
            String inputPath = "src/test/resources/input1.dot";
            String summaryOutputPath = "graph_summary.txt";
            String dotOutputPath = "graph_output.dot";
            String pngOutputPath = "graph_output.png";

            Graph graph = DotGraphParser.parseGraph(inputPath);

            System.out.println("Parsed graph successfully:");
            System.out.println(graph);

            GraphFileManager.outputGraph(graph, summaryOutputPath);
            System.out.println("Graph summary written to: " + summaryOutputPath);

            GraphFileManager.outputDOTGraph(graph, dotOutputPath);
            System.out.println("DOT graph written to: " + dotOutputPath);

            GraphFileManager.outputGraphics(graph, pngOutputPath, "png");
            System.out.println("PNG graph written to: " + pngOutputPath);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
