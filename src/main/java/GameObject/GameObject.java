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
    protected Point possition;
    private double width;
    private double heigh;

    public GameObject(Point possition, double width, double heigh) {
        this.possition = possition;
        this.width = width;
        this.heigh = heigh;

        prepareObjectCorners();
    }
    
    public abstract void paintGameObject();

    private void prepareObjectCorners(){
        Point topRight = new Point(possition.getCoordX() + width, possition.getCoordY());
        Point bottomLeft = new Point(possition.getCoordX(), possition.getCoordY() + heigh);
        Point bottomRight= new Point(possition.getCoordX() + width, possition.getCoordY() + heigh);
        
        cornerPointsOfObject = new CornerPointsOfObject(possition, topRight, bottomLeft, bottomRight);
    }

    public CornerPointsOfObject getCornerPointsOfObject() {
        return cornerPointsOfObject;
    }
    
    
}
