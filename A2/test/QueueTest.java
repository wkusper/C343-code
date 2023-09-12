import exceptions.EmptyPCollectionException;
import interfaces.QueueI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}