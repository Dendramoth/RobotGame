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
public class SimpleBox extends GameStaticObject  {
    
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoSimpleBox = new ArrayList<>();

    public SimpleBox(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, 128, 128, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("box1"));
    }
    
    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(14 + possition.getCoordX(), 118 + possition.getCoordY()));
        pointList.add(new Point(118 + possition.getCoordX(), 118 + possition.getCoordY()));
        pointList.add(new Point(118 + possition.getCoordX(), 14 + possition.getCoordY()));
        pointList.add(new Point(14 + possition.getCoordX(), 14 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(14 + worldPossition.getCoordX(), 118 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(118 + worldPossition.getCoordX(), 118 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(118 + worldPossition.getCoordX(), 14 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(14 + worldPossition.getCoordX(), 14 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintAllMinigunHitsIntoSimpleBox();
    }
    
    private void paintAllMinigunHitsIntoSimpleBox() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoSimpleBox.iterator();
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
        allHitsIntoSimpleBox.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }
    
}
