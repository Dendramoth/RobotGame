/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encounter;

import Enemy.EvilDroneMarkTwo;
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
       int numberOfDrones = r.nextInt(8) + 2;
       
       for (int i = 0; i < numberOfDrones; i++){
            int direction = r.nextInt(4);
            switch (direction){
                case 0:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new EvilDroneMarkTwo(new Point(monitorWindow.getPositionInWorld().getCoordX() - 200, monitorWindow.getPositionInWorld().getCoordY() - r.nextInt(1024)), 4, 15, 20, graphicsContext, gridTable, monitorWindow), 0));
                    break;
                case 1:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new EvilDroneMarkTwo(new Point(monitorWindow.getPositionInWorld().getCoordX() + 2100, monitorWindow.getPositionInWorld().getCoordY() - r.nextInt(1024)), 4, 15, 20, graphicsContext, gridTable, monitorWindow), 0));
                    break;
                case 2:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new EvilDroneMarkTwo(new Point(monitorWindow.getPositionInWorld().getCoordX() + r.nextInt(1920), monitorWindow.getPositionInWorld().getCoordY() -200), 4, 15, 20, graphicsContext, gridTable, monitorWindow), 0));
                    break;
                case 3:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new EvilDroneMarkTwo(new Point(monitorWindow.getPositionInWorld().getCoordX()  + r.nextInt(1920), monitorWindow.getPositionInWorld().getCoordY() + 1250), 4, 15, 20, graphicsContext, gridTable, monitorWindow), 0));
                    break;
                default:
                    listOfEnemiesForEncounterOne.add(new EnemyWithSpecificationForEncounter(new EvilDroneMarkTwo(new Point(monitorWindow.getPositionInWorld().getCoordX() -200, monitorWindow.getPositionInWorld().getCoordY() -200), 4, 15, 20, graphicsContext, gridTable, monitorWindow), 0));
            }
            
       }
   
    }

    public List<EnemyWithSpecificationForEncounter> getListOfEnemiesForEncounterOne() {
        return listOfEnemiesForEncounterOne;
    }
    
}
