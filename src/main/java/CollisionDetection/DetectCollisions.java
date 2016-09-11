/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollisionDetection;

import Enemy.EnemyContainer;
import GameObject.GameObjectWithDistanceDetection;
import MapGridTable.GridTable;
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

    public DetectCollisions(PlayerRobot playerRobot, GameDynamicEnviroment gameDynamicEnviroment, GridTable gridTable, EnemyContainer enemyContainer) {
        this.playerRobot = playerRobot;
        this.gameDynamicEnviroment = gameDynamicEnviroment;
        this.gridTable = gridTable;
        this.enemyContainer = enemyContainer;
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
                if (gameObjectWithDistanceDetection.detectCollisionWithProjectile(shotFromMinigun.getLineForDetection(), shotFromMinigun.getStartPositionOfShot())) {
                    //if collision is detected subrutine to handle the colision is called inside the detectCollisionWithProjectile method
                    playerRobot.getAllShotsFromMinigun().clear();
                    return;
                }
            }

            gameDynamicEnviroment.generateNewMinigunHitOnGround(shotFromMinigun.getEndPositionOfShot());
            playerRobot.getAllShotsFromMinigun().clear();
        }
    }

}
