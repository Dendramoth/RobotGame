/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import GameObject.GameStaticObject;
import GameObject.Point;
import Projectiles.Projectile;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class PathFindingStaticObject extends GameStaticObject {

    private List<Line> finalUnionObjectLineList = new ArrayList<>();
    private List<Point> finalUnionObjectPointList = new ArrayList<>();

    public PathFindingStaticObject(int enemySize, GameStaticObject gameStaticObjectA, GameStaticObject gameStaticObjectB, Point possition, double width, double heigh, int objectLayer, GraphicsContext graphicsContext, MonitorWindow monitorWindow, Image staticObjectImage) {
        super(null, null, possition, width, heigh, objectLayer, graphicsContext, monitorWindow, staticObjectImage);
        createUnionOfTwoInputObjects(enemySize, gameStaticObjectA, gameStaticObjectB);
    }

    private void createUnionOfTwoInputObjects(int enemySize, GameStaticObject gameStaticObjectA, GameStaticObject gameStaticObjectB) {
        List<Line> objectAPointList = new ArrayList<>();
        List<Line> objectBPointList = new ArrayList<>();

        objectAPointList = gameStaticObjectA.getPolygonLineList(enemySize);
        objectBPointList = gameStaticObjectB.getPolygonLineList(enemySize);

        findTheObjectMoreLeftAndTop(objectAPointList, objectBPointList);

        finalUnionObjectPointList.add(new Point(objectAPointList.get(0).getStartX(), objectAPointList.get(0).getStartY()));
        createLineListOfTheUnionObject(objectAPointList, objectBPointList);
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
                    double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                    double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

                    Line newLine = new Line(x1, y1, xi, yi); //from old object up to intersection
                    finalUnionObjectLineList.add(newLine);
                    finalUnionObjectPointList.add(new Point(xi, yi));

                    newLine = new Line(xi, yi, x4, y4); // from intersection to the end of the line in the second object
                    finalUnionObjectLineList.add(newLine);
                    finalUnionObjectPointList.add(new Point(x4, y4));

                    continueInObjectWithAddingLinesAndPoints(lineInObjectB, objectAPointList, objectBPointList);
                    return; //end
                }
            }

            finalUnionObjectLineList.add(lineInObjectA);
            finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));

        }
    }

    private void continueInObjectWithAddingLinesAndPoints(Line intesectingLineInObjectB, List<Line> objectAPointList, List<Line> objectBPointList) {

        for (Line lineInObjectB : objectBPointList) {
            boolean startingLineFound = false;
            
            if (startingLineFound) {
                for (Line lineInObjectA : objectAPointList) {
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
                        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

                        Line newLine = new Line(x1, y1, xi, yi); //from old object up to intersection
                        finalUnionObjectLineList.add(newLine);
                        finalUnionObjectPointList.add(new Point(xi, yi));

                        newLine = new Line(xi, yi, x4, y4); // from intersection to the end of the line in the second object
                        finalUnionObjectLineList.add(newLine);
                        finalUnionObjectPointList.add(new Point(x4, y4));

                        finishInObjectAWithAddingLinesAndPoints(lineInObjectA, objectAPointList, objectBPointList);
                        return; // end
                    }
                }

                finalUnionObjectLineList.add(lineInObjectB);
                finalUnionObjectPointList.add(new Point(lineInObjectB.getEndX(), lineInObjectB.getEndY()));

            }

            if (lineInObjectB == intesectingLineInObjectB) {
                startingLineFound = true;
            }
        }
    }

    private void finishInObjectAWithAddingLinesAndPoints(Line crossedlineInObjectA, List<Line> objectAPointList, List<Line> objectBPointList) {
        for (Line lineInObjectA : objectAPointList) {
            boolean startingLineFound = false;
            if (startingLineFound) {
                if (finalUnionObjectPointList.get(0).getCoordX() == lineInObjectA.getEndX() && finalUnionObjectPointList.get(0).getCoordY() == lineInObjectA.getEndY()) {
                    return;
                } else {
                    finalUnionObjectLineList.add(lineInObjectA);
                    finalUnionObjectPointList.add(new Point(lineInObjectA.getEndX(), lineInObjectA.getEndY()));
                }
            }

            if (lineInObjectA == crossedlineInObjectA) {
                startingLineFound = true;
            }
        }
    }

    private void findTheObjectMoreLeftAndTop(List<Line> objectAPointList, List<Line> objectBPointList) {
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
        List<Line> objectSwitchPointList = new ArrayList<>(objectAPointList);
        objectAPointList = new ArrayList<>(objectBPointList);
        objectBPointList = new ArrayList<>(objectSwitchPointList);

    }

    @Override
    public void paintGameObject() {
    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public void doOnCollision(Point collisionPoint) {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }

}
