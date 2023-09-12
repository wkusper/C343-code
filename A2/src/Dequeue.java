import exceptions.EmptyPCollectionException;
import exceptions.IndexOutOfBoundsException;
import interfaces.DequeueI;

public class Dequeue<E> extends SlowQueue<E> implements DequeueI<E> {
    /**
     * This class extends a queue implementation with dual operations
     * that work from the other end
     *
     * The enqueueBack operation must be O(1)
     * The dequeueBack operation is O(n)
     *
     */
    public Dequeue () {
        super();
    }

    // Constant time operations
    public void enqueueBack (E element) {
        list = list.addFront(element);
    } // O(1)

    // Linear time operations
    public E dequeueBack () throws EmptyPCollectionException { // O(n)
        try {
            E element = list.get(list.size() - 1);
            list = list.remove(list.size() - 1);
            return element;
        }
        catch (IndexOutOfBoundsException e) {
            throw new EmptyPCollectionException();
        }
    }
}
