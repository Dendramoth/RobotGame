/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import GameObject.Point;

/**
 *
 * @author Dendra
 */
public class MonitorWindow {
    Point positionInWorld;

    public MonitorWindow(Point positionInWorld) {
        this.positionInWorld = positionInWorld;
    }

    public Point getPositionInWorld() {
        return positionInWorld;
    }

    public void setPositionInWorld(Point positionInWorld) {
        this.positionInWorld = positionInWorld;
    }
    
    public void moveMonitorWindow(double x, double y) {
        positionInWorld = new Point(positionInWorld.getCoordX() + x, positionInWorld.getCoordY() + y);
    }
    
}
