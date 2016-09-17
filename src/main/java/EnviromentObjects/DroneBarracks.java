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
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Shape;
import playerRobot.PlayerRobot;

/**
 *
 * @author Dendra
 */
public class DroneBarracks extends GameStaticObject {

    private final AudioClip dispatchTheDronesSound = LoadAllResources.getMapOfAllSounds().get("dispatchSound");
    private final List<Point> pointsForDetection = new ArrayList<>();
    private int imageCounter = 0;
    private boolean dronesReadyForRelease = true;
    private boolean dispatchingDrones = false;
    private int dispatchingDronesCounter = 0;
    private PlayerRobot playerRobot;

    public DroneBarracks(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow, PlayerRobot playerRobot) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("droneBarracks1"));
        this.playerRobot = playerRobot;
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
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks1");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks2");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks3");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks4");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks5");
        } else if (imageCounter > 10 && imageCounter <= 20) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks6");
        } else {
            imageCounter = 0;
        }
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());

        if (dronesReadyForRelease) {
            dispatchingDrones = checkIfDronesShouldBeDispatched();
            dronesReadyForRelease = !dispatchingDrones;
        }

        if (dispatchingDrones) {
            dispatchingDronesCounter++;
            if (dispatchingDronesCounter == 1){
                dispatchTheDronesSound.play();
            }
            if (dispatchingDronesCounter % 120 == 0){
                System.out.println("dispatching drone");
            }
            if (dispatchingDronesCounter > 500){
                dispatchingDrones = false;
            }
        }
    }

    private boolean checkIfDronesShouldBeDispatched() {
        double deltaX = Math.abs((playerRobot.getWorldPossition().getCoordX() - width / 2) - worldPossition.getCoordX());
        double deltaY = Math.abs((playerRobot.getWorldPossition().getCoordY() - heigh / 2) - worldPossition.getCoordY());
        System.out.println(deltaX + deltaY);
        if (deltaX + deltaY < 1000) {
            return true;
        }
        return false;
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
