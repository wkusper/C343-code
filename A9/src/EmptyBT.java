public class EmptyBT<E extends Comparable<E>> extends BinaryTree<E> {

    // Access fields

    E getData () throws EmptyTreeE { throw new EmptyTreeE(); }
    BinaryTree<E> getLeftBT () throws EmptyTreeE { throw new EmptyTreeE(); }
    BinaryTree<E> getRightBT () throws EmptyTreeE { throw new EmptyTreeE(); }

    // Basic properties

    boolean isEmpty () { return true; }
    int height () { return 0; }
    int size() { return 0; }
    boolean isBalanced () { return true; }

    BinaryTree<E> insertBalanced (NodeBT<E> node) {
        return node;
    }

    E getRightMost () throws EmptyTreeE {
        throw new EmptyTreeE(); }
    BinaryTree<E> removeRightMost () throws EmptyTreeE {
        throw new EmptyTreeE();}
    BinaryTree<E> deleteRoot () throws EmptyTreeE {
        throw new EmptyTreeE(); }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return null; }
    public TreePrinter.PrintableNode getRight() { return null; }
    public String getText() { return ""; }

    public void swapData(NodeBT<E> node) throws EmptyTreeE{
        throw new EmptyTreeE();
    }
    public void moveUp() {}
    public void moveDown() {}
}
