/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public abstract class GameObjectWithDistanceDetection extends GamePrimitiveObject implements Comparable<GameObjectWithDistanceDetection>, ObjectWithCollision{

    private Point leftTopCorner;
    private Point leftBottomCorner;
    private Point rightTopCorner;
    private Point rightBottomCorner;
    private double distanceFromComparedObject;

    public GameObjectWithDistanceDetection(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(possition, width, heigh, graphicsContext, monitorWindow);
        leftTopCorner = possition;
        leftBottomCorner = new Point(possition.getCoordX(), possition.getCoordY() + heigh);
        rightTopCorner = new Point(possition.getCoordX() + width, possition.getCoordY());
        rightBottomCorner = new Point(possition.getCoordX() + width, possition.getCoordY() + heigh);
        distanceFromComparedObject = 10000;
    }
    
    public void setObjectForComparison(Point objectPointForComparison) {
        double distanceTopLeft = Math.abs(leftTopCorner.getCoordX() - objectPointForComparison.getCoordX()) + Math.abs(leftTopCorner.getCoordY() - objectPointForComparison.getCoordY());
        double distanceBottomLeft = Math.abs(leftBottomCorner.getCoordX() - objectPointForComparison.getCoordX()) + Math.abs(leftBottomCorner.getCoordY() - objectPointForComparison.getCoordY());
        double distanceTopRight = Math.abs(rightTopCorner.getCoordX() - objectPointForComparison.getCoordX()) + Math.abs(rightTopCorner.getCoordY() - objectPointForComparison.getCoordY());
        double distanceRightBottom = Math.abs(rightBottomCorner.getCoordX() - objectPointForComparison.getCoordX()) + Math.abs(rightBottomCorner.getCoordY() - objectPointForComparison.getCoordY());
        
        if (distanceTopLeft < distanceBottomLeft && distanceTopLeft < distanceTopRight && distanceTopLeft < distanceRightBottom){
            distanceFromComparedObject = distanceTopLeft;
        }else if (distanceBottomLeft < distanceTopLeft && distanceBottomLeft < distanceTopRight && distanceBottomLeft < distanceRightBottom){
            distanceFromComparedObject = distanceBottomLeft;
        }else if (distanceTopRight < distanceTopLeft && distanceTopRight < distanceBottomLeft && distanceTopRight < distanceRightBottom){
            distanceFromComparedObject = distanceTopRight;
        }else{
            distanceFromComparedObject = distanceRightBottom;
        }
    }
    
    @Override
    public int compareTo(GameObjectWithDistanceDetection o) {
    //    double myDistance = Math.sqrt(Math.pow(this.getWorldPossition().getCoordX()- this.getObjectForComparison().getCoordX(), 2) + Math.pow(this.getWorldPossition().getCoordY()- this.getObjectForComparison().getCoordY(), 2));
    //    double otherDistance = Math.sqrt(Math.pow(o.getWorldPossition().getCoordX()- o.getObjectForComparison().getCoordX(), 2) + Math.pow(o.getWorldPossition().getCoordY()- o.getObjectForComparison().getCoordY(), 2));
        if (distanceFromComparedObject < o.getDistanceFromComparedObject()) {
            return -1;
        } else {
            return 1;
        }
    }

    public Point getLeftTopCorner() {
        return leftTopCorner;
    }

    public Point getLeftBottomCorner() {
        return leftBottomCorner;
    }

    public Point getRightTopCorner() {
        return rightTopCorner;
    }

    public Point getRightBottomCorner() {
        return rightBottomCorner;
    }

    public double getDistanceFromComparedObject() {
        return distanceFromComparedObject;
    }

    
}
