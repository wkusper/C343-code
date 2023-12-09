package graph.noweight.traversal.iter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TIER I TODO
 * <p>
 * Implement a class that extends NodeCollection and uses a Queue to store the
 * nodes. The constructor should take a String and add it to the queue.
 * The methods add and get should enqueue and dequeue respectively.
 *
 */
public class QueueCollection extends NodeCollection {
    private final Queue<String> queue;
    public QueueCollection(String start) {
        this.queue = new LinkedList<>();
        queue.add(start);
    }
    public boolean isEmpty() {
        if (queue.isEmpty()) {
            return true;
        }
        return false;
    }
    public String get() {
        String s = queue.remove();
        return s;
    }
    public void add(String node) {
        queue.add(node);
    }
}
