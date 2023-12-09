import graph.noweight.Edge;
import graph.noweight.Path;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.AllShortestPaths;
import graph.withweight.traversal.MaximumFlow;
import graph.withweight.traversal.Weight;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TIER IV TESTS
 * <p>
 * MaximumFlow
 */

public class Tier4Tests {
    @Test
    public void maxFlow() {
        WeightedDirectedGraph g = ExampleGraphs.g7();
        MaximumFlow mf = new MaximumFlow(g);
        int flow = mf.run("S", "T");
        assertEquals(15, flow);
        HashMap<Edge, Weight> result = mf.getFlow();
        Edge sa, sc, ab, ac, bt, cd, db, dt;
        sa = new Edge("S", "A");
        sc = new Edge("S", "C");
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bt = new Edge("B", "T");
        cd = new Edge("C", "D");
        db = new Edge("D", "B");
        dt = new Edge("D", "T");
        assertEquals(8, result.get(sc).value());
        assertEquals(7, result.get(sa).value());
        assertEquals(5, result.get(ab).value());
        assertEquals(2, result.get(ac).value());
        assertEquals(5, result.get(bt).value());
        assertEquals(10, result.get(cd).value());
        assertEquals(0, result.get(db).value());
        assertEquals(10, result.get(dt).value());
    }
    @Test
    public void augmentFlow() {
        WeightedDirectedGraph g = ExampleGraphs.g8();
        MaximumFlow mf = new MaximumFlow(g);
        HashMap<Edge, Weight> flow = mf.getFlow();
        AllShortestPaths sp = new AllShortestPaths(g, "A");
        sp.iterativeTraversal();
        Path path = sp.getPath("E");
        mf.augmentFlow(flow, path, new Weight(5));
        Edge ab, bc, cd, de;
        ab = new Edge("A", "B");
        bc = new Edge("B", "C");
        cd = new Edge("C", "D");
        de = new Edge("D", "E");
        assertEquals(5, flow.get(ab).value());
        assertEquals(5, flow.get(bc).value());
        assertEquals(5, flow.get(cd).value());
        assertEquals(5, flow.get(de).value());
    }
    @Test
    public void updateResidual() {
        WeightedDirectedGraph g = ExampleGraphs.g8();
        WeightedDirectedGraph residual = g.copy();
        MaximumFlow mf = new MaximumFlow(g);
        AllShortestPaths sp = new AllShortestPaths(g, "A");
        sp.iterativeTraversal();
        Path path = sp.getPath("E");
        mf.updateResidual(residual, path, new Weight(2));
        Edge ab, bc, cd, de, ba, cb, dc, ed;
        ab = new Edge("A", "B");
        bc = new Edge("B", "C");
        cd = new Edge("C", "D");
        de = new Edge("D", "E");
        ba = new Edge("B", "A");
        cb = new Edge("C", "B");
        dc = new Edge("D", "C");
        ed = new Edge("E", "D");
        assertEquals(2, residual.getWeights().get(ab).value());
        assertEquals(0, residual.getWeights().get(bc).value());
        assertEquals(0, residual.getWeights().get(cd).value());
        assertEquals(2, residual.getWeights().get(de).value());
        assertEquals(2, residual.getWeights().get(ba).value());
        assertEquals(2, residual.getWeights().get(cb).value());
        assertEquals(2, residual.getWeights().get(dc).value());
        assertEquals(2, residual.getWeights().get(ed).value());
    }
    @Test
    public void maxFlow2() {
        WeightedDirectedGraph g = ExampleGraphs.g6();
        MaximumFlow mf = new MaximumFlow(g);
        int flow = mf.run("A", "G");
        assertEquals(13, flow);
        HashMap<Edge, Weight> result = mf.getFlow();
        Edge ab, ac, cd, bd, dg, be, eg, cb;
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        cd = new Edge("C", "D");
        bd = new Edge("B", "D");
        dg = new Edge("D", "G");
        be = new Edge("B", "E");
        eg = new Edge("E", "G");
        cb = new Edge("C", "B");
        assertEquals(5, result.get(ac).value());
        assertEquals(8, result.get(ab).value());
        assertEquals(3, result.get(cd).value());
        assertEquals(2, result.get(bd).value());
        assertEquals(9, result.get(dg).value());
        assertEquals(8, result.get(be).value());
        assertEquals(4, result.get(eg).value());
    }
    @Test
    public void maxFlow3() {
        WeightedDirectedGraph g = ExampleGraphs.g8();
        MaximumFlow mf = new MaximumFlow(g);
        int flow = mf.run("A", "E");
        assertEquals(2, flow);
        HashMap<Edge, Weight> result = mf.getFlow();
        Edge ab, bc, cd, de;
        ab = new Edge("A", "B");
        bc = new Edge("B", "C");
        cd = new Edge("C", "D");
        de = new Edge("D", "E");
        assertEquals(2, result.get(ab).value());
        assertEquals(2, result.get(bc).value());
        assertEquals(2, result.get(cd).value());
        assertEquals(2, result.get(de).value());
    }
}
