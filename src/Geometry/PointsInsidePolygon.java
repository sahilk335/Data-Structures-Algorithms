package Geometry;


public class PointsInsidePolygon {

    /*
     *@Author : Sahil
     * Date : 09 Feb 2019
     *
     * Concept :
     *
     * Sum of interior angles in  polygon is 360 degree or 2 Pi.
     *
     * If sum of interior Angles from point (x,y) to (x1,y1),(x2,y2)......(Xn,Yn) is 2 Pi then it is interior else it isn't
     *
     * How to calculate Inside angle ?
     *  Cosine Formula :
     *  https://en.wikipedia.org/wiki/Law_of_cosines
     *
     *
     * References :
     * https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/
     *
     */

    static class Points {
        int x;
        int y;

        Points(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // returns square of distance b/w two points  (x2-x1)^22 + (Y2-y1)^2
    // returns square of distance b/w two points
    static int lengthSquare(Points p1, Points p2) {
        int xDiff = p1.x - p2.x;
        int yDiff = p1.y - p2.y;
        return xDiff * xDiff + yDiff * yDiff;
    }

    double angle(Points point1, Points point2, Points X) {

        // Square of lengths be a2, b2, c2
        int a2 = lengthSquare(point1, X);
        int b2 = lengthSquare(X, point2);
        int c2 = lengthSquare(point1, point2);

        // lenght of sides be a, b, c
        double a = Math.sqrt(a2);
        double b = Math.sqrt(b2);
        double c = Math.sqrt(c2);

        double theta = Math.acos((a2 + b2 - c2) / (2 * a * b));

        return theta;

    }

    boolean isInsidePolygon(Points[] polygon, Points X) {
        int polygonTriangles = polygon.length;
        double totalAngle = 0.0;

        for (int i = 0; i < polygonTriangles - 1; i++) {
            totalAngle += Math.toDegrees(angle(polygon[i], polygon[i + 1], X));
        }
        totalAngle += Math.toDegrees(angle(polygon[0], polygon[polygon.length - 1], X));

        System.out.println(Math.round(totalAngle));

        if (Math.round(totalAngle) == 360) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        PointsInsidePolygon pointsInsidePolygon = new PointsInsidePolygon();
        Points[] polygon = new Points[4];
        polygon[0] = new Points(0, 0);
        polygon[1] = new Points(10, 0);
        polygon[2] = new Points(10, 10);
        polygon[3] = new Points(0, 10);

        Points X = new Points(-1, 10);

        boolean answer = pointsInsidePolygon.isInsidePolygon(polygon, X);
        System.out.println("Inside " + answer);
    }
}
