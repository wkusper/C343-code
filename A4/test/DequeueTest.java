import exceptions.EmptyPCollectionException;
import interfaces.DequeueI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DequeueTest {
    DequeueI<Integer> dequeue, fastDequeue;

    @BeforeEach
    void setUp() {
        dequeue = new Dequeue<>();
        fastDequeue = new FastDequeue<>();
    }

    @Test
    void isEmpty() {
        assertTrue(dequeue.isEmpty());
        assertTrue(fastDequeue.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, dequeue.size());
        assertEquals(0, fastDequeue.size());
    }

    @Test
    void enqueue() {
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        assertEquals(3, dequeue.size());

        fastDequeue.enqueue(10);
        fastDequeue.enqueue(20);
        fastDequeue.enqueue(30);
        assertEquals(3, fastDequeue.size());
    }

    @Test
    void dequeue() throws EmptyPCollectionException {
        assertThrows(EmptyPCollectionException.class, () -> dequeue.dequeue());
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        assertEquals(10, dequeue.dequeue());
        assertEquals(2, dequeue.size());
        assertEquals(20, dequeue.dequeue());
        assertEquals(30, dequeue.dequeue());
        assertThrows(EmptyPCollectionException.class, () -> dequeue.dequeue());

        assertThrows(EmptyPCollectionException.class, () -> fastDequeue.dequeue());
        fastDequeue.enqueue(10);
        fastDequeue.enqueue(20);
        fastDequeue.enqueue(30);
        assertEquals(10, fastDequeue.dequeue());
        assertEquals(2, fastDequeue.size());
        assertEquals(20, fastDequeue.dequeue());
        assertEquals(30, fastDequeue.dequeue());
        assertThrows(EmptyPCollectionException.class, () -> fastDequeue.dequeue());
    }

    @Test
    void enqueueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(10);
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        assertEquals(3, dequeue.size());
        assertEquals(10, dequeue.dequeueBack());

        fastDequeue.enqueueBack(10);
        fastDequeue.enqueueBack(20);
        fastDequeue.enqueueBack(30);
        assertEquals(3, fastDequeue.size());
        assertEquals(10, fastDequeue.dequeueBack());
    }

    @Test
    void dequeueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(10);
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        assertEquals(10, dequeue.dequeueBack());
        assertEquals(2, dequeue.size());
        assertEquals(20, dequeue.dequeueBack());
        assertEquals(30, dequeue.dequeueBack());
        assertThrows(EmptyPCollectionException.class, () -> dequeue.dequeueBack());

        fastDequeue.enqueueBack(10);
        fastDequeue.enqueueBack(20);
        fastDequeue.enqueueBack(30);
        assertEquals(10, fastDequeue.dequeueBack());
        assertEquals(2, fastDequeue.size());
        assertEquals(20, fastDequeue.dequeueBack());
        assertEquals(30, fastDequeue.dequeueBack());
        assertThrows(EmptyPCollectionException.class, () -> fastDequeue.dequeueBack());
    }

    @Test
    void frontAndBack () throws EmptyPCollectionException {
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        // [10, 20, 30]
        dequeue.enqueueBack(40);
        dequeue.enqueueBack(50);
        // [50, 40, 10, 20, 30]
        assertEquals(50, dequeue.dequeue());
        assertEquals(30, dequeue.dequeueBack());

        fastDequeue.enqueue(10);
        fastDequeue.enqueue(20);
        fastDequeue.enqueue(30);
        // [10, 20, 30]
        fastDequeue.enqueueBack(40);
        fastDequeue.enqueueBack(50);
        // [50, 40, 10, 20, 30]
        assertEquals(50, fastDequeue.dequeue());
        assertEquals(30, fastDequeue.dequeueBack());
    }

    void frontAndBackLarge(DequeueI<Integer> dequeue) throws EmptyPCollectionException {
        int numElements = 2000; // Adjust the number of elements as needed

        // Enqueue elements from 1 to numElements
        for (int i = 1; i <= numElements; i++) {
            dequeue.enqueue(i);
        }

        assertFalse(dequeue.isEmpty());
        assertEquals(numElements, dequeue.size());

        // Dequeue elements from the front
        List<Integer> frontDequeued = new ArrayList<>();
        for (int i = 1; i <= numElements; i++) {
            frontDequeued.add(dequeue.dequeue());
            assertEquals(numElements - i, dequeue.size());
        }

        // Enqueue elements to the back
        for (int i = 1; i <= numElements * 2; i++) {
            dequeue.enqueueBack(i);
        }

        assertEquals(numElements * 2, dequeue.size());

        // Dequeue elements from the back
        List<Integer> backDequeued = new ArrayList<>();
        for (int i = numElements * 2; i >= 1 ; i--) {
            backDequeued.add(dequeue.dequeueBack());
            assertEquals(i - 1, dequeue.size());
        }

        // Check the order of elements after dequeuing from both ends
        for (int i = 0; i < numElements; i++) {
            assertEquals(i+1, frontDequeued.get(i));
            assertEquals(i+1, backDequeued.get(i));
        }

        assertTrue(dequeue.isEmpty());
        assertEquals(0, dequeue.size());
    }

    @Test
    void frontAndBackLarge() throws EmptyPCollectionException {
        long start, end;

        start = System.currentTimeMillis();
        frontAndBackLarge(dequeue);
        end = System.currentTimeMillis();
        long slow = end - start;

        start = System.currentTimeMillis();
        frontAndBackLarge(fastDequeue);
        end = System.currentTimeMillis();
        long fast = end - start;

        assertTrue(fast * 50 < slow);
    }
}