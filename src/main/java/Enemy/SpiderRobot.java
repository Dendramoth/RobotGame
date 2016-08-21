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
public class SpiderRobot extends Enemy {

    private int movementAnimationFrame = 1;
    private int explodingTimer = 0;
    private double angleOfSpider = 0;
    private double lastAngleToAvoidCollision = 0;
    private boolean collisionDetectedInLastTest = false;

    private List<PathfindingPoint> pathPoints = new ArrayList<PathfindingPoint>();
    private final List<Point> pointsForDetection = new ArrayList<>();

    public SpiderRobot(Point possitionInWorld, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow) {
        super(possitionInWorld, width, heigh, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);
        enemyImage = LoadAllResources.getMapOfAllImages().get("walker_idle");
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        if (pathPoints.size() < 1) {
            findPathToPlayer(new Point(playerPossitionX, playerPossitionY));
        }

        double deltaX = pathPoints.get(0).getCoordX() - worldPossition.getCoordX();
        double deltaY = pathPoints.get(0).getCoordY() - worldPossition.getCoordY();
        angleOfSpider = calculateAngleBetweenPlayerAndDrone(deltaX, deltaY) + 90;

        worldPossition.setCoordX(worldPossition.getCoordX() - Math.cos(Math.toRadians(angleOfSpider)) * movementSpeed);
        worldPossition.setCoordY(worldPossition.getCoordY() - Math.sin(Math.toRadians(angleOfSpider)) * movementSpeed);
        removePointThatWasReached();
    }

    private void removePointThatWasReached() {
        PathfindingPoint point = pathPoints.get(0);
        if ((point.getCoordX() > worldPossition.getCoordX() - 3 && point.getCoordX() < worldPossition.getCoordX() + 3)
                && (point.getCoordY() > worldPossition.getCoordY() - 3 && point.getCoordY() < worldPossition.getCoordY() + 3)) { //point was reached
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
    public boolean detectCollision(Shape shape) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            System.out.println("false");
            return false;
        }
        System.out.println("true");
        return true;
    }

    @Override
    public boolean detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        doOnBeingHitByMinigun(new Point(0, 0)); //we dont need possition of shot
        return true;
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX() - 32, 0 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(256 + worldPossition.getCoordX() - 32, 0 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(256 + worldPossition.getCoordX() - 32, 256 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX() - 32, 256 + worldPossition.getCoordY() - 32));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        if (movementAnimationFrame < 4) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_1");
        } else if (movementAnimationFrame >= 4 && movementAnimationFrame < 8) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_2");
        } else if (movementAnimationFrame >= 8 && movementAnimationFrame < 12) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_3");
        } else if (movementAnimationFrame >= 12 && movementAnimationFrame < 16) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_4");
        } else {
            movementAnimationFrame = 0;
        }
        movementAnimationFrame++;

        Point monitorPossition = monitorWindow.getPositionInWorld();

        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(angleOfSpider + 180);
        graphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        graphicsContext.restore();

        paintAllExplosionsEnemy();
        //    graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);

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
    public boolean paintDyingEnemyAnimation() {
        if (explodingTimer < 5) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death1");
        } else if (explodingTimer >= 5 && explodingTimer < 10) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death2");
        } else if (explodingTimer >= 10 && explodingTimer < 15) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death3");
        } else if (explodingTimer >= 15 && explodingTimer < 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death4");
        } else if (explodingTimer >= 20 && explodingTimer < 25) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death5");
        } else {
            return false;
        }

        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);

        explodingTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {

    }

    public double getAngleOfDrone() {
        return angleOfSpider;
    }

    public void setAngleOfDrone(double angleOfDrone) {
        this.angleOfSpider = angleOfDrone;
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

    private void findPathToPlayer(Point playerWorldPosition) {
        List<GameStaticObject> visibleStaticObjects = new ArrayList<GameStaticObject>(gridTable.getAllVisibleObjects());
        Pathfinding pathfinding = new Pathfinding(visibleStaticObjects, graphicsContext);
        pathPoints = pathfinding.createPath(this, playerWorldPosition.getCoordX(), playerWorldPosition.getCoordY());
    }

    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allExplosionsOnEnemy.add(new Explosion(monitorWindow));
        hitPoints--;
        if (hitPoints < damagedStateTreshold) {
            movementSpeed = 1;
        }
        if (hitPoints < 1) {
            alive = false;
        }
    }

}
