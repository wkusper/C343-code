import java.util.*;

/**
 * This class implements a basic binary search tree
 * which is then supplemented with methods to balance the tree during insertions and deletions.
 * The balancing is inspired by scapegoat trees http://people.csail.mit.edu/rivest/pubs/GR93.pdf
 */
public abstract class BST<E extends Comparable<E>> implements TreePrinter.PrintableNode {

    // Access fields

    abstract E data() throws EmptyTreeExc;
    abstract BST<E> left() throws EmptyTreeExc;
    abstract BST<E> right() throws EmptyTreeExc;

    // Basic properties

    abstract boolean isEmpty();
    abstract int height();
    abstract int size();
    abstract boolean isBalanced();

    // Insertions, lookups, and deletions

    abstract boolean find(E key);
    /**
     * Returns a string of 0s and 1s representing the path to the given key in the tree.
     * Left branches are represented by 0s, right branches by 1s.
     */
    abstract String path (E key) throws EmptyTreeExc;
    abstract E findMin() throws EmptyTreeExc;
    abstract E findMax() throws EmptyTreeExc;

    /**
     * insert maintains the order constraints of a binary search tree but does not care about balancing.
     * insertAndBalance additionally balances every subtree it encounters that is not balanced
     */
    abstract BST<E> insert(E elem);
    abstract BST<E> insertAndBalance(E elem);

    /**
     * delete maintains the order constraints of a binary search tree but does not care about balancing.
     * deleteAndBalance additionally balances every subtree it encounters that is not balanced
     */
    abstract BST<E> delete(E elem) throws EmptyTreeExc;
    abstract BST<E> deleteMin() throws EmptyTreeExc;
    abstract BST<E> deleteMax() throws EmptyTreeExc;
    abstract BST<E> deleteAndBalance(E elem) throws EmptyTreeExc;

    // Make trees with various properties

    static <E extends Comparable<E>> BST<E> mkLeaf(E data) {
        return new NodeBST<>(data, new EmptyBST<>(), new EmptyBST<>());
    }

    /**
     * The tree is not necessarily balanced
     */
    static <E extends Comparable<E>> BST<E> fromCollection(Collection<E> elems) {
        BST<E> result = new EmptyBST<>();
        for (E e : elems) result = result.insert(e);
        return result;
    }
}
