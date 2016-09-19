/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectiles;

import Enemy.Enemy;
import Enemy.SpiderRobot;
import GameObject.ObjectWithCollision;
import GameObject.Point;
import com.mycompany.robotgame.GameDynamicEnviroment;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class SpiderLaser extends Projectile {
    
    private int laserCounter = 0;
    private SpiderRobot spiderRobot;
    private GameDynamicEnviroment gameDynamicEnviroment;

    public SpiderLaser(GraphicsContext graphicsContext, double angleOfFiredShot, Point position, Enemy enemy, double width, double height, MonitorWindow monitorWindow, GameDynamicEnviroment gameDynamicEnviroment) {
        super(graphicsContext, angleOfFiredShot, position, enemy, width, height, false, monitorWindow);
        this.spiderRobot = spiderRobot;
        this.gameDynamicEnviroment = gameDynamicEnviroment;
    }

    @Override
    public void moveProjectile() {
        laserCounter++;
    }

    @Override
    public boolean hasProjectileReachedDestination() {
        if (laserCounter > 120) {
            return true;
        }
        return false;
    }

    @Override
    public boolean projectileExplosion() {
        // no explosion
        return false;
    }

    @Override
    public void paintGameObject() {
        if (laserCounter < 5) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 5 && laserCounter < 10) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 10 && laserCounter < 15) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 15 && laserCounter < 20) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 20 && laserCounter < 25) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 25 && laserCounter < 30) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 30 && laserCounter < 35) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 35 && laserCounter < 40) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 40 && laserCounter < 45) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 45 && laserCounter < 50) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 50 && laserCounter < 55) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 55 && laserCounter < 60) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 60 && laserCounter < 65) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 65 && laserCounter < 70) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 70 && laserCounter < 75) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 75 && laserCounter < 80) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 80 && laserCounter < 85) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 85 && laserCounter < 90) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 95 && laserCounter < 100) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 100 && laserCounter < 105) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        } else if (laserCounter >= 105 && laserCounter < 110) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        } else if (laserCounter >= 110 && laserCounter < 115) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser2");
        }else {
            projectileImage = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
        }
        
        if (laserCounter % 5 == 0){
            double endPositionX = worldPossition.getCoordX() - Math.cos(Math.toRadians(spiderRobot.getAngleOfSpiderTower() - 90)) * 250;
            double endPositionY = worldPossition.getCoordY() - Math.sin(Math.toRadians(spiderRobot.getAngleOfSpiderTower() - 90)) * 250;
            gameDynamicEnviroment.generateNewLaserHitOnGround(new Point(endPositionX, endPositionY));
        }

        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        graphicsContext.rotate(spiderRobot.getAngleOfSpiderTower());
        graphicsContext.drawImage(projectileImage, -projectileImage.getWidth() / 2, -projectileImage.getHeight() / 2);
        graphicsContext.restore();
    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public boolean detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return false;
    }

    @Override
    public void doOnCollision() {
        // nothing
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        // nothing
    }

    @Override
    public Shape getProjectileShape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
