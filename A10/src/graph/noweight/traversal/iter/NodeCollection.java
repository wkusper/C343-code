package graph.noweight.traversal.iter;

/**
 * A collection of nodes to be processed by iterative graph traversals. The collection
 * is instantiated to a stack, a queue, or a heap to implement various algorithms.
 */
public abstract class NodeCollection {
    public abstract boolean isEmpty ();
    public abstract String get ();
    public abstract void add (String node);
}
