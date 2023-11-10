import java.util.*;
import java.util.function.Function;

public class TreeTraversals {

    static <E extends Comparable<E>> List<E> preOrderList(BST<E> tree) {
       try {
            List<E> result = new ArrayList<>();
            List<E> d = List.of(tree.data());
            List<E> l = preOrderList(tree.left());
            List<E> r = preOrderList(tree.right());
            result.addAll(d);
            result.addAll(l);
            result.addAll(r);
            return result;
        }
        catch (EmptyTreeExc e) {
            return new ArrayList<>();
        }
    }

    static <E extends Comparable<E>> List<E> inOrderList(BST<E> tree) {
        try {
            List<E> result = new ArrayList<>();
            List<E> d = List.of(tree.data());
            List<E> l = inOrderList(tree.left());
            List<E> r = inOrderList(tree.right());
            result.addAll(l);
            result.addAll(d);
            result.addAll(r);
            return result;
        }
        catch (EmptyTreeExc e) {
            return new ArrayList<>();
        }
    }

    static <E extends Comparable<E>> List<E> postOrderList(BST<E> tree) {
        try {
            List<E> result = new ArrayList<>();
            List<E> d = List.of(tree.data());
            List<E> l = postOrderList(tree.left());
            List<E> r = postOrderList(tree.right());
            result.addAll(l);
            result.addAll(r);
            result.addAll(d);
            return result;
        }
        catch (EmptyTreeExc e) {
            return new ArrayList<>();
        }
    }

    static <E extends Comparable<E>> List<E> breadthFirstList (BST<E> tree) {
        List<E> result = new ArrayList<>();
        Queue<BST<E>> toVisit = new LinkedList<>();
        toVisit.add(tree);
        while (! toVisit.isEmpty()) {
            if (toVisit.poll() instanceof NodeBST<E> currentNode) {
                result.add(currentNode.data());
                toVisit.offer(currentNode.left());
                toVisit.offer(currentNode.right());
            }
        }
        return result;
    }

    static <E extends Comparable<E>, F extends Comparable<F>> BST<F> map (Function<E,F> f, BST<E> tree) {
        try {
            return new NodeBST<>(f.apply(tree.data()), map(f, tree.left()), map(f, tree.right()));
        }
        catch (EmptyTreeExc e) {
            return new EmptyBST<>();
        }
    }

    static int sum (BST<Integer> tree) {
        try {
            return tree.data() + sum(tree.left()) + sum(tree.right());
        }
        catch (EmptyTreeExc e) {
            return 0;
        }
    }

    static int mul (BST<Integer> tree) {
        try {
            return tree.data() * mul(tree.left()) * mul(tree.right());
        }
        catch (EmptyTreeExc e) {
            return 1;
        }
    }
    static <E extends Comparable<E>> BST<E> mirror (BST<E> tree) {
        try {
            return new NodeBST<>(tree.data(), mirror(tree.right()), mirror(tree.left()));
        }
        catch (EmptyTreeExc e) {
            return new EmptyBST<>();
        }
    }

    /**
     * Create a tree from the given list of elements and traverse the tree inorder.
     * See https://en.wikipedia.org/wiki/Tree_sort for more information.
     */
    static <E extends Comparable<E>> List<E> sort (List<E> elems) {
        List<E> sortedList = new ArrayList<>(elems);
        int n = sortedList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedList.get(j).compareTo(sortedList.get(j + 1)) > 0) {
                    // swap sortedList[j] and sortedList[j + 1]
                    E temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }

        return sortedList;
    }


    /**
     * This method is just for convenience. It traverses the tree inorder and calls
     * the other balance method.
     */
    static <E extends Comparable<E>> BST<E> balance (BST<E> tree) {
        List<E> sortedList = inOrderList(tree);
        return balance(sortedList);
    }

    /**
     * The given list is sorted. Find the median and use it as the root of the tree.
     * Recursively build the left and right subtrees from the elements left and right of the median.
     */
    static <E extends Comparable<E>> BST<E> balance (List<E> sorted) {
        if (sorted.isEmpty()) {
            return new EmptyBST<>();
        }

        int midIndex = sorted.size() / 2;
        E median = sorted.get(midIndex);

        List<E> leftElements = sorted.subList(0, midIndex);
        List<E> rightElements = sorted.subList(midIndex + 1, sorted.size());

        return new NodeBST<>(median, balance(leftElements), balance(rightElements));
    }
}
