package graph.noweight;

import graph.withweight.traversal.Weight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * TIER I
 * <p>
 * A path is simply a sequence of edges.
 */
public class Path implements Iterable<Edge> {
    private final List<Edge> edges;
    private Weight totalWeight;
    private Weight minFlow;

    public Path() {
        this.edges = new ArrayList<>();
        this.totalWeight = Weight.ZERO;
        this.minFlow = Weight.INFINITY;
    }

    public boolean isEmpty() {
        return edges.isEmpty();
    }
    public List<Edge> edges() {
        return edges;
    }
    public Weight weight() { return totalWeight; }
    public Weight minFlow() {
        return minFlow;
    }

    /**
     * TIER I
     * <p>
     * Reverses the order of the edges in the path. The edges themselves are not
     * flipped, just the order of the edges in the list.
     */
    public void reverse() {
        Collections.reverse(edges);
    }

    /**
     * TIER I
     * <p>
     * Adds the given edge to the end of the path. The total weight is updated
     * by adding the given weight. The minFlow is also updated to the minimum
     * of the existing minFlow and the given weight.
     */
    public void add(Edge edge, Weight w) {
        edges.add(edge);
        totalWeight = totalWeight.add(w);
        int minflow = Math.min(minFlow.value(), w.value());
        minFlow = new Weight(minflow);
    }

    public Iterator<Edge> iterator() {
        return edges.iterator();
    }

    public boolean equals(Object other) {
        if (other instanceof Path otherPath) {
            return edges.equals(otherPath.edges);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return edges.hashCode();
    }

    public String toString() {
        return edges.toString();
    }
}
