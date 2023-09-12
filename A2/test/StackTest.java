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
        assertThrows(Exception.class, () -> stack.pop());
    }
}