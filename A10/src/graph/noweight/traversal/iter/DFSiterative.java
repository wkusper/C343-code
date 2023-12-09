package graph.noweight.traversal.iter;

import graph.noweight.DirectedGraph;
import graph.noweight.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * TIER II TODO
 * <p>
 * An iterative implementation of DFS.
 * <p>
 * The implementation uses the generic iterative traversal customizing it
 * in three ways:
 * <ul>
 *     <li>the node collection is a stack</li>
 *     <li>the enterAction method adds the node to the traversal list</li>
 *     <li>the relaxEdge method adds the destination node to the node collection</li>
 * </ul>
 */
public class DFSiterative extends IterativeGraphTraversal {
    private final List<String> traversal;

    public DFSiterative(DirectedGraph graph, String start) {
        super(graph, new StackCollection(start));
        this.traversal = new ArrayList<>();
    }

    public List<String> getTraversal() {
        return traversal;
    }

    public void enterAction(String node) {
        traversal.add(node);
    }
    public void relaxEdge(Edge edge) {
        StackCollection stack = (StackCollection) collection;
        stack.add(edge.destination());
    }
}


