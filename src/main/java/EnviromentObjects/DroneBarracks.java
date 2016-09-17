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
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class DroneBarracks extends GameStaticObject {

    private final List<Point> pointsForDetection = new ArrayList<>();
    private int imageCounter = 0;
    private boolean dronesReadyForRelease = true;

    public DroneBarracks(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("droneBarracks1"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(256 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(256 + possition.getCoordX(), 384 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 384 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(256 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(256 + worldPossition.getCoordX(), 384 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 384 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        imageCounter++;
        if (imageCounter <= 10) {
            staticObjectImage= LoadAllResources.getMapOfAllImages().get("droneBarracks1");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage= LoadAllResources.getMapOfAllImages().get("droneBarracks2");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage= LoadAllResources.getMapOfAllImages().get("droneBarracks3");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage= LoadAllResources.getMapOfAllImages().get("droneBarracks4");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage= LoadAllResources.getMapOfAllImages().get("droneBarracks5");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage= LoadAllResources.getMapOfAllImages().get("droneBarracks6");
        } else {
            imageCounter = 0;
        }
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
    public void doOnCollision() {
        //nothing
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        //nothing
    }

}
