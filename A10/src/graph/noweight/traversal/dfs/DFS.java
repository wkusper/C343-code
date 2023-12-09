package graph.noweight.traversal.dfs;

import graph.noweight.DirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TIER V TODO
 * <p>
 * This class extends RecursiveGraphTraversal to perform depth-first searches of a graph.
 * Each depth-first search starts from a given node and recursively visits all the nodes
 * reachable from that node. If the graph is connected, then this depth-first search will
 * visit all the nodes in the graph. Otherwise, we would need to repeat the search
 * starting from another node that has not yet been visited.
 * <p>
 * To implement the depth-first starting from a given node, all we need to do is
 * to override the enterAction method to add the current node to the traversal.
 * <p>
 * To implement the depth-first search of the entire graph, we need to override
 * the sourceAction method to save the current traversal in the allTraversals
 * hashmap and clear the currentTraversal list to prepare for the next traversal.
 */
public class DFS extends RecursiveGraphTraversal {
    private final List<String> currentTraversal;
    private final HashMap<String,List<String>> allTraversals;

    public DFS(DirectedGraph graph) {
        super(graph);
        this.currentTraversal = new ArrayList<>();
        this.allTraversals = new HashMap<>();
    }
    public DFS (DirectedGraph graph, List<String> allNodes) {
        super(graph, allNodes);
        this.currentTraversal = new ArrayList<>();
        this.allTraversals = new HashMap<>();
    }

    public List<String> getCurrentTraversal () { return currentTraversal; }
    public HashMap<String,List<String>> getAllTraversals() { return allTraversals; }

    public void enterAction(String node) {
        currentTraversal.add(node);
    }
    public void sourceAction(String current) {
        allTraversals.put(current, new ArrayList<>(currentTraversal));
        currentTraversal.clear();
    }
}


