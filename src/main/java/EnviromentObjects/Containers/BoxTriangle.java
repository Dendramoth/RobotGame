/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Containers;

import EnviromentObjects.MinigunHitIntoStaticObject;
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
public class BoxTriangle extends GameStaticObject  {
    
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoTriangleBox = new ArrayList<>();

    public BoxTriangle(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, 256, 256, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("box2"));
    }
    
    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(30 + possition.getCoordX(), 238 + possition.getCoordY()));
        pointList.add(new Point(233 + possition.getCoordX(), 238 + possition.getCoordY()));
        pointList.add(new Point(233 + possition.getCoordX(), 10 + possition.getCoordY()));
        pointList.add(new Point(30 + possition.getCoordX(), 10 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(30 + worldPossition.getCoordX(), 238 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(233 + worldPossition.getCoordX(), 238 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(233 + worldPossition.getCoordX(), 10 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(30 + worldPossition.getCoordX(), 10 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSimpleBox();
    }
    
    private void paintAllMinigunHitsIntoSimpleBox() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoTriangleBox.iterator();
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
    public void doOnCollision() {
        // nothing
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allHitsIntoTriangleBox.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }
    
}