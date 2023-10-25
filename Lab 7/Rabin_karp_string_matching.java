public class Rabin_karp_string_matching{
    // Rabin Karp string matching algorithm
    // q -> remainder, d -> radix
    public static void rabinKarp(String text,String pattern,int d,int q){
        int n = text.length();
        int m = pattern.length();
        int h = ((int)Math.pow(d, m-1)) % q;
        int p =0,t=0;
        for(int i=0; i<m; i++){
            p = (d * p + (pattern.charAt(i) - '0')) % q;
            t = (d * t + (text.charAt(i) - '0')) % q;
        }
        // Sliding window to compare hash values
        for (int i = 0; i <= n - m; i++) {
            // If hash values match, check character by character
            if (p == t) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    System.out.println("Pattern found at index " + i);
                }
            }


            // Calculate hash value for the next substring in text
            if (i < n - m) {
                t = (d * (t - (text.charAt(i) - '0') * h) + (text.charAt(i + m) - '0')) % q;
                while(t < 0){
                    t += q;
                }
            }
        }
    }
    public static void main(String[] args) {
        String text = "21457897865";
        String pattern = "5789";
        int q = 13;
        int d = 10;

        rabinKarp(text, pattern, d, q);
    }
}
