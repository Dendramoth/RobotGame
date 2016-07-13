/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

/**
 *
 * @author styma01
 */
public abstract class BackgroundObject extends GameObject{

    public BackgroundObject(Point possition, double width, double heigh) {
        super(possition, width, heigh);
    }

    @Override
    public void paintGameObject() {
    }
    
    public abstract void paintStaticGameObject(double worldPossitionOfPlayerX, double worldPossitionOfPlayerY);
    
}
