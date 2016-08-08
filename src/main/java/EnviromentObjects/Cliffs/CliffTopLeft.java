/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Cliffs;

import GameObject.GameStaticObject;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class CliffTopLeft extends GameStaticObject{
    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffTopLeft(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("cliffTopLeftCorner"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(344 + possition.getCoordX(), 512 + possition.getCoordY()));
        pointList.add(new Point(382 + possition.getCoordX(), 379 + possition.getCoordY()));
        pointList.add(new Point(380 + possition.getCoordX(), 285 + possition.getCoordY()));
        pointList.add(new Point(512 + possition.getCoordX(), 348 + possition.getCoordY()));
        pointList.add(new Point(512 + possition.getCoordX(), 232 + possition.getCoordY()));
        pointList.add(new Point(312 + possition.getCoordX(), 278 + possition.getCoordY()));
        pointList.add(new Point(239 + possition.getCoordX(), 512 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
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
    public boolean detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return false;
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(344 + worldPossition.getCoordX(), 512 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(382 + worldPossition.getCoordX(), 379 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossition.getCoordX(), 285 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(512 + worldPossition.getCoordX(), 348 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(512 + worldPossition.getCoordX(), 232 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(312 + worldPossition.getCoordX(), 278 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(239 + worldPossition.getCoordX(), 512 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }
    
    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun() {
    }
}
