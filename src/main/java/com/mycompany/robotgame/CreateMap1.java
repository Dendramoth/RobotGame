/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import EnviromentObjects.SpaceShipWreckage;
import GameObject.GameObject;
import GameObject.Point;
import MapGridTable.GridCell;
import MapGridTable.GridTable;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public class CreateMap1 {
    private GraphicsContext graphicsContext;
    
    public CreateMap1(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
    
    public GridTable generatedObjectForGame(GridTable gridTable){
        SpaceShipWreckage spaceShipWreckage = new SpaceShipWreckage(new Point(2048, 9216), 512, 512, graphicsContext);
        gridTable.insertGameObjectIntoGrid(spaceShipWreckage);
        
        return gridTable;
    }
}
