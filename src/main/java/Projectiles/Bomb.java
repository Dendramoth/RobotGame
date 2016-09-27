/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectiles;

import Enemy.Enemy;
import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author styma01
 */
public class Bomb extends Projectile{
    
    private int bombExplosionAnimationCounter = 0;

    public Bomb(GraphicsContext graphicsContext, double angleOfFiredShot, Point position, Enemy enemy, boolean firedFromWall, MonitorWindow monitorWindow) {
        super(graphicsContext, angleOfFiredShot, position, enemy, 64, 64, firedFromWall, monitorWindow);
    }

    @Override
    public void moveProjectile() {
        //do not move it
    }

    @Override
    public boolean hasProjectileReachedDestination() {
        return true; //explode bomb imidiately
    }

    @Override
    public boolean projectileExplosion() {
        bombExplosionAnimationCounter++;
        if (bombExplosionAnimationCounter <= 5) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion1");
        } else if (bombExplosionAnimationCounter > 5 && bombExplosionAnimationCounter <= 10) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion2");
        } else if (bombExplosionAnimationCounter > 15 && bombExplosionAnimationCounter <= 20) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion3");
        } else if (bombExplosionAnimationCounter > 25 && bombExplosionAnimationCounter <= 30) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion4");
        } else if (bombExplosionAnimationCounter > 35 && bombExplosionAnimationCounter <= 40) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion5");
        } else if (bombExplosionAnimationCounter > 40) {
            return false;
        }
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(projectileImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);
        return true;
    }

    @Override
    public Shape getProjectileShape() {
        createPolygonForDetection();
        return projectilePolygon;
    }

    @Override
    public void paintGameObject() {
        //do not paint falling bomb
    }

    @Override
    public boolean detectCollision(Shape shape) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, projectilePolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }
    
    public void createPolygonForDetection() {
        List<Point> pointsForDetection = new ArrayList<>();
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(64 + worldPossition.getCoordX(), 64 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 64 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }
    
    protected final void createPolygon(List<Point> pointsList) {
        projectilePolygon = new Polygon();
        for (Point point : pointsList) {
            projectilePolygon.getPoints().add(point.getCoordX());
            projectilePolygon.getPoints().add(point.getCoordY());
        }
    }

    @Override
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnCollision() {
        // do nothing
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        // do nothing
    }
    
    
}
