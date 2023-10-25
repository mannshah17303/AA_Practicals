import java.util.*;

public class GreedyMinimumVertexCover {
    static class Edge {
        int first, second;

        Edge(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Vertex {
        int first, second;

        Vertex(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private List<Edge> edges;
    private Map<Integer, Integer> edgesRemaining = new HashMap<>();
    private PriorityQueue<Vertex> vertices = new PriorityQueue<>(new Comparator<Vertex>() {
        public int compare(Vertex v1, Vertex v2) {
            return Integer.compare(v2.first, v1.first);
        }
    });
    private Set<Integer> answer = new HashSet<>();

    private void reduceCount(int vertex) {
        List<Edge> temp = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.first == vertex) {
                edgesRemaining.put(edge.second, edgesRemaining.get(edge.second) - 1);
                edgesRemaining.put(edge.first, edgesRemaining.get(edge.first) - 1);
            } else if (edge.second == vertex) {
                edgesRemaining.put(edge.first, edgesRemaining.get(edge.first) - 1);
                edgesRemaining.put(edge.second, edgesRemaining.get(edge.second) - 1);
            } else {
                temp.add(edge);
            }
        }
        edges = temp;
        makeQueue();
    }

    private void makeQueue() {
        vertices.clear();
        for (Map.Entry<Integer, Integer> vertex : edgesRemaining.entrySet()) {
            if (vertex.getValue() > 0) {
                vertices.add(new Vertex(vertex.getValue(), vertex.getKey()));
            }
        }
    }

    private void initialize() {
        for (Edge edge : edges) {
            edgesRemaining.put(edge.first, edgesRemaining.getOrDefault(edge.first, 0) + 1);
            edgesRemaining.put(edge.second, edgesRemaining.getOrDefault(edge.second, 0) + 1);
        }
        makeQueue();
    }

    public GreedyMinimumVertexCover(List<Edge> edges) {
        this.edges = edges;
    }

    public void findMVC() {
        initialize();
        while (!vertices.isEmpty()) {
            Vertex currVertex = vertices.poll();
            reduceCount(currVertex.second);
            answer.add(currVertex.second);
        }

        for (int vertex : answer) {
            System.out.print(vertex + " ");
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(1, 9),  new Edge(1, 10), new Edge(1, 11), new Edge(1, 12), new Edge(1, 13),
            new Edge(1, 14), new Edge(2, 9),  new Edge(2, 10), new Edge(2, 11), new Edge(2, 12),
            new Edge(2, 13), new Edge(3, 9),  new Edge(3, 10), new Edge(3, 11), new Edge(3, 12),
            new Edge(4, 9),  new Edge(4, 10), new Edge(4, 11), new Edge(5, 12), new Edge(5, 13),
            new Edge(5, 14), new Edge(6, 9),  new Edge(6, 10), new Edge(7, 11), new Edge(7, 12),
            new Edge(8, 13), new Edge(8, 14)
        );

        GreedyMinimumVertexCover greedyMVC = new GreedyMinimumVertexCover(edges);
        greedyMVC.findMVC();
    }
}
