import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

abstract class StringMatching {
    protected int lengthOfDataString;
    protected int lengthOfSearchString;
    protected int totalMatches;
    protected String dataString;
    protected String searchString;

    protected void printMessage(int indexOfMatching) {
        System.out.print("Found a match at index: " + indexOfMatching + " : ");
        for (int i = 0; i < lengthOfDataString; i++) {
            if (i == indexOfMatching) System.out.print("\"");
            System.out.print(dataString.charAt(i));
            if (i == indexOfMatching + lengthOfSearchString - 1) System.out.print("\"");
        }
        System.out.println();
    }

    public StringMatching(String dataString, String searchString) {
        this.dataString = dataString;
        this.searchString = searchString;
        lengthOfDataString = dataString.length();
        lengthOfSearchString = searchString.length();
        totalMatches = 0;
    }

    public abstract void findMatches();
}

public class FiniteAutomaton extends StringMatching {
    private Map<Integer, Map<Character, Integer> > transitionTable = new HashMap< >();
    private Set<Character> characters = new HashSet< >();
    private int i, j;

    public FiniteAutomaton(String dataString, String searchString) {
        super(dataString, searchString);
        for (char a : searchString.toCharArray()) {
            characters.add(a);
        }
    }

    private boolean isNotSameSuffix(int currState, int currValue, char currCharacter) {
        if (currValue == 0) return false;
        if (currState >= currValue - 1) {
            String currSuffix = searchString.substring(currState - currValue + 1, currState) + currCharacter;
            String currPrefix = searchString.substring(0, currValue - 1);
            return !currSuffix.equals(currPrefix);
        }
        return currCharacter != searchString.charAt(0);
    }    
    
    private void generateTransitionTable() {
        int currValue;
        for (int currState = 0; currState <= lengthOfSearchString; currState++) {
            transitionTable.put(currState, new HashMap<>());
            for (char currCharacter : characters) {
                currValue = Math.min(lengthOfSearchString + 1, currState + 2);
                while (currValue > 0 && isNotSameSuffix(currState, currValue, currCharacter)) {
                    currValue--;
                }
                transitionTable.get(currState).put(currCharacter, currValue);
            }
        }
    }    

    public void findMatches() {
        generateTransitionTable();
        int state = 0;
        for (i = 0; i < lengthOfDataString; i++) {
            char currentCharacter = (i < lengthOfDataString) ? dataString.charAt(i) : 0;
            state = transitionTable.get(state).getOrDefault(currentCharacter, 0);
            if (state == lengthOfSearchString) {
                printMessage(i - lengthOfSearchString + 1);
                totalMatches++;
            }
        }
        System.out.println("Total matches: " + totalMatches);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search string: ");
        String searchString = scanner.nextLine();
        System.out.print("Enter data string: ");
        String dataString = scanner.nextLine();
        System.out.println("2. Finite Automaton Algorithm");

        StringMatching stringMatching = new FiniteAutomaton(dataString, searchString);
        stringMatching.findMatches();
    }
}
