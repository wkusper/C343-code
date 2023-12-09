package graph.withweight.traversal;

import graph.noweight.traversal.iter.NodeCollection;

import java.util.*;

/**
 * An implementation of a heap using the arraylist representation of a binary tree.
 * In addition to maintaining the nodes in a list, we maintain an inverse map
 * from nodes to their indices in the list. This allows us to find the parent
 * of a node, the children of a node, and to swap two nodes in the list. We also
 * maintain a map from nodes to their weights which is used for comparisons.
 * <p>
 */
public class Heap extends NodeCollection {
    private final List<String> nodes;
    private int size;
    private final HashMap<String,Weight> weights;
    private final HashMap<String,Integer> indices;

    /**
     * Initially all the weights are infinity. So the elements can be in
     * any order in the array.
     */
    public Heap(Set<String> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.size = nodes.size();
        this.weights = new HashMap<>();
        for (String node : nodes) this.weights.put(node, Weight.INFINITY);
        this.indices = new HashMap<>();
        for (int i = 0; i < size; i++) indices.put(this.nodes.get(i), i);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String get() {
        return extractMin();
    }

    public void add(String node) {
        insert(node, Weight.INFINITY);
    }

    public Weight getWeight(String node) {
        return weights.get(node);
    }

    /**
     * The caller should guarantee that the new weight is less than the current one.
     * So the only thing we need to do is to move the node up in the heap.
     */
    public void setWeight(String node, Weight w) {
        weights.put(node, w);
        moveUp(node);
    }

    public Optional<String> getParent(String n) {
        int parentIndex = (indices.get(n) - 1) / 2;
        if (parentIndex < 0) return Optional.empty();
        return Optional.of(nodes.get(parentIndex));
    }

    public Optional<String> getLeftChild(String n) {
        int childIndex = 2 * indices.get(n) + 1;
        if (childIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(childIndex));
    }

    public Optional<String> getRightChild(String n) {
        int childIndex = 2 * indices.get(n) + 2;
        if (childIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(childIndex));
    }

    public Optional<String> getMinChild(String n) {
        Optional<String> leftChild = getLeftChild(n);
        Optional<String> rightChild = getRightChild(n);
        if (rightChild.isEmpty()) return leftChild;
        else {
            assert leftChild.isPresent();
            Weight leftWeight = weights.get(leftChild.get());
            Weight rightWeight = weights.get(rightChild.get());
            if (leftWeight.compareTo(rightWeight) < 0) return leftChild;
            else return rightChild;
        }
    }

    public void swap(String n1, String n2) {
        int p1 = indices.get(n1);
        int p2 = indices.get(n2);
        nodes.set(p1, n2);
        indices.put(n2, p1);
        nodes.set(p2, n1);
        indices.put(n1, p2);
    }

    /**
     * TIER II TODO
     */
    public void moveDown(String n) {
        while (true) {
            Optional<String> childOpt = getMinChild(n);
            if (childOpt.isEmpty()) break;
            String child = childOpt.get();
            if (weights.get(n).compareTo(weights.get(child)) <= 0) break;
            swap(n, child);
        }

    }

    /**
     * TIER II TODO
     */
    public void moveUp(String n) {
        while (true) {
            Optional<String> parentOpt = getParent(n);
            if (parentOpt.isEmpty()) break;
            String parent = parentOpt.get();
            if (weights.get(n).compareTo(weights.get(parent)) >= 0) break;
            swap(n, parent);
        }
    }

    /**
     * TIER II TODO
     */
    public void insert(String n, Weight w) {
        nodes.add(n);
        weights.put(n, w);
        indices.put(n, size);
        size++;
        moveUp(n);
    }

    /**
     * TIER II TODO
     */
    public String extractMin() {
        String min = nodes.get(0);
        swap(min, nodes.get(size - 1));
        indices.remove(min);
        nodes.remove(size - 1);
        size--;
        if (size > 0) moveDown(nodes.get(0));
        return min;
    }
}

