/**
 * A heap is a binary tree with the following properties:
 * <ul>
 *     <li>Every node is less than to its children.</li>
 *     <li>It is balanced.</li>
 *  </ul>
 *  <p>
 *  We are going to use a binary tree to represent a heap. The tree is balanced
 *  To manage the heap property efficiently, we will need every node to have
 *  a pointer back to its parent.
 *
 */
class Heap implements TreePrinter.PrintableNode {
    private BinaryTree<Integer> tree;

    Heap () { this.tree = new EmptyBT<>(); }

    boolean isEmpty () { return tree.isEmpty(); }
    int height () { return tree.height(); }
    int size() { return tree.size(); }

    /**
     * Only for testing. The tree should not be exposed.
     */
    BinaryTree<Integer> getTree () { return tree; }

    /**
     * To insert in the heap, we first create a new node with the information
     * to insert. Then, we insert the node in the tree in a way that maintains
     * the tree balanced. Finally, we restore the heap property by moving the
     * node up in the tree until it is in the right place.
     */
    void insert (int n) {
        NodeBT<Integer> node = new NodeBT<>(n, new EmptyBT<>(), new EmptyBT<>());
        tree = tree.insertBalanced(node);
        node.moveUp();
    }

    /**
     * The minimum is guaranteed to be at the root (of course if the tree is
     * not empty).
     */
    int getMin() throws EmptyTreeE {
        if (tree.isEmpty()) {throw new EmptyTreeE();} //empty tree
        return tree.getData();
    }

    /**
     * To delete the minimum, we first delete the root. This returns a balanced
     * tree with the rightmost node at the root. To restore the heap property
     * we move it down until it is in the right place in the tree.
     */
    void deleteMin() throws EmptyTreeE {
        if (tree.isEmpty()) {
            throw new EmptyTreeE();
        }
        tree = tree.deleteRoot();
        tree.moveDown();
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return tree.getLeft(); }
    public TreePrinter.PrintableNode getRight() { return tree.getRight(); }
    public String getText() { return tree.getText(); }
}
