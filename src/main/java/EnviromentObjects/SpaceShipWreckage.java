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
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class SpaceShipWreckage extends GameStaticObject {

    private final GraphicsContext graphicsContext;
    private final Image spaceShipWreckageImage = LoadAllResources.getMapOfAllImages().get("spaceShipWreckage");
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoSpaceShip = new ArrayList<>();

    public SpaceShipWreckage(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow);
        this.graphicsContext = graphicsContext;
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(77 + possition.getCoordX(), 275 + possition.getCoordY()));
        pointList.add(new Point(77 + possition.getCoordX(), 346 + possition.getCoordY()));
        pointList.add(new Point(163 + possition.getCoordX(), 433 + possition.getCoordY()));
        pointList.add(new Point(285 + possition.getCoordX(), 404 + possition.getCoordY()));
        pointList.add(new Point(400 + possition.getCoordX(), 210 + possition.getCoordY()));
        pointList.add(new Point(380 + possition.getCoordX(), 188 + possition.getCoordY()));
        pointList.add(new Point(292 + possition.getCoordX(), 274 + possition.getCoordY()));
        pointList.add(new Point(236 + possition.getCoordX(), 218 + possition.getCoordY()));
        pointList.add(new Point(320 + possition.getCoordX(), 128 + possition.getCoordY()));
        pointList.add(new Point(300 + possition.getCoordX(), 105 + possition.getCoordY()));
        pointList.add(new Point(112 + possition.getCoordX(), 220 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(spaceShipWreckageImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
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
    public boolean detectCollision(Shape shape, Point positionOfColidingObject) {
        Point intersectionPoint = new Point(0, 0);

        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        if (positionOfColidingObject.getCoordX() + 32 < worldPossition.getCoordX() + 256) {
            intersectionPoint.setCoordX(intersect.getLayoutBounds().getMinX() - 16);
        } else {
            intersectionPoint.setCoordX(intersect.getLayoutBounds().getMaxX() - 16);
        }

        if (positionOfColidingObject.getCoordY() + 32 < worldPossition.getCoordY() + 256) {
            intersectionPoint.setCoordY(intersect.getLayoutBounds().getMinY() - 16);
        } else {
            intersectionPoint.setCoordY(intersect.getLayoutBounds().getMaxY() - 16);
        }

        allHitsIntoSpaceShip.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));

        return true;
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(77 + worldPossition.getCoordX(), 275 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(77 + worldPossition.getCoordX(), 346 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(163 + worldPossition.getCoordX(), 433 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(285 + worldPossition.getCoordX(), 404 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(400 + worldPossition.getCoordX(), 210 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossition.getCoordX(), 188 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(292 + worldPossition.getCoordX(), 274 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(236 + worldPossition.getCoordX(), 218 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(320 + worldPossition.getCoordX(), 128 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(300 + worldPossition.getCoordX(), 105 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(112 + worldPossition.getCoordX(), 220 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun() {
    }
}
