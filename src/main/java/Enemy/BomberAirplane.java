/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
import MapGridTable.GridTable;
import Projectiles.Bomb;
import Projectiles.Projectile;
import Projectiles.ProjectileContainer;
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
public class BomberAirplane extends Enemy {

    private final ProjectileContainer projectileContainer;
    private final double rotationSpeed = 0.8;

    private int bomberImageCounter = 0;
    private int dropBombCounter = 0;
    private final List<Point> pointsForDetection = new ArrayList<>();
    private double bomberFlightAngle = 0;
    private int explosionTimer = 0;
    private int reloadingBombTimer = 0;
    private boolean droppingBombs = false;

    public BomberAirplane(Point possitionInWorld, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow, ProjectileContainer projectileContainer) {
        super(possitionInWorld, 64, 64, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);
        this.projectileContainer = projectileContainer;
        enemyImage = LoadAllResources.getMapOfAllImages().get("bomberIdle");
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        double deltaX = playerPossitionX - worldPossition.getCoordX();
        double deltaY = playerPossitionY - worldPossition.getCoordY();

        double angleToPlayer = ((Math.toDegrees(Math.atan2(deltaX, -deltaY)) + 360) % 360) - 90;
        bomberFlightAngle = (bomberFlightAngle + 360) % 360;
        if (((bomberFlightAngle - angleToPlayer > 0) && (bomberFlightAngle - angleToPlayer < 180)) || (bomberFlightAngle - angleToPlayer < -180)) {
            bomberFlightAngle = bomberFlightAngle + rotationSpeed;
        } else {
            bomberFlightAngle = bomberFlightAngle - rotationSpeed;
        }
        worldPossition.setCoordX(worldPossition.getCoordX() - Math.cos(Math.toRadians(bomberFlightAngle)) * movementSpeed);
        worldPossition.setCoordY(worldPossition.getCoordY() - Math.sin(Math.toRadians(bomberFlightAngle)) * movementSpeed);

        if (Math.abs(playerPossitionX - worldPossition.getCoordX()) > 1800) { // if it is to far away from screen, just kill it! :-)
            alive = false;
            hitPoints = 0;
        }

        bombManagement(playerPossitionX, playerPossitionY);
    }
    
    private void bombManagement(double playerPositionX, double playerPositionY) {
        //keep reloading bombs in time
        if (reloadingBombTimer > 0){
            reloadingBombTimer--;
        }
        
        //check distance for dropping bombs
        if (reloadingBombTimer <= 0 && ((Math.abs(worldPossition.getCoordX() - playerPositionX) + Math.abs(worldPossition.getCoordY() - playerPositionY)) < 180)){
            droppingBombs = true;
            reloadingBombTimer = 320;
        }
        
        //drop bombs
        if (droppingBombs) {
            dropBomb();
        }
    }

    private void dropBomb() {
        dropBombCounter++;
        if (dropBombCounter % 8 == 0) {
            double bombPositionX = worldPossition.getCoordX() + Math.cos(Math.toRadians(bomberFlightAngle)) * 50;
            double bombPositionY = worldPossition.getCoordY() + Math.sin(Math.toRadians(bomberFlightAngle)) * 50;
            projectileContainer.addProjectileToContainer(new Bomb(graphicsContext, 0, new Point(bombPositionX, bombPositionY), this, false, monitorWindow));
        }
        if (dropBombCounter >= 80) {
            droppingBombs = false;
            dropBombCounter = 0;
        }
    }

    @Override
    public boolean paintDyingEnemyAnimation() {
        if (explosionTimer < 5) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberDeath1");
        } else if (explosionTimer >= 5 && explosionTimer < 10) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberDeath2");
        } else if (explosionTimer >= 10 && explosionTimer < 15) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberDeath3");
        } else if (explosionTimer >= 15 && explosionTimer < 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberDeath4");
        } else if (explosionTimer >= 20 && explosionTimer < 25) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberDeath5");
        } else {
            return false;
        }

        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(bomberFlightAngle);
        graphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        graphicsContext.restore();

        explosionTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {
    }

    @Override
    public void paintGameObject() {
        bomberImageCounter++;
        if (bomberImageCounter < 6) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberMovingA");
        } else if (bomberImageCounter >= 6 && bomberImageCounter < 9) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberMovingB");
        } else {
            bomberImageCounter = 0;
        }

        Point monitorPossition = monitorWindow.getPositionInWorld();

        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(bomberFlightAngle);
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
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return new ResultOfDetectColisionWithProjectile(false, new Point(0, 0));
        }
        return new ResultOfDetectColisionWithProjectile(true, new Point(0, 0));
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX() - 32, 0 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX() - 32, 0 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX() - 32, 64 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX() - 32, 64 + worldPossition.getCoordY() - 32));
        createPolygon(pointsForDetection);
    }

    @Override
    public void doOnCollision(Point collisionPoint) {
        // nothing
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allExplosionsOnEnemy.add(new Explosion(monitorWindow));
        hitPoints--;
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
