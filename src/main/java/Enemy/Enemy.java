/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.GameObjectWithDistanceDetection;
import GameObject.Point;
import MapGridTable.GridTable;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Dendra
 */
public abstract class Enemy extends GameObjectWithDistanceDetection{
    
    protected final Polygon gameObjectPolygon = new Polygon();
    protected double movementSpeed;
    protected double damagedStateTreshold;
    protected Image enemyImage;
    protected boolean alive = true;
    protected int hitPoints;
    protected ArrayList<Explosion> allExplosionsOnEnemy = new ArrayList<>();
    protected GridTable gridTable;

    public Enemy(Point possitionInWorld, int enemyLayer, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow) {
        super(possitionInWorld, width, heigh, enemyLayer, graphicsContext, monitorWindow);
        this.movementSpeed = movementSpeed;
        this.damagedStateTreshold = damagedStateTreshold;
        this.hitPoints = hitPoints;
        this.gridTable = gridTable;
    }

    public abstract void moveEnemy(double playerPossitionX, double playerPossitionY);
    public abstract void paintAllExplosionsEnemy();
    public abstract boolean paintDyingEnemyAnimation();
    public abstract void paintDeadEnemy(GraphicsContext enemyGraphicsContext);

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getHitPoints() {
        return hitPoints;
    }
    
    protected final void createPolygon(List<Point> pointsList) {
        gameObjectPolygon.getPoints().clear();
        for (Point point : pointsList) {
            gameObjectPolygon.getPoints().add(point.getCoordX());
            gameObjectPolygon.getPoints().add(point.getCoordY());
        }
    }
}
