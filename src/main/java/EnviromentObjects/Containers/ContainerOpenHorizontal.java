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
public class ContainerOpenHorizontal extends GameStaticObject {

    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoBox = new ArrayList<>();

    public ContainerOpenHorizontal(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, 256, 128, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("container2"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(10 + possition.getCoordX(), 118 + possition.getCoordY()));
        pointList.add(new Point(245 + possition.getCoordX(), 118 + possition.getCoordY()));
        pointList.add(new Point(245 + possition.getCoordX(), 13 + possition.getCoordY()));
        pointList.add(new Point(10 + possition.getCoordX(), 13 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(10 + worldPossition.getCoordX(), 118 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(245 + worldPossition.getCoordX(), 118 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(245 + worldPossition.getCoordX(), 13 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(10 + worldPossition.getCoordX(), 13 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSimpleBox();
    }

    private void paintAllMinigunHitsIntoSimpleBox() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoBox.iterator();
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
        allHitsIntoBox.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }
}
