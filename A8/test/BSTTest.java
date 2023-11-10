import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private BST<Integer> emptyTree, leafTree, listTree, balancedTree;

    @BeforeEach
    void setUp() {
        emptyTree = new EmptyBST<>();
        leafTree = BST.mkLeaf(1);
        listTree = BST.fromCollection(IntStream.range(1, 16).boxed().toList());
        balancedTree = BST.fromCollection(
                List.of(8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15));
    }

    @Test
    void basic () {
        assertEquals(0, emptyTree.height());
        assertEquals(1, leafTree.height());
        assertEquals(15, listTree.height());
        assertEquals(4, balancedTree.height());

        assertEquals(0, emptyTree.size());
        assertEquals(1, leafTree.size());
        assertEquals(15, listTree.size());
        assertEquals(15, balancedTree.size());

        assertTrue(emptyTree.isBalanced());
        assertTrue(leafTree.isBalanced());
        assertFalse(listTree.isBalanced());
        assertTrue(balancedTree.isBalanced());
    }

    @Test
    void find () throws EmptyTreeExc {
        assertFalse(emptyTree.find(100));
        assertTrue(leafTree.find(1));
        assertFalse(leafTree.find(100));
        assertTrue(listTree.find(6));
        assertTrue(listTree.find(12));
        assertFalse(listTree.find(16));
        assertFalse(listTree.find(100));
        assertTrue(balancedTree.find(6));
        assertTrue(balancedTree.find(12));
        assertFalse(balancedTree.find(16));
        assertFalse(balancedTree.find(100));

        assertThrows(EmptyTreeExc.class, () -> emptyTree.findMin());
        assertThrows(EmptyTreeExc.class, () -> emptyTree.findMax());
        assertEquals(1, leafTree.findMin());
        assertEquals(1, leafTree.findMax());
        assertEquals(1, listTree.findMin());
        assertEquals(15, listTree.findMax());
        assertEquals(1, balancedTree.findMin());
        assertEquals(15, balancedTree.findMax());
    }

    @Test
    void insert () throws EmptyTreeExc {
        assertEquals(1, emptyTree.insert(1).size());
        assertEquals(2, leafTree.insert(2).size());
        assertEquals(16, listTree.insert(16).size());
        assertEquals(16, balancedTree.insert(16).size());

        assertEquals(1, emptyTree.insertAndBalance(1).size());
        assertEquals(2, leafTree.insertAndBalance(2).size());
        assertEquals(16, listTree.insertAndBalance(16).size());
        assertEquals(16, balancedTree.insertAndBalance(16).size());

        assertTrue(emptyTree.insert(1).isBalanced());
        assertTrue(leafTree.insert(2).isBalanced());
        assertFalse(listTree.insert(16).isBalanced());
        assertTrue(balancedTree.insert(16).isBalanced());

        assertTrue(emptyTree.insertAndBalance(1).isBalanced());
        assertTrue(leafTree.insertAndBalance(2).isBalanced());
        assertTrue(listTree.insertAndBalance(16).isBalanced());
        assertTrue(balancedTree.insertAndBalance(16).isBalanced());

        assertEquals("", balancedTree.path(8));
        assertEquals("0", balancedTree.path(4));
        assertEquals("1", balancedTree.path(12));
        assertEquals("00", balancedTree.path(2));
        assertEquals("01", balancedTree.path(6));
        assertEquals("10", balancedTree.path(10));
        assertEquals("11", balancedTree.path(14));
        assertEquals("000", balancedTree.path(1));
        assertEquals("001", balancedTree.path(3));
        assertEquals("010", balancedTree.path(5));
        assertEquals("011", balancedTree.path(7));
        assertEquals("100", balancedTree.path(9));
        assertEquals("101", balancedTree.path(11));
        assertEquals("110", balancedTree.path(13));
        assertEquals("111", balancedTree.path(15));

        assertEquals("", listTree.insert(16).path(1));
        assertEquals("1", listTree.insert(16).path(2));
        assertEquals("11", listTree.insert(16).path(3));
        assertEquals("111", listTree.insert(16).path(4));
        assertEquals("1111", listTree.insert(16).path(5));
        assertEquals("11111", listTree.insert(16).path(6));
        assertEquals("111111", listTree.insert(16).path(7));
        assertEquals("1111111", listTree.insert(16).path(8));
        assertEquals("11111111", listTree.insert(16).path(9));
        assertEquals("111111111", listTree.insert(16).path(10));
        assertEquals("1111111111", listTree.insert(16).path(11));
        assertEquals("11111111111", listTree.insert(16).path(12));
        assertEquals("111111111111", listTree.insert(16).path(13));
        assertEquals("1111111111111", listTree.insert(16).path(14));
        assertEquals("11111111111111", listTree.insert(16).path(15));
        assertEquals("111111111111111", listTree.insert(16).path(16));

        listTree = listTree.insertAndBalance(16);
        assertEquals("", listTree.path(9));
        assertEquals("0", listTree.path(5));
        assertEquals("1", listTree.path(13));
        assertEquals("00", listTree.path(3));
        assertEquals("01", listTree.path(7));
        assertEquals("10", listTree.path(11));
        assertEquals("11", listTree.path(15));
        assertEquals("000", listTree.path(2));
        assertEquals("001", listTree.path(4));
        assertEquals("010", listTree.path(6));
        assertEquals("011", listTree.path(8));
        assertEquals("100", listTree.path(10));
        assertEquals("101", listTree.path(12));
        assertEquals("110", listTree.path(14));
        assertEquals("111", listTree.path(16));
        assertEquals("0000", listTree.path(1));

    }

    @Test
    void delete () throws EmptyTreeExc {
        assertThrows(EmptyTreeExc.class, () -> emptyTree.delete(10));
        assertEquals(0, leafTree.delete(1).size());
        assertEquals(14, listTree.delete(1).size());
        assertEquals(14, balancedTree.delete(1).size());

        assertThrows(EmptyTreeExc.class, () -> emptyTree.deleteMin());
        assertThrows(EmptyTreeExc.class, () -> emptyTree.deleteMax());
        assertEquals(0, leafTree.deleteMin().size());
        assertEquals(14, listTree.deleteMin().size());
        assertEquals(14, balancedTree.deleteMin().size());
        assertEquals(14, balancedTree.deleteMax().size());

        assertThrows(EmptyTreeExc.class, () -> emptyTree.deleteAndBalance(10));
        assertEquals(0, leafTree.deleteAndBalance(1).size());
        assertEquals(14, listTree.deleteAndBalance(1).size());
        assertEquals(14, balancedTree.deleteAndBalance(1).size());

        assertTrue(leafTree.delete(1).isBalanced());
        assertFalse(listTree.delete(1).isBalanced());
        assertTrue(balancedTree.delete(1).isBalanced());

        assertTrue(leafTree.deleteAndBalance(1).isBalanced());
        assertTrue(listTree.deleteAndBalance(1).isBalanced());
        assertTrue(balancedTree.deleteAndBalance(1).isBalanced());

        assertEquals("", balancedTree.delete(1).path(8));
        assertEquals("0", balancedTree.delete(1).path(4));
        assertEquals("1", balancedTree.delete(1).path(12));
        assertEquals("00", balancedTree.delete(1).path(2));
        assertEquals("01", balancedTree.delete(1).path(6));
        assertEquals("10", balancedTree.delete(1).path(10));
        assertEquals("11", balancedTree.delete(1).path(14));
        assertEquals("001", balancedTree.delete(1).path(3));
        assertEquals("010", balancedTree.delete(1).path(5));
        assertEquals("011", balancedTree.delete(1).path(7));
        assertEquals("100", balancedTree.delete(1).path(9));
        assertEquals("101", balancedTree.delete(1).path(11));
        assertEquals("110", balancedTree.delete(1).path(13));
        assertEquals("111", balancedTree.delete(1).path(15));

        assertEquals("", listTree.delete(1).path(2));
        assertEquals("1", listTree.delete(1).path(3));
        assertEquals("11", listTree.delete(1).path(4));
        assertEquals("111", listTree.delete(1).path(5));
        assertEquals("1111", listTree.delete(1).path(6));
        assertEquals("11111", listTree.delete(1).path(7));
        assertEquals("111111", listTree.delete(1).path(8));
        assertEquals("1111111", listTree.delete(1).path(9));
        assertEquals("11111111", listTree.delete(1).path(10));
        assertEquals("111111111", listTree.delete(1).path(11));
        assertEquals("1111111111", listTree.delete(1).path(12));
        assertEquals("11111111111", listTree.delete(1).path(13));
        assertEquals("111111111111", listTree.delete(1).path(14));
        assertEquals("1111111111111", listTree.delete(1).path(15));

        listTree = listTree.deleteAndBalance(1);

        assertEquals("", listTree.path(9));
        assertEquals("0", listTree.path(5));
        assertEquals("1", listTree.path(13));
        assertEquals("00", listTree.path(3));
        assertEquals("01", listTree.path(7));
        assertEquals("10", listTree.path(11));
        assertEquals("11", listTree.path(15));
        assertEquals("000", listTree.path(2));
        assertEquals("001", listTree.path(4));
        assertEquals("010", listTree.path(6));
        assertEquals("011", listTree.path(8));
        assertEquals("100", listTree.path(10));
        assertEquals("101", listTree.path(12));
        assertEquals("110", listTree.path(14));

    }

    @Test
    void sort () {
        assertEquals(List.of(1), TreeTraversals.sort(List.of(1)));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                TreeTraversals.sort(List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                TreeTraversals.sort(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                TreeTraversals.sort(List.of(1, 10, 2, 9, 3, 8, 4, 7, 5, 6)));
    }
    @Test
    void deleteMinAndFindMin () throws EmptyTreeExc {
        assertThrows(EmptyTreeExc.class, () -> emptyTree.deleteMin());
        assertEquals(0, leafTree.deleteMin().size());
        assertEquals(1, listTree.findMin());
        listTree = listTree.deleteMin();
        assertEquals(14, listTree.size());
        assertEquals(2, listTree.findMin());
        assertEquals(14, balancedTree.deleteMin().size());
    }
    @Test
    void deleteMaxAndFindMax () throws EmptyTreeExc {
        assertThrows(EmptyTreeExc.class, () -> emptyTree.deleteMax());
        assertEquals(0, leafTree.deleteMax().size());
        assertEquals(15, listTree.findMax());
        listTree = listTree.deleteMax();
        assertEquals(14, listTree.size());
        assertEquals(14, listTree.findMax());
        assertEquals(14, balancedTree.deleteMax().size());
    }
    @Test
    void delete2 () throws EmptyTreeExc {
        assertThrows(EmptyTreeExc.class, () -> emptyTree.delete(10));
        assertEquals(0, leafTree.delete(1).size());
        assertEquals(14, listTree.delete(2).size());
        assertEquals(14, balancedTree.delete(2).size());
    }
}