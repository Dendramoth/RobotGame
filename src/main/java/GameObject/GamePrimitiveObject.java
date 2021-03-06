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
 * @author styma01
 */
public abstract class GamePrimitiveObject {
    protected CornerPointsOfObject cornerPointsOfObject;
    protected Point worldPossition;
    protected final GraphicsContext graphicsContext;
    protected double width;
    protected double heigh;
    protected MonitorWindow monitorWindow;
    /**
     * 0 - bottom
     * 1 - above bottom under player
     * 2 - player robot level
     * 3 - above player
     */
    protected int layerPosition = 0;
    

    public GamePrimitiveObject(Point possition, double width, double heigh, int layerPosition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        this.worldPossition = possition;
        this.width = width;
        this.heigh = heigh;
        this.layerPosition = layerPosition;
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;
        prepareObjectCorners();
    }
    
    public abstract void paintGameObject();
    
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

    public void setWorldPossition(Point worldPossition) {
        this.worldPossition = worldPossition;
    }

    public int getLayerPosition() {
        return layerPosition;
    }

    
}
