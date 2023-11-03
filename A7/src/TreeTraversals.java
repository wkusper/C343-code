import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class TreeTraversals {

    static <E extends Comparable<E>> List<E> preOrderList(BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        try {
            result.add(tree.getData());
            result.addAll(preOrderList(tree.getLeftBT()));
            result.addAll(preOrderList(tree.getRightBT()));
        } catch (EmptyTreeE ignored) {}
        return result;
    }

    static <E extends Comparable<E>> List<E> inOrderList(BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        try {
            result.addAll(inOrderList(tree.getLeftBT()));
            result.add(tree.getData());
            result.addAll(inOrderList(tree.getRightBT()));
        } catch (EmptyTreeE ignored) {}
        return result;
    }

    static <E extends Comparable<E>> List<E> postOrderList(BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        try {
            result.addAll(postOrderList(tree.getLeftBT()));
            result.addAll(postOrderList(tree.getRightBT()));
            result.add(tree.getData());
        } catch (EmptyTreeE ignored) {}
        return result;
    }

    static <E extends Comparable<E>> List<E> breadthFirstList (BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            BinaryTree<E> current = queue.poll();
            try {
                result.add(current.getData());
                queue.add(current.getLeftBT());
                queue.add(current.getRightBT());
            } catch (EmptyTreeE ignored) {}
        }
        return result;
    }

    static <E extends Comparable<E>, F extends Comparable<F>> BinaryTree<F>
    map (Function<E,F> f, BinaryTree<E> tree) {
        try {
            return new NodeBT<>(
                    f.apply(tree.getData()),
                    map(f, tree.getLeftBT()),
                    map(f, tree.getRightBT())
            );
        } catch (EmptyTreeE e) {
            return new EmptyBT<>();
        }
    }

    static int sum (BinaryTree<Integer> tree) {
        try {
            return tree.getData() + sum(tree.getLeftBT()) + sum(tree.getRightBT());
        } catch (EmptyTreeE e) {
            return 0;
        }
    }
    static int mul (BinaryTree<Integer> tree) {
        try {
            return tree.getData() * mul(tree.getLeftBT()) * mul(tree.getRightBT());
        } catch (EmptyTreeE e) {
            return 1;
        }
        }
    static <E extends Comparable<E>> BinaryTree<E> mirror (BinaryTree<E> tree) {
        try {
            return new NodeBT<>(tree.getData(), mirror(tree.getRightBT()), mirror(tree.getLeftBT()));
        } catch (EmptyTreeE e) {
            return new EmptyBT<>();
        }
    }
}
