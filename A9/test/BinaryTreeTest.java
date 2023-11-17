import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    void insert () throws NoParentE {

        NodeBT<Integer> leaf1, leaf2, leaf3;
        leaf1 = BinaryTree.mkLeaf(1);
        leaf2 = BinaryTree.mkLeaf(2);
        leaf3 = BinaryTree.mkLeaf(3);

        BinaryTree<Integer> tree = BinaryTree.mkLeaf(5);

        tree = tree.insertBalanced(leaf1);
        tree = tree.insertBalanced(leaf2);
        tree = tree.insertBalanced(leaf3);
        TreePrinter.print(tree);

        NodeBT<Integer> leaf1Parent = leaf1.getParent().orElseThrow(NoParentE::new);
        NodeBT<Integer> leaf2Parent = leaf2.getParent().orElseThrow(NoParentE::new);
        NodeBT<Integer> leaf3Parent = leaf3.getParent().orElseThrow(NoParentE::new);

        assertEquals(leaf1Parent.getData(), 5);
        assertEquals(leaf2Parent.getData(), 5);
        assertEquals(leaf3Parent.getData(), 1);
    }

    @Test
    void getLast () throws EmptyTreeE {
        BinaryTree<Integer> tree = new EmptyBT<>();
        for (int i=0; i<15; i++) {
            tree = tree.insertBalanced(BinaryTree.mkLeaf(i));
            assertEquals (i,tree.getRightMost());
        }
    }

    @Test
    void insertHeap () throws EmptyTreeE {
        Heap heap = new Heap();
        int[] elems = { 5, 12, 1, 11, 2, 3, 15, 10, 9, 14, 8, 4, 6, 13, 7 };

        for (int elem : elems) { heap.insert(elem); }
        TreePrinter.print(heap);

        assertEquals(elems.length, heap.size());
        assertEquals(4, heap.height());
        assertEquals(1, heap.getMin());
        List<Integer> actual = TreeTraversals.breadthFirstList(heap.getTree());
        List<Integer> expected = List.of(1, 3, 2, 4, 11, 5, 7, 12, 10, 14, 13, 9, 6, 15, 8);
        assertEquals(expected, actual);

    }

    @Test
    void deleteRoot () throws EmptyTreeE {
        BinaryTree<Integer> tree = new EmptyBT<>();
        for (int i=1; i<16; i++) {
            tree = tree.insertBalanced(BinaryTree.mkLeaf(i));
        }

        TreePrinter.print(tree);

        for (int i=1; i<16; i++) {
            tree = tree.deleteRoot();
            assertTrue(tree.isBalanced());
        }
    }

    @Test
    void deleteMin () throws EmptyTreeE {
        Heap heap = new Heap();
        for (int i=1; i<16; i++) { heap.insert(i); }

        TreePrinter.print(heap);

        for (int i=1; i<15; i++) {
            heap.deleteMin();
            TreePrinter.print(heap);
            assertTrue(heap.getTree().isBalanced());
            assertEquals(i+1, heap.getMin());
        }
    }
    @Test
    void removeRightMost () throws EmptyTreeE {
        BinaryTree<Integer> tree = new EmptyBT<>();
        for (int i=1; i<16; i++) {
            tree = tree.insertBalanced(BinaryTree.mkLeaf(i));
        }

        TreePrinter.print(tree);

        for (int i=1; i<15; i++) {
            tree = tree.removeRightMost();
            TreePrinter.print(tree);
            assertTrue(tree.isBalanced());
            System.out.println(tree.getRightMost());
        }
    }
    @Test
    void deleteRoot2 () throws EmptyTreeE {
        BinaryTree<Integer> tree = new EmptyBT<>();
        for (int i=1; i<32; i++) {
            tree = tree.insertBalanced(BinaryTree.mkLeaf(i));
        }

        TreePrinter.print(tree);

        for (int i=1; i<32; i++) {
            tree = tree.deleteRoot();
            assertTrue(tree.isBalanced());
        }
    }
    @Test
    void deleteMin2 () throws EmptyTreeE {
        Heap heap = new Heap();
        for (int i=1; i<32; i++) { heap.insert(i); }

        TreePrinter.print(heap);

        for (int i=1; i<31; i++) {
            heap.deleteMin();
            TreePrinter.print(heap);
            assertTrue(heap.getTree().isBalanced());
            assertEquals(i+1, heap.getMin());
        }
    }