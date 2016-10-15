/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encounter;

import Enemy.Enemy;
import Enemy.EnemyContainer;
import Enemy.EvilDroneMarkTwo;
import GameObject.Point;
import MapGridTable.GridTable;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public class Encounter {

    private EnemyContainer enemyContainer;
    private List<EnemyWithSpecificationForEncounter> listOfEnemies = new ArrayList<>();
    private int timeToGenerateNewEncounter = 600;
    private GridTable gridTable;
    private MonitorWindow monitorWindow;
    private GraphicsContext graphicsContext;

    public Encounter(EnemyContainer enemyContainer, GridTable gridTable, MonitorWindow monitorWindow, GraphicsContext graphicsContext) {
        this.enemyContainer = enemyContainer;
        this.gridTable = gridTable;
        this.monitorWindow = monitorWindow;
        this.graphicsContext = graphicsContext;
    }

    public void generateNewEncounter() {
        timeToGenerateNewEncounter--;
        if (timeToGenerateNewEncounter <= 0) {
            timeToGenerateNewEncounter = 2000;
            EncounterOne encounterOne = new EncounterOne(gridTable, monitorWindow, graphicsContext);
            listOfEnemies.addAll(encounterOne.getListOfEnemiesForEncounterOne());
        }
    }

    public void continuousSummoningOfAddsForAlreadyGeneratedEncounters() {
        Iterator<EnemyWithSpecificationForEncounter> iterator = listOfEnemies.iterator();
        while (iterator.hasNext()) {
            EnemyWithSpecificationForEncounter enemyWithSpecificationForEncounter = iterator.next();
            enemyWithSpecificationForEncounter.incrementTimeDelay();
            if (enemyWithSpecificationForEncounter.getCurrentTimeDelayFromEncounterStart() >= enemyWithSpecificationForEncounter.getDelayToSpawnEnemyInSeconds()) {
                enemyContainer.addEnemy(createEnemyBasedOnSpecification(enemyWithSpecificationForEncounter));
                iterator.remove();
            }
        }
    }
    
    private Enemy createEnemyBasedOnSpecification(EnemyWithSpecificationForEncounter enemyWithSpecificationForEncounter){
        String enemyType = enemyWithSpecificationForEncounter.getEnemyType();
        switch (enemyType){
            case "EvilDroneMarkTwo" :
                return new EvilDroneMarkTwo(new Point(monitorWindow.getPositionInWorld().getCoordX() + enemyWithSpecificationForEncounter.getPositionOffset().getCoordX(), monitorWindow.getPositionInWorld().getCoordY() + enemyWithSpecificationForEncounter.getPositionOffset().getCoordY()), 4, 12, 16, graphicsContext, gridTable, monitorWindow);
        }
        
        return new EvilDroneMarkTwo(new Point(monitorWindow.getPositionInWorld().getCoordX(), monitorWindow.getPositionInWorld().getCoordY()), 4, 12, 16, graphicsContext, gridTable, monitorWindow);
    }
}

