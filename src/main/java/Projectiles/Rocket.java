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
    
     public Rocket(GraphicsContext graphicsContext, double angleOfFiredShot, Point position, Enemy enemy, double width, double height, MonitorWindow monitorWindow) {
        super(graphicsContext, angleOfFiredShot, position, enemy, width, height, monitorWindow);
        projectileImage = LoadAllResources.getMapOfAllImages().get("rocket1");
    }

    @Override
    public void moveProjectile() {
        worldPossition = new Point(worldPossition.getCoordX() + 1, worldPossition.getCoordY());
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
    
    public void createPolygonForDetection() {
        List<Point> pointsForDetection = new ArrayList<Point>();
        pointsForDetection.add(new Point(77 + worldPossition.getCoordX(), 275 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(77 + worldPossition.getCoordX(), 346 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(163 + worldPossition.getCoordX(), 433 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(285 + worldPossition.getCoordX(), 404 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(400 + worldPossition.getCoordX(), 210 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossition.getCoordX(), 188 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(292 + worldPossition.getCoordX(), 274 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(236 + worldPossition.getCoordX(), 218 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(320 + worldPossition.getCoordX(), 128 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(300 + worldPossition.getCoordX(), 105 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(112 + worldPossition.getCoordX(), 220 + worldPossition.getCoordY()));
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
