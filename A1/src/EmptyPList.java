public class EmptyPList<E> extends PList<E> {

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean contains(E element) {
        return false;
    }

    public int indexOf(E element) throws NotFoundException {
        throw new NotFoundException();
    }

    public int lastIndexOf(E element) throws NotFoundException {
        throw new NotFoundException();
    }

    public E get(int index) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }

    public PList<E> remove(int index) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }

    public PList<E> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        if (fromIndex == toIndex && fromIndex == 0) {
            return new EmptyPList<>();
        }
        throw new IndexOutOfBoundsException();
    }


    public boolean equals (Object other) {
        return other instanceof EmptyPList;
    }

    public int hashCode() {
        return 0;
    }

}
