import graph.noweight.DirectedGraph;
import graph.noweight.traversal.dfs.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Tier5Tests {

    @Test
    void g1 () {
        DirectedGraph g = ExampleGraphs.g1();

        DFS dfs = new DFS(g);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g);
        cd.traverseFromSource("A");
        assertTrue(cd.hasCycle());

        Reachability r = new Reachability(g);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A"));
        assertEquals(Set.of("A","B","D","E"), table.get("B"));
        assertEquals(Set.of("A","B","D","E"), table.get("C"));
        assertEquals(Set.of("A","B","D","E"), table.get("D"));
        assertEquals(Set.of("A","B","D","E"), table.get("E"));
    }

    @Test
    void g2 () {
        DirectedGraph g = ExampleGraphs.g2();

        DFS dfs = new DFS(g);
        dfs.traverseFromSource("A1");
        assertEquals(List.of("A1","A2","A3","A4","A5","A6","A7","A8","A12","A9","A10","A11"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g);
        cd.traverseFromSource("A1");
        assertFalse(cd.hasCycle());

        TopologicalSort ts = new TopologicalSort(g);
        ts.traverseFromSource("A1");
        assertEquals(List.of("A1","A8","A9","A11","A10","A12","A7","A2","A6","A3","A5","A4"), ts.getTraversal());

        Reachability r = new Reachability(g);
        r.traverseFromSource("A1");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A1"));
        assertEquals(Set.of("A1"), table.get("A2"));
        assertEquals(Set.of("A1","A2"), table.get("A3"));
        assertEquals(Set.of("A1","A2","A3"), table.get("A4"));
        assertEquals(Set.of("A1","A2", "A3"), table.get("A5"));
        assertEquals(Set.of("A1","A2"), table.get("A6"));
        assertEquals(Set.of("A1"), table.get("A7"));
        assertEquals(Set.of("A1"), table.get("A8"));
        assertEquals(Set.of("A1","A8"), table.get("A9"));
        assertEquals(Set.of("A1","A8","A9"), table.get("A10"));
        assertEquals(Set.of("A1","A8","A9"), table.get("A11"));
        assertEquals(Set.of("A1","A8"), table.get("A12"));
    }

    @Test
    void g3 () {
        DirectedGraph g = ExampleGraphs.g3();

        StronglyConnectedComponents scc = new StronglyConnectedComponents(g);
        HashMap<String,List<String>> components = scc.computeSCC();
        assertEquals(List.of("A"), components.get("A"));
        assertEquals(List.of("B"), components.get("B"));
        assertEquals(List.of("C", "J", "F", "D"), components.get("C"));
        assertEquals(List.of(), components.get("D"));
        assertEquals(List.of("E"), components.get("E"));
        assertEquals(List.of(), components.get("F"));
        assertEquals(List.of(), components.get("G"));
        assertEquals(List.of("H", "I", "G"), components.get("H"));
        assertEquals(List.of(), components.get("I"));
        assertEquals(List.of(), components.get("J"));
    }
    @Test
    void g4 () {
        DirectedGraph g4 = ExampleGraphs.g4();

        DFS dfs = new DFS(g4);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g4);
        cd.traverseFromSource("A");
        assertTrue(cd.hasCycle());

        TopologicalSort ts = new TopologicalSort(g4);
        ts.traverseFromSource("A");
        assertEquals(List.of("A","B","D","E","C"), ts.getTraversal());

        Reachability r = new Reachability(g4);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A"));
        assertEquals(Set.of("A","B","D","E"), table.get("B"));
        assertEquals(Set.of("A","B","D","E"), table.get("C"));
        assertEquals(Set.of("A","B","D","E"), table.get("D"));
        assertEquals(Set.of("A","B","D","E"), table.get("E"));

        StronglyConnectedComponents scc = new StronglyConnectedComponents(g4);
        HashMap<String,List<String>> components = scc.computeSCC();
        assertEquals(List.of("A"), components.get("A"));
        assertEquals(List.of("B","E","D"), components.get("B"));
        assertEquals(List.of("C"), components.get("C"));
        assertEquals(List.of(), components.get("D"));
        assertEquals(List.of(), components.get("E"));
    }
    @Test
    void g5 () {
        DirectedGraph g5 = ExampleGraphs.g5();

        DFS dfs = new DFS(g5);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","E","D","F"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g5);
        cd.traverseFromSource("A");
        assertFalse(cd.hasCycle());

        TopologicalSort ts = new TopologicalSort(g5);
        ts.traverseFromSource("A");
        assertEquals(List.of("A","B","C","E","D","F"), ts.getTraversal());

        Reachability r = new Reachability(g5);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A"));
        assertEquals(Set.of("A"), table.get("B"));
        assertEquals(Set.of("A","B"), table.get("C"));
        assertEquals(Set.of("A","B","C"), table.get("E"));
        assertEquals(Set.of("A","B","C","E"), table.get("D"));
        assertEquals(Set.of("A","B","C","D","E"), table.get("F"));

        StronglyConnectedComponents scc_g5 = new StronglyConnectedComponents(g5);
        HashMap<String,List<String>> components_g5 = scc_g5.computeSCC();
        assertEquals(List.of("A"), components_g5.get("A"));
        assertEquals(List.of("B"), components_g5.get("B"));
        assertEquals(List.of("C"), components_g5.get("C"));
        assertEquals(List.of("D"), components_g5.get("D"));
        assertEquals(List.of("E"), components_g5.get("E"));
        assertEquals(List.of("F"), components_g5.get("F"));

    }
    @Test
    void g6 () {
        DirectedGraph g6 = ExampleGraphs.g6();

        DFS dfs = new DFS(g6);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E","G","F"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g6);
        cd.traverseFromSource("A");
        assertTrue(cd.hasCycle());

        TopologicalSort ts = new TopologicalSort(g6);
        ts.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E","G","F"), ts.getTraversal());

        Reachability r = new Reachability(g6);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of("A","B","C","D","E","G","F"), table.get("A"));
        assertEquals(Set.of("A","B","C","D","E","F","G"), table.get("B"));
        assertEquals(Set.of("A","B","C","D","E","F","G"), table.get("C"));
        assertEquals(Set.of("A","B","C","D","E","F","G"), table.get("E"));
        assertEquals(Set.of("A","B","C","D","E","F","G"), table.get("D"));
        assertEquals(Set.of("A","B","C","D","E","F","G"), table.get("F"));

        StronglyConnectedComponents scc_g6 = new StronglyConnectedComponents(g6);
        HashMap<String,List<String>> components_g6 = scc_g6.computeSCC();
        assertEquals(List.of("A","B","C","D","E","G","F"), components_g6.get("A"));
        assertEquals(List.of(), components_g6.get("B"));
        assertEquals(List.of(), components_g6.get("C"));
        assertEquals(List.of(), components_g6.get("D"));
        assertEquals(List.of(), components_g6.get("E"));
        assertEquals(List.of(), components_g6.get("F"));
        assertEquals(List.of(), components_g6.get("G"));
    }
    @Test
    void g7 () {
        DirectedGraph g7 = ExampleGraphs.g7();

        DFS dfs = new DFS(g7);
        dfs.traverseFromSource("S");
        assertEquals(List.of("S","A","B","T","C","D"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g7);
        cd.traverseFromSource("S");
        assertFalse(cd.hasCycle());

        TopologicalSort ts = new TopologicalSort(g7);
        ts.traverseFromSource("S");
        assertEquals(List.of("S","A","C","D","B","T"), ts.getTraversal());

        Reachability r = new Reachability(g7);
        r.traverseFromSource("S");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of("S"), table.get("A"));
        assertEquals(Set.of("A","S","C","D"), table.get("B"));
        assertEquals(Set.of("A","S"), table.get("C"));
        assertEquals(Set.of("A","C","S"), table.get("D"));
        assertEquals(Set.of(), table.get("S")); //start
        assertEquals(Set.of("A","B","S","C","D"), table.get("T"));

        StronglyConnectedComponents scc_g7 = new StronglyConnectedComponents(g7);
        HashMap<String,List<String>> components_g7 = scc_g7.computeSCC();
        assertEquals(List.of("A"), components_g7.get("A"));
        assertEquals(List.of("B"), components_g7.get("B"));
        assertEquals(List.of("C"), components_g7.get("C"));
        assertEquals(List.of("D"), components_g7.get("D"));
        assertEquals(List.of("S"),components_g7.get("S"));
        assertEquals(List.of("T"), components_g7.get("T"));
    }
}
