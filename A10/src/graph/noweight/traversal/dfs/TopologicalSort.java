package graph.noweight.traversal.dfs;

import graph.noweight.DirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * TIER V TODO
 * <p>
 * This class extends RecursiveGraphTraversal to calculate a
 * topological sorting of a graph. The graph should not have
 * cycles. A topological sorting is an ordering of the nodes
 * such that for every edge (u,v), u comes before v in the ordering.
 * <p>
 * To implement this idea, we perform a recursive depth-first traversal
 * of the graph. The exitAction method should add the current node to the
 * front of the traversal list.
 */
public class TopologicalSort extends RecursiveGraphTraversal {
    private final List<String> traversal;
    public TopologicalSort (DirectedGraph graph) {
        super(graph);
        this.traversal = new ArrayList<>();
    }

    public List<String> getTraversal() {
        return traversal;
    }

    public void exitAction (String node) {
        traversal.add(0, node);
    }
}
