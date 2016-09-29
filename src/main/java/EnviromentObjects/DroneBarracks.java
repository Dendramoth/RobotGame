/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import Enemy.EnemyContainer;
import Enemy.EvilDroneMarkTwo;
import GameObject.GameStaticObject;
import GameObject.Point;
import MapGridTable.GridTable;
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
    private EnemyContainer enemyContainer;
    private GraphicsContext enemyGraphicsContext;
    private GridTable gridTable;

    public DroneBarracks(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow, PlayerRobot playerRobot, EnemyContainer enemyContainer, GraphicsContext enemyGraphicsContext, GridTable gridTable) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("droneBarracks1"));
        this.playerRobot = playerRobot;
        this.enemyContainer = enemyContainer;
        this.enemyGraphicsContext = enemyGraphicsContext;
        this.gridTable = gridTable;
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(25 + possition.getCoordX(), 294 + possition.getCoordY()));
        pointList.add(new Point(75 + possition.getCoordX(), 294 + possition.getCoordY()));
        pointList.add(new Point(75 + possition.getCoordX(), 225 + possition.getCoordY()));
        pointList.add(new Point(177 + possition.getCoordX(), 225 + possition.getCoordY()));
        pointList.add(new Point(177 + possition.getCoordX(), 294 + possition.getCoordY()));
        pointList.add(new Point(231 + possition.getCoordX(), 294 + possition.getCoordY()));
        pointList.add(new Point(231 + possition.getCoordX(), 27 + possition.getCoordY()));
        pointList.add(new Point(25 + possition.getCoordX(), 27 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(25 + worldPossition.getCoordX(), 294 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(75 + worldPossition.getCoordX(), 294 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(75 + worldPossition.getCoordX(), 225 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(177 + worldPossition.getCoordX(), 225 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(177 + worldPossition.getCoordX(), 294 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(231 + worldPossition.getCoordX(), 294 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(231 + worldPossition.getCoordX(), 27 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(25 + worldPossition.getCoordX(), 27 + worldPossition.getCoordY()));
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
        } else if (imageCounter > 20 && imageCounter <= 30) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks3");
        } else if (imageCounter > 30 && imageCounter <= 40) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks4");
        } else if (imageCounter > 40 && imageCounter <= 50) {
            staticObjectImage = LoadAllResources.getMapOfAllImages().get("droneBarracks5");
        } else if (imageCounter > 50 && imageCounter <= 60) {
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
            if (dispatchingDronesCounter == 1) {
                dispatchTheDronesSound.play();
            }
            if (dispatchingDronesCounter % 120 == 0) {
                enemyContainer.addEnemy(new EvilDroneMarkTwo(new Point(worldPossition.getCoordX() + width / 2, worldPossition.getCoordY() + heigh - 115), 64, 64, 2, 15, 20, enemyGraphicsContext, gridTable, monitorWindow));
            }
            if (dispatchingDronesCounter > 750) {
                dispatchingDrones = false;
            }
        }
    }

    private boolean checkIfDronesShouldBeDispatched() {
        double deltaX = Math.abs((playerRobot.getWorldPossition().getCoordX() - width / 2) - worldPossition.getCoordX());
        double deltaY = Math.abs((playerRobot.getWorldPossition().getCoordY() - heigh / 2) - worldPossition.getCoordY());
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
