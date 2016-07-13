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
public abstract class GameObject implements Comparable<GameObject>{

    private CornerPointsOfObject cornerPointsOfObject;
    protected Point worldPossition;
    private Point objectForComparison;
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

    public Point getWorldPossition() {
        return worldPossition;
    }

    public Point getObjectForComparison() {
        return objectForComparison;
    }

    public void setObjectForComparison(Point objectForComparison) {
        this.objectForComparison = objectForComparison;
    }
    
    @Override
    public int compareTo(GameObject o) {
        double myDistance = Math.sqrt(Math.pow(this.getWorldPossition().getCoordX()- this.getObjectForComparison().getCoordX(), 2) + Math.pow(this.getWorldPossition().getCoordY()- this.getObjectForComparison().getCoordY(), 2));
        double otherDistance = Math.sqrt(Math.pow(o.getWorldPossition().getCoordX()- o.getObjectForComparison().getCoordX(), 2) + Math.pow(o.getWorldPossition().getCoordY()- o.getObjectForComparison().getCoordY(), 2));
        if (myDistance < otherDistance) {
            return -1;
        } else {
            return 1;
        }
    }
}
