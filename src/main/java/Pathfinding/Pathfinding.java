/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import GameObject.GameObjectWithDistanceDetection;
import GameObject.GameStaticObject;
import GameObject.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class Pathfinding {

    private List<GameStaticObject> gameStaticObjectsList = new ArrayList<GameStaticObject>();
    private List<PathfindingPoint> listOfPathPoints = new ArrayList<PathfindingPoint>();
    private boolean pathWasGeneratedBefore = false;
    private boolean finalPointIsDirectlyVisible = true;
    int sizeOfEnemy;

    public Pathfinding(int sizeOfEnemy, List<GameStaticObject> gameStaticObjectsList, GraphicsContext graphicsContext, List<PathfindingPoint> listOfPathPoints) {
        this.gameStaticObjectsList = gameStaticObjectsList;
        this.listOfPathPoints = listOfPathPoints;
        this.sizeOfEnemy = sizeOfEnemy;
        pathWasGeneratedBefore = listOfPathPoints != null && !listOfPathPoints.isEmpty();
    }

    public List<PathfindingPoint> createPath(GameObjectWithDistanceDetection startGameObject, double targetPointX, double targetPointY) {
        sortStaticObjectsBasedOnDistanceFromPlayer(startGameObject);
        PathfindingPoint currentPoint = new PathfindingPoint(startGameObject.getWorldPossition().getCoordX(), startGameObject.getWorldPossition().getCoordY());

        for (GameStaticObject gameStaticObject : gameStaticObjectsList) {
            System.out.println(gameStaticObject.getClass());
            Line line = new Line(currentPoint.getCoordX(), currentPoint.getCoordY(), targetPointX, targetPointY);

            Shape intersection = gameStaticObject.detectIntersection(sizeOfEnemy, line);
            if (!(intersection.getLayoutBounds().getHeight() <= 0 || intersection.getLayoutBounds().getWidth() <= 0)) {
                finalPointIsDirectlyVisible = false;
                if (pathWasGeneratedBefore == true) {
                    return listOfPathPoints;
                }

                PathfindingPoint intersectionPoint = getIntersectionPointCoordinates(intersection, startGameObject, targetPointX, targetPointY);
                FindPathAroundObjectTwo findPathAroundObject = new FindPathAroundObjectTwo(sizeOfEnemy, new Point(targetPointX, targetPointY), gameStaticObject);
                List<PathfindingPoint> listOfPointToGoAroundObject = findPathAroundObject.findPathAroundObject(intersectionPoint);
                listOfPathPoints.addAll(listOfPointToGoAroundObject);
                currentPoint = listOfPathPoints.get(listOfPathPoints.size() - 1);
            } 
        }

        if (finalPointIsDirectlyVisible) {
            listOfPathPoints.clear();
        }
        listOfPathPoints.add(new PathfindingPoint(targetPointX, targetPointY));

        return listOfPathPoints;
    }

    private PathfindingPoint getIntersectionPointCoordinates(Shape intersection, GameObjectWithDistanceDetection startGameObject, double targetPointX, double targetPointY) {
        PathfindingPoint intersectionPoint = new PathfindingPoint(0, 0);

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

    private void sortStaticObjectsBasedOnDistanceFromPlayer(GameObjectWithDistanceDetection startGameObject) {
        for (int i = 0; i < gameStaticObjectsList.size(); i++) {
            gameStaticObjectsList.get(i).setObjectForComparison(new GameObject.Point(startGameObject.getWorldPossition().getCoordX(), startGameObject.getWorldPossition().getCoordY()));
        }
        Collections.sort(gameStaticObjectsList);
    }
}
