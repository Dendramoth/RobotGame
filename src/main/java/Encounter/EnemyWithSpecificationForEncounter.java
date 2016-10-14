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
    private int delayToSpawnEnemyInSeconds;
    private int currentTimeDelayFromEncounterStart = 0;

    public EnemyWithSpecificationForEncounter(Enemy enemy, double angleOfEnemy, int delayToSpawnEnemyInSeconds) {
        this.enemy = enemy;
        this.angleOfEnemy = angleOfEnemy;
        this.delayToSpawnEnemyInSeconds = delayToSpawnEnemyInSeconds;
    }
    
    public void incrementTimeDelay(){
        currentTimeDelayFromEncounterStart++;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public double getAngleOfEnemy() {
        return angleOfEnemy;
    }

    public int getDelayToSpawnEnemyInSeconds() {
        return delayToSpawnEnemyInSeconds;
    }

    public int getCurrentTimeDelayFromEncounterStart() {
        return currentTimeDelayFromEncounterStart;
    }
    
    
}
