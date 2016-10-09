/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import EnviromentObjects.Craters.CraterBig;
import EnviromentObjects.DeadSpiderRobot;
import EnviromentObjects.DeadStaticRocketTurretBase;
import GameObject.Point;
import MapGridTable.GridTable;
import com.mycompany.robotgame.GameMainInfrastructure;
import com.mycompany.robotgame.MonitorWindow;
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
    private final List<Enemy> dyingEnemyList = new ArrayList<>();
    private final GraphicsContext graphicsContext;
    private final GridTable gridTable;
    private final MonitorWindow monitorWindow;

    public EnemyContainer(GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow) {
        this.graphicsContext = graphicsContext;
        this.gridTable = gridTable;
        this.monitorWindow = monitorWindow;
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
                if (enemy instanceof StaticRocketTurret) {
                    gridTable.insertGameObjectIntoGridCell(new DeadStaticRocketTurretBase(new Point(enemy.getWorldPossition().getCoordX() - 32, enemy.getWorldPossition().getCoordY() - 32), 64, 64, graphicsContext, monitorWindow));
                } else if (enemy instanceof SpiderRobot) {
                    gridTable.insertGameObjectIntoGridCell(new DeadSpiderRobot(new Point(enemy.getWorldPossition().getCoordX() - 64, enemy.getWorldPossition().getCoordY() - 64), 128, 128, graphicsContext, monitorWindow));
                }

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

    public void paintEnemies(Point playerRobotWorldPossition, boolean paintingEnemiesAbovePlayer) {
        // graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        for (Enemy enemy : enemyList) {
            if ((enemy.isAbovePlayer() && paintingEnemiesAbovePlayer) || (!enemy.isAbovePlayer() && !paintingEnemiesAbovePlayer)) {
                enemy.paintGameObject();
            }

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
