/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author styma01
 */
public abstract class GamePrimitiveObject {
    protected CornerPointsOfObject cornerPointsOfObject;
    protected Point worldPossition;
    protected final GraphicsContext graphicsContext;
    protected double width;
    protected double heigh;

    public GamePrimitiveObject(Point possition, double width, double heigh, GraphicsContext graphicsContext) {
        this.worldPossition = possition;
        this.width = width;
        this.heigh = heigh;
        this.graphicsContext = graphicsContext;
        prepareObjectCorners();
    }
    
    public abstract void paintGameObject();
    public abstract void paintStaticGameObject(Point playerWorldPossition, Point playerScreenPosition);
    
    private void prepareObjectCorners(){
        Point topRight = new Point(worldPossition.getCoordX() + width, worldPossition.getCoordY());
        Point bottomLeft = new Point(worldPossition.getCoordX(), worldPossition.getCoordY() + heigh);
        Point bottomRight= new Point(worldPossition.getCoordX() + width, worldPossition.getCoordY() + heigh);
        
        cornerPointsOfObject = new CornerPointsOfObject(worldPossition, topRight, bottomLeft, bottomRight);
    }

    public CornerPointsOfObject getCornerPointsOfObject() {
        return cornerPointsOfObject;
    }

    public Point getWorldPossition() {
        return worldPossition;
    }
}
