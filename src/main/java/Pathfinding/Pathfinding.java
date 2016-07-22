/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import GameObject.GameStaticObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class Pathfinding {

    private List<GameStaticObject> gameStaticObjectsList = new ArrayList<GameStaticObject>();
    private GraphicsContext graphicsContext;
    private List<Point> listOfPathPoints = new ArrayList<Point>();

    public Pathfinding(List<GameStaticObject> gameStaticObjectsList, GraphicsContext graphicsContext) {
        this.gameStaticObjectsList = gameStaticObjectsList;
        this.graphicsContext = graphicsContext;
    }

    public List<Point> createPath(GameStaticObject startGameObject, double targetPointX, double targetPointY) {

        boolean point2IsVisibleFromPoint1 = true;

        sortStaticObjectsBasedOnDistanceFromPlayer(startGameObject);

        Point currentPoint = new Point(startGameObject.getWorldPossition().getCoordX(), startGameObject.getWorldPossition().getCoordY());

        for (GameStaticObject gameStaticObject : gameStaticObjectsList) {
            Line line = new Line(currentPoint.getCoordX(), currentPoint.getCoordY(), targetPointX, targetPointY);

            Shape intersection = gameStaticObject.detectIntersection(line);
            if (!(intersection.getLayoutBounds().getHeight() <= 0 || intersection.getLayoutBounds().getWidth() <= 0)) {
                Point intersectionPoint = getIntersectionPointCoordinates(intersection, startGameObject, targetPointX, targetPointY);
                point2IsVisibleFromPoint1 = false;

                graphicsContext.setStroke(Color.RED);
                graphicsContext.strokeLine(currentPoint.getCoordX(), currentPoint.getCoordY(), intersectionPoint.getCoordX(), intersectionPoint.getCoordY());

                graphicsContext.setFill(Color.BLACK);
                graphicsContext.fillOval(intersectionPoint.getCoordX() - 5, intersectionPoint.getCoordY() - 5, 11, 11);
                FindPathAroundObject findPathAroundObject = new FindPathAroundObject(targetPointX, targetPointY, gameStaticObject, graphicsContext, listOfPathPoints);
                currentPoint = findPathAroundObject.findPathAroundObject(intersectionPoint);
                listOfPathPoints.add(currentPoint);
            }
        }

        listOfPathPoints.add(new Point(targetPointX, targetPointY));
        graphicsContext.setStroke(Color.RED);
        graphicsContext.strokeLine(currentPoint.getCoordX(), currentPoint.getCoordY(), targetPointX, targetPointY);

        if (point2IsVisibleFromPoint1) {
            graphicsContext.setStroke(Color.RED);
            graphicsContext.strokeLine(startGameObject.getWorldPossition().getCoordX(), startGameObject.getWorldPossition().getCoordY(), targetPointX, targetPointY);
        }

        return listOfPathPoints;
    }

    private Point getIntersectionPointCoordinates(Shape intersection, GameStaticObject startGameObject, double targetPointX, double targetPointY) {
        Point intersectionPoint = new Point(0, 0);

        if (startGameObject.getWorldPossition().getCoordX() < targetPointX) {
            intersectionPoint.setCoordX(intersection.getLayoutBounds().getMinX());
        } else {
            intersectionPoint.setCoordX(intersection.getLayoutBounds().getMaxX());
        }

        if (startGameObject.getWorldPossition().getCoordY() < targetPointY) {
            intersectionPoint.setCoordY(intersection.getLayoutBounds().getMinY());
        } else {
            intersectionPoint.setCoordY(intersection.getLayoutBounds().getMaxY());
        }

        return intersectionPoint;
    }

    private void sortStaticObjectsBasedOnDistanceFromPlayer(GameStaticObject startGameObject) {
        for (int i = 0; i < gameStaticObjectsList.size(); i++) {
            gameStaticObjectsList.get(i).setObjectForComparison(new GameObject.Point(startGameObject.getWorldPossition().getCoordX(),startGameObject.getWorldPossition().getCoordY()));
        }
        Collections.sort(gameStaticObjectsList);
    }

    public void paintAllPathPoints() {
        for (int i = 0; i < listOfPathPoints.size(); i++) {
            Point point = listOfPathPoints.get(i);
            graphicsContext.setFill(Color.AQUA);
            graphicsContext.fillOval(point.getCoordX() - 5, point.getCoordY() - 5, 10, 10);
        }
    }

}
