/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encounter;

import GameObject.Point;
import MapGridTable.GridTable;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 *  EncounterOne is a random encounter.
 * @author Dendra
 */
public class EncounterOne {
    private GridTable gridTable;
    private MonitorWindow monitorWindow;
    private GraphicsContext graphicsContext;
    private List<EnemyWithSpecificationForEncounter> listOfEnemiesForEncounterOne = new ArrayList<>();

    public EncounterOne(GridTable gridTable, MonitorWindow monitorWindow, GraphicsContext graphicsContext) {
        this.gridTable = gridTable;
        this.monitorWindow = monitorWindow;
        this.graphicsContext = graphicsContext;
        addEnemies();
    }
    
    private void addEnemies(){
       Random r = new Random();
       int numberOfDrones = r.nextInt(6) + 4;
       
       for (int i = 0; i <= numberOfDrones; i++){
            int direction = r.nextInt(4);
            int timeDelay = r.nextInt(5) * 60;
            switch (direction){
                case 0:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new Point( - 200, r.nextInt(1024)), 0, timeDelay, "EvilDroneMarkTwo"));
                    break;
                case 1:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new Point( + 2100, r.nextInt(1024)), 0, timeDelay, "EvilDroneMarkTwo"));
                    break;
                case 2:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new Point( r.nextInt(1024), - 200), 0, timeDelay, "EvilDroneMarkTwo"));
                    break;
                case 3:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new Point( r.nextInt(1024), + 1250), 0, timeDelay, "EvilDroneMarkTwo"));
                    break;
                default:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new Point( - 200, r.nextInt(1024)), 0, timeDelay,"EvilDroneMarkTwo"));
            }
            
       }
   
    }

    public List<EnemyWithSpecificationForEncounter> getListOfEnemiesForEncounterOne() {
        return listOfEnemiesForEncounterOne;
    }
    
}
