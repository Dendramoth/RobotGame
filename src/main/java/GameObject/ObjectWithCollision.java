/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Projectiles.Projectile;
import javafx.scene.shape.Shape;

/**
 *
 * @author styma01
 */
public interface ObjectWithCollision{
    public abstract boolean detectCollision(Shape shape);
    public abstract ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape shape, Point positionOfColidingObject);
    public abstract void doOnCollision(Point collisionPoint);
    public abstract void doOnBeingHitByMinigun(Point intersectionPoint);
    public abstract void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile);
}
