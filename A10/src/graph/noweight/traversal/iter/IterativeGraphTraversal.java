package graph.noweight.traversal.iter;

import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.traversal.GraphTraversal;

/**
 * This class implements a generic iterative graph traversal. It is abstract because it
 * does not specify how to relax edges or what to do when entering a node. These are
 * left to the subclasses.
 * <p>
 * The traversal is implemented using a NodeCollection, which is a data structure that
 * holds the nodes that are currently being processed, i.e., nodes that have been
 * entered but whose outgoing edges have not yet been relaxed. The NodeCollection
 * is initialized with the start node, and then the iterativeTraversal method should be
 * called to perform the traversal.
 */
public abstract class IterativeGraphTraversal extends GraphTraversal {
    protected final NodeCollection collection;

    public IterativeGraphTraversal (DirectedGraph graph, NodeCollection collection) {
        super(graph);
        this.collection = collection;
    }

    public abstract void relaxEdge(Edge edge);
    public void enterAction(String node) {}

    /**
     * Generic iterative traversal.
     * <p>
     * We loop over the collection of nodes. For each node, if it has already been
     * visited, we skip it. Otherwise, we add it to the visited set, enter the node,
     * and relax all of its outgoing edges.
     * <p>
     * Specific traversals would customize the action to do when entering a node
     * and what to do when relaxing an edge.
     */
    public void iterativeTraversal () {
        while (!collection.isEmpty()) {
            String current = collection.get();
            if (!visited.contains(current)) {
                visited.add(current);
                enterAction(current);
                for (Edge edge : graph.outgoingEdges(current)) {
                    relaxEdge(edge);
                }
            }
        }
    }
}
