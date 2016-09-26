/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.GameStaticObject;
import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
import Pathfinding.PathfindingPoint;
import MapGridTable.GridTable;
import Pathfinding.Pathfinding;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author styma01
 */
public class BomberAirplane extends Enemy{

    public BomberAirplane(Point possitionInWorld, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow) {
        super(possitionInWorld, 64, 64, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);
        enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        worldPossition.setCoordX(worldPossition.getCoordX() - 5); // move just to left
        if (Math.abs(playerPossitionX - worldPossition.getCoordX()) > 1800){ // if it is to far away from screen, just kill it! :-)
            alive = false;
            hitPoints = 0;
        }
    }

    @Override
    public void paintAllExplosionsEnemy() {
        // do nothing
    }

    @Override
    public boolean paintDyingEnemyAnimation() {
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnCollision() {
        // nothing
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        alive = false;
        hitPoints = 0;
    }
    
    
}
