package edu.asu.cse464;

public class GraphSearchStrategyFactory {
    public static GraphSearchStrategy createStrategy(Algorithm algo){
        if(algo == null){
            throw new IllegalArgumentException("Algorithm cannot be null.");
        }
        switch (algo) {
            case BFS:
                return new BFS();

            case DFS:
                return new DFS();

            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algo);
        }
    }

}
