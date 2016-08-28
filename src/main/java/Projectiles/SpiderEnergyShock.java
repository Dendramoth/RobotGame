/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectiles;

/**
 *
 * @author Dendra
 */
import Enemy.Enemy;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public class SpiderEnergyShock extends Projectile {

    private int energyShockCounter = 0;

    public SpiderEnergyShock(GraphicsContext graphicsContext, double angleOfFiredShot, Point position, Enemy enemy, double width, double height, MonitorWindow monitorWindow) {
        super(graphicsContext, angleOfFiredShot, position, enemy, width, height, monitorWindow);
    }

    @Override
    public void moveProjectile() {
        energyShockCounter++;
    }

    @Override
    public boolean hasProjectileReachedDestination() {
        if (energyShockCounter > 24) {
            return true;
        }
        return false;
    }

    @Override
    public void paintGameObject() {
        if (energyShockCounter < 4) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLightning7A");
        } else if (energyShockCounter >= 4 && energyShockCounter < 8) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLightning7B");
        } else if (energyShockCounter >= 8 && energyShockCounter < 12) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLightning7A");
        } else if (energyShockCounter >= 12 && energyShockCounter < 16) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLightning7B");
        } else if (energyShockCounter >= 16 && energyShockCounter < 20) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLightning7A");
        } else {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLightning7B");
        }

        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(angleOfFiredShot);
        graphicsContext.drawImage(projectileImage, -projectileImage.getWidth() / 2, -projectileImage.getHeight() / 2);
        graphicsContext.restore();
    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public boolean projectileExplosion() {
        return false;
    }

    public void createPolygonForDetection() {
        List<Point> pointsForDetection = new ArrayList<Point>();
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 64 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX(), 64 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    protected final void createPolygon(List<Point> pointsList) {
        projectilePolygon.getPoints().clear();
        for (Point point : pointsList) {
            projectilePolygon.getPoints().add(point.getCoordX());
            projectilePolygon.getPoints().add(point.getCoordY());
        }
    }

    @Override
    public boolean detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return false;
    }

    @Override
    public void doOnCollision() {
        //explode
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        //nothing
    }

}
