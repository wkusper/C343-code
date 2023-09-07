public class NEmptyPList<E> extends PList<E> {
    private final E first;
    private final PList<E> rest;

    public NEmptyPList(E first, PList<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    public int size() {
          return 1 + rest.size();
    }

    public boolean isEmpty() {
        return !(this.first instanceof Integer);
    }
    public boolean contains (E element) {
         if (first.equals(element)) {
            return true;
        }
         else if (this.isEmpty())
        {return false;}
         else {
            return rest.contains(element);
        }
    }
    public int indexOf (E element) throws NotFoundException {
        try {
            if (first.equals(element)) {
                return 0;
            } else {
                return 1 + rest.indexOf(element);
            }
        } catch (NotFoundException e) {
            throw e;
        }
    }

    public int lastIndexOf (E element) throws NotFoundException {
        int lIndex = -1;
        for (int i = 0; i < this.size(); i++) {
            try {
                if (this.get(i).equals(element)) {
                    lIndex = i;
                }
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException(e);
            }
        }
        if (lIndex == -1) {
            throw new NotFoundException();
        } else {
            return lIndex;
        }
    }

    public E get (int index) throws IndexOutOfBoundsException {
        try {
            if (index == 0) {
                return first;
            } else {
                return rest.get(index - 1);
            }
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public PList<E> remove (int index) throws IndexOutOfBoundsException {
    try {
            if (index == 0) {
                return rest;
            } else {
                return new NEmptyPList<>(first, rest.remove(index - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public PList<E> subList (int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        try {
            if (fromIndex > toIndex) {
                throw new IndexOutOfBoundsException();
            }
            if (fromIndex < 0 || toIndex > this.size()) {
                throw new IndexOutOfBoundsException();
            }
            if (fromIndex == 0) {
                if (toIndex == 0) {
                    return new EmptyPList<>();
                } else {
                    return new NEmptyPList<>(first, rest.subList(0, toIndex - 1));
                }
            } else {
                return rest.subList(fromIndex - 1, toIndex - 1);
            }
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public boolean equals (Object other) {
        if (other instanceof NEmptyPList<?> otherList) {
            return first.equals(otherList.first) && rest.equals(otherList.rest);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return first.hashCode() + rest.hashCode();
    }
}
