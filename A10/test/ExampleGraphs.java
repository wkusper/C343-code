import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.Weight;

import java.util.HashMap;
import java.util.Set;

public class ExampleGraphs {

    public static DirectedGraph g0() {
        Edge ab, ac, bd, cd;
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bd = new Edge("B", "D");
        cd = new Edge("C", "D");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ab, ac));
        adjacencyLists.put("B", Set.of(bd));
        adjacencyLists.put("C", Set.of(cd));
        adjacencyLists.put("D", Set.of());

        return new DirectedGraph(adjacencyLists);
    }

    public static DirectedGraph g1() {
        Edge ab, ac, bc, bd, be, de, eb;
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bc = new Edge("B", "C");
        bd = new Edge("B", "D");
        be = new Edge("B", "E");
        de = new Edge("D", "E");
        eb = new Edge("E", "B");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ab, ac));
        adjacencyLists.put("B", Set.of(bc, bd, be));
        adjacencyLists.put("C", Set.of());
        adjacencyLists.put("D", Set.of(de));
        adjacencyLists.put("E", Set.of(eb));

        return new DirectedGraph(adjacencyLists);
    }

    public static DirectedGraph g2() {
        Edge a1a2, a1a7, a1a8;
        Edge a2a3, a2a6;
        Edge a3a4, a3a5;
        Edge a8a9, a8a12;
        Edge a9a10, a9a11;
        a1a2 = new Edge("A1", "A2");
        a1a7 = new Edge("A1", "A7");
        a1a8 = new Edge("A1", "A8");
        a2a3 = new Edge("A2", "A3");
        a2a6 = new Edge("A2", "A6");
        a3a4 = new Edge("A3", "A4");
        a3a5 = new Edge("A3", "A5");
        a8a9 = new Edge("A8", "A9");
        a8a12 = new Edge("A8", "A12");
        a9a10 = new Edge("A9", "A10");
        a9a11 = new Edge("A9", "A11");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A1", Set.of(a1a2, a1a7, a1a8));
        adjacencyLists.put("A2", Set.of(a2a3, a2a6));
        adjacencyLists.put("A3", Set.of(a3a4, a3a5));
        adjacencyLists.put("A4", Set.of());
        adjacencyLists.put("A5", Set.of());
        adjacencyLists.put("A6", Set.of());
        adjacencyLists.put("A7", Set.of());
        adjacencyLists.put("A8", Set.of(a8a9, a8a12));
        adjacencyLists.put("A9", Set.of(a9a10, a9a11));
        adjacencyLists.put("A10", Set.of());
        adjacencyLists.put("A11", Set.of());
        adjacencyLists.put("A12", Set.of());

        return new DirectedGraph(adjacencyLists);
    }

    public static DirectedGraph g3() {
        Edge ac, ah, ba, bg, cd, df, ea, ei, fj, gi, hf, hg, ih, jc;
        ac = new Edge("A", "C");
        ah = new Edge("A", "H");
        ba = new Edge("B", "A");
        bg = new Edge("B", "G");
        cd = new Edge("C", "D");
        df = new Edge("D", "F");
        ea = new Edge("E", "A");
        ei = new Edge("E", "I");
        fj = new Edge("F", "J");
        gi = new Edge("G", "I");
        hf = new Edge("H", "F");
        hg = new Edge("H", "G");
        ih = new Edge("I", "H");
        jc = new Edge("J", "C");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ac, ah));
        adjacencyLists.put("B", Set.of(ba, bg));
        adjacencyLists.put("C", Set.of(cd));
        adjacencyLists.put("D", Set.of(df));
        adjacencyLists.put("E", Set.of(ea, ei));
        adjacencyLists.put("F", Set.of(fj));
        adjacencyLists.put("G", Set.of(gi));
        adjacencyLists.put("H", Set.of(hf, hg));
        adjacencyLists.put("I", Set.of(ih));
        adjacencyLists.put("J", Set.of(jc));

        return new DirectedGraph(adjacencyLists);
    }

    public static WeightedDirectedGraph g4() {
        Edge ab, ac, bc, bd, be, de, eb;
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bc = new Edge("B", "C");
        bd = new Edge("B", "D");
        be = new Edge("B", "E");
        de = new Edge("D", "E");
        eb = new Edge("E", "B");

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(ab, new Weight(1));
        weights.put(ac, new Weight(2));
        weights.put(bc, new Weight(3));
        weights.put(bd, new Weight(4));
        weights.put(be, new Weight(5));
        weights.put(de, new Weight(6));
        weights.put(eb, new Weight(7));

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ab, ac));
        adjacencyLists.put("B", Set.of(bc, bd, be));
        adjacencyLists.put("C", Set.of());
        adjacencyLists.put("D", Set.of(de));
        adjacencyLists.put("E", Set.of(eb));

        return new WeightedDirectedGraph(adjacencyLists, weights);
    }

    public static WeightedDirectedGraph g5() {
        Edge ab, ac, bc, bd, ce, df, ed;
        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bc = new Edge("B", "C");
        bd = new Edge("B", "D");
        ce = new Edge("C", "E");
        df = new Edge("D", "F");
        ed = new Edge("E", "D");

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(ab, new Weight(4));
        weights.put(ac, new Weight(2));
        weights.put(bc, new Weight(5));
        weights.put(bd, new Weight(10));
        weights.put(ce, new Weight(3));
        weights.put(df, new Weight(11));
        weights.put(ed, new Weight(4));

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ab, ac));
        adjacencyLists.put("B", Set.of(bc, bd));
        adjacencyLists.put("C", Set.of(ce));
        adjacencyLists.put("D", Set.of(df));
        adjacencyLists.put("E", Set.of(ed));
        adjacencyLists.put("F", Set.of());

        return new WeightedDirectedGraph(adjacencyLists, weights);
    }

    public static WeightedDirectedGraph g6() {
        /*
                        A
                      /   \
                    B  ---  C
                    | \   / |
                    |   D   |
                    | / | \ |
                    E   |   F
                     \  |  /
                        G

        AC, BD, CD, DE, CF, EG

        */

        Edge ab, ac, bc, bd, cd;
        Edge be, de, cf, df;
        Edge eg, dg, fg;

        ab = new Edge("A", "B");
        ac = new Edge("A", "C");
        bc = new Edge("B", "C");
        bd = new Edge("B", "D");
        cd = new Edge("C", "D");
        be = new Edge("B", "E");
        de = new Edge("D", "E");
        cf = new Edge("C", "F");
        df = new Edge("D", "F");
        eg = new Edge("E", "G");
        dg = new Edge("D", "G");
        fg = new Edge("F", "G");

        Edge ba, ca, cb, db, dc;
        Edge eb, ed, fc, fd;
        Edge ge, gd, gf;

        ba = new Edge("B", "A");
        ca = new Edge("C", "A");
        cb = new Edge("C", "B");
        db = new Edge("D", "B");
        dc = new Edge("D", "C");
        eb = new Edge("E", "B");
        ed = new Edge("E", "D");
        fc = new Edge("F", "C");
        fd = new Edge("F", "D");
        ge = new Edge("G", "E");
        gd = new Edge("G", "D");
        gf = new Edge("G", "F");

        HashMap<String, Set<Edge>> adjacencyLists1 = new HashMap<>();
        adjacencyLists1.put("A", Set.of(ab, ac));
        adjacencyLists1.put("B", Set.of(bc, bd, be));
        adjacencyLists1.put("C", Set.of(cd, cf));
        adjacencyLists1.put("D", Set.of(de, df, dg));
        adjacencyLists1.put("E", Set.of(eg));
        adjacencyLists1.put("F", Set.of(fg));
        adjacencyLists1.put("G", Set.of());

        HashMap<String, Set<Edge>> adjacencyLists2 =
                new DirectedGraph(adjacencyLists1).bidirectional().getAdjacencyLists();

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(ab, new Weight(8));
        weights.put(ba, new Weight(8));
        weights.put(ac, new Weight(5));
        weights.put(ca, new Weight(5));
        weights.put(bc, new Weight(10));
        weights.put(cb, new Weight(10));
        weights.put(bd, new Weight(2));
        weights.put(db, new Weight(2));
        weights.put(cd, new Weight(3));
        weights.put(dc, new Weight(3));
        weights.put(be, new Weight(18));
        weights.put(eb, new Weight(18));
        weights.put(de, new Weight(12));
        weights.put(ed, new Weight(12));
        weights.put(cf, new Weight(16));
        weights.put(fc, new Weight(16));
        weights.put(df, new Weight(30));
        weights.put(fd, new Weight(30));
        weights.put(eg, new Weight(4));
        weights.put(ge, new Weight(4));
        weights.put(dg, new Weight(14));
        weights.put(gd, new Weight(14));
        weights.put(fg, new Weight(26));
        weights.put(gf, new Weight(26));

        return new WeightedDirectedGraph(adjacencyLists2, weights);
    }

    public static WeightedDirectedGraph g7() {
        /*
                  -- A --------- B --
             10 /    |     5     |    \ 7
              S    2 |         8 |      T
              8 \    |    10     |    / 10
                  -- C --------- D --

            Max flow from S to T is 15
         */
        Edge sa, sc, ac, ab, cd, db, bt, dt;
        sa = new Edge("S", "A");
        sc = new Edge("S", "C");
        ac = new Edge("A", "C");
        ab = new Edge("A", "B");
        cd = new Edge("C", "D");
        db = new Edge("D", "B");
        bt = new Edge("B", "T");
        dt = new Edge("D", "T");

        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("S", Set.of(sa, sc));
        adjacencyLists.put("A", Set.of(ac, ab));
        adjacencyLists.put("C", Set.of(cd));
        adjacencyLists.put("B", Set.of(bt));
        adjacencyLists.put("D", Set.of(db, dt));
        adjacencyLists.put("T", Set.of());

        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(sa, new Weight(10));
        weights.put(sc, new Weight(8));
        weights.put(ac, new Weight(2));
        weights.put(ab, new Weight(5));
        weights.put(cd, new Weight(10));
        weights.put(db, new Weight(8));
        weights.put(bt, new Weight(7));
        weights.put(dt, new Weight(10));

        return new WeightedDirectedGraph(adjacencyLists, weights);
    }
    public static WeightedDirectedGraph g8() {
        /*
            A -- 4 --> B -- 2 --> C -- 2 --> D -- 4 --> E
         */
        Edge ab, bc, cd, de;
        ab = new Edge("A", "B");
        bc = new Edge("B", "C");
        cd = new Edge("C", "D");
        de = new Edge("D", "E");
        HashMap<String, Set<Edge>> adjacencyLists = new HashMap<>();
        adjacencyLists.put("A", Set.of(ab));
        adjacencyLists.put("B", Set.of(bc));
        adjacencyLists.put("C", Set.of(cd));
        adjacencyLists.put("D", Set.of(de));
        adjacencyLists.put("E", Set.of());
        HashMap<Edge, Weight> weights = new HashMap<>();
        weights.put(ab, new Weight(4));
        weights.put(bc, new Weight(2));
        weights.put(cd, new Weight(2));
        weights.put(de, new Weight(4));
        return new WeightedDirectedGraph(adjacencyLists, weights);
    }
}
