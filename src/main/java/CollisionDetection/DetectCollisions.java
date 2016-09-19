/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollisionDetection;

import Enemy.Enemy;
import Enemy.EnemyContainer;
import Enemy.EvilDroneMarkTwo;
import GameObject.GameObjectWithDistanceDetection;
import GameObject.ResultOfDetectColisionWithProjectile;
import MapGridTable.GridTable;
import Projectiles.Projectile;
import Projectiles.ProjectileContainer;
import com.mycompany.robotgame.GameDynamicEnviroment;
import playerRobot.PlayerRobot;
import playerRobot.ShotsFromMinigun;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Dendra
 */
public class DetectCollisions {

    private final PlayerRobot playerRobot;
    private final GameDynamicEnviroment gameDynamicEnviroment;
    private final GridTable gridTable;
    private final EnemyContainer enemyContainer;
    private final ProjectileContainer projectileContainer;

    public DetectCollisions(PlayerRobot playerRobot, GameDynamicEnviroment gameDynamicEnviroment, GridTable gridTable, EnemyContainer enemyContainer, ProjectileContainer projectileContainer) {
        this.playerRobot = playerRobot;
        this.gameDynamicEnviroment = gameDynamicEnviroment;
        this.gridTable = gridTable;
        this.enemyContainer = enemyContainer;
        this.projectileContainer = projectileContainer;
    }

    public void detectCollisionsWithPlayerMinigunShots() {
        if (playerRobot.getAllShotsFromMinigun() != null && playerRobot.getAllShotsFromMinigun().size() > 0) {
            ShotsFromMinigun shotFromMinigun = playerRobot.getAllShotsFromMinigun().get(0);

            List<GameObjectWithDistanceDetection> visibleObjects = new ArrayList<GameObjectWithDistanceDetection>(gridTable.getAllVisibleObjects());
            visibleObjects.addAll(enemyContainer.getEnemyList());

            for (int i = 0; i < visibleObjects.size(); i++) {
                visibleObjects.get(i).setObjectForComparison(shotFromMinigun.getStartPositionOfShot());
            }
            Collections.sort(visibleObjects);

            Iterator<GameObjectWithDistanceDetection> iterator = visibleObjects.iterator();
            while (iterator.hasNext()) {
                GameObjectWithDistanceDetection gameObjectWithDistanceDetection = iterator.next();
                ResultOfDetectColisionWithProjectile resultOfDetectColisionWithProjectile = gameObjectWithDistanceDetection.detectCollisionWithProjectile(shotFromMinigun.getLineForDetection(), shotFromMinigun.getStartPositionOfShot());
                if (resultOfDetectColisionWithProjectile.isColiding()) {
                    gameObjectWithDistanceDetection.doOnBeingHitByMinigun(resultOfDetectColisionWithProjectile.getIntersectionPoint());
                    //if collision is detected subrutine to handle the colision is called inside the detectCollisionWithProjectile method
                    playerRobot.getAllShotsFromMinigun().clear();
                    return;
                }
            }

            gameDynamicEnviroment.generateNewMinigunHitOnGround(shotFromMinigun.getEndPositionOfShot());
            playerRobot.getAllShotsFromMinigun().clear();
        }
    }

    public void detectCollisionOfAllDronesWithPlayerRobot() {
        for (Enemy enemy : enemyContainer.getEnemyList()) {
            if (enemy instanceof EvilDroneMarkTwo) {
                EvilDroneMarkTwo evilDroneMarkTwo = (EvilDroneMarkTwo) enemy;
                double distance = Math.abs(evilDroneMarkTwo.getWorldPossition().getCoordX() - playerRobot.getWorldPossition().getCoordX()) + Math.abs(evilDroneMarkTwo.getWorldPossition().getCoordY() - playerRobot.getWorldPossition().getCoordY());
                if (distance < 52) {
                    evilDroneMarkTwo.doOnCollision();
                    playerRobot.removeHitPoints(50);
                }
            }
        }
    }

    public void detectCollisionOfRocketWithStaticObjectsAndOtherEnemies() {
        if (projectileContainer.getAllFiredProjectiles().size() > 0) {
            for (Projectile projectile : projectileContainer.getAllFiredProjectiles()) {
                List<GameObjectWithDistanceDetection> visibleObjects = new ArrayList<GameObjectWithDistanceDetection>(gridTable.getAllVisibleObjects());
                visibleObjects.addAll(enemyContainer.getEnemyList());
                visibleObjects.add(playerRobot);
                visibleObjects.remove(projectile.getEnemyWhoShootedThisProjectile());
                if (projectile.getObjectToIgnore() != null){
                    visibleObjects.remove(projectile.getObjectToIgnore());
                }

                for (int i = 0; i < visibleObjects.size(); i++) {
                    visibleObjects.get(i).setObjectForComparison(projectile.getWorldPossition());
                }
                Collections.sort(visibleObjects);

                Iterator<GameObjectWithDistanceDetection> iterator = visibleObjects.iterator();
                while (iterator.hasNext()) {
                    GameObjectWithDistanceDetection gameObjectWithDistanceDetection = iterator.next();
                    ResultOfDetectColisionWithProjectile resultOfDetectColisionWithProjectile = gameObjectWithDistanceDetection.detectCollisionWithProjectile(projectile.getProjectileShape(), projectile.getWorldPossition());
                    if (resultOfDetectColisionWithProjectile.isColiding()) {
                        if (projectile.isFiredFromWall() && projectile.getObjectToIgnore() == null) {
                            projectile.setObjectToIgnore(gameObjectWithDistanceDetection);
                        } else {
                            projectile.doOnCollision();
                        }
                    }
                }
            }
        }
    }

}
