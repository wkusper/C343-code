import java.util.ArrayList;

public class NodeBST<E extends Comparable<E>> extends BST<E> {
    private final E data;
    private final BST<E> left, right;
    private final int height, size;

    NodeBST(E data, BST<E> left, BST<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = Math.max(left.height(), right.height()) + 1;
        this.size = left.size() + right.size() + 1;
    }

    // Access fields

    E data() { return data; }
    BST<E> left() { return left; }
    BST<E> right() { return right; }

    // Basic properties

    boolean isEmpty () { return false; }
    int height() { return height; }
    int size() { return size; }
    boolean isBalanced() { return Math.abs(left.height() - right.height()) <= 1; }

    // Insertions, lookups, and deletions

    public BST<E> insert(E elem) {
        if (elem.compareTo(data) < 0) return new NodeBST<>(data, left.insert(elem), right);
        else return new NodeBST<>(data, left, right.insert(elem));
    }
    public BST<E> insertAndBalance (E elem) {
    BST<E> result = insert(elem);
    if (!result.isBalanced()) {
        return TreeTraversals.balance(result);
    }
    return result;
    }
    public boolean find(E elem) {
        if (elem.compareTo(data) == 0) return true;
        else if (elem.compareTo(data) < 0) return left.find(elem);
        else return right.find(elem);
    }
    String path(E key) throws EmptyTreeExc {
        if (key.compareTo(data) == 0) return "";
        else if (key.compareTo(data) < 0) return "0" + left.path(key);
        else return "1" + right.path(key);
    }
    public BST<E> delete(E elem) throws EmptyTreeExc {
        if (elem.compareTo(data) < 0) return new NodeBST<>(data, left.delete(elem), right);
        else if (elem.compareTo(data) > 0) return new NodeBST<>(data, left, right.delete(elem));
        else {
            if (!left.isEmpty()) {
                E max = left.findMax();
                return new NodeBST<>(max, left.deleteMax(), right);
            }
            else return right;
            }
        }
    BST<E> deleteAndBalance(E elem) throws EmptyTreeExc {
        BST<E> result = delete(elem);
        if (!result.isBalanced()) {
            return TreeTraversals.balance(result);
        }
        return result;
    }
    public E findMin() {
        try {
            return left.findMin();
        }
        catch (EmptyTreeExc e) {
            return data;
        }
    }
    public E findMax() {
        try {
            return right.findMax();
        }
        catch (EmptyTreeExc e) {
            return data;
        }
    }
    public BST<E> deleteMin() {
        try {
            return new NodeBST<>(data, left.deleteMin(), right);
        }
        catch (EmptyTreeExc e) {
            return right;
        }
    }
    public BST<E> deleteMax() {
        try {
            return new NodeBST<>(data, left, right.deleteMax());
        } catch (EmptyTreeExc e) {
            return left;
        }
    }

    // Object methods

    public boolean equals(Object other) {
        if (other instanceof NodeBST otherNode) {
            return data.equals(otherNode.data) && left.equals(otherNode.left) && right.equals(otherNode.right);
        }
        else return false;
    }

    public String toString() {
        return String.format("(%s %s %s)", left, data, right);
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return left.isEmpty() ? null : left; }
    public TreePrinter.PrintableNode getRight() { return right.isEmpty() ? null : right; }
    public String getText() { return String.valueOf(data); }

}

