/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public abstract class GameObjectWithDistanceDetection extends GamePrimitiveObject implements Comparable<GameObjectWithDistanceDetection>, ObjectWithCollision{

    private Point objectForComparison;

    public GameObjectWithDistanceDetection(Point possition, double width, double heigh, GraphicsContext graphicsContext) {
        super(possition, width, heigh, graphicsContext);
    }
    
    public Point getObjectForComparison() {
        return objectForComparison;
    }

    public void setObjectForComparison(Point objectForComparison) {
        this.objectForComparison = objectForComparison;
    }
    
    @Override
    public int compareTo(GameObjectWithDistanceDetection o) {
        double myDistance = Math.sqrt(Math.pow(this.getWorldPossition().getCoordX()- this.getObjectForComparison().getCoordX(), 2) + Math.pow(this.getWorldPossition().getCoordY()- this.getObjectForComparison().getCoordY(), 2));
        double otherDistance = Math.sqrt(Math.pow(o.getWorldPossition().getCoordX()- o.getObjectForComparison().getCoordX(), 2) + Math.pow(o.getWorldPossition().getCoordY()- o.getObjectForComparison().getCoordY(), 2));
        if (myDistance < otherDistance) {
            return -1;
        } else {
            return 1;
        }
    }
}
