package graph.withweight.traversal;

import graph.noweight.Edge;
import graph.noweight.traversal.iter.IterativeGraphTraversal;
import graph.withweight.WeightedDirectedGraph;

import java.util.HashMap;

/**
 * This is a version of IterativeGraphTraversal that also holds information
 * about the weights of the edges. It uses a heap as the node collection
 * to always extract the node with the minimum weight.
 */
public abstract class WeightedIterativeGraphTraversal extends IterativeGraphTraversal {
    protected final HashMap<Edge,Weight> weights;
    protected final Heap heap;
    public WeightedIterativeGraphTraversal(WeightedDirectedGraph graph, Heap collection) {
        super(graph, collection);
        this.weights = graph.getWeights();
        this.heap = collection;
    }

}
