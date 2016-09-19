/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Cliffs;

import GameObject.GameStaticObject;
import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
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
public class CliffTopRight extends GameStaticObject{
    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffTopRight(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("cliffTopRightCorner"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(170 + possition.getCoordX(), 512 + possition.getCoordY()));
        pointList.add(new Point(135 + possition.getCoordX(), 382 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 345 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 236 + possition.getCoordY()));
        pointList.add(new Point(207 + possition.getCoordX(), 278 + possition.getCoordY()));
        pointList.add(new Point(275 + possition.getCoordX(), 512 + possition.getCoordY()));
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
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return new ResultOfDetectColisionWithProjectile(false, new Point(0,0));
    }

    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(170 + worldPossition.getCoordX(), 512 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(135 + worldPossition.getCoordX(), 382 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 345 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 236 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(207 + worldPossition.getCoordX(), 278 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(275 + worldPossition.getCoordX(), 512 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }
    
    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
    }
}
