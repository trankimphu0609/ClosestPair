/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;

import java.util.List;

import Entity.Pair;
import Entity.Point;

/**
 * @author trankimphu0609
 */
public class BruteForce {

    private static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static Pair bruteForceClosestPair(List<Point> points) {
        if (points.size() < 2) {
            return null;
        }

        Pair closestPair = new Pair(points.get(0), points.get(1));
        double closestDistance = closestPair.distance();

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double distance = distance(p1, p2);

                if (distance < closestDistance) {
                    closestPair = new Pair(p1, p2);
                    closestDistance = distance;
                }
            }
        }
        return closestPair;
    }
}
