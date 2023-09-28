import exceptions.DuplicateKeyException;
import exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HMTest {
    private HM<Integer, String> hm;

    @BeforeEach
    void setUp() {
        hm = new HM<>();
    }

    @Test
    void rehash() throws DuplicateKeyException, NotFoundException {
        int capacity = 2467;
        hm = new HM<>(capacity);
        int size = 2000;

        long start, end;

        // all the keys collide
        start = System.currentTimeMillis();
        for (int i=0; i<size; i++)
            hm.put(i*capacity, Integer.toString(i));
        for (int i=0; i<size; i++)
            hm.get(i*capacity);
        end = System.currentTimeMillis();
        long slow = end - start;

        hm.clear();
        // not a single collision
        start = System.currentTimeMillis();
        for (int i=0; i<size; i++)
            hm.put(i, Integer.toString(i));
        for (int i=0; i<size; i++)
            hm.get(i);
        end = System.currentTimeMillis();
        long fast = end - start;

        assertTrue(slow > fast * 1000); 
    }

    @Test
    void studentTests() throws DuplicateKeyException, NotFoundException {
        // test put, size, loadFactor, and isEmpty
        assertTrue(hm.isEmpty());
        assertEquals(0, hm.size());
        assertEquals(0, hm.loadFactor());
        hm.put(1, "one");
        assertFalse(hm.isEmpty());
        assertEquals(1, hm.size());
        assertEquals(1.0/11, hm.loadFactor());
        hm.put(2, "two");
        assertEquals(2, hm.size());
        assertEquals(2.0/11, hm.loadFactor());
        hm.put(3, "three");
        assertEquals(3, hm.size());
        assertEquals(3.0/11, hm.loadFactor());
        assertThrows(DuplicateKeyException.class, () -> hm.put(3, "three"));

        // test get
        assertEquals("one", hm.get(1));
        assertEquals("two", hm.get(2));
        assertEquals("three", hm.get(3));
        assertThrows(NotFoundException.class, () -> hm.get(4));

        // test containsKey
        assertTrue(hm.containsKey(1));
        assertTrue(hm.containsKey(2));
        assertTrue(hm.containsKey(3));
        assertFalse(hm.containsKey(4));

        // test containsValue
        assertTrue(hm.containsValue("one"));
        assertTrue(hm.containsValue("two"));
        assertTrue(hm.containsValue("three"));
        assertFalse(hm.containsValue("four"));

        // test rehash
        hm.put(4, "four");
        hm.put(5, "five");
        hm.put(6, "six");
        hm.put(7, "seven");
        hm.put(8, "eight");
        hm.put(9, "nine");
        hm.put(10, "ten");
        assertEquals(23, hm.getCapacity());


        // test clear
        hm.clear();
        assertEquals(0, hm.size());
        assertThrows(NotFoundException.class, () -> hm.get(1));

        // test nextPrime
        assertEquals(11, hm.nextPrime(10));
        assertEquals(23, hm.nextPrime(22));
        assertEquals(29, hm.nextPrime(23));
    }
}