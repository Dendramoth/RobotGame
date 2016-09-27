/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.GameStaticObject;
import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
import MapGridTable.GridTable;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author styma01
 */
public class BomberAirplane extends Enemy {

    private int bomberImageCounter = 0;

    public BomberAirplane(Point possitionInWorld, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, GridTable gridTable, MonitorWindow monitorWindow) {
        super(possitionInWorld, 64, 64, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, gridTable, monitorWindow);
        enemyImage = LoadAllResources.getMapOfAllImages().get("bomberIdle");
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        worldPossition.setCoordX(worldPossition.getCoordX() - movementSpeed); // move just to left
        if (Math.abs(playerPossitionX - worldPossition.getCoordX()) > 1800) { // if it is to far away from screen, just kill it! :-)
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
        bomberImageCounter++;
        if (bomberImageCounter < 6) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberMovingA");
        } else if (bomberImageCounter >= 6 && bomberImageCounter < 9) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("bomberMovingB");
        } else {
            bomberImageCounter = 0;
        }
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        System.out.println("painting airplane");
    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return new ResultOfDetectColisionWithProjectile(false, new Point(0, 0));
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
