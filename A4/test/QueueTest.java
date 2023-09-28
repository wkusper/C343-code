import exceptions.EmptyPCollectionException;
import interfaces.QueueI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private QueueI<Integer> slow, fast;

    @BeforeEach
    void setUp() {
        slow = new SlowQueue<>();
        fast = new FastQueue<>();
    }

    @Test
    void isEmpty() {
        assertTrue(slow.isEmpty());
        assertTrue(fast.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, slow.size());
        assertEquals(0, fast.size());
        slow.enqueue(10);
        assertEquals(1, slow.size());
        fast.enqueue(10);
        assertEquals(1, fast.size());
    }

    @Test
    void enqueue() {
        slow.enqueue(10);
        slow.enqueue(20);
        slow.enqueue(30);
        assertEquals(3, slow.size());
        fast.enqueue(10);
        fast.enqueue(20);
        assertEquals(2, fast.size());
    }

    @Test
    void dequeue() throws EmptyPCollectionException {
        assertThrows(EmptyPCollectionException.class, () -> slow.dequeue());
        assertThrows(EmptyPCollectionException.class, () -> fast.dequeue());
        slow.enqueue(10);
        slow.enqueue(20);
        slow.enqueue(30);
        assertEquals(10, slow.dequeue());
        assertEquals(20, slow.dequeue());
        assertEquals(30, slow.dequeue());
        fast.enqueue(10);
        fast.enqueue(20);
        assertEquals(10, fast.dequeue());
        assertEquals(20, fast.dequeue());
    }

    @Test
    void EmptyQueueTests() {
        assertTrue(fast.isEmpty());
        assertTrue(slow.isEmpty());
        assertEquals(0, fast.size());
        assertEquals(0, slow.size());

        assertThrows(EmptyPCollectionException.class, () -> fast.dequeue());
        assertThrows(EmptyPCollectionException.class, () -> slow.dequeue());
    }

    @Test
    void SingleElementQueueTests() throws EmptyPCollectionException {
        fast.enqueue(42);
        slow.enqueue(42);

        assertFalse(fast.isEmpty());
        assertFalse(slow.isEmpty());
        assertEquals(1, fast.size());
        assertEquals(1, slow.size());

        assertEquals(42, fast.dequeue());
        assertEquals(42, slow.dequeue());

        assertTrue(fast.isEmpty());
        assertTrue(slow.isEmpty());
        assertEquals(0, fast.size());
        assertEquals(0, slow.size());
    }

    @Test
    void LargeNumberOfElementsTests() throws EmptyPCollectionException {
        int initialSize = new Random().nextInt(10000) + 1000; // Random size between 1000 and 100000

        for (int i = 1; i <= initialSize; i++) {
            fast.enqueue(i);
            slow.enqueue(i);
        }

        assertFalse(fast.isEmpty());
        assertFalse(slow.isEmpty());
        assertEquals(initialSize, fast.size());
        assertEquals(initialSize, slow.size());

        for (int i = 1; i <= initialSize; i++) {
            assertEquals(i, fast.dequeue());
            assertEquals(i, slow.dequeue());
            assertEquals(initialSize - i, fast.size());
            assertEquals(initialSize - i, slow.size());
        }

        assertTrue(fast.isEmpty());
        assertTrue(slow.isEmpty());
        assertEquals(0, fast.size());
        assertEquals(0, slow.size());
    }

    @Test
    void BoundaryValueTests() throws EmptyPCollectionException {
        // Test with maximum allowed size
        int maxSize = 10000;
        for (int i = 1; i <= maxSize; i++) {
            fast.enqueue(i);
            slow.enqueue(i);
        }

        assertFalse(fast.isEmpty());
        assertFalse(slow.isEmpty());
        assertEquals(maxSize, fast.size());
        assertEquals(maxSize, slow.size());

        for (int i = 1; i <= maxSize; i++){
            assertEquals(i, fast.dequeue());
            assertEquals(i, slow.dequeue());
            assertEquals(maxSize - i, fast.size());
            assertEquals(maxSize - i, slow.size());
        }

        assertTrue(fast.isEmpty());
        assertTrue(slow.isEmpty());
        assertEquals(0, fast.size());
        assertEquals(0, slow.size());

        // Test with minimum allowed size (empty queue)
        assertThrows(EmptyPCollectionException.class, () -> {
            fast.dequeue();
            slow.dequeue();
        });
    }

    @Test
    void InteractionOfAllMethods() throws EmptyPCollectionException {
        // Initialize with a random number of elements
        int initialSize = new Random().nextInt(10000) + 100; // Random size between 100 and 10000

        for (int i = 1; i <= initialSize; i++) {
            fast.enqueue(i);
            slow.enqueue(i);
        }

        // Test size and isEmpty
        assertEquals(initialSize, fast.size());
        assertFalse(fast.isEmpty());
        assertEquals(initialSize, slow.size());
        assertFalse(slow.isEmpty());



        // Test dequeue for both fast and slow
        for (int i = 1; i <= initialSize; i++) {
            assertEquals(i, fast.dequeue());
            assertEquals(i, slow.dequeue());
            assertEquals(initialSize - i, fast.size());
            assertEquals(initialSize - i, slow.size());
        }

        assertTrue(fast.isEmpty());
        assertTrue(slow.isEmpty());
    }


}