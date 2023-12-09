package graph.withweight.traversal;


import graph.noweight.Edge;
import graph.noweight.Path;
import graph.withweight.WeightedDirectedGraph;

import java.util.HashMap;

/**
 * TIER IV TODO
 * <p>
 * Imagine pouring water into a graph. The water flows from the source to the
 * destination. Obviously the amount of water going into a node must equal the
 * amount of water going out of a node. The edges have a maximum capacity of
 * water that can flow through them. The maximum flow is the maximum amount of
 * water that can flow from the source to the destination.
 * <p>
 * The Ford-Fulkerson algorithm (see the method run below) will compute two results:
 * the method returns an int which is the maximum amount that can flow through the graph
 * and updates the instance variable flow which the amount flowing along each edge.
 */
public class MaximumFlow {
    private final WeightedDirectedGraph graph;
    private final HashMap<Edge, Weight> flow;

    public MaximumFlow(WeightedDirectedGraph graph) {
        this.graph = graph;
        this.flow = new HashMap<>();
        for (Edge e : graph.getWeights().keySet()) {
            flow.put(e, Weight.ZERO);
        }
    }

    public HashMap<Edge, Weight> getFlow() {
        return flow;
    }

    /**
     * TIER IV TODO
     * <p>
     * We are given a path and a certain amount currentFlow. The method
     * updates the instance variable flow by adding currentFlow to the weight
     * of each edge in the given path.
     */
    public void augmentFlow (HashMap<Edge,Weight> flow, Path path, Weight currentFlow) {
        for (Edge e : path.edges()) {
            Weight newWeight = new Weight(flow.get(e).value() + currentFlow.value());
            flow.put(e, newWeight);
        }
    }

    /**
     * TIER IV TODO
     * <p>
     * We are given a path and a certain amount currentFlow. The method
     * updates the residual graph in two ways: it subtracts currentFlow from the
     * weight of every edge in the path, and for every edge in the given path,
     * it inserts a flipped version with weight currentFlow.
     */
    public void updateResidual (WeightedDirectedGraph residual, Path path, Weight currentFlow) {
        for (Edge e : path.edges()) {
            Weight newWeight = new Weight(residual.getWeights().get(e).value() - currentFlow.value());
            if (newWeight.value() == 0) {
                residual.getAdjacencyLists().get(e.source()).remove(e);
            }
            residual.getWeights().put(e, newWeight);
            Edge flipped = new Edge(e.destination(), e.source());
            Weight flippedWeight = new Weight(currentFlow.value());
            residual.getWeights().put(flipped, flippedWeight);
        }
    }

    /**
     * TIER IV TODO
     * <p>
     * Computes the maximum flow from source to destination. The algorithm
     * works by iterating over the shortest path algorithm.
     * <p>
     * We start with a residual graph which is a copy of the current graph.
     * We compute the shortest path from the source to the destination in that
     * residual graph. The "current flow" is the minimum weight along that
     * path.
     * <p>
     * We then update the hashmap flow by augmenting the edges along the
     * path with the current flow. We then update the residual graph
     * by subtracting the current flow from the weight of each edge in the path
     * and adding a flipped edge with weight current flow.
     * <p>
     * We repeat until there is no possible path in the residual graph.
     */
    public int run (String source, String destination) {
        WeightedDirectedGraph residual = graph.copy();
        AllShortestPaths sp = new AllShortestPaths(residual, source);
        sp.iterativeTraversal();
        Path path = sp.getPath(destination);
        while (!path.isEmpty()) {
            Weight currentFlow = path.minFlow();
            augmentFlow(flow, path, currentFlow);
            updateResidual(residual, path, currentFlow);
            sp = new AllShortestPaths(residual, source);
            sp.iterativeTraversal();
            path = sp.getPath(destination);
        }
        int maxFlow = 0;
        for (Edge e : flow.keySet()) {
            if (e.source().equals(source)) {
                maxFlow += flow.get(e).value();
            }
        }
        return maxFlow;
    }
}
