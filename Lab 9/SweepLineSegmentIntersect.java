import java.util.*;

class Point implements Comparable<Point> {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.x, other.x);
    }
}

class Segment implements Comparable<Segment> {
    Point start, end;

    Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Segment other) {
        // Sort segments based on their y-coordinates
        return Integer.compare(this.start.y, other.start.y);
    }
}

public class SweepLineSegmentIntersect {
    public static boolean intersect(Segment s1, Segment s2) {
        int x1 = s1.start.x;
        int y1 = s1.start.y;
        int x2 = s1.end.x;
        int y2 = s1.end.y;
        int x3 = s2.start.x;
        int y3 = s2.start.y;
        int x4 = s2.end.x;
        int y4 = s2.end.y;

        // Check if segments s1 and s2 intersect using the cross-product
        int d1 = crossProduct(x4 - x3, y4 - y3, x1 - x3, y1 - y3);
        int d2 = crossProduct(x4 - x3, y4 - y3, x2 - x3, y2 - y3);
        int d3 = crossProduct(x2 - x1, y2 - y1, x3 - x1, y3 - y1);
        int d4 = crossProduct(x2 - x1, y2 - y1, x4 - x1, y4 - y1);

        return (d1 * d2 < 0) && (d3 * d4 < 0);
    }

    public static int crossProduct(int dx1, int dy1, int dx2, int dy2) {
        return dx1 * dy2 - dx2 * dy1;
    }

    public static boolean anyPairIntersect(List<Segment> segments) {
        Collections.sort(segments);
        TreeSet<Segment> activeSegments = new TreeSet<>();

        for (Segment segment : segments) {
            // Remove segments that are no longer active
            Iterator<Segment> iterator = activeSegments.iterator();
            while (iterator.hasNext()) {
                Segment active = iterator.next();
                if (active.end.y < segment.start.y) {
                    iterator.remove();
                } else {
                    break;
                }
            }

            // Check for intersections with active segments
            for (Segment active : activeSegments) {
                if (intersect(segment, active)) {
                    return true;
                }
            }

            // Add the current segment to the active set
            activeSegments.add(segment);
        }

        return false;
    }

    public static void main(String[] args) {
        // Define your segments here
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(1, 1), new Point(10, 10)));
        segments.add(new Segment(new Point(5, 5), new Point(8, 8)));

        boolean intersects = anyPairIntersect(segments);
        if (intersects) {
            System.out.println("At least one pair of segments intersect.");
        } else {
            System.out.println("No pair of segments intersect.");
        }
    }
}
