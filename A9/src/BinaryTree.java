import java.util.*;

/**
 * This is a modified implementation of our binary trees.
 * The main change is that children have pointers to their parent.
 */
public abstract class BinaryTree<E extends Comparable<E>> implements TreePrinter.PrintableNode {
    protected Optional<NodeBT<E>> parent;
    /**
     * Even though the class is abstract, we include a constructor. The reasoning is
     * that when an instance of a subclass is created, the constructor of the superclass
     * (below) is implicitly called. Initially, the parent is empty. It is expected that
     * each non-empty node is responsible for properly setting the parent field of its
     * children.
     */
    BinaryTree () { this.parent = Optional.empty(); }

    // Access fields

    abstract E getData () throws EmptyTreeE;
    abstract BinaryTree<E> getLeftBT () throws EmptyTreeE;
    abstract BinaryTree<E> getRightBT () throws EmptyTreeE;
    Optional<NodeBT<E>> getParent () { return parent; }

    // Basic properties. Essentially the same as before.

    abstract boolean isEmpty();
    abstract int height ();
    abstract int size ();
    abstract boolean isBalanced ();

    /*
     * We are aiming to use the tree to implement a heap (also known as a priority queue).
     * The order constraint here is different from the BST constraint. Here we insist that each node
     * is smaller than everything under it. This is called the heap property. This means
     * that the minimum is always at the root.
     */

    /**
     * This is almost identical to the insertBalanced method from the previous assignment.
     * The only change is that the argument is a node rather than just a number
     * (so that we can update the parent field) when the node is finally inserted in the tree.
     */
    abstract BinaryTree<E> insertBalanced(NodeBT<E> node);

    /**
     * This is identical to the getRightMost method from the previous assignment.
     */
    abstract E getRightMost () throws EmptyTreeE;

    /**
     * This is almost identical to the removeRightMost method from the previous assignment.
     * As we reconstruct the tree when returning from each recursive call, we keep swapping the
     * left and right subtrees to keep the tree balanced. (This parallels the way we keep the tree
     * balanced in the insertBalanced method.)
     */
    abstract BinaryTree<E> removeRightMost () throws EmptyTreeE;

    /**
     * The strategy is to replace the root with the rightmost element in the tree. Specifically,
     * we remove the rightmost element from the tree, and return a new tree with the rightmost
     * element at the root. (Be careful to account for the case where the tree only had one node.)
     */
    abstract BinaryTree<E> deleteRoot () throws EmptyTreeE;

    // Iterators

    public Iterator<E> preOrder () { return TreeTraversals.preOrderList(this).iterator(); }
    public Iterator<E> inOrder () { return TreeTraversals.inOrderList(this).iterator(); }
    public Iterator<E> postOrder () { return TreeTraversals.postOrderList(this).iterator(); }
    public Iterator<E> breadthFirst () { return TreeTraversals.breadthFirstList(this).iterator(); }

    // Make trees with various properties

    /**
     * The parent field of the new node is empty.
     */
    static <E extends Comparable<E>> NodeBT<E> mkLeaf (E data) {
        return new NodeBT<>(data, new EmptyBT<>(), new EmptyBT<>());
    }
    abstract void moveUp ();
    abstract void moveDown ();
    abstract void swapData(NodeBT<E> node) throws EmptyTreeE;
}
