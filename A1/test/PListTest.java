import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import java.time.Duration;

class PListTest {
    private PList<Integer> list;
    private final Class<IndexOutOfBoundsException> boundsC = IndexOutOfBoundsException.class;
    private final Class<NotFoundException> notFoundC = NotFoundException.class;

    @BeforeEach
    void setUp() {
        list = new EmptyPList<>();
    }

    PList<Integer> makeList(int... elements) {
        PList<Integer> list = new EmptyPList<>();
        for (int i = elements.length - 1; i >= 0; i--) {
            list = new NEmptyPList<>(elements[i], list);
        }
        return list;
    }

    @Test
    @DisplayName("empty: This test is included with the assignment.")
    void empty() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
            list = makeList(10);
            assertFalse(list.isEmpty());
            assertEquals(1, list.size());
            assertFalse(list.isEmpty());
            assertNotEquals(2, list.size());
            list = makeList(10, 20);
            assertFalse(list.isEmpty());
            assertNotEquals(1, list.size());
            assertNotEquals(3, list.size());
            assertEquals(2, list.size());
        });
    }

    @Test
    @DisplayName("contains: This test is included with the assignment.")
    void contains() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertFalse(list.contains(10));
            list = makeList(10);
            assertTrue(list.contains(10));
            assertFalse(list.contains(20));
            list = makeList(10, 20);
            assertTrue(list.contains(10));
            assertTrue(list.contains(20));
            assertFalse(list.contains(30));
            list = makeList(10, 20, 50, 40);
            assertTrue(list.contains(10));
            assertTrue(list.contains(20));
            assertTrue(list.contains(50));
        });
    }

    @Test
    @DisplayName("get: This test is included with the assignment.")
    void get() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertThrows(boundsC, () -> list.get(0));
            list = makeList(10);
            assertEquals(10, list.get(0));
            assertThrows(boundsC, () -> list.get(1));
            list = makeList(10, 20);
            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertThrows(boundsC, () -> list.get(2));
            list = makeList(10, 20, 30);
            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertEquals(30, list.get(2));
            assertThrows(boundsC, () -> list.get(3));
            assertThrows(boundsC, () -> list.get(-1));
        });
    }

    @Test
    @DisplayName("indexOf: This test is included with the assignment.")
    void indexOf() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            list = makeList(10);
            assertEquals(0, list.indexOf(10));
            assertThrows(notFoundC, () -> list.indexOf(40));
            list = makeList(10, 20);
            assertEquals(0, list.indexOf(10));
            assertEquals(1, list.indexOf(20));
            assertThrows(notFoundC, () -> list.indexOf(40));
           list = makeList(10, 20, 20);
            assertEquals(0, list.indexOf(10));
            assertEquals(1, list.indexOf(20));
            assertThrows(notFoundC, () -> list.indexOf(40));
        });
    }

    @Test
    @DisplayName("lastIndexOf: This test is included with the assignment.")
    void lastIndexOf() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertThrows(notFoundC, () -> list.lastIndexOf(40));
            list = makeList(10);
            assertEquals(0, list.lastIndexOf(10));
            assertThrows(notFoundC, () -> list.lastIndexOf(40));
            list = makeList(10, 10, 20, 20, 20, 20, 30);
            assertEquals(5, list.lastIndexOf(20));
            list = makeList(10,20,10,20,10,20,10);
            assertEquals(6, list.lastIndexOf(10));
            assertEquals(5, list.lastIndexOf(20));
        });
    }

    @Test
    @DisplayName("remove: This test is included with the assignment.")
    void remove() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertThrows(boundsC, () -> list.remove(0));
            list = makeList(10);
            assertEquals(new EmptyPList<>(), list.remove(0));
            assertThrows(boundsC, () -> list.remove(1));
            list = makeList(10, 20);
            assertEquals(makeList(20), list.remove(0));
            assertEquals(makeList(10), list.remove(1));
            assertThrows(boundsC, () -> list.remove(2));
            assertThrows(boundsC, () -> list.remove(-1));
            PList<Integer> list2 = makeList(10, 20, 30);
            PList<Integer> list3 = list2.remove(1);
            assertTrue(list2.contains(20));
            assertFalse(list3.contains(20));
        });
    }

    @Test
    @DisplayName("subList: This test is included with the assignment.")
    void subList() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertEquals(new EmptyPList<>(), list.subList(0, 0));
            list = makeList(10);
            assertEquals(new EmptyPList<>(), list.subList(0, 0));
            assertEquals(new EmptyPList<>(), list.subList(1, 1));
            assertThrows(boundsC, () -> list.subList(0, 2));
            list = makeList(10, 20);
            assertEquals(makeList(10), list.subList(0, 1));
            assertEquals(makeList(20), list.subList(1, 2));
            assertThrows(boundsC, () -> list.subList(0, 3));
            assertThrows(boundsC, () -> list.subList(2, 1));
            assertThrows(boundsC, () -> list.subList(-1, 1));
        });
    }

}
