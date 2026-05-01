package edu.asu.cse464;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class GraphSearchAbstract implements GraphSearchStrategy {
    @Override
    public final Path search(Graph graph, Node src, Node dst){
        String srcLabel = src.getLabel();
        String dstLabel = dst.getLabel();

        if(!graph.containsNode(srcLabel) || !graph.containsNode(dstLabel)){
            return null;
        }
        if(srcLabel.equals(dstLabel)){
            Path startPath = new Path(List.of(new Node(srcLabel)));
            printVisit(startPath);
            return startPath;
        }
        Set<String> visited = new HashSet<>();
        Path startPath = new Path(List.of(new Node(srcLabel)));

        visited.add(srcLabel);
        addPath(startPath);

        while(hasMorePaths()){
            Path currentPath = getNextPath();
            printVisit(currentPath);

            String currentLabel = getLastNodeLabel(currentPath);
            List<String> neighbors = chooseNextNodes(graph, currentLabel, visited);

            for(String neighbor : neighbors){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);

                    Path newPath = extendPath(currentPath, neighbor);

                    if(neighbor.equals(dstLabel)){
                        printVisit(newPath);
                        System.out.println("Found target node: " + dstLabel);
                        return newPath;
                    }
                    addPath(newPath);
                }
            }
        }
        return null;
    }

    protected abstract void addPath(Path path);
    protected abstract Path getNextPath();
    protected abstract boolean hasMorePaths();

    private String getLastNodeLabel(Path path){
        List<Node> nodes = path.getNodes();
        return nodes.get(nodes.size() - 1).getLabel();
    }

    private Path extendPath(Path currentPath, String nextLabel){
        List<Node> newNodes = new ArrayList<>(currentPath.getNodes());
        newNodes.add(new Node(nextLabel));
        return new Path(newNodes);
    }

    protected List<String> chooseNextNodes(Graph graph, String currentLabel, Set<String> visited){
        List<String> neighbors = new ArrayList<>();

        for(String neighbor : graph.getNeighbors(currentLabel)){
            if(!visited.contains(neighbor)){
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private void printVisit(Path path){
        String history = path.getNodes().stream()
                .map(Node::getLabel)
                .collect(java.util.stream.Collectors.joining("-"));

        System.out.println("Visit Node History: " + history);
    }
}
