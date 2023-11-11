import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTraversals {

    static <E extends Comparable<E>> List<E> preOrderList(BinaryTree<E> tree) {
       try {
            List<E> result = new ArrayList<>();
            List<E> d = List.of(tree.getData());
            List<E> l = preOrderList(tree.getLeftBT());
            List<E> r = preOrderList(tree.getRightBT());
            result.addAll(d);
            result.addAll(l);
            result.addAll(r);
            return result;
        }
        catch (EmptyTreeE e) {
            return new ArrayList<>();
        }
    }

    static <E extends Comparable<E>> List<E> inOrderList(BinaryTree<E> tree) {
        try {
            List<E> result = new ArrayList<>();
            List<E> d = List.of(tree.getData());
            List<E> l = inOrderList(tree.getLeftBT());
            List<E> r = inOrderList(tree.getRightBT());
            result.addAll(l);
            result.addAll(d);
            result.addAll(r);
            return result;
        }
        catch (EmptyTreeE e) {
            return new ArrayList<>();
        }
    }

    static <E extends Comparable<E>> List<E> postOrderList(BinaryTree<E> tree) {
        try {
            List<E> result = new ArrayList<>();
            List<E> d = List.of(tree.getData());
            List<E> l = postOrderList(tree.getLeftBT());
            List<E> r = postOrderList(tree.getRightBT());
            result.addAll(l);
            result.addAll(r);
            result.addAll(d);
            return result;
        }
        catch (EmptyTreeE e) {
            return new ArrayList<>();
        }
    }

    static <E extends Comparable<E>> List<E> breadthFirstList (BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        Queue<BinaryTree<E>> toVisit = new LinkedList<>();
        toVisit.add(tree);
        while (! toVisit.isEmpty()) {
            if (toVisit.poll() instanceof NodeBT<E> currentNode) {
                result.add(currentNode.getData());
                toVisit.offer(currentNode.getLeftBT());
                toVisit.offer(currentNode.getRightBT());
            }
        }
        return result;
    }

}
