import java.util.*;

public abstract class BinaryTree<E extends Comparable<E>>
        implements TreePrinter.PrintableNode {

    // Access fields

    abstract E getData () throws EmptyTreeE;
    abstract BinaryTree<E> getLeftBT () throws EmptyTreeE;
    abstract BinaryTree<E> getRightBT () throws EmptyTreeE;

    // Basic properties

    abstract boolean isEmpty();
    abstract int height ();
    abstract boolean isBalanced ();

    // Basic insert

    abstract BinaryTree<E> insertBalanced(E elem);

    // BST insertions and lookups

    public abstract BinaryTree<E> insertBST(E elem);
    public abstract boolean findBST(E key);

    // Iterators

    public Iterator<E> preOrder () { return TreeTraversals.preOrderList(this).iterator(); }
    public Iterator<E> inOrder () { return TreeTraversals.inOrderList(this).iterator(); }
    public Iterator<E> postOrder () { return TreeTraversals.postOrderList(this).iterator(); }
    public Iterator<E> breadthFirst () { return TreeTraversals.breadthFirstList(this).iterator(); }

    // Make trees with various properties

    static <E extends Comparable<E>> BinaryTree<E> mkLeaf (E data) {
        return new NodeBT<>(data, new EmptyBT<>(), new EmptyBT<>());
    }

    static <E extends Comparable<E>> BinaryTree<E> mkBalanced (Collection<E> elems) {
        BinaryTree<E> result = new EmptyBT<>();
        for (E e : elems) result = result.insertBalanced(e);
        return result;
    }

    static <E extends Comparable<E>> BinaryTree<E> mkBST (Collection<E> elems) {
        BinaryTree<E> result = new EmptyBT<>();
        for (E e : elems) result = result.insertBST(e);
        return result;
    }

}
