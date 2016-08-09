/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerRobot;

import GameObject.GameObjectWithDistanceDetection;
import GameObject.GameStaticObject;
import GameObject.Point;
import MapGridTable.GridTable;
import com.mycompany.robotgame.GameMainInfrastructure;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class PlayerRobot extends GameObjectWithDistanceDetection {

    private final AudioClip idleRobotSound = LoadAllResources.getMapOfAllSounds().get("idleRobotSound");
    private final AudioClip movingRobotSound = LoadAllResources.getMapOfAllSounds().get("movingRobotSound");
    private final GraphicsContext robotGraphicsContext;
    private final PlayerRobotTurret playerRobotTurret;
    private final PlayerRobotShield playerRobotShield;

    private double robotPositionChangeX = 0;
    private double robotPositionChangeY = 0;
    private Image robotImage;
    private Image robotImageMoving;
    private Image shieldImage = LoadAllResources.getMapOfAllImages().get("energyShield1");
    private int hitPoints = 100;
    private double facingAngle = 0.0;
    private int moveTracks = 0;
    private int shieldImageRotationCounter = 0;
    private int previousShieldImage = 2;
    private int currentShieldImage = 1;
    private List<Point> pointsForDetection = new ArrayList<Point>();
    private GridTable gridTable;

    public PlayerRobot(GraphicsContext robotGraphicsContext, Point worldPossition, GridTable gridTable, MonitorWindow monitorWindow) {
        super(worldPossition, 64, 64, robotGraphicsContext, monitorWindow);
        this.robotGraphicsContext = robotGraphicsContext;
        this.gridTable = gridTable;

        robotImage = LoadAllResources.getMapOfAllImages().get("basePassive");
        robotImageMoving = LoadAllResources.getMapOfAllImages().get("baseMoving");
        playerRobotTurret = new PlayerRobotTurret(robotGraphicsContext);
        playerRobotShield = new PlayerRobotShield(100);
    }

    public void playRobotIdleSound() {
        if (!idleRobotSound.isPlaying()) {
            idleRobotSound.play();
            if (movingRobotSound.isPlaying()) {
                movingRobotSound.stop();
            }
        }
    }

    public void playRobotMovingSound() {
        if (!movingRobotSound.isPlaying()) {
            movingRobotSound.setVolume(0.4);
            movingRobotSound.play();
            if (idleRobotSound.isPlaying()) {
                idleRobotSound.stop();
            }
        }
    }

    public void moveRobotForward() {
        robotPositionChangeX = Math.cos(Math.toRadians(facingAngle - 90)) * 4;
        robotPositionChangeY = Math.sin(Math.toRadians(facingAngle - 90)) * 4;

        worldPossition.setCoordX(worldPossition.getCoordX() + robotPositionChangeX);
        worldPossition.setCoordY(worldPossition.getCoordY() + robotPositionChangeY);

        if (detectCollisionWithStaticGameObjects()) {
            worldPossition.setCoordX(worldPossition.getCoordX() - robotPositionChangeX);
            worldPossition.setCoordY(worldPossition.getCoordY() - robotPositionChangeY);
        } else {
            monitorWindow.moveMonitorWindow(robotPositionChangeX, robotPositionChangeY);
        }
    }

    public void moveRobotBackward() {
        robotPositionChangeX = Math.cos(Math.toRadians(facingAngle + 90)) * 2.5;
        robotPositionChangeY = Math.sin(Math.toRadians(facingAngle + 90)) * 2.5;

        worldPossition.setCoordX(worldPossition.getCoordX() + robotPositionChangeX);
        worldPossition.setCoordY(worldPossition.getCoordY() + robotPositionChangeY);

        if (detectCollisionWithStaticGameObjects()) {
            worldPossition.setCoordX(worldPossition.getCoordX() - robotPositionChangeX);
            worldPossition.setCoordY(worldPossition.getCoordY() - robotPositionChangeY);
        } else {
            monitorWindow.moveMonitorWindow(robotPositionChangeX, robotPositionChangeY);
        }
    }

    public void moveRobotLeft() {
        facingAngle = facingAngle - 2;
    }

    public void moveRobotRight() {
        facingAngle = facingAngle + 2;
    }

    public void shootFromRobotTurret(boolean shoot) {
        playerRobotTurret.shootTurret(shoot, worldPossition);
    }

    public void moveTracks() {
        moveTracks++;
        if (moveTracks >= 10) {
            Image changeImage = robotImageMoving;
            robotImageMoving = robotImage;
            robotImage = changeImage;

            moveTracks = 0;
        }
    }

    public Polygon getPlayerRobotPolygon() {
        return createPolygonForColisionDetection();
    }

    private Polygon createPolygonForColisionDetection() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
            worldPossition.getCoordX() + 0.0 - robotImage.getWidth() / 2, worldPossition.getCoordY() + 0.0 - robotImage.getHeight() / 2,
            worldPossition.getCoordX() + 0.0 - robotImage.getWidth() / 2, worldPossition.getCoordY() + 64.0 - robotImage.getHeight() / 2,
            worldPossition.getCoordX() + 64.0 - robotImage.getWidth() / 2, worldPossition.getCoordY() + 64.0 - robotImage.getHeight() / 2,
            worldPossition.getCoordX() + 64.0 - robotImage.getWidth() / 2, worldPossition.getCoordY() + 0.0 - robotImage.getHeight() / 2});
        polygon.setRotate(facingAngle);
        return polygon;
    }

    private boolean detectCollisionWithStaticGameObjects() {
        Polygon playerRobotPolygon = createPolygonForColisionDetection();
        HashSet<GameStaticObject> visibleStaticObjects = gridTable.getAllVisibleObjects();
        for (GameStaticObject gameStaticObject : visibleStaticObjects) {
            if (gameStaticObject.detectCollision(playerRobotPolygon)) {  //v testu mam pouze jeden static object a ten je tridy SpaceShipWreckage
                return true;
            }
        }
        return false;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void removeHitPoints(int hpToRemove) {
        if (!playerRobotShield.isActive()) {
            hitPoints = hitPoints - hpToRemove;
        } else {
            playerRobotShield.removeHitPointsFromShield(hpToRemove);
        }
    }

    public void addHitPoints(int hpToAdd) {
        hitPoints = hitPoints + hpToAdd;
    }

    public ArrayList<ShotsFromMinigun> getAllShotsFromMinigun() {
        return playerRobotTurret.getAllShotsFromMinigun();
    }

    @Override
    public void paintGameObject() {
        robotGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        Point monitorPosition = new Point(worldPossition.getCoordX() - monitorWindow.getPositionInWorld().getCoordX(), worldPossition.getCoordY() - monitorWindow.getPositionInWorld().getCoordY());

        robotGraphicsContext.save();
        robotGraphicsContext.translate(monitorPosition.getCoordX(), monitorPosition.getCoordY());
        robotGraphicsContext.rotate(facingAngle);
        robotGraphicsContext.drawImage(robotImage, -robotImage.getWidth() / 2, -robotImage.getHeight() / 2);
        robotGraphicsContext.restore();
        playerRobotTurret.paintTurret(monitorPosition);

        if (playerRobotShield.isActive()) {
            robotGraphicsContext.save();
            robotGraphicsContext.translate(monitorPosition.getCoordX(), monitorPosition.getCoordY());
            robotGraphicsContext.rotate(facingAngle);
            paintShield();
            robotGraphicsContext.restore();
        }

        robotGraphicsContext.setFill(Color.GREEN);
        for (int i = 0; i < pointsForDetection.size(); i++) {
            robotGraphicsContext.fillOval(pointsForDetection.get(i).getCoordX() - 5, pointsForDetection.get(i).getCoordY() - 5, 10, 10);
        }
    }

    private void paintShield() {
        shieldImageRotationCounter++;

        if (shieldImageRotationCounter > 10) {
            shieldImageRotationCounter = 0;
            if (currentShieldImage == 1) {
                currentShieldImage = 2;
                previousShieldImage = 1;
                shieldImage = LoadAllResources.getMapOfAllImages().get("energyShield2");
            } else if (currentShieldImage == 3) {
                currentShieldImage = 2;
                previousShieldImage = 3;
                shieldImage = LoadAllResources.getMapOfAllImages().get("energyShield2");
            } else if (currentShieldImage == 2 && previousShieldImage == 1) {
                currentShieldImage = 3;
                shieldImage = LoadAllResources.getMapOfAllImages().get("energyShield3");
            } else if (currentShieldImage == 2 && previousShieldImage == 3) {
                currentShieldImage = 1;
                shieldImage = LoadAllResources.getMapOfAllImages().get("energyShield1");
            }
        }

        robotGraphicsContext.drawImage(shieldImage, -shieldImage.getWidth() / 2, -shieldImage.getHeight() / 2);
    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }
    
    @Override
    public boolean detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return false;
    }

    public void setShieldActive(boolean shieldActive) {
        if (playerRobotShield.getShieldHitPoints() > 20) {
            playerRobotShield.setActive(shieldActive);
        }

        if (playerRobotShield.isActive()) {
            playerRobotShield.removeHitPointsFromShield(0.15);
        } else {
            playerRobotShield.addHitPointsToShield(0.1);
        }
    }

    public PlayerRobotShield getPlayerRobotShield() {
        return playerRobotShield;
    }

    @Override
    public Point getWorldPossition() {
        return super.getWorldPossition(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
    }
    
    
}
