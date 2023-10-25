import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HorspoolsAlgorithm {
    private String dataString;
    private String searchString;
    private int lengthOfDataString;
    private int lengthOfSearchString;
    private int totalMatches;
    private Map<Character, Integer> shifts;

    public HorspoolsAlgorithm(String dataString, String searchString) {
        this.dataString = dataString;
        this.searchString = searchString;
        this.lengthOfDataString = dataString.length();
        this.lengthOfSearchString = searchString.length();
        this.totalMatches = 0;
        this.shifts = new HashMap<>();
        initializeShifts();
    }

    private void initializeShifts() {
        for (int i = 0; i < 127; i++) {
            shifts.put((char) i, lengthOfSearchString);
        }
        for (int i = 0; i < lengthOfSearchString - 1; i++) {
            shifts.put(searchString.charAt(i), lengthOfSearchString - i - 1);
        }
    }

    private boolean matchString(int indexToSearch) {
        for (int i = lengthOfSearchString - 1; i >= 0; i--) {
            if (searchString.charAt(i) != dataString.charAt(indexToSearch + i)) {
                return false;
            }
        }
        return true;
    }

    public void findMatches() {
        for (int i = 0; i < lengthOfDataString - lengthOfSearchString + 1;) {
            if (matchString(i)) {
                printMessage(i);
                totalMatches++;
            }
            i += shifts.getOrDefault(dataString.charAt(i + lengthOfSearchString - 1), lengthOfSearchString);
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

        System.out.println("By Horspools Algorithm:\n");
        HorspoolsAlgorithm horspoolsAlgorithm = new HorspoolsAlgorithm(dataInput, searchInput);
        horspoolsAlgorithm.findMatches();
    }
}
