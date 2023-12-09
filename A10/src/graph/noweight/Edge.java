package graph.noweight;

public record Edge(String source, String destination) {
    public Edge flip() {
        return new Edge(destination, source);
    }
    public String toString() {
        return source + "->" + destination;
    }

}
