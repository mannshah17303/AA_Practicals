import java.util.*;

public class ClosestPoints {
    private List<Pair> points;
    private int minDistance;
    private Pair answer;

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public ClosestPoints(List<Pair> points) {
        this.points = points;
        minDistance = Integer.MAX_VALUE;
    }

    private int findDistance(Pair point1, Pair point2) {
        return (int) (Math.pow(point1.first - point2.first, 2) + Math.pow(point1.second - point2.second, 2));
    }

    public void findClosestPoints() {
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int distance = findDistance(points.get(i), points.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                    answer = new Pair(i, j);
                }
            }
        }
        System.out.println("Minimum distance: " + minDistance);
        System.out.println("Closest points: (" + points.get(answer.first).first + ", " + points.get(answer.first).second +
                "), (" + points.get(answer.second).first + ", " + points.get(answer.second).second + ")");
    }

    public static void main(String[] args) {
        List<Pair> points = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of points: ");
        int n = scanner.nextInt();
        System.out.println("Enter points:");

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Pair(x, y));
        }

        ClosestPoints closestPoints = new ClosestPoints(points);
        closestPoints.findClosestPoints();
    }
}
