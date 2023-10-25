import java.util.*;
import java.lang.*;

class PointType {
    int first;
    int second;
    PointType(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class PointWithSin implements Comparable<PointWithSin> {
    double first;
    PointType second;
    PointWithSin(double first, PointType second) {
        this.first = first;
        this.second = second;
    }
    public int compareTo(PointWithSin other) {
        return Double.compare(this.first, other.first);
    }
}
    
class GrahamConvexHull {
    PointType basePoint;
    List<PointWithSin> points;

    void generateSin() {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).second.equals(basePoint)) continue;
            int y = points.get(i).second.second - basePoint.second;
            double d = Math.sqrt(Math.pow(points.get(i).second.first - basePoint.first, 2) + Math.pow(y, 2));
            double sin = y / d;
            double value;
            if (points.get(i).second.first < basePoint.first)
                value = 2 - sin;
            else
                value = sin;
            points.get(i).first = value;
        }
    }

    void findBasePoint() {
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        for (PointWithSin point : points) {
            if (point.second.second < minY) {
                minY = point.second.second;
                minX = point.second.first;
            } else if (point.second.second == minY)
                minX = Math.min(minX, point.second.first);
        }
        basePoint = new PointType(minX, minY);
    }

    int direction(PointType p1, PointType p2, PointType p3) {
        return (p3.first - p1.first) * (p2.second - p1.second) - (p2.first - p1.first) * (p3.second - p1.second);
    }

    public GrahamConvexHull(List<PointType> points1) {
        points = new ArrayList<>();
        for (PointType point : points1) {
            points.add(new PointWithSin(-1.0, point));
        }
    }

    public List<PointWithSin> getConvexHull() {
        findBasePoint();
        generateSin();
        Collections.sort(points);
        List<PointWithSin> convexHull = new ArrayList<>();
        if (3 > points.size()) return points;
        convexHull.add(points.get(0));
        convexHull.add(points.get(1));
        convexHull.add(points.get(2));
        PointWithSin nextTop;
        PointWithSin top;
        for (int i = 3; i < points.size(); i++) {
            while (convexHull.size() > 1) {
                nextTop = convexHull.get(convexHull.size() - 2);
                top = convexHull.get(convexHull.size() - 1);
                if (direction(top.second, nextTop.second, points.get(i).second) < 0)
                    convexHull.remove(convexHull.size() - 1);
                else
                    break;
            }
            convexHull.add(points.get(i));
        }
        return convexHull;
    }
}

public class GrahamScan {
    public static void main(String[] args) {
        List<PointType> pointsInput = new ArrayList<>();
        pointsInput.add(new PointType(-7, 8));
        pointsInput.add(new PointType(-4, 6));
        pointsInput.add(new PointType(2, 6));
        pointsInput.add(new PointType(6, 4));
        pointsInput.add(new PointType(8, 6));
        pointsInput.add(new PointType(7, -2));
        pointsInput.add(new PointType(4, -6));
        pointsInput.add(new PointType(8, -7));
        pointsInput.add(new PointType(0, 0));
        pointsInput.add(new PointType(3, -2));
        pointsInput.add(new PointType(6, -10));
        pointsInput.add(new PointType(0, -6));
        pointsInput.add(new PointType(-9, -5));
        pointsInput.add(new PointType(-8, -2));
        pointsInput.add(new PointType(-8, 0));
        pointsInput.add(new PointType(-10, 3));
        pointsInput.add(new PointType(-2, 2));
        pointsInput.add(new PointType(-10, 4));

        GrahamConvexHull grahamHull = new GrahamConvexHull(pointsInput);
        List<PointWithSin> convexHull = grahamHull.getConvexHull();
        for (PointWithSin point : convexHull) {
            System.out.println("(" + point.second.first + ", " + point.second.second + ")");
        }
    }
}
