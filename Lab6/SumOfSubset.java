package Lab6;

public class SumOfSubset{
   public static boolean dynamicKnapsack(int p[], int w[], int c, int v) {
       int n = p.length;
       int[][] K = new int[n + 1][c + 1];
  
       for (int i = 0; i <= n; i++) {
           for (int j = 0; j <= c; j++) {
               if (i == 0 || j == 0)
                   K[i][j] = 0;
               else if (w[i - 1] <= j)
                   K[i][j] = Math.max(p[i - 1] + K[i - 1][j - w[i - 1]], K[i - 1][j]);
               else
                   K[i][j] = K[i - 1][j];
           }
       }
  
       if(K[n][c] >= v){
           return true;
       }
       else{
           return false;
       }
   }
  
   public static boolean sumOfSubset(int s[], int sum){
       // profit array
       int p[] = new int[s.length];
       // weight array
       int w[] = new int[s.length];


       for(int i=0; i<s.length; i++){
           p[i] = w[i] = s[i];
       }
       int c = sum;
       int v = sum;
       if(dynamicKnapsack(p, w, c, v)){
           return true;
       }
       else{
            return false;
       }
   }
   public static void main(String[] args) {
       // profit array
       int s[] = {2, 4, 6 , 8};
       int sum = 8;


       System.out.println(sumOfSubset(s, sum));


   }
}

