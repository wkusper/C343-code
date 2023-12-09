package graph.noweight.traversal.dfs;

import graph.noweight.DirectedGraph;
import graph.noweight.traversal.GraphTraversal;

import java.util.HashMap;
import java.util.List;

/**
 * TIER V TODO
 * <p>
 * This class calculates the strongly connected components of a graph.
 * A strongly connected component is a subset of the nodes in a graph such that
 * every node in the subset can reach every other node in the subset.
 * <p>
 * The algorithm we will use to calculate the strongly connected components is
 * the following. First, we perform a topological sort of the graph from all sources.
 * Then, we perform a depth-first traversal from all sources of the transpose of
 * the graph. In that search we must visit the nodes in the order returned by
 * the topological sorting.
 */
public class StronglyConnectedComponents extends GraphTraversal {

    public StronglyConnectedComponents(DirectedGraph graph) {
        super(graph);
    }

    public HashMap<String,List<String>> computeSCC () {
        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverseFromAllSources();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String n : ts.getTraversal()) {
            if (!map.containsKey(n)) {
                DFS dfs = new DFS(graph.transpose(), ts.getTraversal());
                dfs.traverseFromAllSources();
                map = dfs.getAllTraversals();
            }
        }
        return map;
    }

}
