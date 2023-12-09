package graph.noweight.traversal.iter;

import java.util.Stack;

/**
 * TIER I TODO
 * <p>
 * Implement a class that extends NodeCollection and uses a Stack to store the
 * nodes. The constructor should take a String and add it to the stack.
 * The methods add and get should push and pop respectively.
 *
 */
public class StackCollection extends NodeCollection {
    private final Stack<String> stack;
    public StackCollection(String start) {
        stack = new Stack<>();
        stack.push(start);
    }
    public boolean isEmpty() {
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
    public String get() {
        String s = stack.pop();
        return s;
    }
    public void add(String node) {
        stack.push(node);
    }
}
