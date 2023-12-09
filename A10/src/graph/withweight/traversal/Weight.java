package graph.withweight.traversal;

/**
 * TIER I TODO
 * <p>
 * This class represents a distance between two nodes in a graph.
 * <p>
 * The weight is represented as a double number which, in Java, supports positive
 * and negative infinities, division by zero, etc. This allows us to easily represent
 * the distance between two nodes that are not connected by a path as positive
 * infinity.
 */
public class Weight implements Comparable<Weight>{
    private final double weight;

    private Weight () { this.weight = Double.POSITIVE_INFINITY; }
    private Weight (double weight) { this.weight = weight; }

    /**
     * The only public constructor takes an integer. The use
     * of doubles is hidden internally.
     */
    public Weight (int weight) { this.weight = weight; }
    public static final Weight INFINITY = new Weight();
    public static final Weight ZERO = new Weight(0);

    public int value () { return (int) this.weight; }

    /**
     * TIER I
     * <p>
     * The following methods implement the basic arithmetic operations on weights.
     */
    public Weight add (Weight w) {
        Weight newWeight = new Weight(this.weight + w.weight);
        return newWeight;
    }

    /**
     * TIER I
     */
    public Weight subtract (Weight w) {
        Weight newWeight = new Weight(this.weight - w.weight);
        return newWeight;
    }

    /**
     * TIER I
     */
    public int compareTo (Weight other) {
        if (this.weight < other.weight) return -1;
        else if (this.weight > other.weight) return 1;
        else return 0;
    }

    /**
     * TIER I
     */
    public static Weight min (Weight w1, Weight w2) {
        if (w1.compareTo(w2) < 0) return w1;
        else return w2;
    }

    public boolean equals (Object o) {
        if (o instanceof Weight w)
            return this.value() == w.value();
        return false;
    }

    public int hashCode () { return this.value(); }

    public String toString () {
        if (Double.isFinite(weight)) return String.valueOf(this.value());
        else return "*";
    }
}
