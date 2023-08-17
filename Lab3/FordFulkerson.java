package Lab3;

import java.util.LinkedList;
import java.util.Queue;


public class FordFulkerson {
    static int n = 6;
    static int fordFulkerson(int[][] G, int s, int t){
        int[][] rgraph = new int[n][n];
        for(int u=0; u<n; u++){
            for(int v=0; v<n; v++){
                rgraph[u][v] = G[u][v];
            }
        }
        int[] parent = new int[n];
        int max_flow = 0;
        while(bfs(rgraph, s, t, parent)){
            int path_flow = Integer.MAX_VALUE;
            for(int v = t; v!=s; v=parent[v]){
               int u = parent[v];
               path_flow = Math.min(path_flow, rgraph[u][v]);
            }
            for(int v=t; v!=s; v=parent[v]){
                int u = parent[v];
                rgraph[u][v] -= path_flow;
                rgraph[v][u] += path_flow;
            }
            max_flow += path_flow;
        }
        return max_flow;
    }
    static boolean bfs(int rgraph[][], int s, int t, int parent[]){
        int[] visited = new int[n];
        for(int i=0; i<(n-1); i++){
            visited[i] = 0;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = 1;
        parent[s] = -1;
        while(!q.isEmpty()){
            int u = q.poll();
            for(int v=0; v<n; v++){
                if(visited[v] == 0 && rgraph[u][v] > 0){
                    if(v==t){
                        parent[v] = u;
                        return true;
                    }
                    q.add(v);
                    visited[v] = 1;
                    parent[v] = u;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] G = {
            {0, 16, 13, 0, 0,0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
                    };
                   int res = fordFulkerson(G, 0, 5);
                   System.out.println(res);
    }
   
}
