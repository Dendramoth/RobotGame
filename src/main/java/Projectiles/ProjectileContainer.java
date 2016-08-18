/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Dendra
 */
public class ProjectileContainer {
    
    private List<Projectile> allFiredRockets = new ArrayList<>();
    
    public void moveAllProjectiles(){
        Iterator<Projectile> iterator = allFiredRockets.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.moveProjectile();
        }
    }
    
    public void paintAllProjectiles(){
        Iterator<Projectile> iterator = allFiredRockets.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.paintGameObject();
        }
    }
    
    public void addProjectileToContainer(Projectile projectile) {
        allFiredRockets.add(projectile);
    }
    
}
