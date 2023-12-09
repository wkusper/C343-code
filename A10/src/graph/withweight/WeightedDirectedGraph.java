package graph.withweight;

import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.withweight.traversal.Weight;

import java.util.*;

/**
 * This class extended DirectedGraph with information about the weights of
 * the edges. The weights are maintained separately from the adjacency lists
 * in a HashMap.
 */
public class WeightedDirectedGraph extends DirectedGraph {
    private final HashMap<Edge,Weight> weights;
    public WeightedDirectedGraph(HashMap<String,Set<Edge>> adjacencyLists, HashMap<Edge,Weight> weights) {
        super(adjacencyLists);
        this.weights = weights;
    }

    public HashMap<Edge,Weight> getWeights() {
        return weights;
    }

    /**
     * TIER II TODO
     * <p>
     * Subtract the given weight from the weight of the given edge. If the result is zero,
     * remove the edge from the graph.
     */
    public void subtractEdgeWeight (Edge edge, Weight diff) {
        int weight = weights.get(edge).value();
        if (weight - diff.value() == 0) {
            removeEdge(edge);
        } else {
            weights.put(edge, new Weight(weight - diff.value()));
        }
    }

    /**
     * TIER II TODO
     * <p>
     * Add the given weight to the weight of the given edge. If the edge is not in the graph,
     * add it.
     */
    public void insertEdge (Edge edge, Weight weight) {
        if (weights.containsKey(edge)) {
            weights.put(edge, new Weight(weights.get(edge).value() + weight.value()));
        } else {
            weights.put(edge, weight);
            adjacencyLists.get(edge.source()).add(edge);
        }
    }
    /**
     * TIER II TODO
     * <p>
     * This method should return a new WeightedDirectedGraph that is a copy of this one.
     * It is important to create a new copy of the adjacency lists and weights, rather than
     * just returning the existing ones, because otherwise the caller could modify the
     * adjacency lists or weights of the returned graph, which would also modify
     * the original graph.
     */
    public WeightedDirectedGraph copy () {
        HashMap<String,Set<Edge>> newAdjacencyLists = new HashMap<>();
        for (String node : adjacencyLists.keySet()) {
            newAdjacencyLists.put(node, new HashSet<>(adjacencyLists.get(node)));
        }
        HashMap<Edge,Weight> newWeights = new HashMap<>();
        for (Edge edge : weights.keySet()) {
            newWeights.put(edge, weights.get(edge));
        }
        return new WeightedDirectedGraph(newAdjacencyLists, newWeights);
    }

    public boolean equals (Object o) {
        if (o instanceof WeightedDirectedGraph other) {
            return adjacencyLists.equals(other.adjacencyLists) && weights.equals(other.weights);
        }
        return false;
    }
}
