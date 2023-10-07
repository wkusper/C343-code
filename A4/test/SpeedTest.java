import exceptions.EmptyPCollectionException;
import interfaces.QueueI;
import interfaces.StackI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpeedTest {
    private StackI<Integer> stack;
    QueueI<Integer> slow, fast;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
        slow = new SlowQueue<>();
        fast = new FastQueue<>();
    }

    @Test
    void stack () throws EmptyPCollectionException { // all operations O(1)
        long start, end;
        double baseline; // approximate cost of 100000 operations
        int increment = 100000;
        int repetitions = 1000;

        start = System.currentTimeMillis();
        for (int i = 0; i < increment; i++) stack.push(i);
        for (int i = 0; i < increment; i++) stack.pop();
        end = System.currentTimeMillis();
        baseline = (end - start) * 1.1;

        // cost per 100000 operations should remain about the same
        start = System.currentTimeMillis();
        for (int i = 0; i < repetitions * increment; i += increment) stack.push(i);
        for (int i = 0; i < repetitions * increment; i += increment) stack.pop();
        end = System.currentTimeMillis();
        assertTrue(((double) (end - start)) / repetitions < baseline);
    }

    @Test
    void queues () throws EmptyPCollectionException { // slowQueue enqueue is O(n); all other operations amortized O(1)
        long start, end;
        int n = 10000;

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) fast.enqueue(i);
        for (int i = 0; i < n; i++) fast.dequeue();
        end = System.currentTimeMillis();
        assertTrue(end-start < 10);

        slow = new SlowQueue<>();

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) slow.enqueue(i); // O(n)
        for (int i = 0; i < n; i++) slow.dequeue();
        end = System.currentTimeMillis();
        assertTrue(end-start > 450);

    }
    @Test
    void slowVsFast () throws EmptyPCollectionException {
        long start, end;
        int n = 10000;

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) fast.enqueue(i);
        for (int i = 0; i < n; i++) fast.dequeue();
        end = System.currentTimeMillis();
        long fastTime = end - start;

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) slow.enqueue(i); // O(n)
        for (int i = 0; i < n; i++) slow.dequeue();
        end = System.currentTimeMillis();
        long slowTime = end-start;

        assertTrue(slowTime > fastTime);

    }


}
