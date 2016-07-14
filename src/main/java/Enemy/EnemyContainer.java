/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dendra
 */
public class EnemyContainer {
    List<Enemy> enemyList = new ArrayList<>();

    public EnemyContainer() {
    }
    
    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }
    
    public void removeEnemy(Enemy enemy){
        enemyList.remove(enemy);
    }
    
    public void moveEnemies(Point moveToPoint){
        for (Enemy enemy : enemyList){
            enemy.moveEnemy(moveToPoint.getCoordX(), moveToPoint.getCoordY());
        }
    }
    
    public void paintEnemies(Point playerRobotWorldPossition, Point playerScreenPosstion){
        for (Enemy enemy : enemyList){
            enemy.paintEnemy(playerRobotWorldPossition, playerScreenPosstion);
        }
    }
}
