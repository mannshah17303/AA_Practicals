package Lab4;

import java.util.*;

public class KargerMincut{
    // Karger's algorithm to find Mincut
    public static int mincut(int graph[][],int n){
        int cutvalue = 0;
        int loop = 100 * (n * n);
        int graph1[][] = new int[n][n];
        Random rand = new Random();
        while(n > 2){
            for(int k=0; k<loop; k++){
                // step 1
                int u = rand.nextInt(n);
                int v = rand.nextInt(n);
                // step 2
                for(int i=0; i<n ; i++){
                    for(int j=i+1; j<n; j++){
                        int count = 0;
                        if(i == u && i == v){
                            graph1[i][j] = graph1[j][i] = 0;
                        }  
                        else if(i == u || i == v){
                            if(graph[u][j] > 0){
                                count++;
                            }
                            if(graph[v][j] > 0){
                                count++;
                            }
                            if(u < j){
                                graph1[u][j] = count;
                            }
                            else{
                                graph1[j][u] = count;
                            }
                            if(v < j){
                                graph1[v][j] = count;
                            }
                            else{
                                graph1[j][v] = count;
                            }
                        }
                        else if(j == u || j == v){
                            if(graph[u][i] > 0){
                                count++;
                            }
                            if(graph[v][i] > 0){
                                count++;
                            }
                            if(u < i){
                                graph1[u][i] = count;
                            }
                            else{
                                graph1[i][u] = count;
                            }
                            if(v < i){
                                graph1[v][i] = count;
                            }
                            else{
                                graph1[i][v] = count;
                            }
                        }
                       
                    }
                }
                //step 3
                for(int i=0; i<n; i++){
                    for(int j=0; j<n;j++){
                        if(i >= j){
                            graph1[i][j] = graph[j][i];
                        }
                    }
                }
                // step 4
                int graph2[][] = new int[n-1][n-1];
                int rows = 0;
                int cols = 0;


                for (int i = 0; i < n; i++) {
                    if (i == v) {
                        continue;
                    }
                    for (int j = 0; j < n; j++) {
                        if (j == v) {
                            continue;
                        }
                        graph2[rows][cols] = graph1[i][j];
                        cols++;
                    }
                    rows++;
                    cols = 0;
                }
                // step 5
                for(int i=0; i<graph2.length; i++){
                    for(int j=0; j<graph2.length; j++){
                        graph[i][j] = graph2[i][j];
                    }
                }
                // step 6
                n = n-1;
                if(n < 2){
                    return graph[0][1];
                }
                // step 8
                cutvalue = graph[0][1];
            }
        }
        return cutvalue;
    }
    public static void main(String[] args) {
        int n = 4;
        int graph[][] = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        System.out.println("Mincut value is :"+mincut(graph, n));
       
    }
}
