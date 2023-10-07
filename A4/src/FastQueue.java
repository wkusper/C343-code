import exceptions.EmptyPCollectionException;
import interfaces.QueueI;
import interfaces.StackI;

public class FastQueue<E> implements QueueI<E> {
    private final StackI<E> frontStack;
    private final StackI<E> backStack;

    public FastQueue () {
        frontStack = new Stack<>();
        backStack = new Stack<>();
    }

    // Constant time operations

    public boolean isEmpty () {
        return frontStack.isEmpty() && backStack.isEmpty();
    } // O(1)

    public int size () {
        return frontStack.size() + backStack.size();
    } // O(1)

    public void enqueue (E element) {
        backStack.push(element);
    }  // O(1)

    // Amortized constant time operations
    public E dequeue () throws EmptyPCollectionException { // amortized O(1)
        if (isEmpty()) {
            throw new EmptyPCollectionException();
        }
        if (frontStack.isEmpty()) {
            while (!backStack.isEmpty()) {
                frontStack.push(backStack.pop());
            }
        }
        return frontStack.pop();
    }
}
