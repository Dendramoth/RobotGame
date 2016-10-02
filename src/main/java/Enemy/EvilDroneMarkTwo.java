/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.GameStaticObject;
import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
import Pathfinding.PathfindingPoint;
import MapGridTable.GridTable;
import Pathfinding.Pathfinding;
import Projectiles.Projectile;
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
public class EvilDroneMarkTwo extends Enemy{
    
    private int blinkCounter = 0;
    private int explodingTimer = 0;
    private double angleOfDrone = 0;
    private double damagedStateAngleOfDrone = 0;
    private double lastAngleToAvoidCollision = 0;
    private boolean collisionDetectedInLastTest = false;
    private int valueToAddToBlinkCounter = 1;
    private int timerForRecalculationOfPathfinding = 0;

    private List<PathfindingPoint> pathPoints = new ArrayList<PathfindingPoint>();
    private final List<Point> pointsForDetection = new ArrayList<>();

    public EvilDroneMarkTwo(Point possitionInWorld, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow) {
        super(possitionInWorld, width, heigh, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);
    }
    
    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        timerForRecalculationOfPathfinding++;
        if (pathPoints.size() < 1 || timerForRecalculationOfPathfinding >= 20) {
            timerForRecalculationOfPathfinding = 0;
            findPathToPlayer(new Point(playerPossitionX, playerPossitionY));
        }

        double deltaX = pathPoints.get(0).getCoordX() - worldPossition.getCoordX();
        double deltaY = pathPoints.get(0).getCoordY() - worldPossition.getCoordY();
        angleOfDrone = calculateAngleBetweenPlayerAndDrone(deltaX, deltaY) + 90;

        worldPossition.setCoordX(worldPossition.getCoordX() - Math.cos(Math.toRadians(angleOfDrone)) * movementSpeed);
        worldPossition.setCoordY(worldPossition.getCoordY() - Math.sin(Math.toRadians(angleOfDrone)) * movementSpeed);
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
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return new ResultOfDetectColisionWithProjectile(false, new Point(0,0));
        }
        return new ResultOfDetectColisionWithProjectile(true, new Point(0, 0));
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(16 + worldPossition.getCoordX() - 32, 16 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(48 + worldPossition.getCoordX() - 32, 16 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(48 + worldPossition.getCoordX() - 32, 48 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(16 + worldPossition.getCoordX() - 32, 48 + worldPossition.getCoordY() - 32));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        if (hitPoints >= damagedStateTreshold) {
            blinkCounter = blinkCounter + valueToAddToBlinkCounter;
            if (blinkCounter > 0 && blinkCounter <= 10) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneMarkTwo1");
            } else if (blinkCounter > 10 && blinkCounter <=20) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneMarkTwo2");
            } else if (blinkCounter > 20 && blinkCounter <=30) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneMarkTwo3");
            } else if (blinkCounter > 30 && blinkCounter <=40){
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneMarkTwo4");
            } else{
                if (blinkCounter > 40){
                    valueToAddToBlinkCounter = -1;
                    blinkCounter = 30;
                }else{
                    valueToAddToBlinkCounter = 1;
                    blinkCounter = 10;
                } 
                
            }
        } else {
            blinkCounter++;
            damagedStateAngleOfDrone = damagedStateAngleOfDrone + 1;
            angleOfDrone = damagedStateAngleOfDrone;
            
            if (blinkCounter <= 10) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneMarkTwoDamaged1");
            }
            if (blinkCounter > 10) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneMarkTwoDamaged2");
            }
            if (blinkCounter > 19) {
                blinkCounter = 0;
            }
        }
        
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(angleOfDrone + 180);
        graphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        graphicsContext.restore();
        
        paintAllExplosionsEnemy();
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
        explodingTimer++;
        if (explodingTimer <= 5) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion1");
        } else if (explodingTimer > 5 && explodingTimer <= 10) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion2");
        } else if (explodingTimer > 15 && explodingTimer <= 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion3");
        } else if (explodingTimer > 25 && explodingTimer <= 30) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion4");
        } else if (explodingTimer > 35 && explodingTimer <= 40) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion5");
        } else if (explodingTimer > 40) {
            return false;
        }
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);
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

    private void findPathToPlayer(Point playerWorldPosition) {
        List<GameStaticObject> visibleStaticObjects = new ArrayList<GameStaticObject>(gridTable.getAllVisibleObjects());
        Pathfinding pathfinding = new Pathfinding(visibleStaticObjects, graphicsContext, pathPoints);
        pathPoints = pathfinding.createPath(this, playerWorldPosition.getCoordX(), playerWorldPosition.getCoordY());
    }

    @Override
    public void doOnCollision() {
        hitPoints = 0; //die!
        alive = false;
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allExplosionsOnEnemy.add(new Explosion(monitorWindow));
        hitPoints--;
        if (hitPoints < damagedStateTreshold) {
            movementSpeed = 1;
            damagedStateAngleOfDrone = angleOfDrone;
        }
        if (hitPoints < 1) {
            alive = false;
        }
    }
    
    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
        hitPoints = hitPoints - projectile.getDamage();
        if (hitPoints < 1) {
            alive = false;
        }
    }
    
}
