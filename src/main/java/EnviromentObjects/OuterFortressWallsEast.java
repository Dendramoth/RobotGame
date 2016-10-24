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
public class OuterFortressWallsEast extends GameStaticObject {

    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoSpaceShip = new ArrayList<>();

    public OuterFortressWallsEast(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), getPoints(possition), possition, width, heigh, 0, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("outerWallEast"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(33 + possition.getCoordX(), 1498 + possition.getCoordY()));
        pointList.add(new Point(608 + possition.getCoordX(), 1498 + possition.getCoordY()));
        pointList.add(new Point(608 + possition.getCoordX(), 923 + possition.getCoordY()));
        pointList.add(new Point(480 + possition.getCoordX(), 923 + possition.getCoordY()));
        pointList.add(new Point(480 + possition.getCoordX(), 602 + possition.getCoordY()));
        pointList.add(new Point(608 + possition.getCoordX(), 602 + possition.getCoordY()));
        pointList.add(new Point(608 + possition.getCoordX(), 27 + possition.getCoordY()));
        pointList.add(new Point(32 + possition.getCoordX(), 27 + possition.getCoordY()));
        pointList.add(new Point(32 + possition.getCoordX(), 227 + possition.getCoordY()));
        pointList.add(new Point(96 + possition.getCoordX(), 227 + possition.getCoordY()));
        pointList.add(new Point(96 + possition.getCoordX(), 89 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 89 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 538 + possition.getCoordY()));
        pointList.add(new Point(416 + possition.getCoordX(), 538 + possition.getCoordY()));
        pointList.add(new Point(416 + possition.getCoordX(), 985 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 985 + possition.getCoordY()));
        pointList.add(new Point(545 + possition.getCoordX(), 1435 + possition.getCoordY()));
        pointList.add(new Point(95 + possition.getCoordX(), 1435 + possition.getCoordY()));
        pointList.add(new Point(95 + possition.getCoordX(), 1300 + possition.getCoordY()));
        pointList.add(new Point(33 + possition.getCoordX(), 1300 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoWall();
    }

    private void paintAllMinigunHitsIntoWall() {
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
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
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
