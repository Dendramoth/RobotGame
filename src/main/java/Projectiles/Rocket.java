/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectiles;

import Enemy.Enemy;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class Rocket extends Projectile{
    private int switchingRocketImageCounter = 0;
    private boolean switchImage = true;
    private int rocketTimout = 120;
    private int rocketExplosionCounter = 0;
    
     public Rocket(GraphicsContext graphicsContext, double angleOfFiredShot, Point position, Enemy enemy, double width, double height, MonitorWindow monitorWindow) {
        super(graphicsContext, angleOfFiredShot, new Point(position.getCoordX(), position.getCoordY()), enemy, width, height, monitorWindow);
        projectileImage = LoadAllResources.getMapOfAllImages().get("rocket1");
    }

    @Override
    public void moveProjectile() {
        worldPossition.setCoordX(worldPossition.getCoordX() - Math.cos(Math.toRadians(angleOfFiredShot - 90)) * 5);
        worldPossition.setCoordY(worldPossition.getCoordY() - Math.sin(Math.toRadians(angleOfFiredShot - 90)) * 5);
    }
     
    @Override
    public boolean hasProjectileReachedDestination() {
        rocketTimout--;
        if (rocketTimout < 1) {
            return true;
        }
        return false;
    } 

    @Override
    public void paintGameObject() {
        switchingRocketImageCounter++;
        if (switchingRocketImageCounter > 8) {
            switchingRocketImageCounter = 0;
            if (switchImage) {
                projectileImage = LoadAllResources.getMapOfAllImages().get("rocket2");
                switchImage = false;
            } else {
                projectileImage = LoadAllResources.getMapOfAllImages().get("rocket1");
                switchImage = true;
            }
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
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, projectilePolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean projectileExplosion() {
        rocketExplosionCounter++;
        if (rocketExplosionCounter <= 5) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion1");
        } else if (rocketExplosionCounter > 5 && rocketExplosionCounter <= 10) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion2");
        } else if (rocketExplosionCounter > 15 && rocketExplosionCounter <= 20) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion3");
        } else if (rocketExplosionCounter > 25 && rocketExplosionCounter <= 30) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion4");
        } else if (rocketExplosionCounter > 35 && rocketExplosionCounter <= 40) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion5");
        } else if (rocketExplosionCounter > 40) {
            return false;
        }
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(projectileImage, worldPossition.getCoordX() - monitorPossition.getCoordX() - width / 2, worldPossition.getCoordY() - monitorPossition.getCoordY() - heigh / 2);
        return true;
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
