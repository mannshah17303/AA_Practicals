import java.util.*;
class Graph{
    static class Edge{
        int src;
        int dest;
        Edge(int src,int dest){
            this.src = src;
            this.dest = dest;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
    }
    public static void addEdge(ArrayList<Edge> graph[],int src,int dest){
        graph[src].add(new Edge(src,dest));
        graph[dest].add(new Edge(dest,src));
    }
    public static void removeAllEdges(ArrayList<Edge> graph[], int vertex) {
        List<Edge> edgesToRemove = new ArrayList<>(graph[vertex]);
        for (Edge edge : edgesToRemove) {
            int dest = edge.dest;
            graph[vertex].remove(edge);
    
            // Remove the corresponding edge from the adjacent vertex
            graph[dest].removeIf(e -> e.dest == vertex);
        }
    }

}

public class RandomizedApproximationVertexCover extends Graph{
    // randomized approximation algo for mvc
    public static Set<Integer> randomizedVertexCover(ArrayList<Edge> graph[]) {
        int graphLength = graph.length;
        Set<Integer> result = new HashSet<>();
    
        // Create a list of all edges in the graph
        List<Edge> allEdges = new ArrayList<>();
        for (int i = 0; i < graphLength; i++) {
            allEdges.addAll(graph[i]);
        }
    
        while (!allEdges.isEmpty()) {
            // Select a random edge from the list
            int randomIndex = (int) (Math.random() * allEdges.size());
            Edge randomEdge = allEdges.get(randomIndex);
    
            int src = randomEdge.src;
            int dest = randomEdge.dest;
    
            result.add(src);
            result.add(dest);
    
            // Remove all edges connected to src and dest
            removeAllEdges(graph, src);
            removeAllEdges(graph, dest);
    
            // Remove the selected edge from the list
            allEdges.remove(randomEdge);
        }
        return result;
    }
    public static void main(String[] args) {
        int V = 6; 
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 3);
        
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        
        addEdge(graph, 2, 4);
        addEdge(graph, 2, 5);
        
        addEdge(graph, 3, 4);
        
        Set<Integer> result = randomizedVertexCover(graph);
        System.out.println("Minimum vertex cover is "+result);

    }
}
