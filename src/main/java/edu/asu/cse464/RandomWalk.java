package edu.asu.cse464;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RandomWalk extends GraphSearchAbstract {
    private final Queue<Path> queue = new LinkedList<>();

    @Override
    protected void addPath(Path path){
        queue.offer(path);
    }

    @Override
    protected Path getNextPath(){
        return queue.poll();
    }

    @Override
    protected boolean hasMorePaths(){
        return !queue.isEmpty();
    }

    @Override
    protected List<String> chooseNextNodes(Graph graph, String currentLabel, Set<String> visited) {
        List<String> neighbors = new ArrayList<>();

        for (String neighbor : graph.getNeighbors(currentLabel)) {
            if (!visited.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        if (neighbors.isEmpty()) {
            return neighbors;
        }
        Collections.shuffle(neighbors);
        return List.of(neighbors.get(0));
    }
}
