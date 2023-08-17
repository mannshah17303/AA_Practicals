package Lab5;

public class StringMatcher {
    public static void BF_StringMatcher(String t, String p) {
        int n = t.length();
        int m = p.length();

        char[] text_array = new char[n + 1];
        char[] pattern_array = new char[m + 1];

        t.getChars(0, n, text_array, 0);
        p.getChars(0, m, pattern_array, 0);

        for (int s = 0; s <= n - m; s++) {
            int i = 0, j = 0;
            while (j < m && text_array[s + i] == pattern_array[j]) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.println("Pattern matches with shift " + s);
            }
        }
    }

    public static void main(String[] args) {
        String t = "Jim Saw Me In A Barber Shop";
        String p = "Barber";
        BF_StringMatcher(t, p);
    }
}

