/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

/**
 *
 * @author Dendra
 */
public abstract class GameObject {

    private CornerPointsOfObject cornerPointsOfObject;
    protected Point worldPossition;
    private double width;
    private double heigh;

    public GameObject(Point possition, double width, double heigh) {
        this.worldPossition = possition;
        this.width = width;
        this.heigh = heigh;

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
    
}
