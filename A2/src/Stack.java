import exceptions.EmptyPCollectionException;
import exceptions.IndexOutOfBoundsException;
import interfaces.StackI;

public class Stack<E> implements StackI<E> {
    /**
     * This class implements a stack interface by internally maintaining a PList.
     * All the operations must be O(1)
     *
     */
    private PList<E> list;

    public Stack () {
        list = new EmptyPList<>();
    }

    // Constant time operations

    public boolean isEmpty () {
        return list.isEmpty();
    } // O(1)

    public int size () {
        return list.size();
    } // O(1)
    public void push (E element) {
        list = list.addFront(element);
    } // O(1)
    public E pop() throws EmptyPCollectionException {
        try {
            E element = list.get(0);
            list = list.remove(0);
            return element;
        }
        catch (IndexOutOfBoundsException e) {
            throw new EmptyPCollectionException();
        }
    } // O(1)
}
