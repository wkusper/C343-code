import graph.noweight.Edge;
import graph.noweight.Path;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.AllShortestPaths;
import graph.withweight.traversal.MinimumSpanningTree;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TIER III TESTS
 * <p>
 * AllShortestsPaths
 * MinimumSpanningTree
 */
public class Tier3Tests {
    @Test
    public void shortestPaths() {
        WeightedDirectedGraph g = ExampleGraphs.g5();
        AllShortestPaths sp = new AllShortestPaths(g, "A");
        sp.iterativeTraversal();
        Edge ac, ce, ed, df;
        ac = new Edge("A", "C");
        ce = new Edge("C", "E");
        ed = new Edge("E", "D");
        df = new Edge("D", "F");
        List<Edge> best = List.of(ac, ce, ed, df);
        Path computed = sp.getPath("F");
        assertEquals(best,computed.edges());
        assertEquals(20, computed.weight().value());
        assertEquals(2, computed.minFlow().value());
    }

    @Test
    public void mst() {
        WeightedDirectedGraph g = ExampleGraphs.g6();
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");
        mst.iterativeTraversal();
        HashMap<String,String> result = mst.getPreviousNodes();
        assertEquals("D", result.get("B"));
        assertEquals("A", result.get("C"));
        assertEquals("C", result.get("D"));
        assertEquals("D", result.get("E"));
        assertEquals("C", result.get("F"));
        assertEquals("E", result.get("G"));
        assertEquals(42, mst.getWeight());
    }
    @Test
    public void shortestPaths2() {
        WeightedDirectedGraph g = ExampleGraphs.g4();
        AllShortestPaths sp = new AllShortestPaths(g, "A");
        sp.iterativeTraversal();
        Edge ab, be;
        ab = new Edge("A", "B");
        be = new Edge("B", "E");
        List<Edge> best = List.of(ab, be);
        Path computed = sp.getPath("E");
        assertEquals(best,computed.edges());
        assertEquals(6, computed.weight().value());
        assertEquals(1, computed.minFlow().value());
    }
    @Test
    public void mst2() {
        WeightedDirectedGraph g = ExampleGraphs.g5();
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");
        mst.iterativeTraversal();
        HashMap<String,String> result = mst.getPreviousNodes();
        assertEquals("A", result.get("B"));
        assertEquals("A", result.get("C"));
        assertEquals("E", result.get("D"));
        assertEquals("C", result.get("E"));
        assertEquals("D", result.get("F"));
        assertEquals(24, mst.getWeight());
    }
    @Test
    public void shortestPaths3() {
        WeightedDirectedGraph g = ExampleGraphs.g7();
        AllShortestPaths sp = new AllShortestPaths(g, "S");
        sp.iterativeTraversal();
        Edge sa, ab, bt;
        sa = new Edge("S", "A");
        ab = new Edge("A", "B");
        bt = new Edge("B", "T");
        List<Edge> best = List.of(sa, ab, bt);
        Path computed = sp.getPath("T");
        assertEquals(best,computed.edges());
        assertEquals(22, computed.weight().value());
        assertEquals(5, computed.minFlow().value());
    }
    @Test
    public void shortestPaths4() {
        WeightedDirectedGraph g = ExampleGraphs.g7();
        Edge ab, sc;
        ab = new Edge("A", "B");
        sc = new Edge("S", "C");
        g.removeEdge(ab);
        g.removeEdge(sc);
        AllShortestPaths sp = new AllShortestPaths(g, "S");
        sp.iterativeTraversal();
        Edge sa, ac, cd, dt;
        sa = new Edge("S", "A");
        ac = new Edge("A", "C");
        cd = new Edge("C", "D");
        dt = new Edge("D", "T");
        List<Edge> best = List.of(sa, ac, cd, dt);
        Path computed = sp.getPath("T");
        assertEquals(best,computed.edges());
        assertEquals(32, computed.weight().value());
        assertEquals(2, computed.minFlow().value());
    }
}
