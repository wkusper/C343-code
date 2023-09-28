package interfaces;

import exceptions.EmptyPCollectionException;

public interface DequeueI<E> extends QueueI<E> {
    void enqueueBack (E item);
    E dequeueBack () throws EmptyPCollectionException;
}
