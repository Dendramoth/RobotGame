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

    private List<Line> finalUnionObjectLineList = new ArrayList<>();
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
        createLineListOfTheUnionObject(objectALineList, objectBLineList);
    }

    private void createLineListOfTheUnionObject(List<Line> objectAPointList, List<Line> objectBPointList) {

        for (Line lineInObjectA : objectAPointList) {
            for (Line lineInObjectB : objectBPointList) {
                double x1 = lineInObjectA.getStartX();
                double x2 = lineInObjectA.getEndX();
                double y1 = lineInObjectA.getStartY();
                double y2 = lineInObjectA.getEndY();

                double x3 = lineInObjectB.getStartX();
                double x4 = lineInObjectB.getEndX();
                double y3 = lineInObjectB.getStartY();
                double y4 = lineInObjectB.getEndY();

                double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
                if (d != 0) {
                    //intersection point:
                    double xIntersection = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                    double yIntersection = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

                    Line newLine = new Line(x1, y1, xIntersection, yIntersection); //from old object up to intersection
                    finalUnionObjectLineList.add(newLine);
                    finalUnionObjectPointList.add(new Point(xIntersection, yIntersection));

                    newLine = new Line(xIntersection, yIntersection, x4, y4); // from intersection to the end of the line in the second object
                    finalUnionObjectLineList.add(newLine);
                    finalUnionObjectPointList.add(new Point(x4, y4));

                    continueInObjectBWithAddingLinesAndPointForNewUnionShape(lineInObjectB, objectAPointList, objectBPointList);
                    return; //end
                }
            }

            finalUnionObjectLineList.add(lineInObjectA);
            finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));

        }
    }

    private void continueInObjectBWithAddingLinesAndPointForNewUnionShape(Line intersectingLineInObjectB, List<Line> objectALineList, List<Line> objectBLineList) {
        int indexOfContinuationLineInObjectB = 0;
        for (int i = 0; i < objectBLineList.size(); i++) {
            if (intersectingLineInObjectB == objectBLineList.get(i)) {
                indexOfContinuationLineInObjectB = (i + 1) % objectBLineList.size();
            }
        }

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

                double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
                if (d != 0) {
                    //intersection point:
                    double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                    double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

                    Line newLine = new Line(x1, y1, xi, yi); //from old object up to intersection
                    finalUnionObjectLineList.add(newLine);
                    finalUnionObjectPointList.add(new Point(xi, yi));

                    newLine = new Line(xi, yi, x4, y4); // from intersection to the end of the line in the second object
                    finalUnionObjectLineList.add(newLine);
                    finalUnionObjectPointList.add(new Point(x4, y4));

                    if (finalUnionObjectPointList.get(0).getCoordX() == x4 && finalUnionObjectPointList.get(0).getCoordY() == y4){
                        return; // end
                    }
                    finishInObjectAWithAddingLinesAndPoints(lineInObjectA, objectALineList, objectBLineList);
                    return; // end
                }
            }
            //line in Object B was not crossed by any line in object A
            finalUnionObjectLineList.add(lineInObjectB);
            finalUnionObjectPointList.add(new Point(lineInObjectB.getEndX(), lineInObjectB.getEndY()));

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
                finalUnionObjectLineList.add(lineInObjectA);
                finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));
            }
        }

    }

    private void findTheObjectMoreLeftAndTop(List<Line> objectAPointList, List<Line> objectBPointList) {
        //get most left-top point
        Point objectAFirstPoint = new Point(objectAPointList.get(0).getStartX(), objectAPointList.get(0).getStartY());
        Point objectBFirstPoint = new Point(objectBPointList.get(0).getStartX(), objectBPointList.get(0).getStartY());

        if (objectAFirstPoint.getCoordX() < objectBFirstPoint.getCoordY()) {
            //its OK, object A is the first one.
        } else if (objectAFirstPoint.getCoordX() > objectBFirstPoint.getCoordY()) {
            //its not OK, the object more to the left is second, change their order.
            switchObjectsOrder(objectAPointList, objectBPointList);
        } else if (objectAFirstPoint.getCoordY() < objectBFirstPoint.getCoordY()) {
            //its OK
        } else {
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

}
