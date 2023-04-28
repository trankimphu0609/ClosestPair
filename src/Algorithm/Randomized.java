/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;

/**
 * @author NguyenVuong
 */

import java.util.*;

import Entity.Point;

public class Randomized {

    public static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static Random rand = new Random();

    public static Point[] bruteForceClosestPair(Point[] S) {
        Point[] closest = new Point[2];
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < S.length; i++) {
            for (int j = i + 1; j < S.length; j++) {
                double d = distance(S[i], S[j]);
                if (d < minDist) {
                    minDist = d;
                    closest[0] = S[i];
                    closest[1] = S[j];
                }
            }
        }
        return closest;
    }

    public static Point[] closestPair(Point[] points) {
        if (points.length < 2) {
            return null;
        }

        if (points.length == 2) {
            return points;
        }

        if (points.length <= 75) {
            return bruteForceClosestPair(points);
        }
        int m = (int) Math.floor(Math.pow(points.length, 2.0 / 3.0));

        for (int i = m - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Point temp = points[i];
            points[i] = points[j];
            points[j] = temp;
        }
        Point[] subset = Arrays.copyOf(points, m);

        Point[] closest = closestPair(subset);
        double dist = distance(closest[0], closest[1]);

        List<Point>[] squares = new List[4];
        for (int i = 0; i < 4; i++) {
            squares[i] = new ArrayList<>();
        }

        double meshSize = dist / 2;

        for (Point p : points) {
            if (p.x <= closest[0].x + meshSize && p.y <= closest[0].y + meshSize) {
                squares[0].add(p);
            } else if (p.x >= closest[0].x - meshSize && p.y <= closest[0].y + meshSize) {
                squares[1].add(p);
            } else if (p.x <= closest[0].x + meshSize && p.y >= closest[0].y - meshSize) {
                squares[2].add(p);
            } else if (p.x >= closest[0].x - meshSize && p.y >= closest[0].y - meshSize) {
                squares[3].add(p);
            }
        }

        Point[] bestPair = closest;
        for (List<Point> square : squares) {
            if (square.size() > 1) {
                Point[] pair = closestPair(square.toArray(Point[]::new));
                if (pair != null) {
                    double d = distance(pair[0], pair[1]);
                    if (d < dist) {
                        dist = d;
                        bestPair = pair;
                    }
                }
            }
        }

        return bestPair;
    }

}
