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
    
    private List<Projectile> allFiredProjectiles = new ArrayList<>();
    private List<Projectile> allExplodingProjectiles = new ArrayList<>();
    
    public void moveAllProjectiles(){
        Iterator<Projectile> iterator = allFiredProjectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.moveProjectile();
        }
    }
    
    public void paintAllProjectiles(){
        Iterator<Projectile> iterator = allFiredProjectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.paintGameObject();
        }
    }
    
    public void testAllProjectilesForReachingMaximumRange(){
        Iterator<Projectile> iterator = allFiredProjectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            if (projectile.hasProjectileReachedDestination()){
                allExplodingProjectiles.add(projectile);
                iterator.remove();
            }
        }
    }
    
    public void paintAllProjectilesExplosions(){
        Iterator<Projectile> iterator = allExplodingProjectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            if (!projectile.projectileExplosion()){
                iterator.remove();
            }
        }
    }
    
    public void addProjectileToContainer(Projectile projectile) {
        allFiredProjectiles.add(projectile);
    }

    public List<Projectile> getAllFiredProjectiles() {
        return allFiredProjectiles;
    }
    
    
    
}
