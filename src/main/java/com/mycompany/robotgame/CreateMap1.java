/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import EnviromentObjects.BackgroundHex;
import EnviromentObjects.SpaceShipWreckage;
import GameObject.Point;
import MapGridTable.GridTable;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class CreateMap1 {
    private GraphicsContext graphicsContext;
    
    public CreateMap1(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
    
    public void generatedObjectForGame(GridTable gridTable){
        SpaceShipWreckage spaceShipWreckage = new SpaceShipWreckage(new Point(2048, 9216), 512, 512, graphicsContext);
        gridTable.insertGameObjectIntoGridCell(spaceShipWreckage);
    }
    
    public void generateBackground(GridTable gridTable) {
        Image image;
        Random random = new Random();
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 39; j++) {
                switch (random.nextInt(15)) {
                    case 0:
                        image = LoadAllResources.getMapOfAllImages().get("terrainCrater");
                        break;
                    case 1:
                        image = LoadAllResources.getMapOfAllImages().get("terrain2");
                        break;
                    case 2:
                        image = LoadAllResources.getMapOfAllImages().get("terrain3");
                        break;
                    case 3:
                        image = LoadAllResources.getMapOfAllImages().get("terrain4");
                        break;
                    case 4:
                        image = LoadAllResources.getMapOfAllImages().get("terrain5");
                        break;
                    case 5:
                        image = LoadAllResources.getMapOfAllImages().get("terrain6");
                        break;
                    case 6:
                        image = LoadAllResources.getMapOfAllImages().get("terrain7");
                        break;
                    case 7:
                        image = LoadAllResources.getMapOfAllImages().get("terrain8");
                        break;
                    case 8:
                        image = LoadAllResources.getMapOfAllImages().get("terrain9");
                        break;
                    case 9:
                        image = LoadAllResources.getMapOfAllImages().get("terrain10");
                        break;
                    case 10:
                        image = LoadAllResources.getMapOfAllImages().get("terrain6");
                        break;
                    case 11:
                        image = LoadAllResources.getMapOfAllImages().get("terrain7");
                        break;
                    case 12:
                        image = LoadAllResources.getMapOfAllImages().get("terrain8");
                        break;
                    case 13:
                        image = LoadAllResources.getMapOfAllImages().get("terrain9");
                        break;
                    case 14:
                        image = LoadAllResources.getMapOfAllImages().get("terrain10");
                        break;
                    default:
                        image = LoadAllResources.getMapOfAllImages().get("terrain10");
                        break;
                }
                BackgroundHex backgroundHex = new BackgroundHex(new Point(i * 256, j * 256), 256, 256, image, graphicsContext);
                gridTable.insertBackgroundIntoGridCell(backgroundHex);
            }
        }
    }
    
}
