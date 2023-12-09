import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.Path;
import graph.noweight.traversal.iter.QueueCollection;
import graph.noweight.traversal.iter.StackCollection;
import graph.withweight.traversal.Weight;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tier1Tests {
    /*
     * TIER I TESTS
     *
     * QueueCollection
     * StackCollection
     * Weight
     * Path
     * DirectedGraph
     *
     */
    @Test
    public void queue () {
        QueueCollection q = new QueueCollection("A");
        q.add("B");
        q.add("C");
        q.add("D");
        assertEquals("A", q.get());
        assertEquals("B", q.get());
        assertEquals("C", q.get());
        q.add("E");
        q.add("F");
        assertEquals("D", q.get());
        assertEquals("E", q.get());
        assertEquals("F", q.get());
        assertTrue(q.isEmpty());
    }

    @Test
    public void stack () {
        StackCollection s = new StackCollection("A");
        s.add("B");
        s.add("C");
        s.add("D");
        assertEquals("D", s.get());
        assertEquals("C", s.get());
        assertEquals("B", s.get());
        s.add("E");
        s.add("F");
        assertEquals("F", s.get());
        assertEquals("E", s.get());
        assertEquals("A", s.get());
        assertTrue(s.isEmpty());
    }

    @Test
    public void weight () {
        Weight w1 = new Weight(1);
        Weight w2 = new Weight(2);
        Weight w3 = new Weight(3);
        Weight w4 = new Weight(4);
        assertEquals(1, w1.value());
        assertEquals(3, w1.add(w2).value());
        assertEquals(1, w3.subtract(w2).value());
        assertEquals(0, w1.compareTo(new Weight(1)));
        assertTrue(w1.compareTo(w2) < 0);
        assertTrue(w2.compareTo(w1) > 0);
        assertEquals(w4, new Weight(4));
        assertTrue(w4.compareTo(Weight.INFINITY) < 0);
        assertTrue(Weight.INFINITY.compareTo(w4) > 0);
        assertEquals(Weight.INFINITY, Weight.INFINITY.add(w1));
        assertEquals(Weight.INFINITY, Weight.INFINITY.subtract(w1));
    }

    @Test
    public void path () {
        Path p = new Path();
        assertTrue(p.isEmpty());
        assertEquals(Weight.ZERO, p.weight());
        assertEquals(Weight.INFINITY, p.minFlow());
        Edge e1, e2, e3;
        e1 = new Edge("A", "B");
        e2 = new Edge("B", "C");
        e3 = new Edge("C", "D");
        p.add(e1, new Weight(1));
        p.add(e2, new Weight(2));
        p.add(e3, new Weight(3));
        List<Edge> edges = p.edges();
        assertEquals(List.of(e1, e2, e3), edges);
        assertEquals(1, p.minFlow().value());
        assertEquals(6, p.weight().value());
        p.reverse();
        List<Edge> redges = p.edges();
        assertEquals(List.of(e3, e2, e1), redges);
        assertEquals(1, p.minFlow().value());
        assertEquals(6, p.weight().value());
    }

    @Test
    public void graph () {
        DirectedGraph g = ExampleGraphs.g1();
        /*
        A -> B
        A -> C
        B -> C
        B -> D
        B -> E
        D -> E
        E -> B
        */
        // outgoing edges
        assertEquals(2, g.outgoingEdges("A").size());
        assertEquals(3, g.outgoingEdges("B").size());
        assertEquals(0, g.outgoingEdges("C").size());
        assertEquals(1, g.outgoingEdges("D").size());
        assertEquals(1, g.outgoingEdges("E").size());
        assertTrue(g.outgoingEdges("A").contains(new Edge("A", "B")));
        assertTrue(g.outgoingEdges("A").contains(new Edge("A", "C")));
        // neighbors
        assertEquals(2, g.neighbors("A").size());
        assertEquals(3, g.neighbors("B").size());
        assertEquals(0, g.neighbors("C").size());
        assertEquals(1, g.neighbors("D").size());
        assertEquals(1, g.neighbors("E").size());
        assertTrue(g.neighbors("A").contains("B"));
        assertTrue(g.neighbors("A").contains("C"));
        // transpose
        DirectedGraph gT = g.transpose();
        assertEquals(0, gT.outgoingEdges("A").size());
        assertEquals(2, gT.outgoingEdges("B").size());
        assertEquals(2, gT.outgoingEdges("C").size());
        assertEquals(1, gT.outgoingEdges("D").size());
        assertEquals(2, gT.outgoingEdges("E").size());
        assertTrue(gT.outgoingEdges("B").contains(new Edge("B", "A")));
        assertTrue(gT.outgoingEdges("B").contains(new Edge("B", "E")));
        assertTrue(gT.outgoingEdges("C").contains(new Edge("C", "A")));
        assertTrue(gT.outgoingEdges("C").contains(new Edge("C", "B")));
        assertTrue(gT.outgoingEdges("D").contains(new Edge("D", "B")));
        assertTrue(gT.outgoingEdges("E").contains(new Edge("E", "B")));
        assertTrue(gT.outgoingEdges("E").contains(new Edge("E", "D")));
        assertEquals(0, gT.neighbors("A").size());
        assertEquals(2, gT.neighbors("B").size());
        assertEquals(2, gT.neighbors("C").size());
        assertEquals(1, gT.neighbors("D").size());
        assertEquals(2, gT.neighbors("E").size());
        assertTrue(gT.neighbors("B").contains("A"));
        assertTrue(gT.neighbors("B").contains("E"));
        assertTrue(gT.neighbors("C").contains("A"));
        assertTrue(gT.neighbors("C").contains("B"));
        assertTrue(gT.neighbors("D").contains("B"));
        assertTrue(gT.neighbors("E").contains("B"));
        assertTrue(gT.neighbors("E").contains("D"));
        // bidirectional
        DirectedGraph gB = g.bidirectional();
        assertEquals(2, gB.outgoingEdges("A").size());
        assertEquals(4, gB.outgoingEdges("B").size());
        assertEquals(2, gB.outgoingEdges("C").size());
        assertEquals(2, gB.outgoingEdges("D").size());
        assertEquals(2, gB.outgoingEdges("E").size());
        assertTrue(gB.outgoingEdges("A").contains(new Edge("A", "B")));
        assertTrue(gB.outgoingEdges("A").contains(new Edge("A", "C")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "A")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "C")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "D")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "E")));
        assertTrue(gB.outgoingEdges("C").contains(new Edge("C", "A")));
        assertTrue(gB.outgoingEdges("C").contains(new Edge("C", "B")));
        assertTrue(gB.outgoingEdges("D").contains(new Edge("D", "B")));
        assertTrue(gB.outgoingEdges("D").contains(new Edge("D", "E")));
        assertTrue(gB.outgoingEdges("E").contains(new Edge("E", "B")));
        assertTrue(gB.outgoingEdges("E").contains(new Edge("E", "D")));
        assertEquals(2, gB.neighbors("A").size());
        assertEquals(4, gB.neighbors("B").size());
        assertEquals(2, gB.neighbors("C").size());
        assertEquals(2, gB.neighbors("D").size());
        assertEquals(2, gB.neighbors("E").size());
        assertTrue(gB.neighbors("A").contains("B"));
        assertTrue(gB.neighbors("A").contains("C"));
        assertTrue(gB.neighbors("B").contains("A"));
        assertTrue(gB.neighbors("B").contains("C"));
        assertTrue(gB.neighbors("B").contains("D"));
        assertTrue(gB.neighbors("B").contains("E"));
        assertTrue(gB.neighbors("C").contains("A"));
        assertTrue(gB.neighbors("C").contains("B"));
        assertTrue(gB.neighbors("D").contains("B"));
        assertTrue(gB.neighbors("D").contains("E"));
        assertTrue(gB.neighbors("E").contains("B"));
        assertTrue(gB.neighbors("E").contains("D"));
    }
    @Test
    public void insert() {
        DirectedGraph g = ExampleGraphs.g1();
        Edge e1 = new Edge("A", "D");
        Edge e2 = new Edge("D", "A");
        g.insertEdge(e1);
        g.insertEdge(e2);
        assertEquals(3, g.outgoingEdges("A").size());
        assertEquals(2, g.outgoingEdges("D").size());
        assertTrue(g.outgoingEdges("A").contains(e1));
        assertTrue(g.outgoingEdges("D").contains(e2));
    }
    @Test
    public void remove() {
        DirectedGraph g = ExampleGraphs.g1();
        Edge e1 = new Edge("A", "B");
        g.removeEdge(e1);
        assertEquals(1, g.outgoingEdges("A").size());
        assertEquals(3, g.outgoingEdges("B").size());
        assertTrue(g.outgoingEdges("A").contains(new Edge("A", "C")));
        assertTrue(g.outgoingEdges("B").contains(new Edge("B", "C")));
        assertTrue(g.outgoingEdges("B").contains(new Edge("B", "D")));
        assertTrue(g.outgoingEdges("B").contains(new Edge("B", "E")));
        assertFalse(g.outgoingEdges("A").contains(new Edge("A", "B")));
        Edge e2 = new Edge("B", "C");
        g.removeEdge(e2);
        assertEquals(1, g.outgoingEdges("A").size());
        assertEquals(2, g.outgoingEdges("B").size());
        assertTrue(g.outgoingEdges("A").contains(new Edge("A", "C")));
        assertTrue(g.outgoingEdges("B").contains(new Edge("B", "D")));
        assertTrue(g.outgoingEdges("B").contains(new Edge("B", "E")));
        assertFalse(g.outgoingEdges("B").contains(new Edge("B", "C")));
    }
    @Test
    public void graph2(){
        DirectedGraph g = ExampleGraphs.g2();
        assertEquals(3, g.outgoingEdges("A1").size());
        assertEquals(2, g.outgoingEdges("A2").size());
        assertEquals(2, g.outgoingEdges("A3").size());
        assertEquals(0, g.outgoingEdges("A4").size());
        assertEquals(0, g.outgoingEdges("A5").size());
        assertEquals(0, g.outgoingEdges("A6").size());
        assertEquals(0, g.outgoingEdges("A7").size());
        assertEquals(2, g.outgoingEdges("A8").size());
        assertEquals(2, g.outgoingEdges("A9").size());
        assertEquals(0, g.outgoingEdges("A10").size());
        assertEquals(0, g.outgoingEdges("A11").size());
        assertEquals(0, g.outgoingEdges("A12").size());
        assertTrue(g.outgoingEdges("A1").contains(new Edge("A1", "A2")));
        assertTrue(g.outgoingEdges("A1").contains(new Edge("A1", "A7")));
        assertTrue(g.outgoingEdges("A1").contains(new Edge("A1", "A8")));
        assertTrue(g.outgoingEdges("A2").contains(new Edge("A2", "A3")));
        assertTrue(g.outgoingEdges("A2").contains(new Edge("A2", "A6")));
        assertTrue(g.outgoingEdges("A3").contains(new Edge("A3", "A4")));
        assertTrue(g.outgoingEdges("A3").contains(new Edge("A3", "A5")));
        assertTrue(g.outgoingEdges("A8").contains(new Edge("A8", "A9")));
        assertTrue(g.outgoingEdges("A8").contains(new Edge("A8", "A12")));
        assertTrue(g.outgoingEdges("A9").contains(new Edge("A9", "A10")));
        assertTrue(g.outgoingEdges("A9").contains(new Edge("A9", "A11")));
        assertEquals(3, g.neighbors("A1").size());
        assertEquals(2, g.neighbors("A2").size());
        assertEquals(2, g.neighbors("A3").size());
        assertEquals(0, g.neighbors("A4").size());
        assertEquals(0, g.neighbors("A5").size());
        assertEquals(0, g.neighbors("A6").size());
        assertEquals(0, g.neighbors("A7").size());
        assertEquals(2, g.neighbors("A8").size());
        assertEquals(2, g.neighbors("A9").size());
        assertEquals(0, g.neighbors("A10").size());
        assertEquals(0, g.neighbors("A11").size());
        assertEquals(0, g.neighbors("A12").size());
    }
}
