/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import GameObject.GameStaticObject;
import GameObject.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Line;

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
        //     System.out.println(objectALineList.get(0).getStartX() + " " + objectALineList.get(0).getStartY());
        //     System.out.println(objectBLineList.get(0).getStartX() + " " + objectBLineList.get(0).getStartY());
        findTheObjectMoreLeftAndTop(objectALineList, objectBLineList);

        //     System.out.println("after swap");
        //     System.out.println(objectALineList.get(0).getStartX() + " " + objectALineList.get(0).getStartY());
        //      System.out.println(objectBLineList.get(0).getStartX() + " " + objectBLineList.get(0).getStartY());
        //add First point into newly created union shape
        finalUnionObjectPointList.add(new Point(objectALineList.get(0).getStartX(), objectALineList.get(0).getStartY()));
        //      System.out.println("union object: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());
        createLineListOfTheUnionObject(objectALineList, objectBLineList);
    }

    private void createLineListOfTheUnionObject(List<Line> objectAPointList, List<Line> objectBPointList) {

        for (Line lineInObjectA : objectAPointList) {
            //      System.out.println("line in object A: " + lineInObjectA.getStartX() + " " + lineInObjectA.getStartY() + " , " + lineInObjectA.getEndX() + " " + lineInObjectA.getEndY());
            double x1 = lineInObjectA.getStartX();
            double x2 = lineInObjectA.getEndX();
            double y1 = lineInObjectA.getStartY();
            double y2 = lineInObjectA.getEndY();

            for (int i = 0; i < objectBPointList.size(); i++) {
                Line lineInObjectB = objectBPointList.get(i);
                //        System.out.println("line in object B: " + lineInObjectB.getStartX() + " " + lineInObjectB.getStartY() + " , " + lineInObjectB.getEndX() + " " + lineInObjectB.getEndY());

                double x3 = lineInObjectB.getStartX();
                double x4 = lineInObjectB.getEndX();
                double y3 = lineInObjectB.getStartY();
                double y4 = lineInObjectB.getEndY();

                if (lineInObjectA.intersects(lineInObjectB.getBoundsInParent())) {

                    //check if next is also coliding
                    Line futureLineInObjectB = objectBPointList.get((i + 1) % objectBPointList.size());
                    if (lineInObjectA.intersects(futureLineInObjectB.getBoundsInParent())) {
                        lineInObjectB = futureLineInObjectB;
                    }

                    //intersection point:
                    Point intersectionPoint = intersectionPointOfTwoLineSegment(x1, y1, x2, y2, x3, y3, x4, y4);
                    double xIntersection = intersectionPoint.getCoordX();
                    double yIntersection = intersectionPoint.getCoordY();

                    finalUnionObjectPointList.add(new Point(xIntersection, yIntersection));
                    //     System.out.println("union object1: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());

                    finalUnionObjectPointList.add(new Point(x2, y2));
                    //     System.out.println("union object2: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());

                    continueInObjectBWithAddingLinesAndPointForNewUnionShape(lineInObjectB, objectAPointList, objectBPointList);
                    return; //end
                }
            }

            finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));
            //     System.out.println("union object3: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());
        }
    }

    private void continueInObjectBWithAddingLinesAndPointForNewUnionShape(Line intersectingLineInObjectB, List<Line> objectALineList, List<Line> objectBLineList) {
        int indexOfContinuationLineInObjectB = 0;
        for (int i = 0; i < objectBLineList.size(); i++) {
            if (intersectingLineInObjectB == objectBLineList.get(i)) {
                indexOfContinuationLineInObjectB = (i + 1) % objectBLineList.size();
            }
        }

        finalUnionObjectPointList.add(new Point(objectBLineList.get(indexOfContinuationLineInObjectB).getStartX(), objectBLineList.get(indexOfContinuationLineInObjectB).getStartY()));
        //     System.out.println("union object vlozeny: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());

        for (int i = indexOfContinuationLineInObjectB; i < objectBLineList.size(); i++) {
            Line lineInObjectB = objectBLineList.get(i);

            for (Line lineInObjectA : objectALineList) {
                double x1 = lineInObjectB.getStartX();
                double x2 = lineInObjectB.getEndX();
                double y1 = lineInObjectB.getStartY();
                double y2 = lineInObjectB.getEndY();

                double x3 = lineInObjectA.getStartX();
                double x4 = lineInObjectA.getEndX();
                double y3 = lineInObjectA.getStartY();
                double y4 = lineInObjectA.getEndY();

                if (lineInObjectA.intersects(lineInObjectB.getBoundsInParent())) {
                    //intersection point:
                    Point intersectionPoint = intersectionPointOfTwoLineSegment(x1, y1, x2, y2, x3, y3, x4, y4);
                    double xi = intersectionPoint.getCoordX();
                    double yi = intersectionPoint.getCoordY();

                    finalUnionObjectPointList.add(new Point(xi, yi));
                    //     System.out.println("union object4: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());

                    finalUnionObjectPointList.add(new Point(x2, y2));
                    //     System.out.println("union object5: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());

                    if (finalUnionObjectPointList.get(0).getCoordX() == x2 && finalUnionObjectPointList.get(0).getCoordY() == y2) {
                        return; // end
                    }
                    finishInObjectAWithAddingLinesAndPoints(lineInObjectA, objectALineList, objectBLineList);
                    return; // end
                }
            }
            //line in Object B was not crossed by any line in object A
            finalUnionObjectPointList.add(new Point(lineInObjectB.getEndX(), lineInObjectB.getEndY()));
            //    System.out.println("union object6: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());

        }
    }

    private void finishInObjectAWithAddingLinesAndPoints(Line crossedlineInObjectA, List<Line> objectALineList, List<Line> objectBLineList) {
        int indexOfContinuationLineInObjectA = 0;
        for (int i = 0; i < objectALineList.size(); i++) {
            if (crossedlineInObjectA == objectALineList.get(i)) {
                indexOfContinuationLineInObjectA = (i + 1) % objectALineList.size();
            }
        }

        for (int i = indexOfContinuationLineInObjectA; i < objectALineList.size(); i++) {
            Line lineInObjectA = objectALineList.get(i);

            if (finalUnionObjectPointList.get(0).getCoordX() == lineInObjectA.getEndX() && finalUnionObjectPointList.get(0).getCoordY() == lineInObjectA.getEndY()) {
                return;
            } else {
                finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));
                //  System.out.println("union object7: " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordX() + " " + finalUnionObjectPointList.get(finalUnionObjectPointList.size() - 1).getCoordY());
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

}
