
public abstract class PList<E> {

    /**
     * Returns the number of elements in this list.
     */
    abstract int size();

    /**
     * Returns true if this list contains no elements.
     */
    abstract boolean isEmpty() ;
    /**
     * Returns true if this list contains the specified element.
     */
    abstract boolean contains (E element);

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     * @throws NotFoundException if the element is not in this list.
     */
    abstract int indexOf (E element) throws NotFoundException;

    /**
     * Returns the index of the last occurrence of the specified element in this list.
     * @throws NotFoundException if the element is not in this list.
     */
    abstract int lastIndexOf (E element) throws NotFoundException;

    /**
     * Returns the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    abstract E get (int index) throws IndexOutOfBoundsException;

    /**
     * Returns a new list with the element at the given index removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    abstract PList<E> remove (int index) throws IndexOutOfBoundsException;

    /**
     * Returns a new list containing the elements from the first given index (inclusive)
     * to the second given index (exclusive).
     * @throws IndexOutOfBoundsException if either index is out of range.
     */
    abstract PList<E> subList (int fromIndex, int toIndex) throws IndexOutOfBoundsException;
}
