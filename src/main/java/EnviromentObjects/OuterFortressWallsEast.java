/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GameStaticObject;
import GameObject.Point;
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
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("outerWallEast"));
    }
    
    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(10 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(10 + possition.getCoordX(), 10 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 10 + possition.getCoordY()));
      /*  pointList.add(new Point(380 + possition.getCoordX(), 188 + possition.getCoordY()));
        pointList.add(new Point(292 + possition.getCoordX(), 274 + possition.getCoordY()));
        pointList.add(new Point(236 + possition.getCoordX(), 218 + possition.getCoordY()));
        pointList.add(new Point(320 + possition.getCoordX(), 128 + possition.getCoordY()));
        pointList.add(new Point(300 + possition.getCoordX(), 105 + possition.getCoordY()));
        pointList.add(new Point(112 + possition.getCoordX(), 220 + possition.getCoordY()));*/
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
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(10 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(10 + worldPossition.getCoordX(), 10 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 10 + worldPossition.getCoordY()));
    /*    pointsForDetection.add(new Point(400 + worldPossition.getCoordX(), 210 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossition.getCoordX(), 188 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(292 + worldPossition.getCoordX(), 274 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(236 + worldPossition.getCoordX(), 218 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(320 + worldPossition.getCoordX(), 128 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(300 + worldPossition.getCoordX(), 105 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(112 + worldPossition.getCoordX(), 220 + worldPossition.getCoordY()));*/
        createPolygon(pointsForDetection);
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allHitsIntoSpaceShip.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }
}
