package edu.asu.cse464;

import java.util.ArrayDeque;
import java.util.Deque;

public class DFS extends GraphSearchAbstract {
    private final Deque<Path> stack = new ArrayDeque<>();

    @Override
    protected void addPath(Path path){
        stack.push(path);
    }

    @Override
    protected Path getNextPath(){
        return stack.pop();
    }

    @Override
    protected boolean hasMorePaths(){
        return !stack.isEmpty();
    }

}
