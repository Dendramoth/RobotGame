/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Containers;

import EnviromentObjects.MinigunHitIntoStaticObject;
import GameObject.GameStaticObject;
import GameObject.Point;
import Projectiles.Projectile;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class SimpleBox extends GameStaticObject {

    private final List<MinigunHitIntoStaticObject> allHitsIntoSimpleBox = new ArrayList<>();

    public SimpleBox(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), getPoints64(possition), getPoints256(possition), possition, 128, 128, 0, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("box1"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(14 + possition.getCoordX(), 14 + possition.getCoordY()));
        pointList.add(new Point(118 + possition.getCoordX(), 14 + possition.getCoordY()));
        pointList.add(new Point(118 + possition.getCoordX(), 118 + possition.getCoordY()));
        pointList.add(new Point(14 + possition.getCoordX(), 118 + possition.getCoordY()));
        return pointList;
    }
    
    private static List<Point> getPoints64(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(-18 + possition.getCoordX(), -18 + possition.getCoordY()));
        pointList.add(new Point(150 + possition.getCoordX(), -18 + possition.getCoordY()));
        pointList.add(new Point(150 + possition.getCoordX(), 150 + possition.getCoordY()));
        pointList.add(new Point(-18 + possition.getCoordX(), 150 + possition.getCoordY()));
        return pointList;
    }
    
    private static List<Point> getPoints256(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(-50 + possition.getCoordX(), -50 + possition.getCoordY()));
        pointList.add(new Point(182 + possition.getCoordX(), -50 + possition.getCoordY()));
        pointList.add(new Point(182 + possition.getCoordX(), 182 + possition.getCoordY()));
        pointList.add(new Point(-50 + possition.getCoordX(), 182 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSimpleBox();
    }

    private void paintAllMinigunHitsIntoSimpleBox() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoSimpleBox.iterator();
        while (iterator.hasNext()) {
            MinigunHitIntoStaticObject minigunHitIntoStaticObject = iterator.next();
            minigunHitIntoStaticObject.paint();
            if (minigunHitIntoStaticObject.getNumberOfFramesBeingDisplayed() < 1) {
                iterator.remove();
            }
        }
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
    public void doOnCollision(Point collisionPoint) {
        // nothing
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allHitsIntoSimpleBox.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }

}
