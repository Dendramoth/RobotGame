/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

/**
 *
 * @author Dendra
 */
public class PathfindingPoint {
    private double coordX;
    private double coordY;
    private boolean lastPointInObject = false;
    private boolean pointWasReached = false;

    public PathfindingPoint(double coordX, double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public boolean isLastPointInObject() {
        return lastPointInObject;
    }

    public void setLastPointInObject(boolean lastPointInObject) {
        this.lastPointInObject = lastPointInObject;
    }

    public boolean isPointWasReached() {
        return pointWasReached;
    }

    public void setPointWasReached(boolean pointWasReached) {
        this.pointWasReached = pointWasReached;
    }
    
}
