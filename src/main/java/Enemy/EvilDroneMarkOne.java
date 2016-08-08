/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.GameStaticObject;
import GameObject.Point;
import Pathfinding.PathfindingPoint;
import MapGridTable.GridTable;
import Pathfinding.Pathfinding;
import com.mycompany.robotgame.GameMainInfrastructure;
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
public class EvilDroneMarkOne extends Enemy {

    private int blinkCounter = 0;
    private int explodingTimer = 0;
    private double angleOfDrone = 0;
    private double lastAngleToAvoidCollision = 0;
    private boolean collisionDetectedInLastTest = false;

    private double x1 = 0;
    private double x2 = 0;
    private double y1 = 0;
    private double y2 = 0;

    private List<PathfindingPoint> pathPoints = new ArrayList<PathfindingPoint>();
    private final List<Point> pointsForDetection = new ArrayList<>();

    public EvilDroneMarkOne(Point possitionInWorld, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow) {
        super(possitionInWorld, width, heigh, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);

        enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
        hitPoints = 30;
        damagedStateTreshold = 25;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        if (pathPoints.size() < 1) {
            findPathToPlayer(new Point(playerPossitionX, playerPossitionY));
        }

        double deltaX = pathPoints.get(0).getCoordX() - worldPossition.getCoordX();
        double deltaY = pathPoints.get(0).getCoordY() - worldPossition.getCoordY();
        angleOfDrone = calculateAngleBetweenPlayerAndDrone(deltaX, deltaY);

   //     worldPossition.setCoordX(worldPossition.getCoordX() - Math.cos(Math.toRadians(angleOfDrone + 90)) * movementSpeed);
   //     worldPossition.setCoordY(worldPossition.getCoordY() - Math.sin(Math.toRadians(angleOfDrone + 90)) * movementSpeed);

        removePointThatWasReached();
    }

    private void removePointThatWasReached() {
        PathfindingPoint point = pathPoints.get(0);
        if ((point.getCoordX() > worldPossition.getCoordX() - 1.5 && point.getCoordX() < worldPossition.getCoordX() + 1.5)
                && (point.getCoordY() > worldPossition.getCoordY() - 1.5 && point.getCoordY() < worldPossition.getCoordY() + 1.5)) { //point was reached
            pathPoints.remove(0);
        }
    }

    private double calculateAngleBetweenPlayerAndDrone(double x, double y) {
        double angle;
        if (y == 0 && x == 0) {
            angle = 0;
        } else if (y > 0) {
            angle = Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        } else {
            angle = -Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        }
        angle = (angle + 360) % 360;
        return angle;
    }

    @Override
    public boolean detectCollision(Shape shape, Point point) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX(), 64 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 64 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintAllExplosionsEnemy() {
        Iterator<Explosion> iterator = allExplosionsOnEnemy.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            explosion.paint(worldPossition, graphicsContext);
            if (explosion.getNumberOfFramesBeingDisplayed() < 1) {
                iterator.remove();
            }
        }
    }

    @Override
    protected boolean paintDyingEnemyAnimation(GraphicsContext enemyGraphicsContext) {
        if (explodingTimer < 4) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death1");
        } else if (explodingTimer <= 5) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death2");
        } else if (explodingTimer > 5 && explodingTimer <= 10) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death3");
        } else if (explodingTimer > 10 && explodingTimer <= 15) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death4");
        } else if (explodingTimer > 15 && explodingTimer <= 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death5");
        } else {
            return false;
        }

        enemyGraphicsContext.drawImage(enemyImage, worldPossition.getCoordX(), worldPossition.getCoordY());
        explodingTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {

    }

    public double getAngleOfDrone() {
        return angleOfDrone;
    }

    public void setAngleOfDrone(double angleOfDrone) {
        this.angleOfDrone = angleOfDrone;
    }

    public double getLastAngleToAvoidCollision() {
        return lastAngleToAvoidCollision;
    }

    public void setLastAngleToAvoidCollision(double lastAngleToAvoidCollision) {
        this.lastAngleToAvoidCollision = lastAngleToAvoidCollision;
    }

    public boolean isCollisionDetectedInLastTest() {
        return collisionDetectedInLastTest;
    }

    public void setCollisionDetectedInLastTest(boolean collisionDetectedInLastTest) {
        this.collisionDetectedInLastTest = collisionDetectedInLastTest;
    }

    @Override
    public void paintGameObject() {
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

        blinkCounter++;
        if (hitPoints >= damagedStateTreshold) {
            if (blinkCounter <= 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
            }
            if (blinkCounter > 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle2");
            }
            if (blinkCounter == 30) {
                blinkCounter = 0;
            }
        }
        if (hitPoints < damagedStateTreshold) {
            if (blinkCounter <= 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1Damaged");
            }
            if (blinkCounter > 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle2Damaged");
            }
            if (blinkCounter == 30) {
                blinkCounter = 0;
            }
        }

        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);

        paintAllExplosionsEnemy();
    }

    private void findPathToPlayer(Point playerWorldPosition) {
        List<GameStaticObject> visibleStaticObjects = new ArrayList<GameStaticObject>(gridTable.getAllVisibleObjects());
        Pathfinding pathfinding = new Pathfinding(visibleStaticObjects, graphicsContext);
        pathPoints = pathfinding.createPath(this, playerWorldPosition.getCoordX(), playerWorldPosition.getCoordY());
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun() {
        allExplosionsOnEnemy.add(new Explosion(monitorWindow));
    }

}
