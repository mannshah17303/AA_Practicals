class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class SegmentIntersect{
    public static int min(int a, int b){
        return a < b ? a : b;
    }
    public static int max(int a, int b){
        return a > b ? a : b;
    }
    public static boolean segment_intersect(Point p1, Point p2, Point p3, Point p4){
        int d1 = Direction(p3, p4 ,p1);
        int d2 = Direction(p3, p4 ,p2);
        int d3 = Direction(p1, p2 ,p3);
        int d4 = Direction(p1, p2 ,p4);


        if(((d1 > 0 && d2  < 0)  || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d4 > 0 && d3 < 0))){
            return true;
        }
        else if(d1 == 0 && onSegment(p3, p4, p1)){
            return true;
        }
        else if(d2 == 0 && onSegment(p3, p4, p2)){
            return true;
        }
        else if(d3 == 0 && onSegment(p1, p2, p3)){
            return true;
        }
        else if(d2 == 0 && onSegment(p1, p2, p4)){
            return true;
        }
        else{
            return false;
        }

    }
    public static int Direction(Point p, Point q, Point r){
        int val = (r.x - p.x) * (q.y - p.y) - (r.y -p.y) * (q.x - p.x);


        return val;
    }
    public static boolean onSegment(Point pi, Point pj, Point pk) {
        boolean condition1 = (min(pi.x, pj.x) <= pk.x) && (pk.x <= max(pi.x, pj.x));
        boolean condition2 = (min(pi.y, pj.y) <= pk.y) && (pk.y <= max(pi.y, pj.y));
        return condition1 && condition2;
    }  
    public static void main(String[] args) {
        Point p1 = new Point(2,2);  
        Point p2 = new Point(8,3);  
        Point p3 = new Point(1,4);  
        Point p4 = new Point(6,5);

        System.out.println(segment_intersect(p1,p2,p3,p4));
    }
}
