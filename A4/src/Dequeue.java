import exceptions.EmptyPCollectionException;
import exceptions.IndexOutOfBoundsException;
import interfaces.DequeueI;

public class Dequeue<E> extends SlowQueue<E> implements DequeueI<E> {
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
            E elem = list.get(list.size() - 1);
            list = list.remove(list.size() - 1);
            return elem;
        }
        catch (IndexOutOfBoundsException e) {
            throw new EmptyPCollectionException();
        }
    }

}
