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
import Projectiles.ProjectileContainer;
import Projectiles.SpiderLaser;
import com.mycompany.robotgame.GameDynamicEnviroment;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class SpiderRobot extends Enemy {

    private int movementAnimationFrame = 1;
    private int explodingTimer = 0;
    private double angleOfSpider = 0;
    private double angleOfSpiderTower = 0;
    private double lastAngleToAvoidCollision = 0;
    private boolean collisionDetectedInLastTest = false;
    private Image enemyTurretImage;
    private final double turretAngleSpeed = 0.5;
    private int spiderTurretImgCounter = 0;
    private int timerForRecalculationOfPathfinding = 0;

    private List<PathfindingPoint> pathPoints = new ArrayList<PathfindingPoint>();
    private final List<Point> pointsForDetection = new ArrayList<>();
    private final ProjectileContainer projectileContainer;
    private int shockCounter = 0;
    private boolean damaged = false;
    private final GameDynamicEnviroment gameDynamicEnviroment;

    public SpiderRobot(Point possitionInWorld, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow, ProjectileContainer projectileContainer, GameDynamicEnviroment gameDynamicEnviroment) {
        super(possitionInWorld, width, heigh, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);
        enemyImage = LoadAllResources.getMapOfAllImages().get("walker_idle");
        this.projectileContainer = projectileContainer;
        this.gameDynamicEnviroment = gameDynamicEnviroment;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        timerForRecalculationOfPathfinding++;
        if (pathPoints.size() < 1 || timerForRecalculationOfPathfinding >= 20) {
            timerForRecalculationOfPathfinding = 0;
            findPathToPlayer(new Point(playerPossitionX, playerPossitionY));
        }
        
        double deltaXToPlayer = playerPossitionX - worldPossition.getCoordX();
        double deltaYToPlayer = playerPossitionY - worldPossition.getCoordY();
        angleOfSpiderTower = (angleOfSpiderTower + 360) % 360;
        double angleToPlayer = (Math.toDegrees(Math.atan2(deltaXToPlayer, -deltaYToPlayer)) + 360) % 360;
        if (((angleOfSpiderTower - angleToPlayer > 0) && (angleOfSpiderTower - angleToPlayer < 180)) || (angleOfSpiderTower - angleToPlayer < -180)) {
            angleOfSpiderTower = angleOfSpiderTower + turretAngleSpeed;
        } else {
            angleOfSpiderTower = angleOfSpiderTower - turretAngleSpeed;
        }

        double deltaX = pathPoints.get(0).getCoordX() - worldPossition.getCoordX();
        double deltaY = pathPoints.get(0).getCoordY() - worldPossition.getCoordY();
        angleOfSpider = calculateAngleBetweenDroneAndNextPointInPathfinding(deltaX, deltaY) + 90;

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

    private double calculateAngleBetweenDroneAndNextPointInPathfinding(double x, double y) {
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
        return new ResultOfDetectColisionWithProjectile(true, new Point(0,0));
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
        } else if (movementAnimationFrame >= 16 && movementAnimationFrame < 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_5");
        } else if (movementAnimationFrame >= 20 && movementAnimationFrame < 24) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_6");
        } else if (movementAnimationFrame >= 24 && movementAnimationFrame < 28) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_7");
        } else if (movementAnimationFrame >= 28 && movementAnimationFrame < 32) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("walker_moving_8");
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

        paintSpiderTurret(monitorPossition);
        paintAllExplosionsEnemy();

        shockCounter++;
        if (shockCounter > 320) {
            shockCounter = 0;
            projectileContainer.addProjectileToContainer(new SpiderLaser(graphicsContext, angleOfSpiderTower, worldPossition, this, 512, 512, monitorWindow, gameDynamicEnviroment));
        }
    }

    private void paintSpiderTurret(Point monitorPossition) {
        spiderTurretImgCounter++;
        if (damaged == false) {
            if (spiderTurretImgCounter < 12) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTower1");
            } else if (spiderTurretImgCounter >= 12 && spiderTurretImgCounter < 24) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTower2");
            } else if (spiderTurretImgCounter >= 24 && spiderTurretImgCounter < 36) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTower3");
            } else if (spiderTurretImgCounter >= 36 && spiderTurretImgCounter < 48) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTower4");
            } else {
                spiderTurretImgCounter = 0;
            }
        }else{
            if (spiderTurretImgCounter < 12) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTowerDamaged1");
            } else if (spiderTurretImgCounter >= 12 && spiderTurretImgCounter < 24) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTowerDamaged2");
            } else if (spiderTurretImgCounter >= 24 && spiderTurretImgCounter < 36) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTowerDamaged3");
            } else if (spiderTurretImgCounter >= 36 && spiderTurretImgCounter < 48) {
                enemyTurretImage = LoadAllResources.getMapOfAllImages().get("spiderTowerDamaged4");
            } else {
                spiderTurretImgCounter = 0;
            }
        }
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(angleOfSpiderTower);
        graphicsContext.drawImage(enemyTurretImage, -enemyTurretImage.getWidth() / 2, -enemyTurretImage.getHeight() / 2);
        graphicsContext.restore();
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
        if (explodingTimer < 8) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("spiderExplosion1");
        } else if (explodingTimer >= 8 && explodingTimer < 16) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("spiderExplosion2");
        } else if (explodingTimer >= 16 && explodingTimer < 24) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("spiderExplosion3");
        } else if (explodingTimer >= 24 && explodingTimer < 32) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("spiderExplosion4");
        } else if (explodingTimer >= 32 && explodingTimer < 40) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("spiderExplosion5");
        } else {
            return false;
        }

        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(angleOfSpiderTower);
        graphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        graphicsContext.restore();
        
        explodingTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {

    }

    public double getAngleOfSpiderTower() {
        return angleOfSpiderTower;
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
        Pathfinding pathfinding = new Pathfinding(visibleStaticObjects, graphicsContext, pathPoints);
        pathPoints = pathfinding.createPath(this, playerWorldPosition.getCoordX(), playerWorldPosition.getCoordY());
    }

    @Override
    public void doOnCollision(Point collisionPoint) {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allExplosionsOnEnemy.add(new Explosion(monitorWindow));
        hitPoints--;
        if (hitPoints < 1) {
            alive = false;
        }else if (hitPoints < damagedStateTreshold){
            damaged = true;
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
