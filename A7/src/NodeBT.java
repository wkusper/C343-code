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
        if (left.height() < right.height()) {
            NodeBT<E> newNode = new NodeBT<>(data, left.insertBalanced(elem), right);
            return new NodeBT<>(newNode.data, newNode.right, newNode.left);
        } else if (left.height() > right.height()) {
            NodeBT<E> newNode = new NodeBT<>(data, left, right.insertBalanced(elem));
            return new NodeBT<>(newNode.data, newNode.right, newNode.left);
        } else {
            NodeBT<E> newNode = new NodeBT<>(data, left.insertBalanced(elem), right);
            return new NodeBT<>(newNode.data, newNode.right, newNode.left);
        }
    }

    // BST insertions, lookups, and deletions

    public BinaryTree<E> insertBST(E elem) {
        if (elem.compareTo(data) < 0) {
            return new NodeBT<>(data, left.insertBST(elem), right);
        } else  {
            return new NodeBT<>(data, left, right.insertBST(elem));
        }
    }

    public boolean findBST(E elem) {
        if (elem.compareTo(data) == 0) {
            return true;
        } else if (elem.compareTo(data) < 0) {
            return left.findBST(elem);
        } else {
            return right.findBST(elem);
        }
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return left.isEmpty() ? null : left; }
    public TreePrinter.PrintableNode getRight() { return right.isEmpty() ? null : right; }
    public String getText() { return String.valueOf(data); }

}

