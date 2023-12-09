package graph.noweight.traversal.dfs;

import graph.noweight.DirectedGraph;
import graph.noweight.traversal.GraphTraversal;

import java.util.List;

/**
 *  This class provides a generic recursive graph traversal. The traverseFromSource
 *  visits the given node, and recursively visits all of its neighbors. The traversal
 *  is parameterized by three actions: what to do when we first encounter a node
 *  (enterAction), what to do when we encounter a node again (touchAction), and
 *  what to do when we finish processing a node and all its neighbors (exitAction).
 *  <p>
 *  The traverseFromAllSources method performs the same traversal starting from
 *  each node in the graph. The traversal is parameterized by a fourth action:
 *  what to do when we finish a traversal from a given source and we are
 *  about to start a traversal from another source.
 */
public abstract class RecursiveGraphTraversal extends GraphTraversal {
    public RecursiveGraphTraversal (DirectedGraph graph) { super(graph); }
    public RecursiveGraphTraversal (DirectedGraph graph, List<String> allNodes) { super(graph, allNodes); }

    public void enterAction (String node) {}
    public void touchAction (String node) {}
    public void exitAction (String node) {}
    public void sourceAction(String node) {}

    public void traverseFromSource(String current) {
        if (visited.contains(current)) touchAction(current);
        else {
            visited.add(current);
            enterAction(current);
            graph.neighbors(current).forEach(this::traverseFromSource);
            exitAction(current);
        }
    }

    public void traverseFromAllSources() {
        for (String current : allNodes) {
            traverseFromSource(current);
            sourceAction(current);
        }
    }
}
