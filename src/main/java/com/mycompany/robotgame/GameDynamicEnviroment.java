/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import EnviromentObjects.LaserGroundHits;
import java.util.Iterator;
import EnviromentObjects.MinigunGroundHits;
import GameObject.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import playerRobot.PlayerRobot;
/**
 *
 * @author Dendra
 */
public class GameDynamicEnviroment {
    private List<MinigunGroundHits> allMinigunGroundHits = new ArrayList<>();
    private List<LaserGroundHits> allLaserGroundHits = new ArrayList<>();
    private GraphicsContext graphicsContext;
    private MonitorWindow monitorWindow;

    public GameDynamicEnviroment(GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;
    }

    public void paintAllMinigunsHitsOnGround() {
        Iterator<MinigunGroundHits> iterator = allMinigunGroundHits.iterator();
        while (iterator.hasNext()) {
            MinigunGroundHits minigunHitIntoGround = iterator.next();
            minigunHitIntoGround.paint();
            if (minigunHitIntoGround.getNumberOfFramesBeingDisplayed() < 1) {
                iterator.remove();
            }
        }
    }
    
    public void paintAllLaserHitsOnGround() {
        Iterator<LaserGroundHits> iterator = allLaserGroundHits.iterator();
        while (iterator.hasNext()) {
            LaserGroundHits laserGroundHits = iterator.next();
            laserGroundHits.paint();
            if (laserGroundHits.getNumberOfFramesBeingDisplayed() < 1) {
                iterator.remove();
            }
        }
    }
    
    
    public void generateNewMinigunHitOnGround(Point position){
        allMinigunGroundHits.add(new MinigunGroundHits(position, graphicsContext, monitorWindow));
    }
    
    public void generateNewLaserHitOnGround(Point position){
        allLaserGroundHits.add(new LaserGroundHits(position, graphicsContext, monitorWindow));
    }
}
