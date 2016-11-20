/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import GameObject.GameStaticObject;
import GameObject.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class PathFindingStaticObject {

    private List<Point> finalUnionObjectPointList = new ArrayList<>();

    public PathFindingStaticObject(int enemySize, GameStaticObject gameStaticObjectA, GameStaticObject gameStaticObjectB) {
        createUnionOfTwoInputObjects(enemySize, gameStaticObjectA, gameStaticObjectB);
    }

    private void createUnionOfTwoInputObjects(int enemySize, GameStaticObject gameStaticObjectA, GameStaticObject gameStaticObjectB) {
        List<Line> objectALineList = new ArrayList<>(gameStaticObjectA.getPolygonLineList(enemySize));
        List<Line> objectBLineList = new ArrayList<>(gameStaticObjectB.getPolygonLineList(enemySize));

        //merge the object in the correct order
        findTheObjectMoreLeftAndTop(objectALineList, objectBLineList);

        //add First point into newly created union shape
        finalUnionObjectPointList.add(new Point(objectALineList.get(0).getStartX(), objectALineList.get(0).getStartY()));
        createPointListOfTheUnionObject(objectALineList, objectBLineList);
    }

    private void createPointListOfTheUnionObject(List<Line> objectALineList, List<Line> objectBLineList) {
        double distanceFromIntersect = 99999;
        Line objectBIntersectingLine = new Line();
        Line potentialObjectBIntersectingLine;
        Point finalIntersectionPoint = new Point(0, 0);
        boolean intersectFound = false;

        for (Line lineInObjectA : objectALineList) {
            //      System.out.println("line in object A: " + lineInObjectA.getStartX() + " " + lineInObjectA.getStartY() + " , " + lineInObjectA.getEndX() + " " + lineInObjectA.getEndY());
            double lineAStartX = lineInObjectA.getStartX();
            double lineAEndX = lineInObjectA.getEndX();
            double lineAStartY = lineInObjectA.getStartY();
            double lineAEndY = lineInObjectA.getEndY();

            for (int i = 0; i < objectBLineList.size(); i++) {
                Line lineInObjectB = copyLineByValue(objectBLineList.get(i));

                double lineBStartX = lineInObjectB.getStartX();
                double lineBEndX = lineInObjectB.getEndX();
                double lineBStartY = lineInObjectB.getStartY();
                double lineBEndY = lineInObjectB.getEndY();

                //    if (lineInObjectA.intersects(lineInObjectB.getBoundsInParent())) {
                if (((Path) Shape.intersect(lineInObjectA, lineInObjectB)).getElements().size() > 0) {
                    System.out.println("intersecting lines First Step: ");
                    System.out.println(lineInObjectA.getStartX() + " " + lineInObjectA.getStartY() + " , " + lineInObjectA.getEndX() + " " + lineInObjectA.getEndY());
                    System.out.println(lineInObjectB.getStartX() + " " + lineInObjectB.getStartY() + " , " + lineInObjectB.getEndX() + " " + lineInObjectB.getEndY());

                    //check if next is also coliding
                    potentialObjectBIntersectingLine = copyLineByValue(lineInObjectB);
                    //    Line futureLineInObjectB = objectBPointList.get((i + 1) % objectBPointList.size());

                    Line futureLineInObjectB = copyLineByValue(objectBLineList.get((i + 1) % objectBLineList.size()));
                    if (lineInObjectA.intersects(futureLineInObjectB.getBoundsInParent())) {
                        potentialObjectBIntersectingLine = new Line(futureLineInObjectB.getStartX(), futureLineInObjectB.getStartY(), futureLineInObjectB.getEndX(), futureLineInObjectB.getEndY());
                    }

                    //intersection point:
                    Point intersectionPoint = intersectionPointOfTwoLineSegment(lineAStartX, lineAStartY, lineAEndX, lineAEndY, lineBStartX, lineBStartY, lineBEndX, lineBEndY);

                    if (distanceBetweenPoints(intersectionPoint, new Point(lineAStartX, lineAStartY)) < distanceFromIntersect) {
                        intersectFound = true;
                        objectBIntersectingLine = potentialObjectBIntersectingLine;
                        distanceFromIntersect = distanceBetweenPoints(intersectionPoint, new Point(lineAStartX, lineAStartY));
                        finalIntersectionPoint = new Point(intersectionPoint.getCoordX(), intersectionPoint.getCoordY());
                    }
                }
            }
            if (intersectFound) {
                finalUnionObjectPointList.add(finalIntersectionPoint);
                System.out.println("Point added: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());
                continueInObjectBWithAddingLinesAndPointForNewUnionShape(objectBIntersectingLine, objectALineList, objectBLineList);
                removeFromPointListUnnecessaryPoints();
                return;
            } else {
                finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));
                System.out.println("Point added: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());
            }

        }

    }

    private void continueInObjectBWithAddingLinesAndPointForNewUnionShape(Line intersectingLineInObjectB, List<Line> objectALineList, List<Line> objectBLineList) {
        System.out.println();
        double distanceFromIntersect = 99999;
        Point finalIntersectionPoint = new Point(0, 0);
        boolean intersectFound = false;
        Line objectAIntersectingLine = new Line();

        int indexOfContinuationLineInObjectB = 0;
        for (int i = 0; i < objectBLineList.size(); i++) {
            if (intersectingLineInObjectB.getStartX() == objectBLineList.get(i).getStartX() && intersectingLineInObjectB.getStartY() == objectBLineList.get(i).getStartY()
                    && intersectingLineInObjectB.getEndX() == objectBLineList.get(i).getEndX() && intersectingLineInObjectB.getEndY() == objectBLineList.get(i).getEndY()) {
                indexOfContinuationLineInObjectB = (i + 1) % objectBLineList.size();
            }
        }

        finalUnionObjectPointList.add(new Point(objectBLineList.get(indexOfContinuationLineInObjectB).getStartX(), objectBLineList.get(indexOfContinuationLineInObjectB).getStartY()));
        //     System.out.println("union object vlozeny: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());

        for (int i = indexOfContinuationLineInObjectB; i < objectBLineList.size(); i++) {
            Line lineInObjectB = objectBLineList.get(i);

            for (Line lineInObjectA : objectALineList) {
                double lineBStartX = lineInObjectB.getStartX();
                double lineBEndX = lineInObjectB.getEndX();
                double lineBStartY = lineInObjectB.getStartY();
                double lineBEndY = lineInObjectB.getEndY();

                double lineAStartX = lineInObjectA.getStartX();
                double lineAEndX = lineInObjectA.getEndX();
                double lineAStartY = lineInObjectA.getStartY();
                double lineAEndY = lineInObjectA.getEndY();

                //     if (lineInObjectA.intersects(lineInObjectB.getBoundsInParent())) {
                if (((Path) Shape.intersect(lineInObjectA, lineInObjectB)).getElements().size() > 0) {
                    System.out.println("intersecting lines second step: ");
                    System.out.println(lineInObjectA.getStartX() + " " + lineInObjectA.getStartY() + " , " + lineInObjectA.getEndX() + " " + lineInObjectA.getEndY());
                    System.out.println(lineInObjectB.getStartX() + " " + lineInObjectB.getStartY() + " , " + lineInObjectB.getEndX() + " " + lineInObjectB.getEndY());

                    //intersection point:
                    Point intersectionPoint = intersectionPointOfTwoLineSegment(lineBStartX, lineBStartY, lineBEndX, lineBEndY, lineAStartX, lineAStartY, lineAEndX, lineAEndY);

                    if (distanceBetweenPoints(intersectionPoint, new Point(lineBStartX, lineBStartY)) < distanceFromIntersect) {
                        intersectFound = true;
                        objectAIntersectingLine = copyLineByValue(lineInObjectA);
                        distanceFromIntersect = distanceBetweenPoints(intersectionPoint, new Point(lineBStartX, lineBStartY));
                        finalIntersectionPoint = new Point(intersectionPoint.getCoordX(), intersectionPoint.getCoordY());
                    }

                    //      finalUnionObjectPointList.add(new Point(intersectionPoint.getCoordX(), intersectionPoint.getCoordY()));
                    //      finalUnionObjectPointList.add(new Point(lineBEndX, lineBEndY));
                }
            }

            if (intersectFound) {
                finalUnionObjectPointList.add(new Point(finalIntersectionPoint.getCoordX(), finalIntersectionPoint.getCoordY()));
                finishInObjectAWithAddingLinesAndPoints(objectAIntersectingLine, objectALineList);
                return;
            }
            finalUnionObjectPointList.add(new Point(lineInObjectB.getEndX(), lineInObjectB.getEndY()));

        }
    }

    private void finishInObjectAWithAddingLinesAndPoints(Line crossedlineInObjectA, List<Line> objectALineList) {
        int indexOfContinuationLineInObjectA = 0;
        for (int i = 0; i < objectALineList.size(); i++) {
            if (areLinesEqual(crossedlineInObjectA, objectALineList.get(i))) {
                indexOfContinuationLineInObjectA = (i + 1) % objectALineList.size();
                System.out.println(indexOfContinuationLineInObjectA);
            }
        }
        finalUnionObjectPointList.add(new Point(objectALineList.get(indexOfContinuationLineInObjectA).getStartX(), objectALineList.get(indexOfContinuationLineInObjectA).getStartY()));

        for (int i = indexOfContinuationLineInObjectA; i < objectALineList.size(); i++) {
            Line lineInObjectA = objectALineList.get(i);
            System.out.println("final lines:" + lineInObjectA.getStartX() + " " + lineInObjectA.getStartY() + " , " + lineInObjectA.getEndX() + " " + lineInObjectA.getEndY());
            
            if (finalUnionObjectPointList.get(0).getCoordX() == lineInObjectA.getEndX() && finalUnionObjectPointList.get(0).getCoordY() == lineInObjectA.getEndY()) {
                return;
            } else {
                finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));
                System.out.println("Point added: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());
            }
        }
    }

    private void findTheObjectMoreLeftAndTop(List<Line> objectAPointList, List<Line> objectBPointList) {
        //get most left-top point
        Point objectAFirstPoint = new Point(objectAPointList.get(0).getStartX(), objectAPointList.get(0).getStartY());
        Point objectBFirstPoint = new Point(objectBPointList.get(0).getStartX(), objectBPointList.get(0).getStartY());

        if (objectAFirstPoint.getCoordX() < objectBFirstPoint.getCoordX()) {
            //     System.out.println("objectAX < objectBX");
            //its OK, object A is the first one.
        } else if (objectAFirstPoint.getCoordX() > objectBFirstPoint.getCoordX()) {
            //its not OK, the object more to the left is second, change their order.
            //     System.out.println("objectAX > objectBX");
            switchObjectsOrder(objectAPointList, objectBPointList);
        } else if (objectAFirstPoint.getCoordY() < objectBFirstPoint.getCoordY()) {
            //      System.out.println("objectAY < objectAY");
            //its OK
        } else {
            //    System.out.println("objectAY > objectBY");
            switchObjectsOrder(objectAPointList, objectBPointList);
        }
    }

    private void switchObjectsOrder(List<Line> objectAPointList, List<Line> objectBPointList) {
        List<Line> objectSwitchPointList = new ArrayList<>();
        objectSwitchPointList.addAll(objectAPointList);

        objectAPointList.clear();
        objectAPointList.addAll(objectBPointList);

        objectBPointList.clear();
        objectBPointList.addAll(objectSwitchPointList);
    }

    private Point intersectionPointOfTwoLineSegment(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double x = ((y2 - y1) * (x4 - x3) * x1 + (x2 - x1) * (x4 - x3) * (y3 - y1) - (y4 - y3) * (x2 - x1) * x3)
                / ((y2 - y1) * (x4 - x3) - (x2 - x1) * (y4 - y3));
        double y = ((y1 - y2) * (y3 - y4) * (x1 - x3) + (x3 - x4) * (y1 - y2) * y3 - (y3 - y4) * (x1 - x2) * y1)
                / ((y1 - y2) * (x3 - x4) - (y3 - y4) * (x1 - x2));
        return new Point(x, y);
    }

    public List<Point> getFinalUnionObjectPointList() {
        return finalUnionObjectPointList;
    }

    private void removeFromPointListUnnecessaryPoints() {
        HashSet<Point> pathfindingPointsToRemove = new HashSet<>();
        for (int i = 0; i < finalUnionObjectPointList.size(); i++) {
            if ((finalUnionObjectPointList.get(pointIndex(i)).getCoordX() == finalUnionObjectPointList.get(pointIndex(i + 1)).getCoordX()) && (finalUnionObjectPointList.get(pointIndex(i)).getCoordX() == finalUnionObjectPointList.get(pointIndex(i + 2)).getCoordX())) {
                pathfindingPointsToRemove.add(finalUnionObjectPointList.get(i + 1));
            }
            if ((finalUnionObjectPointList.get(pointIndex(i)).getCoordY() == finalUnionObjectPointList.get(pointIndex(i + 1)).getCoordY()) && (finalUnionObjectPointList.get(pointIndex(i)).getCoordY() == finalUnionObjectPointList.get(pointIndex(i + 2)).getCoordY())) {
                pathfindingPointsToRemove.add(finalUnionObjectPointList.get(i + 1));
            }
        }

        finalUnionObjectPointList.removeAll(pathfindingPointsToRemove);
    }

    private int pointIndex(int index) {
        int modulo = finalUnionObjectPointList.size();
        return ((index % modulo) + modulo) % modulo;
    }

    private double distanceBetweenPoints(Point pointA, Point pointB) {
        return Math.sqrt(Math.pow((pointA.getCoordX() - pointB.getCoordX()), 2) + Math.pow((pointA.getCoordY() - pointB.getCoordY()), 2));
    }

    private Line copyLineByValue(Line line) {
        return new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }

    private boolean areLinesEqual(Line lineA, Line lineB) {
        return (lineA.getStartX() == lineB.getStartX() && lineA.getStartY() == lineB.getStartY() && lineA.getEndX() == lineB.getEndX() && lineA.getEndY() == lineB.getEndY());
    }

}
