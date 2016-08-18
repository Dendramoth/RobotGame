/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import MapGridTable.GridTable;
import Projectiles.ProjectileContainer;
import Projectiles.Rocket;
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
public class StaticRocketTurret extends Enemy {

    private boolean active = false;
    private boolean playInitialIntro = false;
    private int initialIntroFrame = 0;
    private double turretAngle = 0;
    private double playerPossX = 0;
    private double playerPossY = 0;
    private double turretAngleSpeed = 1;

    private int rocketCounter = 0;
    private int explodingTimer = 0;

    private final List<Point> pointsForDetection = new ArrayList<>();
    private ProjectileContainer projectileContainer;
    

    public StaticRocketTurret(Point possitionInWorld, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow, ProjectileContainer projectileContainer) {
        super(possitionInWorld, width, heigh, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);
        this.projectileContainer = projectileContainer;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        double deltaX = playerPossitionX - worldPossition.getCoordX();
        double deltaY = playerPossitionY - worldPossition.getCoordY();
        /**
         * rotate Turret to player by turretAngleSpeed
         */
        if (active && !playInitialIntro) {
            double angleToPlayer = (Math.toDegrees(Math.atan2(deltaX, -deltaY)) + 360) % 360;
            turretAngle = (turretAngle + 360) % 360;
            if (((turretAngle - angleToPlayer > 0) && (turretAngle - angleToPlayer < 180)) || (turretAngle - angleToPlayer < -180)) {
                turretAngle = turretAngle + turretAngleSpeed;
            } else {
                turretAngle = turretAngle - turretAngleSpeed;
            }
        }

        if (!active) {
            double distanceFromPlayer = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
            if (distanceFromPlayer < 500 && active == false) {
                active = true;
                playInitialIntro = true;
            }
        }
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
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX() - 32, 0 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX() - 32, 64 + worldPossition.getCoordY() - 32));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX() - 32, 64 + worldPossition.getCoordY() - 32));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();

        if (playInitialIntro) {
            paintInitialIntro();
            graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);
        } else if (active) {
            rocketCounter++;
            if (rocketCounter > 100) {
                rocketCounter = 0;
                projectileContainer.addProjectileToContainer(new Rocket(graphicsContext, turretAngle, worldPossition, this, 64, 64, monitorWindow));
            }

            enemyImage = LoadAllResources.getMapOfAllImages().get("turretBase");
            graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);
            paintRotatedGunOnTurret(monitorPossition);
        } else {
            enemyImage = LoadAllResources.getMapOfAllImages().get("idleTurret");
            graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);
        }
        paintAllExplosionsEnemy();
    }

    private void paintRotatedGunOnTurret(Point monitorPossition) {
        if (hitPoints > damagedStateTreshold) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretTower");
        } else {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDamaged");
        }
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(turretAngle);
        graphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        graphicsContext.restore();
    }

    private void paintInitialIntro() {
        initialIntroFrame++;
        if (initialIntroFrame <= 8) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro1");
        } else if (initialIntroFrame > 13 && initialIntroFrame <= 24) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro2");
        } else if (initialIntroFrame > 24 && initialIntroFrame <= 32) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro3");
        } else if (initialIntroFrame > 32 && initialIntroFrame <= 40) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro4");
        } else if (initialIntroFrame > 40 && initialIntroFrame <= 48) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro5");
        } else if (initialIntroFrame > 48) {
            playInitialIntro = false;
        }
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
