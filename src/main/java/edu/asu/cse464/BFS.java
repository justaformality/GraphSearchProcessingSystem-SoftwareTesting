package edu.asu.cse464;

import java.util.LinkedList;
import java.util.Queue;

public class BFS extends GraphSearchAbstract{
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
}
