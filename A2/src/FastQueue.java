import exceptions.EmptyPCollectionException;
import interfaces.QueueI;
import interfaces.StackI;

public class FastQueue<E> implements QueueI<E> {
    /**
     * This is another implementation of queues that is much faster
     * than the one in SlowQueue. The idea is to use two stacks to
     * implement the queue. The frontStack is used to dequeue elements
     * and the backStack is used to enqueue elements. When the frontStack
     * is empty, we pop all the elements from the backStack and push them
     * into the frontStack. This way, the frontStack will have the elements
     * in the correct order to dequeue them.
     *
     * The enqueue operation must be O(1)
     *
     * Even though the dequeue operation is O(n) in the worst
     * case, it is amortized O(1) because we only need to move all the
     * elements from the backStack to the frontStack when the frontStack is
     * empty. This only happens once every n operations, so the amortized
     * cost is O(n) / n = O(1).
     *
     */
    private final StackI<E> frontStack;
    private final StackI<E> backStack;

    public FastQueue () {
        frontStack = new Stack<>();
        backStack = new Stack<>();
    }

    // Constant time operations

    public boolean isEmpty () {
      return (frontStack.isEmpty() && backStack.isEmpty());
    } // O(1)

    public int size () {
      return frontStack.size() + backStack.size();
    } // O(1)

    public void enqueue (E element) {
        backStack.push(element);
    }  // O(1)

    // Amortized constant time operations
    public E dequeue () throws EmptyPCollectionException { // amortized O(1)
        if (frontStack.isEmpty()) {
            while (!backStack.isEmpty()) {
                frontStack.push(backStack.pop());
            }
        }
        return frontStack.pop();
    }
}
