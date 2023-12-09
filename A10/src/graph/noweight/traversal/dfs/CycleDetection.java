package graph.noweight.traversal.dfs;

import graph.noweight.DirectedGraph;

import java.util.HashSet;
import java.util.Set;

/**
 * TIER V TODO
 * <p>
 * This class extends RecursiveGraphTraversal to detect cycles in a graph.
 * <p>
 * A cycle is a path that starts and ends at the same node. An easy way to
 * detect cycles is to keep track of the nodes that are ancestors of the current
 * node during a depth-first recursive traversal. If we ever visit a node that
 * is already in the ancestor set, then we have found a cycle.
 * <p>
 * To implement this idea, the enterAction method should add the current node to
 * the set of ancestors. The exitAction method should remove the current node
 * from the set of ancestors. The touchAction method should check if the current
 * node is an ancestor. If so, it should set the hasCycle flag to true.
 */
public class CycleDetection extends RecursiveGraphTraversal {
    private boolean hasCycle;
    private final Set<String> ancestors;
    public CycleDetection(DirectedGraph graph) {
        super(graph);
        this.ancestors = new HashSet<>();
        this.hasCycle = false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public void enterAction(String node) {
        ancestors.add(node);
    }
    public void touchAction(String node) {
        if (ancestors.contains(node)) {
            hasCycle = true;
        }
    }
    public void exitAction(String node) {
        ancestors.remove(node);
    }
}
