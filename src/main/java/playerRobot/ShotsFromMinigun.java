/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerRobot;

import GameObject.Point;
import javafx.scene.shape.Line;

/**
 *
 * @author Dendra
 */
public class ShotsFromMinigun {

    private final Point startPositionOfShot;
    private final Point endPositionOfShot;

    public ShotsFromMinigun(Point startPoint, double angleOfFiredShot) {
        this.startPositionOfShot = startPoint;
        double endPositionX = startPositionOfShot.getCoordX() - Math.cos(Math.toRadians(angleOfFiredShot + 90)) * 200;
        double endPositionY = startPositionOfShot.getCoordY() - Math.sin(Math.toRadians(angleOfFiredShot + 90)) * 200;
        this.endPositionOfShot = new Point(endPositionX, endPositionY);
    }

    public Line getLineForDetection() {
        Line line = new Line();
        line.setStartX(startPositionOfShot.getCoordX() );
        line.setStartY(startPositionOfShot.getCoordY() );
        line.setEndX(endPositionOfShot.getCoordX());
        line.setEndY(endPositionOfShot.getCoordY());
        return line;
    }

    public Point getStartPositionOfShot() {
        return startPositionOfShot;
    }

    public Point getEndPositionOfShot() {
        return endPositionOfShot;
    }
    
    
}
