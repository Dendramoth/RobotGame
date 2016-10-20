/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Rocks;

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
public class RockLargeOne extends GameStaticObject {
    
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoRock = new ArrayList<>();

    public RockLargeOne(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, 256, 256, 0, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("rockLargeOne"));
    }
    
    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(20 + possition.getCoordX(), 243 + possition.getCoordY()));
        pointList.add(new Point(237 + possition.getCoordX(), 243 + possition.getCoordY()));
        pointList.add(new Point(237 + possition.getCoordX(), 20 + possition.getCoordY()));
        pointList.add(new Point(20 + possition.getCoordX(), 20 + possition.getCoordY()));
        return pointList;
    }
    
    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(20 + worldPossition.getCoordX(), 243 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(237 + worldPossition.getCoordX(), 243 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(237 + worldPossition.getCoordX(), 20 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(20 + worldPossition.getCoordX(), 20 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSimpleBox();
    }
    
    private void paintAllMinigunHitsIntoSimpleBox() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoRock.iterator();
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
        createPolygonForDetection();
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
        allHitsIntoRock.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }
    
    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }
}
