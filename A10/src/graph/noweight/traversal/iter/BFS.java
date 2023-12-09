package graph.noweight.traversal.iter;

import graph.noweight.DirectedGraph;
import graph.noweight.Edge;

import java.util.ArrayList;
import java.util.List;
/**
 * TIER II TODO
 * <p>
 * An iterative implementation of BFS.
 * <p>
 * The implementation uses the generic iterative traversal customizing it
 * in three ways:
 * <ul>
 *     <li>the node collection is a queue</li>
 *     <li>the enterAction method adds the node to the traversal list</li>
 *     <li>the relaxEdge method adds the destination node to the node collection</li>
 * </ul>
 */
public class BFS extends IterativeGraphTraversal{
    private final List<String> traversal;

    public BFS(DirectedGraph graph, String start) {
        super(graph, new QueueCollection(start));
        this.traversal = new ArrayList<>();
    }

    public List<String> getTraversal() {
        return traversal;
    }

    public void enterAction(String node) {
        traversal.add(node);
    }
    public void relaxEdge(Edge edge) {
        QueueCollection queue = (QueueCollection) collection;
        queue.add(edge.destination());
    }
}
