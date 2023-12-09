    package graph.noweight;

    import java.util.*;
    import java.util.stream.Collectors;

    /**
     * TIER I TODO:
     * <p>
     * This class represents a directed graph. The graph is represented as a
     * collection of nodes and a mapping from each node to its outgoing edges.
     */
    public class DirectedGraph {
        protected final Set<String> nodes;
        protected final HashMap<String,Set<Edge>> adjacencyLists;

        public DirectedGraph(HashMap<String,Set<Edge>> adjacencyLists) {
            this.nodes = adjacencyLists.keySet();
            this.adjacencyLists = adjacencyLists;
        }

        public Set<String> getNodes () { return nodes; }
        public HashMap<String,Set<Edge>> getAdjacencyLists () { return adjacencyLists; }

        /**
         * TIER I
         * Return a set of the outgoing edges from the given node.
         */
        public Set<Edge> outgoingEdges (String node) {
            Set<Edge> edges;
            edges = adjacencyLists.get(node);
            return edges;
        }

        /**
         * TIER I TODO:
         * Return a set of the neighbors of the given node.
         */
        public Set<String> neighbors(String node) {
            Set<Edge> edges = adjacencyLists.get(node);
            Set<String> neighbors = new HashSet<>();
            for (Edge edge : edges) {
                if (edge.source().equals(node)) {
                    neighbors.add(edge.destination());
                }
                if (edge.destination().equals(node)) {
                    neighbors.add(edge.source());
                }
            }
            return neighbors;
        }

        /**
         * TIER I TODO:
         * Remove the given edge from the graph.
         */
        public void removeEdge (Edge edge) {
            String sourceNode = edge.source();
            Set<Edge> edges = adjacencyLists.get(sourceNode);
            if (edges != null) {
                edges = new HashSet<>(edges);
                edges.remove(edge);
                adjacencyLists.put(sourceNode, edges);
            }
        }

        /**
         * TIER I TODO:
         * Insert the given edge into the graph.
         */
        public void insertEdge (Edge edge) {
            String sourceNode = edge.source();
            Set<Edge> edges = adjacencyLists.get(sourceNode);
            if (edges == null) {
                edges = new HashSet<>();
                adjacencyLists.put(sourceNode, edges);
            } else {
                edges = new HashSet<>(edges);
                adjacencyLists.put(sourceNode, edges);
            }
            edges.add(edge);
        }

        /**
         * TIER I TODO
         * <p>
         * This method should return a new DirectedGraph that is a copy of this one.
         * It is important to create a new copy of the adjacency lists, rather than
         * just returning the existing ones, because otherwise the caller could
         * modify the adjacency lists of the returned graph, which would also modify
         * the original lists.
         */
        public DirectedGraph copy () {
            HashMap<String,Set<Edge>> newAdjacencyLists = new HashMap<>();
            for (String node : adjacencyLists.keySet()) {
                Set<Edge> edges = adjacencyLists.get(node);
                Set<Edge> newEdges = new HashSet<>();
                newEdges.addAll(edges);
                newAdjacencyLists.put(node, newEdges);
            }
            return new DirectedGraph(newAdjacencyLists);
        }

        /**
         * TIER I TODO:
         * Return a new DirectedGraph that is the transpose of this graph. In
         * a transpose graph, the direction of every edge is reversed.
         */
        public DirectedGraph transpose () {
            HashMap<String, Set<Edge>> flippedGraph = new HashMap<>();

            // Iterate through each node in the original graph
            for (String node : adjacencyLists.keySet()) {
                // Handle the case where outgoingEdges for a node is null
                Set<Edge> edges = adjacencyLists.get(node);
                if (edges != null) {
                    // Iterate through each edge associated with the current node
                    for (Edge edge : edges) {
                        // Flip the edge
                        Edge flippedEdge = new Edge(edge.destination(), edge.source());

                        // Update the flippedGraph for the flipped edge
                        flippedGraph
                                .computeIfAbsent(flippedEdge.source(), k -> new HashSet<>())
                                .add(flippedEdge);

                        // Ensure that the original node is also present in the flippedGraph
                        flippedGraph.computeIfAbsent(node, k -> new HashSet<>());
                    }
                }
            }

            return new DirectedGraph(flippedGraph);
        }

        /**
         * TIER I TODO:
         * Return a new DirectedGraph that is the bidirectional version of this
         * graph. In a bidirectional graph, for every edge A -> B, there is also
         * an edge B -> A.
         */
        public DirectedGraph bidirectional () {
            HashMap<String, Set<Edge>> bidirectionalGraph = new HashMap<>();

            // Iterate through each node in the original graph
            for (String node : adjacencyLists.keySet()) {
                // Handle the case where outgoingEdges for a node is null
                Set<Edge> edges = adjacencyLists.get(node);
                if (edges != null) {
                    // Iterate through each edge associated with the current node
                    for (Edge edge : edges) {
                        // Create the flipped edge
                        Edge flippedEdge = new Edge(edge.destination(), edge.source());

                        // Update the bidirectionalGraph for the flipped edge
                        bidirectionalGraph
                                .computeIfAbsent(flippedEdge.source(), k -> new HashSet<>())
                                .add(flippedEdge);

                        // Update the bidirectionalGraph for the original edge
                        bidirectionalGraph
                                .computeIfAbsent(edge.source(), k -> new HashSet<>())
                                .add(edge);
                    }
                }
            }

            return new DirectedGraph(bidirectionalGraph);
        }
    }
