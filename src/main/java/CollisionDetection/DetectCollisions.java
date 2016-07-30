/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollisionDetection;

import GameObject.Point;
import com.mycompany.robotgame.GameDynamicEnviroment;
import playerRobot.PlayerRobot;
import playerRobot.ShotsFromMinigun;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class DetectCollisions {
    private PlayerRobot playerRobot;
    private GameDynamicEnviroment gameDynamicEnviroment;

    public DetectCollisions(PlayerRobot playerRobot, GameDynamicEnviroment gameDynamicEnviroment){
        this.playerRobot = playerRobot;
        this.gameDynamicEnviroment = gameDynamicEnviroment;
    }
    
    public boolean detectCollisionOfPlayerRobotWithStaticObjects(){
        return false;
    }
    
    public void detectCollisionsWithPlayerMinigunShots() {
        if (playerRobot.getAllShotsFromMinigun() != null && playerRobot.getAllShotsFromMinigun().size() > 0) {
            ShotsFromMinigun shotFromMinigun = playerRobot.getAllShotsFromMinigun().get(0);
            Boolean shotHitSomething = false;

       /*     for (int i = 0; i < allLivingEnemiesList.size(); i++) {
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
*/
        //    if (!shotHitSomething) {
                
         //   }
         
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
