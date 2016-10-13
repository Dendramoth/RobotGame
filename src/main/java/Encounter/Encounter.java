/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encounter;

import Enemy.EnemyContainer;
import MapGridTable.GridTable;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
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
    
    public void generateNewEncounter(){
        timeToGenerateNewEncounter--;
        if (timeToGenerateNewEncounter <= 0){
            timeToGenerateNewEncounter = 1200;
            EncounterOne encounterOne = new EncounterOne(gridTable, monitorWindow, graphicsContext);
            listOfEnemies.addAll(encounterOne.getListOfEnemiesForEncounterOne());
        }
    }
    
    public void continuousSummoningOfAddsForAlreadyGeneratedEncounters(){
        for (EnemyWithSpecificationForEncounter enemyWithSpecificationForEncounter : listOfEnemies){
            enemyContainer.addEnemy(enemyWithSpecificationForEncounter.getEnemy());
        }
        listOfEnemies.clear();
    }
    
    
}
