/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollisionDetection;

import Enemy.EnemyContainer;
import GameObject.GameObjectWithDistanceDetection;
import GameObject.GameStaticObject;
import GameObject.Point;
import MapGridTable.GridTable;
import com.mycompany.robotgame.GameDynamicEnviroment;
import com.mycompany.robotgame.MonitorWindow;
import playerRobot.PlayerRobot;
import playerRobot.ShotsFromMinigun;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class DetectCollisions {

    private PlayerRobot playerRobot;
    private GameDynamicEnviroment gameDynamicEnviroment;
    private final GridTable gridTable;
    private EnemyContainer enemyContainer;

    public DetectCollisions(PlayerRobot playerRobot, GameDynamicEnviroment gameDynamicEnviroment, GridTable gridTable, EnemyContainer enemyContainer) {
        this.playerRobot = playerRobot;
        this.gameDynamicEnviroment = gameDynamicEnviroment;
        this.gridTable = gridTable;
        this.enemyContainer = enemyContainer;
    }

    public boolean detectCollisionOfPlayerRobotWithStaticObjects() {
        return false;
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
                if (gameObjectWithDistanceDetection.detectCollision(shotFromMinigun.getLineForDetection())) {
                    playerRobot.getAllShotsFromMinigun().clear();
                    return;
                }
            }

            gameDynamicEnviroment.generateNewMinigunHitOnGround(shotFromMinigun.getEndPositionOfShot());
            playerRobot.getAllShotsFromMinigun().clear();
        }
    }
    /*
    
    public void detectCollisionsOfAllEnemiesWithPlayerRobot() {
        Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
        while (iterator.hasNext()) {
            Enemy enemyWithCollision = iterator.next();
            if (enemyWithCollision.detectCollision(playerRobot.getPlayerRobotPolygon())) {
                playerRobot.doOnCollision(null);
                playerRobot.removeHitPoints(40);
                enemyWithCollision.setAlive(false);
                allDyingEneniesList.add(enemyWithCollision);
                iterator.remove();
            }
        }
    }

    public void detectCollisionsOfAllEnemiesWithPlayerMinigunShots() {
        if (playerRobot.getAllShotsFromMinigun() != null && playerRobot.getAllShotsFromMinigun().size() > 0) {
            ShotsFromMinigun shotFromMinigun = playerRobot.getAllShotsFromMinigun().get(0);
            Boolean shotHitSomething = false;

            for (int i = 0; i < allLivingEnemiesList.size(); i++) {
                allLivingEnemiesList.get(i).setObjectForComparisonPosX(playerRobot.getPossitionX());
                allLivingEnemiesList.get(i).setObjectForComparisonPosY(playerRobot.getPossitionY());
            }

            Collections.sort(allLivingEnemiesList);

            Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                if (enemy.detectCollision(shotFromMinigun.getLineForDetection())) {
                    enemy.doOnBeingHit("minigun");
                    if (enemy.getHitPoints() < 1) {
                        enemy.setAlive(false);
                        allDyingEneniesList.add(enemy);
                        iterator.remove();
                    }
                    shotHitSomething = true;
                    break;
                }
            }

            if (!shotHitSomething) {
                gameEnviroment.generateNewMinigunHitOnGround(shotFromMinigun.getEndPositionOfShotX(), shotFromMinigun.getEndPositionOfShotY());
            }
            playerRobot.getAllShotsFromMinigun().clear();
        }
    }*/

}
