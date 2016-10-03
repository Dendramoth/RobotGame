/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

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
public class OuterFortressWallsWest extends GameStaticObject {

    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoSpaceShip = new ArrayList<>();

    public OuterFortressWallsWest(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("outerWallWest"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(606 + possition.getCoordX(), 1500 + possition.getCoordY()));
        pointList.add(new Point(33 + possition.getCoordX(), 1500 + possition.getCoordY()));
        pointList.add(new Point(33 + possition.getCoordX(), 925 + possition.getCoordY()));
        pointList.add(new Point(162 + possition.getCoordX(), 925 + possition.getCoordY()));
        pointList.add(new Point(162 + possition.getCoordX(), 605 + possition.getCoordY()));
        pointList.add(new Point(33 + possition.getCoordX(), 605 + possition.getCoordY()));
        pointList.add(new Point(33 + possition.getCoordX(), 30 + possition.getCoordY()));
        pointList.add(new Point(608 + possition.getCoordX(), 30 + possition.getCoordY()));
        pointList.add(new Point(608 + possition.getCoordX(), 223 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 223 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 91 + possition.getCoordY()));
        pointList.add(new Point(97 + possition.getCoordX(), 91 + possition.getCoordY()));
        pointList.add(new Point(97 + possition.getCoordX(), 541 + possition.getCoordY()));
        pointList.add(new Point(224 + possition.getCoordX(), 541 + possition.getCoordY()));
        pointList.add(new Point(224 + possition.getCoordX(), 986 + possition.getCoordY()));
        pointList.add(new Point(97 + possition.getCoordX(), 986 + possition.getCoordY()));
        pointList.add(new Point(97 + possition.getCoordX(), 1436 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 1436 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 1303 + possition.getCoordY()));
        pointList.add(new Point(606 + possition.getCoordX(), 1303 + possition.getCoordY()));

        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSpaceShip();
    }

    private void paintAllMinigunHitsIntoSpaceShip() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoSpaceShip.iterator();
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
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(606 + worldPossition.getCoordX(), 1500 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(33 + worldPossition.getCoordX(), 1500 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(33 + worldPossition.getCoordX(), 925 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(162 + worldPossition.getCoordX(), 925 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(162 + worldPossition.getCoordX(), 605 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(33 + worldPossition.getCoordX(), 605 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(33 + worldPossition.getCoordX(), 30 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(608 + worldPossition.getCoordX(), 30 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(608 + worldPossition.getCoordX(), 223 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(545 + worldPossition.getCoordX(), 223 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(545 + worldPossition.getCoordX(), 91 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(97 + worldPossition.getCoordX(), 91 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(97 + worldPossition.getCoordX(), 541 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(224 + worldPossition.getCoordX(), 541 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(224 + worldPossition.getCoordX(), 986 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(97 + worldPossition.getCoordX(), 986 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(97 + worldPossition.getCoordX(), 1436 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(545 + worldPossition.getCoordX(), 1436 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(545 + worldPossition.getCoordX(), 1303 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(606 + worldPossition.getCoordX(), 1303 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void doOnCollision(Point collisionPoint) {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allHitsIntoSpaceShip.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }

}
