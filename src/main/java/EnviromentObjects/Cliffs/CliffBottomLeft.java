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
public class CliffBottomLeft extends GameStaticObject{
    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffBottomLeft(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("cliffBottomLeftCorner"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(235 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(258 + possition.getCoordX(), 181 + possition.getCoordY()));
        pointList.add(new Point(380 + possition.getCoordX(), 285 + possition.getCoordY()));
        pointList.add(new Point(512 + possition.getCoordX(), 287 + possition.getCoordY()));
        pointList.add(new Point(512 + possition.getCoordX(), 167 + possition.getCoordY()));
        pointList.add(new Point(377 + possition.getCoordX(), 132 + possition.getCoordY()));
        pointList.add(new Point(347 + possition.getCoordX(), 0 + possition.getCoordY()));
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
    
    
    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(235 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(258 + worldPossition.getCoordX(), 181 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossition.getCoordX(), 285 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(512 + worldPossition.getCoordX(), 287 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(512 + worldPossition.getCoordX(), 167 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(377 + worldPossition.getCoordX(), 132 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(347 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
    }
    
    
}
