import exceptions.NotFoundException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class PList<E> implements Iterable<E> {
    abstract int size();
    abstract boolean isEmpty();
    abstract boolean contains (E element);
    abstract int indexOf (E element) throws NotFoundException;
    abstract int lastIndexOf (E element) throws NotFoundException;
    abstract E get (int index) throws IndexOutOfBoundsException;
    abstract PList<E> remove (int index) throws IndexOutOfBoundsException;
    abstract PList<E> subList (int fromIndex, int toIndex) throws IndexOutOfBoundsException;

    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int index = 0;

            public boolean hasNext() {
                return index < size();
            }

            public E next() {
                try {
                    return get(index++);
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
