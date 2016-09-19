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
public class ResultOfDetectColisionWithProjectile {
    private boolean coliding = false;
    private Point intersectionPoint = new Point(0,0);

    public ResultOfDetectColisionWithProjectile(boolean coliding, Point intersectionPoint) {
        this.coliding = coliding;
        this.intersectionPoint = intersectionPoint;
    }

    public boolean isColiding() {
        return coliding;
    }

    public Point getIntersectionPoint() {
        return intersectionPoint;
    }
    
}
