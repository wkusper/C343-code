package graph.withweight.traversal;

import graph.noweight.Edge;
import graph.noweight.Path;
import graph.withweight.WeightedDirectedGraph;

import java.util.HashMap;
import java.util.Set;

/**
 * TIER III TODO
 * <p>
 * This class implements Dijkstra's algorithm to find the shortest path from a
 * source node to all other nodes in a graph. This information is represented
 * as a HashMap that maps each node to the previous node along the shortest
 * path back to the source.
 * <p>
 * The traversal itself is implemented by customizing the generic iterative
 * traversal by defining the relaxEdge method. (See below.)
 */
public class AllShortestPaths extends WeightedIterativeGraphTraversal {
    private final HashMap<String,String> previousNodes;

    public AllShortestPaths(WeightedDirectedGraph graph, String source) {
        this(graph, new Heap(graph.getNodes()), source);
    }
    public AllShortestPaths(WeightedDirectedGraph graph, Heap heap, String source) {
        super(graph, heap);
        this.previousNodes = new HashMap<>();
        heap.setWeight(source, new Weight(0));
    }
    WeightedDirectedGraph getGraph() {
        return (WeightedDirectedGraph) graph;
    }
    public HashMap<Edge,Weight> getWeights() {
        return weights;
    }

    /**
     * TIER III TODO
     * <p>
     * This method is called whenever a node is visited. It checks all outgoing
     * edges from the node and performs the following actions:
     * <ul>
     *     <li> If the destination node has been visited, we skip it
     *     <li> Otherwise, we calculate the new way of reaching the
     *     destination node by adding the weight of the edge to the weight of
     *     the current node.
     *     <li> If this new way is shorter than the previous way, we update
     *     the weight of the destination node and record the current node as
     *     the predecessor of the destination node along the shortest path so far.
     * </ul>
     */
    public void relaxEdge(Edge edge) {
        String source = edge.source();
        String destination = edge.destination();
        Heap heap = this.heap;
        if (!visited.contains(destination)) {
            Weight newWeight = weights.get(edge).add(heap.getWeight(source));
            if (newWeight.compareTo(heap.getWeight(destination)) < 0) {
                heap.setWeight(destination, newWeight);
                previousNodes.put(destination, source);
            }
        }
    }

    /**
     * ITER III TODO
     * <p>
     * This method follows the previousNodes map to reconstruct the shortest
     * path from the source to the destination node.
     */
    public Path getPath (String destination) {
        Path path = new Path();
        String current = destination;
        while (previousNodes.containsKey(current)) {
            String previous = previousNodes.get(current);
            path.add(new Edge(previous, current), weights.get(new Edge(previous, current)));
            current = previous;
        }
        path.reverse();
        return path;
    }
}
