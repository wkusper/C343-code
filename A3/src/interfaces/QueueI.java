package interfaces;

import exceptions.EmptyPCollectionException;

public interface QueueI<E> extends PCollectionI<E> {
    void enqueue (E item);
    E dequeue () throws EmptyPCollectionException;
}
