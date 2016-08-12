/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import com.mycompany.robotgame.GameMainInfrastructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public class EnemyContainer {

    private List<Enemy> enemyList = new ArrayList<>();
    private List<Enemy> dyingEnemyList = new ArrayList<Enemy>();
    private GraphicsContext graphicsContext;

    public EnemyContainer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
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
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        for (Enemy enemy : enemyList) {
            enemy.paintGameObject();
        }
    }

    public void paintAllDiingEnemies() {
        Iterator<Enemy> iterator = dyingEnemyList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (!enemy.paintDyingEnemyAnimation()) {
                iterator.remove();
            }
        }
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

}
