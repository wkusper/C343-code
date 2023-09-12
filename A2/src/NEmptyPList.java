import exceptions.IndexOutOfBoundsException;
import exceptions.NotFoundException;

public class NEmptyPList<E> extends PList<E> {
    private final E first;
    private final PList<E> rest;
    private final int size;

    public NEmptyPList(E first, PList<E> rest) {
        this.first = first;
        this.rest = rest;
        this.size = 1 + rest.size();
    }

    //

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return size;
    }

    //
    public boolean contains (E element) {
        return first.equals(element) || rest.contains(element);
    }
    public int indexOf (E element) throws NotFoundException {
        if (first.equals(element)) return 0;
        else return 1 + rest.indexOf(element);
    }

    public int lastIndexOf (E element) throws NotFoundException {
        try {
            return 1 + rest.lastIndexOf(element);
        }
        catch (NotFoundException e) {
            if (first.equals(element)) return 0;
            else throw e;
        }
    }
    public E get (int index) throws IndexOutOfBoundsException {
        if (index == 0) return first;
        else return rest.get(index - 1);
    }
    public PList<E> remove (int index) throws IndexOutOfBoundsException {
        if (index == 0) return rest;
        else return new NEmptyPList<>(first, rest.remove(index - 1));
    }
    public PList<E> subList (int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        if (fromIndex == 0) {
            if (toIndex == 0) return new EmptyPList<>();
            else return new NEmptyPList<>(first, rest.subList(0, toIndex - 1));
        }
        else return rest.subList(fromIndex - 1, toIndex - 1);
    }
    public PList<E> addBack (E element) {
        return new NEmptyPList<>(first, rest.addBack(element));
    }

    // Equality

    public boolean equals (Object other) {
        if (other instanceof NEmptyPList<?> otherList)
            return first.equals(otherList.first) && rest.equals(otherList.rest);
        else return false;
    }
    public int hashCode() {
        return first.hashCode() + rest.hashCode();
    }
}
