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
 * @author styma01
 */
public class InnerFortressWallNorth extends GameStaticObject {
    
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoWall = new ArrayList<>();

    public InnerFortressWallNorth(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("northWall"));
    }
    
    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(16 + possition.getCoordX(), 29 + possition.getCoordY()));
        pointList.add(new Point(473 + possition.getCoordX(), 29 + possition.getCoordY()));
        pointList.add(new Point(473 + possition.getCoordX(), 86 + possition.getCoordY()));
        pointList.add(new Point(16 + possition.getCoordX(), 86 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSpaceShip();
    }

    private void paintAllMinigunHitsIntoSpaceShip() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoWall.iterator();
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
        pointsForDetection.add(new Point(16 + worldPossition.getCoordX(), 29 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(473 + worldPossition.getCoordX(), 29 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(473 + worldPossition.getCoordX(), 86 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(16 + worldPossition.getCoordX(), 86 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allHitsIntoWall.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }
    
    
    
}
