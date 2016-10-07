/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Skeletons;

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
public class DinosaurusSkeleton extends GameStaticObject {
    
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoSkeleton = new ArrayList<>();

    public DinosaurusSkeleton(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, 512, 512, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("skeletonDinosaurus"));
    }
    
    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(450 + possition.getCoordX(), 493 + possition.getCoordY()));
        pointList.add(new Point(410 + possition.getCoordX(), 25 + possition.getCoordY()));
        pointList.add(new Point(57 + possition.getCoordX(), 154 + possition.getCoordY()));
        pointList.add(new Point(244 + possition.getCoordX(), 209 + possition.getCoordY()));
        pointList.add(new Point(85 + possition.getCoordX(), 504 + possition.getCoordY()));
        pointList.add(new Point(331 + possition.getCoordX(), 431 + possition.getCoordY()));
        return pointList;
    }
    
    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(450 + worldPossition.getCoordX(), 493 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(410 + worldPossition.getCoordX(), 25 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(57 + worldPossition.getCoordX(), 154 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(244 + worldPossition.getCoordX(), 209 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(85 + worldPossition.getCoordX(), 504 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(331 + worldPossition.getCoordX(), 431 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSimpleBox();
    }
    
    private void paintAllMinigunHitsIntoSimpleBox() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoSkeleton.iterator();
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
        allHitsIntoSkeleton.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }
    
    
    
}
