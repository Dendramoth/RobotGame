/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import javafx.scene.shape.Shape;

/**
 *
 * @author styma01
 */
public interface ObjectWithCollision{
    public abstract boolean detectCollision(Shape shape);
    public abstract void doOnCollision();
    public abstract void doOnBeingHitByMinigun();
}
