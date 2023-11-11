import java.util.Optional;

public class NodeBT<E extends Comparable<E>> extends BinaryTree<E> {
    private E data; // is updated when moveUp / moveDown are called
    private final BinaryTree<E> left, right;
    private final int height, size;

    NodeBT (E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.size = left.size() + right.size() + 1;
        this.height = Math.max(left.height(), right.height()) + 1;
        left.parent = Optional.of(this);
        right.parent = Optional.of(this);
    }

    // Access fields

    E getData () { return data; }
    BinaryTree<E> getLeftBT () { return left; }
    BinaryTree<E> getRightBT () { return right; }

    // Basic properties

    boolean isEmpty () { return false; }
    int height() { return height; }
    int size() { return size; }
    boolean isBalanced () { return Math.abs(left.height() - right.height()) <= 1; }

    BinaryTree<E> insertBalanced(NodeBT<E> node) {
        return null; //TODO
    }

    E getRightMost () {
            try {
                return right.getRightMost();
            } catch (EmptyTreeE e) {
                return data;
            }
    }

    BinaryTree<E> removeRightMost () {
        try {
            return getRightBT().removeRightMost();
        } catch (EmptyTreeE e) {
            return left;
        }
    }

    BinaryTree<E> deleteRoot () {
        return null; //TODO
    }

    /**
     * If the node is the root, do nothing.
     * Otherwise, if the node is smaller than its parent, then swap the data and
     * ask the parent to move up.
     */
    void moveUp () {
        if (parent.isEmpty()) return;
        if (data.compareTo(parent.get().data) < 0) {
            E temp = data;
            data = parent.get().data;
            parent.get().data = temp;
            parent.get().moveUp();
        }
    }

    /**
     *  If the node is a leaf, do nothing.
     *  If the node has only one child, check if the child is smaller than the node, and
     *  in that case swap the data and ask the child to move down.
     *  If the node has two children, repeat the process above with the smaller of the
     *  two children.
     */
    void moveDown () {
        //TODO
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return left.isEmpty() ? null : left; }
    public TreePrinter.PrintableNode getRight() { return right.isEmpty() ? null : right; }
    public String getText() { return String.valueOf(data); }

}

