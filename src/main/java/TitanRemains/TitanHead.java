/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TitanRemains;

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
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class TitanHead extends GameStaticObject {
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoTitanHead = new ArrayList<>();

    public TitanHead(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, 512, 512, 0, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("TitanFace"));
    }
    
    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(60 + possition.getCoordX(), 235 + possition.getCoordY()));
        pointList.add(new Point(309 + possition.getCoordX(), 416 + possition.getCoordY()));
        pointList.add(new Point(434 + possition.getCoordX(), 356 + possition.getCoordY()));
        pointList.add(new Point(420 + possition.getCoordX(), 236 + possition.getCoordY()));
        pointList.add(new Point(183 + possition.getCoordX(), 97 + possition.getCoordY()));
        pointList.add(new Point(95 + possition.getCoordX(), 138 + possition.getCoordY()));
        return pointList;
    }
    
    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(60 + worldPossition.getCoordX(), 235 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(309 + worldPossition.getCoordX(), 416 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(434 + worldPossition.getCoordX(), 356 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(420 + worldPossition.getCoordX(), 236 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(183 + worldPossition.getCoordX(), 97 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(95 + worldPossition.getCoordX(), 138 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSimpleBox();
    }

    private void paintAllMinigunHitsIntoSimpleBox() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoTitanHead.iterator();
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
        allHitsIntoTitanHead.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }
}
