package interfaces;

import exceptions.EmptyPCollectionException;

public interface StackI<E> extends PCollectionI<E> {
    void push (E item);
    E pop() throws EmptyPCollectionException;
}
