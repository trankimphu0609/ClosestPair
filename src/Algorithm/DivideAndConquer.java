/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entity.Pair;
import Entity.Point;

/**
 * @author Trần Kim Phú
 */
public class DivideAndConquer {

    private static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static Pair closestPair(List<Point> points) {
        List<Point> sortedPoints = new ArrayList<>(points);
        Collections.sort(sortedPoints, (p1, p2) -> Double.compare(p1.x, p2.x));
        return recursiveClosestPair(sortedPoints);
    }

    private static Pair recursiveClosestPair(List<Point> points) {
        if (points.size() <= 3) {
            return bruteForceClosestPair(points);
        }

        int middle = points.size() / 2;
        List<Point> leftPoints = points.subList(0, middle);
        List<Point> rightPoints = points.subList(middle, points.size());

        Pair leftPair = recursiveClosestPair(leftPoints);
        Pair rightPair = recursiveClosestPair(rightPoints);
        Pair closestPair = leftPair.distance() < rightPair.distance() ? leftPair : rightPair;
        double closestDistance = closestPair.distance();

        double middleLineX = rightPoints.get(0).x;
        List<Point> stripPoints = new ArrayList<>();
        for (Point point : points) {
            if (Math.abs(point.x - middleLineX) < closestDistance) {
                stripPoints.add(point);
            }
        }

        Collections.sort(stripPoints, (p1, p2) -> Double.compare(p1.y, p2.y));
        for (int i = 0; i < stripPoints.size() - 1; i++) {
            Point p1 = stripPoints.get(i);
            for (int j = i + 1; j < stripPoints.size(); j++) {
                Point p2 = stripPoints.get(j);
                if (p2.y - p1.y >= closestDistance) {
                    break;
                }
                double distance = distance(p1, p2);
                if (distance < closestDistance) {
                    closestPair = new Pair(p1, p2);
                    closestDistance = distance;
                }
            }
        }

        return closestPair;
    }

    private static Pair bruteForceClosestPair(List<Point> points) {
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
