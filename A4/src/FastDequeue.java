import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import exceptions.EmptyPCollectionException;
import interfaces.DequeueI;

public class FastDequeue<E> implements DequeueI<E> {
    /**
     * This in an implementation of a dynamically resizable dequeue.
     * All operations run in amortized constant time
     * <p>
     * In general the values are stored in the array as follows:
     * <p>
     * |-------------------------|
     * | 4 5 6 _ _ _ _ _ _ 1 2 3 |
     * |-------------------------|
     *        /\        /\      /\
     *       left      right  capacity
     * <p>
     * left and right typically point to the next available slot
     * all arithmetic modulo capacity
     * data stored at right+1, right+2, ... left-2, left-1
     *
     */
    private int capacity;
    private Optional<E>[] elements;
    private int left, right, size;
    private final Function<Integer,Integer> growthStrategy;

    @SuppressWarnings("unchecked")
    FastDequeue () {
        this.capacity = 8;
        this.elements = (Optional<E>[]) Array.newInstance(Optional.class, capacity);
        Arrays.fill(elements, Optional.empty());
        left = 0;
        right = this.capacity - 1;
        size = 0;
        this.growthStrategy = n -> n * 2;
    }

    public int size() { // O(1)
        return size;
    }

    public boolean isEmpty() { // O(1)
        return size == 0;
    }
    public void enqueue(E item) { // uses right pointer; O(1) amortized
        if (size == capacity) resize();
        elements[right] = Optional.of(item);
        right = Math.floorMod(right-1, capacity);
        size++;
    }

    public E dequeue() throws EmptyPCollectionException { // uses left pointer; O(1) amortized
        int index = Math.floorMod(left - 1, capacity);
        E res = elements[index].orElseThrow(EmptyPCollectionException::new);
        elements[index] = Optional.empty();
        left = index;
        size--;
        return res;
    }
    public void enqueueBack(E item) { // uses left pointer; O(1) amortized
        if (size == capacity) resize();
        elements[left] = Optional.of(item);
        left = Math.floorMod(left+1, capacity);
        size++;
    }

    public E dequeueBack() throws EmptyPCollectionException { // uses right pointer; O(1) amortized
        int index = Math.floorMod(right + 1, capacity);
        E res = elements[index].orElseThrow(EmptyPCollectionException::new);
        elements[index] = Optional.empty();
        right = index;
        size--;
        return res;
    }

    @SuppressWarnings("unchecked")
    private void resize () {
        int newCapacity = growthStrategy.apply(capacity);
        Optional<E>[] newElements = (Optional<E>[]) Array.newInstance(Optional.class, newCapacity);
        Arrays.fill(newElements, Optional.empty());
        for (int i=0; i<size; i++)
            newElements[i] = elements[Math.floorMod(right+1+i, capacity)];
        capacity = newCapacity;
        elements = newElements;
        left = size;
        right = capacity - 1;
    }
}
