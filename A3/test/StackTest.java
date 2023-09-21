import exceptions.EmptyPCollectionException;
import interfaces.StackI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    StackI<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, stack.size());
    }

    @Test
    void push() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(3, stack.size());
    }

    @Test
    void pop() throws EmptyPCollectionException {
        assertThrows(Exception.class, () -> stack.pop());
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void EmptyStackTests() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        assertThrows(EmptyPCollectionException.class, () -> stack.pop());
    }

    @Test
    void SingleElementStackTests() throws EmptyPCollectionException {
        stack.push(42);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        assertEquals(42, stack.pop());

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        assertThrows(EmptyPCollectionException.class, () -> stack.pop());
    }

    @Test
    void LargeNumberOfElementsTests() throws EmptyPCollectionException {
        int maxSize = 10000;

        for (int i = 1; i <= maxSize; i++) {
            stack.push(i);
        }

        assertFalse(stack.isEmpty());
        assertEquals(maxSize, stack.size());

        for (int i = maxSize; i >= 1; i--) {
            assertEquals(i, stack.pop());
            assertEquals(i - 1, stack.size());
        }

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        assertThrows(EmptyPCollectionException.class, () -> stack.pop());
    }

    @Test
    void BoundaryValueTests() throws EmptyPCollectionException {
        // Define a reasonable maximum size
        int maxSize = 1000;

        // Push up to the maximum size
        for (int i = 1; i <= maxSize; i++) {
            stack.push(i);
        }

        assertFalse(stack.isEmpty());
        assertEquals(maxSize, stack.size());

        // Pop and verify elements
        for (int i = maxSize; i >= 1; i--) {
            assertEquals(i, stack.pop());
            assertEquals(i - 1, stack.size());
        }

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        // Test with an empty stack
        assertThrows(EmptyPCollectionException.class, () -> stack.pop());
    }

    @Test
    void InteractionOfAllMethods() throws EmptyPCollectionException {
        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.size());

        // Pop elements and check their order
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        assertThrows(EmptyPCollectionException.class, () -> stack.pop());
    }


}