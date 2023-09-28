import exceptions.IndexOutOfBoundsException;
import exceptions.NotFoundException;
import interfaces.PCollectionI;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class PList<E> implements PCollectionI<E>, Iterable<E> {
    static PList<Integer> makeList(int... elements) {
        PList<Integer> list = new EmptyPList<>();
        for (int i = elements.length - 1; i >= 0; i--) {
            list = new NEmptyPList<>(elements[i], list);
        }
        return list;
    }

    // Constant time operations

    public abstract boolean isEmpty(); // O(1)
    public abstract int size(); // O(1)
    PList<E> addFront (E element) { // O(1)
        return new NEmptyPList<>(element, this);
    }

    // Linear time operations

    abstract boolean contains (E element); // O(n)
    abstract int indexOf (E element) throws NotFoundException; // O(n)
    abstract int lastIndexOf (E element) throws NotFoundException; // O(n)
    abstract E get (int index) throws IndexOutOfBoundsException; // O(n)
    abstract PList<E> remove (int index) throws IndexOutOfBoundsException; // O(n)
    abstract PList<E> subList (int fromIndex, int toIndex) throws IndexOutOfBoundsException; // O(n)
    abstract PList<E> addBack (E element); // O(n)

   // Easy access to all the elements

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
