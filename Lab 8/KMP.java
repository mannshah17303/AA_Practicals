public class KMP {
    // Print the contents of an array
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }


    // Construct the partial match table (also known as pi table) using Knuth-Morris-Pratt algorithm
    public static int[] buildPartialMatchTable(String pattern) {
        int m = pattern.length();
        int piTable[] = new int[m]; // Partial match table
        piTable[0] = 0; // Initialize the first value
        int k = 0; // Length of the longest proper prefix that is also a proper suffix
        boolean mismatch;


        int comparisonCount = 0; // Count of character comparisons
        int loopCount = 0; // Count of loop iterations


        for (int q = 1; q < m; q++) {
            mismatch = (pattern.charAt(k) != pattern.charAt(q));
            comparisonCount++;


            // Update k using the partial match table until a match is found or k becomes 0
            while (k > 0 && mismatch) {
                k = piTable[k - 1]; // Corrected decrement of k
                mismatch = (pattern.charAt(k) != pattern.charAt(q));
                comparisonCount++;
                loopCount++;
            }


            // Update k if a match is found
            if (!mismatch) {
                k++;
            }


            piTable[q] = k; // Update the partial match table
        }


        System.out.println("Character comparison count: " + comparisonCount);
        System.out.println("Loop iteration count: " + loopCount);
        printArray(piTable);
        return piTable;
    }
    public static void stringMatcher(String text, String pattern){
        int n = text.length();
        int m = pattern.length();


        int piTable[] = buildPartialMatchTable(pattern);
        int q = 0;
        boolean mismatch;
        for(int i = 0; i < n;i++){
            mismatch = (pattern.charAt(q) != text.charAt(i));
            while(q > 0 && mismatch){
                q = piTable[q-1];
                mismatch = (pattern.charAt(q) != text.charAt(i));  
            }
            if(!mismatch){
                q++;
            }
            if(q == m){
                System.out.println("Pattern occurs with shift "+(i-m+1));
                q = piTable[q-1];
            }
        }


    }
    public static void main(String[] args) {
        String text = "aaabcdeaaabcde";
        String pattern = "abcde";


        stringMatcher(text,pattern);
    }
}
