/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollisionDetection;

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

 //   private AllProjectilesContainer allProjectilesContainer;
    private PlayerRobot playerRobot;
 /*   private GameEnviroment gameEnviroment;

    private ArrayList<GameObjectPathfinding> allGameObjectsWithColisions = new ArrayList<GameObjectPathfinding>();
    private ArrayList<Enemy> allLivingEnemiesList = new ArrayList<Enemy>();
    private ArrayList<Enemy> allDyingEneniesList = new ArrayList<Enemy>();

    private ArrayList<ProjectileWeapon> allProjectilesList = new ArrayList<ProjectileWeapon>();
    private ArrayList<ProjectileWeapon> allExplodingProjectilesList = new ArrayList<ProjectileWeapon>();
    
    private ArrayList<Rock> allRocks = new ArrayList<Rock>();

 */
    public DetectCollisions(PlayerRobot playerRobot){
        this.playerRobot = playerRobot;
    }
    
    public boolean detectCollisionOfPlayerRobotWithStaticObjects(){
        return false;
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
