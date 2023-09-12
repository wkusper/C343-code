import exceptions.EmptyPCollectionException;
import interfaces.DequeueI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeueTest {
    DequeueI<Integer> dequeue;

    @BeforeEach
    void setUp() {
        dequeue = new Dequeue<>();
    }

    @Test
    void isEmpty() {
        assertTrue(dequeue.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, dequeue.size());
    }

    @Test
    void enqueue() {
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        assertEquals(3, dequeue.size());
    }

    @Test
    void dequeue() throws EmptyPCollectionException {
        dequeue.enqueue(10);
        dequeue.enqueue(20);
        dequeue.enqueue(30);
        assertEquals(10, dequeue.dequeue());
        assertEquals(2, dequeue.size());
    }

    @Test
    void enqueueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(10);
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        assertEquals(3, dequeue.size());
        assertEquals(10, dequeue.dequeueBack());
    }

    @Test
    void dequeueBack() throws EmptyPCollectionException {
        dequeue.enqueueBack(10);
        dequeue.enqueueBack(20);
        dequeue.enqueueBack(30);
        assertEquals(10, dequeue.dequeueBack());
        assertEquals(2, dequeue.size());
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
    }
}