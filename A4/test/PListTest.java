import exceptions.IndexOutOfBoundsException;
import exceptions.NotFoundException;
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
    
    @Test
    @DisplayName("empty: This test is included with the assignment.")
    void empty() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
            list = PList.makeList(10);
            assertFalse(list.isEmpty());
            assertEquals(1, list.size());
        });
    }

    @Test
    @DisplayName("contains: This test is included with the assignment.")
    void contains() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertFalse(list.contains(10));
            list = PList.makeList(10);
            assertTrue(list.contains(10));
            assertFalse(list.contains(20));
            list = PList.makeList(10, 20);
            assertTrue(list.contains(10));
            assertTrue(list.contains(20));
            assertFalse(list.contains(30));
        });
    }

    @Test
    @DisplayName("get: This test is included with the assignment.")
    void get() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertThrows(boundsC, () -> list.get(0));
            list = PList.makeList(10);
            assertEquals(10, list.get(0));
            assertThrows(boundsC, () -> list.get(1));
            list = PList.makeList(10, 20);
            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertThrows(boundsC, () -> list.get(2));
        });
    }

    @Test
    @DisplayName("indexOf: This test is included with the assignment.")
    void indexOf() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            list = PList.makeList(10);
            assertEquals(0, list.indexOf(10));
            assertThrows(notFoundC, () -> list.indexOf(40));
            list = PList.makeList(10, 20);
            assertEquals(0, list.indexOf(10));
            assertEquals(1, list.indexOf(20));
        });
    }

    @Test
    @DisplayName("lastIndexOf: This test is included with the assignment.")
    void lastIndexOf() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            list = PList.makeList(10);
            assertEquals(0, list.lastIndexOf(10));
            assertThrows(notFoundC, () -> list.lastIndexOf(40));
            list = PList.makeList(10, 10, 20, 20, 20, 20, 30);
            assertEquals(5, list.lastIndexOf(20));
        });
    }

    @Test
    @DisplayName("remove: This test is included with the assignment.")
    void remove() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertThrows(boundsC, () -> list.remove(0));
            list = PList.makeList(10);
            assertEquals(new EmptyPList<>(), list.remove(0));
            assertThrows(boundsC, () -> list.remove(1));
            list = PList.makeList(10, 20);
            assertEquals(PList.makeList(20), list.remove(0));
            assertEquals(PList.makeList(10), list.remove(1));
            assertThrows(boundsC, () -> list.remove(2));
            PList<Integer> list2 = PList.makeList(10, 20, 30);
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
            list = PList.makeList(10);
            assertEquals(new EmptyPList<>(), list.subList(0, 0));
            assertEquals(new EmptyPList<>(), list.subList(1, 1));
            assertThrows(boundsC, () -> list.subList(0, 2));
            list = PList.makeList(10, 20);
            assertEquals(PList.makeList(10), list.subList(0, 1));
            assertEquals(PList.makeList(20), list.subList(1, 2));
            assertThrows(boundsC, () -> list.subList(0, 3));
        });
    }

    @Test
    @DisplayName("comprehensive")
    void comprehensive() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            list = PList.makeList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
            assertEquals(10, list.size());
            assertFalse(list.isEmpty());
            assertTrue(list.contains(10));
            assertTrue(list.contains(20));
            assertTrue(list.contains(30));
            assertTrue(list.contains(40));
            assertTrue(list.contains(50));
            assertTrue(list.contains(60));
            assertTrue(list.contains(70));
            assertTrue(list.contains(80));
            assertTrue(list.contains(90));
            assertTrue(list.contains(100));
            assertEquals(0, list.indexOf(10));
            assertEquals(1, list.indexOf(20));
            assertEquals(2, list.indexOf(30));
            assertEquals(3, list.indexOf(40));
            assertEquals(4, list.indexOf(50));
            assertEquals(5, list.indexOf(60));
            assertEquals(6, list.indexOf(70));
            assertEquals(7, list.indexOf(80));
            assertEquals(8, list.indexOf(90));
            assertEquals(9, list.indexOf(100));
            assertEquals(9, list.lastIndexOf(100));
            assertEquals(8, list.lastIndexOf(90));
            assertEquals(7, list.lastIndexOf(80));
            assertEquals(6, list.lastIndexOf(70));
            assertEquals(5, list.lastIndexOf(60));
            assertEquals(4, list.lastIndexOf(50));
            assertEquals(3, list.lastIndexOf(40));
            assertEquals(2, list.lastIndexOf(30));
            assertEquals(1, list.lastIndexOf(20));
            assertEquals(0, list.lastIndexOf(10));
            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertEquals(30, list.get(2));
            assertEquals(40, list.get(3));
            assertEquals(50, list.get(4));
            assertEquals(60, list.get(5));
            assertEquals(70, list.get(6));
            assertEquals(80, list.get(7));
            assertEquals(90, list.get(8));
            assertEquals(100, list.get(9));
            assertEquals(PList.makeList(20, 30, 40, 50, 60, 70, 80, 90, 100), list.remove(0));
            assertEquals(PList.makeList(10, 30, 40, 50, 60, 70, 80, 90, 100), list.remove(1));
            assertEquals(PList.makeList(10, 20, 40, 50, 60, 70, 80, 90, 100), list.remove(2));
            assertEquals(PList.makeList(10, 20, 30, 50, 60, 70, 80, 90, 100), list.remove(3));
            assertEquals(PList.makeList(10, 20, 30, 40, 60, 70, 80, 90, 100), list.remove(4));
            assertEquals(PList.makeList(10, 20, 30, 40, 50, 70, 80, 90, 100), list.remove(5));
            assertEquals(PList.makeList(10, 20, 30, 40, 50, 60, 80, 90, 100), list.remove(6));
            assertEquals(PList.makeList(10, 20, 30, 40, 50, 60, 70, 90, 100), list.remove(7));
            assertEquals(PList.makeList(10, 20, 30, 40, 50, 60, 70, 80, 100), list.remove(8));
            assertEquals(PList.makeList(10, 20, 30, 40, 50, 60, 70, 80, 90), list.remove(9));
            assertEquals(new EmptyPList<>(), list.subList(0, 0));
            assertEquals(new EmptyPList<>(), list.subList(1, 1));
            assertEquals(new EmptyPList<>(), list.subList(2, 2));
            assertEquals(new EmptyPList<>(), list.subList(3, 3));
            assertEquals(new EmptyPList<>(), list.subList(4, 4));
            assertEquals(new EmptyPList<>(), list.subList(5, 5));
            assertEquals(new EmptyPList<>(), list.subList(6, 6));
            assertEquals(new EmptyPList<>(), list.subList(7, 7));
            assertEquals(new EmptyPList<>(), list.subList(8, 8));
            assertEquals(new EmptyPList<>(), list.subList(9, 9));
            assertEquals(PList.makeList(10), list.subList(0, 1));
            assertEquals(PList.makeList(10, 20), list.subList(0, 2));
            assertEquals(PList.makeList(10, 20, 30), list.subList(0, 3));
            assertEquals(PList.makeList(20), list.subList(1, 2));
            assertEquals(PList.makeList(20, 30), list.subList(1, 3));
            assertEquals(PList.makeList(20, 30, 40), list.subList(1, 4));
            assertEquals(PList.makeList(30), list.subList(2, 3));
            assertEquals(PList.makeList(30, 40), list.subList(2, 4));
            assertEquals(PList.makeList(30, 40, 50), list.subList(2, 5));
            assertEquals(PList.makeList(40), list.subList(3, 4));
            assertEquals(PList.makeList(40, 50), list.subList(3, 5));
            assertEquals(PList.makeList(40, 50, 60), list.subList(3, 6));
            assertEquals(PList.makeList(50), list.subList(4, 5));
            assertEquals(PList.makeList(60), list.subList(5, 6));
            assertEquals(PList.makeList(60, 70), list.subList(5, 7));
            assertEquals(PList.makeList(60, 70, 80), list.subList(5, 8));
            assertEquals(PList.makeList(70), list.subList(6, 7));
            assertEquals(PList.makeList(70, 80), list.subList(6, 8));
            assertEquals(PList.makeList(80), list.subList(7, 8));
            assertEquals(PList.makeList(80, 90), list.subList(7, 9));
            assertEquals(PList.makeList(90), list.subList(8, 9));
            assertEquals(PList.makeList(90, 100), list.subList(8, 10));
            assertEquals(PList.makeList(100), list.subList(9, 10));
            assertEquals(new EmptyPList<>(), list.subList(10, 10));
            assertEquals(new EmptyPList<>(), list.subList(0, 0).subList(0, 0));
            assertEquals(PList.makeList(10), list.subList(0, 1).subList(0, 1));
            assertEquals(PList.makeList(10, 20), list.subList(0, 2).subList(0, 2));
        });
    }

    @Test
    @DisplayName("add: This test is included with the assignment.")
    void iterable () {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            list = PList.makeList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
            int i = 0;
            for (int element : list) {
                assertEquals(10 * (i + 1), element);
                i++;
            }
            assertEquals(10, i);
        });
    }
}
