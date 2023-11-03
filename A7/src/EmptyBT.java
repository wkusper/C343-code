public class EmptyBT<E extends Comparable<E>> extends BinaryTree<E> {

    // Access fields

    E getData () throws EmptyTreeE { throw new EmptyTreeE(); }
    BinaryTree<E> getLeftBT () throws EmptyTreeE { throw new EmptyTreeE(); }
    BinaryTree<E> getRightBT () throws EmptyTreeE { throw new EmptyTreeE(); }

    // Basic properties

    boolean isEmpty () { return true; }
    int height () { return 0; }
    boolean isBalanced () { return true; }

    // Basic insert

    BinaryTree<E> insertBalanced(E elem) {
        return new NodeBT<>(elem, new EmptyBT<>(), new EmptyBT<>());
    }

    // BST insertions, lookups, and deletions

    public BinaryTree<E> insertBST(E elem) {
        return new NodeBT<>(elem, new EmptyBT<>(), new EmptyBT<>());
    }
    public boolean findBST(E elem) {
        return false;
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return null; }
    public TreePrinter.PrintableNode getRight() { return null; }
    public String getText() { return ""; }

}
