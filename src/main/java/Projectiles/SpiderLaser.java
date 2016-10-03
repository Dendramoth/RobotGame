/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectiles;

import Enemy.Enemy;
import Enemy.SpiderRobot;
import GameObject.Point;
import GameObject.ResultOfDetectColisionWithProjectile;
import com.mycompany.robotgame.GameDynamicEnviroment;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class SpiderLaser extends Projectile {

    private int laserCounter = 0;
    private SpiderRobot spiderRobot;
    private GameDynamicEnviroment gameDynamicEnviroment;
    private Image projectileImageA = LoadAllResources.getMapOfAllImages().get("spiderLaser1");
    private Image projectileImageB = LoadAllResources.getMapOfAllImages().get("spiderLaser2");

    public SpiderLaser(GraphicsContext graphicsContext, double angleOfFiredShot, Point position, Enemy enemy, double width, double height, MonitorWindow monitorWindow, GameDynamicEnviroment gameDynamicEnviroment) {
        super(1, graphicsContext, angleOfFiredShot, position, enemy, width, height, false, monitorWindow);
        this.spiderRobot = (SpiderRobot) enemy;
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
            projectileImage = projectileImageA;
        } else if (laserCounter >= 5 && laserCounter < 10) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 10 && laserCounter < 15) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 15 && laserCounter < 20) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 20 && laserCounter < 25) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 25 && laserCounter < 30) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 30 && laserCounter < 35) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 35 && laserCounter < 40) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 40 && laserCounter < 45) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 45 && laserCounter < 50) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 50 && laserCounter < 55) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 55 && laserCounter < 60) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 60 && laserCounter < 65) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 65 && laserCounter < 70) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 70 && laserCounter < 75) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 75 && laserCounter < 80) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 80 && laserCounter < 85) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 85 && laserCounter < 90) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 95 && laserCounter < 100) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 100 && laserCounter < 105) {
            projectileImage = projectileImageB;
        } else if (laserCounter >= 105 && laserCounter < 110) {
            projectileImage = projectileImageA;
        } else if (laserCounter >= 110 && laserCounter < 115) {
            projectileImage = projectileImageB;
        } else {
            projectileImage = projectileImageA;
        }

        if (laserCounter % 5 == 0) {
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
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject) {
        return new ResultOfDetectColisionWithProjectile(false, new Point(0, 0));
    }

    @Override
    public void doOnCollision(Point collisionPoint) {
        double distanceFromIntersection = Math.sqrt(Math.pow(worldPossition.getCoordX() - collisionPoint.getCoordX(), 2) + Math.pow(worldPossition.getCoordY() - collisionPoint.getCoordY(), 2));
        System.out.println(distanceFromIntersection);
        if (distanceFromIntersection < 96){
            projectileImageA = LoadAllResources.getMapOfAllImages().get("spiderLaser11");
            projectileImageB = LoadAllResources.getMapOfAllImages().get("spiderLaser12");
        } else if (distanceFromIntersection >= 96 && distanceFromIntersection < 128){
            projectileImageA = LoadAllResources.getMapOfAllImages().get("spiderLaser21");
            projectileImageB = LoadAllResources.getMapOfAllImages().get("spiderLaser22");
        } else if (distanceFromIntersection >= 128 && distanceFromIntersection < 160){
            projectileImageA = LoadAllResources.getMapOfAllImages().get("spiderLaser31");
            projectileImageB = LoadAllResources.getMapOfAllImages().get("spiderLaser32");
        } else if (distanceFromIntersection >= 160 && distanceFromIntersection < 192){
            projectileImageA = LoadAllResources.getMapOfAllImages().get("spiderLaser41");
            projectileImageB = LoadAllResources.getMapOfAllImages().get("spiderLaser42");
        } else if (distanceFromIntersection >= 192 && distanceFromIntersection < 224){
            projectileImageA = LoadAllResources.getMapOfAllImages().get("spiderLaser51");
            projectileImageB = LoadAllResources.getMapOfAllImages().get("spiderLaser52");
        } else {
            projectileImageA = LoadAllResources.getMapOfAllImages().get("spiderLaser61");
            projectileImageB = LoadAllResources.getMapOfAllImages().get("spiderLaser62");
        }
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
    }

    @Override
    public Shape getProjectileShape() {
        double endPositionX = worldPossition.getCoordX() - Math.cos(Math.toRadians(spiderRobot.getAngleOfSpiderTower() - 90)) * 250;
        double endPositionY = worldPossition.getCoordY() - Math.sin(Math.toRadians(spiderRobot.getAngleOfSpiderTower() - 90)) * 250;
        return new Line(worldPossition.getCoordX(), worldPossition.getCoordY(), endPositionX, endPositionY);
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }

}
