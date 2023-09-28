import exceptions.EmptyPCollectionException;
import exceptions.IndexOutOfBoundsException;
import interfaces.StackI;

public class Stack<E> implements StackI<E> {
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
    public E pop() throws EmptyPCollectionException { // O(1)
        try {
            E result = list.get(0);
            list = list.remove(0);
            return result;
        }
        catch (IndexOutOfBoundsException e) {
            throw new EmptyPCollectionException();
        }
    }
}
