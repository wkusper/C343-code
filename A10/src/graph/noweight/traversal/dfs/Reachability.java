package graph.noweight.traversal.dfs;

import graph.noweight.DirectedGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * TIER V TODO
 * <p>
 * This class extends RecursiveGraphTraversal to calculate all the nodes that
 * can reach a given node. The result is represented as a HashMap that maps each
 * node to the set of nodes that can reach it.
 * <p>
 * To implement this idea, we perform the same action every time we enter, visit,
 * or exit a node. This action does the following steps:
 * <ul>
 *     <li> Get the set of nodes that can reach the current node. Call it S.
 *     <li> For each neighbor, add the current node and all of the nodes in S
 *     to the set of nodes that can reach the neighbor.
 * </ul>
 */
public class Reachability extends RecursiveGraphTraversal {
    private final HashMap<String, Set<String>> table;
    public Reachability (DirectedGraph graph) {
        super(graph);
        this.table = new HashMap<>();
        for (String node : graph.getNodes()) {
            table.put(node, new HashSet<>());
        }
    }

    public HashMap<String, Set<String>> getTable() {
        return table;
    }

    public void enterAction (String node) {
        for (String neighbor : graph.neighbors(node)) {
            table.get(neighbor).add(node);
            table.get(neighbor).addAll(table.get(node));
        }
    }
    public void touchAction (String node) {
        for (String neighbor : graph.neighbors(node)) {
            table.get(neighbor).add(node);
            table.get(neighbor).addAll(table.get(node));
        }
    }
    public void exitAction (String node) {
        for (String neighbor : graph.neighbors(node)) {
            table.get(neighbor).add(node);
            table.get(neighbor).addAll(table.get(node));
        }
    }
}
