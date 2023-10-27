import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Random;
public class PerformanceTest {

    @Test
    public void testInsertingSortedNumbersInBST() {
        /* TODO: */
        /* 1. Create a list of 10000 sorted numbers. */
        List<Integer> sortedNumbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            sortedNumbers.add(i);
        }
        /* 2. Create an empty BST. */
        BinaryTree<Integer> bst = new EmptyBT<>();
        /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 1000; i++) {
            bst = bst.insertBST(i);
        }
        /* 4. Measure the time it takes to insert the numbers. */
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            bst = bst.insertBST(i);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BST: " + duration);
    }

    @Test
    public void testInsertingSortedNumbersInBBT() {
        List<Integer> sortedNumbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            sortedNumbers.add(i);
        }
        /* 2. Create an empty BST. */
        BinaryTree<Integer> bbt = new EmptyBT<>();
        /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 1000; i++) {
            bbt = bbt.insertBalanced(i);
        }
        /* 4. Measure the time it takes to insert the numbers. */
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            bbt = bbt.insertBalanced(i);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BBT: " + duration);
    }

    @Test
    public void testRandomNumbersInBST() {
        Random random = new Random();
List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            randomNumbers.add(random.nextInt(100000));
        }
        /* 2. Create an empty BST. */
        BinaryTree<Integer> bst = new EmptyBT<>();
        /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 1000; i++) {
            bst = bst.insertBST(i);
        }
        /* 4. Measure the time it takes to insert the numbers. */
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            bst = bst.insertBST(i);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("BST: " + duration);
    }

    @Test
    public void testRandomNumbersInBBT() {
        Random random = new Random();
    List<Integer> randomNumbers = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
        randomNumbers.add(random.nextInt(100000));
    }
    /* 2. Create an empty BST. */
    BinaryTree<Integer> bbt = new EmptyBT<>();
    /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 1000; i++) {
        bbt = bbt.insertBalanced(i);
        }
    /* 4. Measure the time it takes to insert the numbers. */
    long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
        bbt = bbt.insertBalanced(i);
        }
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
        System.out.println("BBT: " + duration);
    }
    @Test
    public void testFindingNumbersInBST() {
        Random random = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            randomNumbers.add(random.nextInt(100000));
        }
        /* 2. Create an empty BST. */
        BinaryTree<Integer> bbt = new EmptyBT<>();
        /* 3. Insert the numbers in the BST. */
        for (int i = 0; i < 1000; i++) {
            bbt = bbt.insertBalanced(i);
        }
        /* 4. Measure the time it takes to find the numbers. */
        int randomInt = randomNumbers.get(5000);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            bbt.findBST(randomInt);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Find in BST: " + duration);
    }
}