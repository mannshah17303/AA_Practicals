import java.util.Scanner;

public class NaiveAlgorithm {
    private String dataString;
    private String searchString;
    private int lengthOfDataString;
    private int lengthOfSearchString;
    private int totalMatches;

    public NaiveAlgorithm(String dataString, String searchString) {
        this.dataString = dataString;
        this.searchString = searchString;
        this.lengthOfDataString = dataString.length();
        this.lengthOfSearchString = searchString.length();
        this.totalMatches = 0;
    }

    private boolean matchString(int indexToSearch) {
        for (int i = 0; i < lengthOfSearchString; i++) {
            if (dataString.charAt(indexToSearch + i) != searchString.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public void findMatches() {
        for (int i = 0; i < lengthOfDataString - lengthOfSearchString + 1; i++) {
            if (matchString(i)) {
                printMessage(i);
                totalMatches++;
            }
        }
        System.out.println("Total matches are: " + totalMatches);
    }

    private void printMessage(int indexOfMatching) {
        System.out.print("Found a match at index: " + indexOfMatching + " : \"");
        for (int i = 0; i < lengthOfDataString; i++) {
            if (i == indexOfMatching) {
                System.out.print("\"");
            }
            System.out.print(dataString.charAt(i));
            if (i == indexOfMatching + lengthOfSearchString - 1) {
                System.out.print("\"");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Data String: ");
        String dataInput = scanner.nextLine();

        System.out.print("Enter String to Search in Data: ");
        String searchInput = scanner.nextLine();

        System.out.println("\nBy Naive Algorithm:\n");
        NaiveAlgorithm naiveAlgorithm = new NaiveAlgorithm(dataInput, searchInput);
        naiveAlgorithm.findMatches();
    }
}