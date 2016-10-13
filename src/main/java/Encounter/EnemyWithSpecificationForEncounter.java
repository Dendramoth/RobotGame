/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encounter;

import Enemy.Enemy;

/**
 *
 * @author Dendra
 */
public class EnemyWithSpecificationForEncounter {
    private Enemy enemy;
    private double angleOfEnemy;

    public EnemyWithSpecificationForEncounter(Enemy enemy, double angleOfEnemy) {
        this.enemy = enemy;
        this.angleOfEnemy = angleOfEnemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public double getAngleOfEnemy() {
        return angleOfEnemy;
    }
    
    
}
