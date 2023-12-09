package graph.noweight.traversal;

import graph.noweight.DirectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * The class maintains an ordered list of the nodes to traverse.
 * As the traversal proceeds, visited nodes should be maintained in
 * the visited set.
 * <p>
 * Even though the class does not have any abstract methods, it is
 * abstract because it is not meant to be instantiated. Specific
 * traversal algorithms should extend this class.
 */
public abstract class GraphTraversal {
    protected final DirectedGraph graph;
    protected final HashSet<String> visited;
    protected final List<String> allNodes;

    public GraphTraversal (DirectedGraph graph) {
        this(graph, new ArrayList<>(graph.getNodes()));
    }
    public GraphTraversal (DirectedGraph graph, List<String> allNodes) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.allNodes = allNodes;
    }

}
