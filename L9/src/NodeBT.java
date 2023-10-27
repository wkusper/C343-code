public class NodeBT<E extends Comparable<E>> extends BinaryTree<E> {
    private final E data;
    private final BinaryTree<E> left, right;
    private final int height;

    NodeBT (E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = Math.max(left.height(), right.height()) + 1;
    }

    // Access fields

    E getData () { return data; }
    BinaryTree<E> getLeftBT () { return left; }
    BinaryTree<E> getRightBT () { return right; }

    // Basic properties

    boolean isEmpty () { return false; }
    int height() { return height; }
    boolean isBalanced() { return Math.abs(left.height() - right.height()) < 2; }

    // Basic insert

    BinaryTree<E> insertBalanced(E elem) {
        return null; // TODO
    }

    // BST insertions, lookups, and deletions

    public BinaryTree<E> insertBST(E elem) {
        return null; // TODO
    }

    public boolean findBST(E elem) {
        return false; // TODO
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return left.isEmpty() ? null : left; }
    public TreePrinter.PrintableNode getRight() { return right.isEmpty() ? null : right; }
    public String getText() { return String.valueOf(data); }

}
