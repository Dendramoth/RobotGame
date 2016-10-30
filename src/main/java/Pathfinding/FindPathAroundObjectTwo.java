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
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class FindPathAroundObjectTwo {

    private final Point targetPosition;
    private final List<Line> objectToFindPathAroundLineList;
    private final List<PathfindingPoint> leftPathPoints = new ArrayList<>();
    private final List<PathfindingPoint> rightPathPoints = new ArrayList<>();

    private double comulativeDistanceLeft = 0;
    private double comulativeDistanceRight = 0;
    private int currentLeftLineIndex = 0;
    private int currentRightLineIndex = 0;

    public FindPathAroundObjectTwo(int enemySize, Point targetPosition, GameStaticObject gameObjectToFindPathAround) {
        this.targetPosition = targetPosition;
        objectToFindPathAroundLineList = gameObjectToFindPathAround.getPolygonLineList(enemySize);
    }

    public List<PathfindingPoint> findPathAroundObject(PathfindingPoint intersectionWithObject) {
        int indexOfCrossedLine = findTheIndexOfCrossedLine(intersectionWithObject);
        currentLeftLineIndex = indexOfCrossedLine;
        currentRightLineIndex = indexOfCrossedLine;

        leftPathPoints.add(intersectionWithObject);
        rightPathPoints.add(intersectionWithObject);

        for (int i = 0; i < objectToFindPathAroundLineList.size(); i++) {
            if (comulativeDistanceLeft <= comulativeDistanceRight) {
                if (detectTargetVisibilityForLeftWiseDetection()) {
                    return leftPathPoints;
                }
            } else if (detectTargetVisibilityForRightWiseDetection()) {
                return rightPathPoints;
            }
        }
        System.out.println("Path was not found.");
        return leftPathPoints;
    }

    private boolean detectTargetVisibilityForLeftWiseDetection() {
        Line currentLine = getLineFromObjectWithIndex(currentLeftLineIndex);
        Line nextLine = getLineFromObjectWithIndex(currentLeftLineIndex + 1);
        PathfindingPoint pointToTest = new PathfindingPoint(currentLine.getEndX(), currentLine.getEndY());
        boolean targetIsVisible = testIfTargetIsVisibleFromPoint(pointToTest, currentLine, nextLine);
        comulativeDistanceLeft = comulativeDistanceLeft + getDistanceBetweenPoints(pointToTest, leftPathPoints.get(leftPathPoints.size() - 1));
        currentLeftLineIndex++;

        leftPathPoints.add(pointToTest);
        return targetIsVisible;
    }

    private boolean detectTargetVisibilityForRightWiseDetection() {
        Line currentLine = getLineFromObjectWithIndex(currentRightLineIndex);
        Line previousLine = getLineFromObjectWithIndex(currentRightLineIndex - 1);
        PathfindingPoint pointToTest = new PathfindingPoint(currentLine.getStartX(), currentLine.getStartY());
        boolean targetIsVisible = testIfTargetIsVisibleFromPoint(pointToTest, previousLine, currentLine);
        comulativeDistanceRight = comulativeDistanceRight + getDistanceBetweenPoints(pointToTest, rightPathPoints.get(rightPathPoints.size() - 1));
        currentRightLineIndex--;

        rightPathPoints.add(pointToTest);
        return targetIsVisible;
    }

    private double getDistanceBetweenPoints(PathfindingPoint pointA, PathfindingPoint PointB) {
        return Math.abs(pointA.getCoordX() - PointB.getCoordX()) + Math.abs(pointA.getCoordY() - PointB.getCoordY());
    }

    private boolean testIfTargetIsVisibleFromPoint(PathfindingPoint pointToTest, Line lineToIgnoreOne, Line lineToIgnoreTwo) {
        Line detectionLine = new Line(pointToTest.getCoordX(), pointToTest.getCoordY(), targetPosition.getCoordX(), targetPosition.getCoordY());
        for (Line lineInObject : objectToFindPathAroundLineList) {
            if ((lineInObject != lineToIgnoreOne) && (lineInObject != lineToIgnoreTwo)) {
                //if (detectionLine.intersects(lineInObject.getBoundsInParent())) {
                if (((Path) Shape.intersect(lineInObject, detectionLine)).getElements().size() > 0) {
                    //lines have intersection
                    return false;
                }
            }
        }
        return true;
    }

    private Line getLineFromObjectWithIndex(int lineIndex) {
        int modulo = objectToFindPathAroundLineList.size();
        return objectToFindPathAroundLineList.get(((lineIndex % modulo) + modulo) % modulo);
    }

    private int findTheIndexOfCrossedLine(PathfindingPoint intersectionWithObject) {
        for (int i = 0; i < objectToFindPathAroundLineList.size(); i++) {
            if (objectToFindPathAroundLineList.get(i).contains(intersectionWithObject.getCoordX(), intersectionWithObject.getCoordY())) {
                return i;
            }
        }
        return 0;
    }
}
