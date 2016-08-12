/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Dendra
 */
public class EnemyContainer {

    List<Enemy> enemyList = new ArrayList<>();
    List<Enemy> dyingEnemyList = new ArrayList<Enemy>();

    public EnemyContainer() {
    }

    public void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemyList.remove(enemy);
    }

    public void testEnemiesAlive() {
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (!enemy.isAlive()) {
                dyingEnemyList.add(enemy);
                iterator.remove();
            }
        }
    }

    public void moveEnemies(Point moveToPoint) {
        for (Enemy enemy : enemyList) {
            enemy.moveEnemy(moveToPoint.getCoordX(), moveToPoint.getCoordY());
        }
    }

    public void paintEnemies(Point playerRobotWorldPossition) {
        for (Enemy enemy : enemyList) {
            enemy.paintGameObject();
        }
    }

    public void paintAllDiingEnemies() {
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (!enemy.isAlive()) {
                if (!enemy.paintDyingEnemyAnimation()) {
                    iterator.remove();
                }
            }
        }
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

}
