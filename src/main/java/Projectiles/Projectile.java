/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectiles;

import Enemy.Enemy;
import GameObject.GameObjectWithDistanceDetection;
import GameObject.ObjectWithCollision;
import GameObject.Point;
import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public abstract class Projectile extends GameObjectWithDistanceDetection {

    protected double angleOfFiredShot = 0;
    protected Image projectileImage;
    protected Enemy enemyWhoShootedThisProjectile;
    protected Polygon projectilePolygon;
    protected ObjectWithCollision objectToIgnore = null;
    protected boolean firedFromWall = false;
    protected int damage;

    public Projectile(int damage, GraphicsContext graphicsContext, double angleOfFiredShot, Point position, Enemy enemy, double width, double height, boolean firedFromWall, MonitorWindow monitorWindow) {
        super(position, width, height, 3, graphicsContext, monitorWindow);
        this.damage = damage;
        this.angleOfFiredShot = angleOfFiredShot;
        this.enemyWhoShootedThisProjectile = enemy;
        this.firedFromWall = firedFromWall;
    }

    public abstract void moveProjectile();

    public abstract boolean hasProjectileReachedDestination();

    public abstract boolean projectileExplosion();

    public abstract Shape getProjectileShape();
    
    public abstract void doOnProjectileHittingNothing();

    public Enemy getEnemyWhoShootedThisProjectile() {
        return enemyWhoShootedThisProjectile;
    }

    public ObjectWithCollision getObjectToIgnore() {
        return objectToIgnore;
    }

    public void setObjectToIgnore(ObjectWithCollision objectToIgnore) {
        this.objectToIgnore = objectToIgnore;
    }

    public boolean isFiredFromWall() {
        return firedFromWall;
    }

    public int getDamage() {
        return damage;
    }
}
