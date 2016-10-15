/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encounter;

import Enemy.Enemy;
import GameObject.Point;

/**
 *
 * @author Dendra
 */
public class EnemyWithSpecificationForEncounter {
    private double angleOfEnemy;
    private int delayToSpawnEnemyInSeconds;
    private int currentTimeDelayFromEncounterStart = 0;
    private Point positionOffset;
    private String enemyType = "EvilDroneMarkTwo";

    public EnemyWithSpecificationForEncounter(Point positionOffset, double angleOfEnemy, int delayToSpawnEnemyInSeconds, String enemyType) {
        this.angleOfEnemy = angleOfEnemy;
        this.delayToSpawnEnemyInSeconds = delayToSpawnEnemyInSeconds;
        this.positionOffset = positionOffset;
        this.enemyType = enemyType;
    }
    
    public void incrementTimeDelay(){
        currentTimeDelayFromEncounterStart++;
    }

    public Point getPositionOffset() {
        return positionOffset;
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

    public String getEnemyType() {
        return enemyType;
    }
    
    
}
