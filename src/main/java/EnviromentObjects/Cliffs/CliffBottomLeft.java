/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Cliffs;

import GameObject.GameStaticObject;
import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
import Projectiles.Projectile;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class CliffBottomLeft extends GameStaticObject{
    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffBottomLeft(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), getPoints(possition), possition, width, heigh, 0, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("cliffBottomLeftCorner"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(112 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(128 + possition.getCoordX(), 117 + possition.getCoordY()));
        pointList.add(new Point(255 + possition.getCoordX(), 142 + possition.getCoordY()));
        pointList.add(new Point(255 + possition.getCoordX(), 170 + possition.getCoordY()));
        pointList.add(new Point(90 + possition.getCoordX(), 170 + possition.getCoordY()));
        pointList.add(new Point(90 + possition.getCoordX(), 0 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }

    @Override
    public boolean detectCollision(Shape shape) {
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return new ResultOfDetectColisionWithProjectile(false, new Point(0,0));
    }

    @Override
    public void doOnCollision(Point collisionPoint) {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
    }
    
    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }
    
    
}
