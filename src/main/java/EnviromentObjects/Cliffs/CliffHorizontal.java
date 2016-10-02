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
public class CliffHorizontal extends GameStaticObject {

    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffHorizontal(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("cliffHorizontal"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0 + possition.getCoordX(), 93 + possition.getCoordY()));
        pointList.add(new Point(1024 + possition.getCoordX(), 93 + possition.getCoordY()));
        pointList.add(new Point(1024 + possition.getCoordX(), 100 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 100 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        //    createPolygonForDetection(playerWorldPosition.getCoordX(), playerWorldPosition.getCoordY());
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
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return new ResultOfDetectColisionWithProjectile(false, new Point(0, 0));
    }

    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 93 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(1024 + worldPossition.getCoordX(), 93 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(1024 + worldPossition.getCoordX(), 100 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 100 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }
}
