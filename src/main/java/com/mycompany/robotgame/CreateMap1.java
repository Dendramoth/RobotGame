/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import Enemy.EnemyContainer;
import EnviromentObjects.BackgroundHex;
import EnviromentObjects.Cliffs.CliffBottomLeft;
import EnviromentObjects.Cliffs.CliffBottomRight;
import EnviromentObjects.Cliffs.CliffHorizontal;
import EnviromentObjects.Cliffs.CliffHorizontalReversed;
import EnviromentObjects.Cliffs.CliffTopLeft;
import EnviromentObjects.Cliffs.CliffTopRight;
import EnviromentObjects.Cliffs.CliffVertical;
import EnviromentObjects.Cliffs.CliffVerticalReversed;
import EnviromentObjects.Containers.BoxTriangle;
import EnviromentObjects.Containers.ContainerOpenHorizontal;
import EnviromentObjects.Containers.ContainerOpenVertical;
import EnviromentObjects.Containers.SimpleBox;
import EnviromentObjects.Craters.CraterBig;
import EnviromentObjects.Craters.CraterMedium;
import EnviromentObjects.Craters.CraterSmallOne;
import EnviromentObjects.Craters.CraterSmallThree;
import EnviromentObjects.Craters.CraterSmallTwo;
import EnviromentObjects.DroneBarracks;
import EnviromentObjects.InnerFortressWallNorth;
import EnviromentObjects.InnerFortressWallSouth;
import EnviromentObjects.OuterFortressWallsEast;
import EnviromentObjects.OuterFortressWallsWest;
import EnviromentObjects.Rocks.RockLargeOne;
import EnviromentObjects.Rocks.RockLargeTwo;
import EnviromentObjects.Rocks.RockMediumOne;
import EnviromentObjects.Rocks.RockMediumThree;
import EnviromentObjects.Rocks.RockMediumTwo;
import EnviromentObjects.Satelite;
import EnviromentObjects.Skeletons.DinosaurusSkeleton;
import EnviromentObjects.SpaceShipWreckage;
import GameObject.Point;
import MapGridTable.GridTable;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import playerRobot.PlayerRobot;

/**
 *
 * @author Dendra
 */
public class CreateMap1 {

    private final GraphicsContext graphicsContext;
    private final MonitorWindow monitorWindow;
    private final PlayerRobot playerRobot;
    private final EnemyContainer enemyContainer;
    private GraphicsContext enemyGraphicsContext;
    private GridTable gridTable;

    public CreateMap1(GraphicsContext graphicsContext, MonitorWindow monitorWindow, PlayerRobot playerRobot, EnemyContainer enemyContainer, GraphicsContext enemyGraphicsContext, GridTable gridTable) {
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;
        this.playerRobot = playerRobot;
        this.enemyContainer = enemyContainer;
        this.enemyGraphicsContext = enemyGraphicsContext;
        this.gridTable = gridTable;
    }

    public void generatedObjectForGame(GridTable gridTable) {
        generatedObjectsForGameWithoutCollision(gridTable);
        
        SpaceShipWreckage spaceShipWreckage = new SpaceShipWreckage(new Point(4048, 8216), 512, 512, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(spaceShipWreckage);
        
        OuterFortressWallsWest outerFortressWallsWest = new OuterFortressWallsWest(new Point(3600,6000), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(outerFortressWallsWest);
        OuterFortressWallsEast outerFortressWallsEast = new OuterFortressWallsEast(new Point(4450,6000), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(outerFortressWallsEast);
        InnerFortressWallNorth innerFortressWallNorth = new InnerFortressWallNorth(new Point(4100,6400), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallNorth);
        InnerFortressWallSouth innerFortressWallSouth = new InnerFortressWallSouth(new Point(4100,7000), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallSouth);
        
        SimpleBox simpleBox = new SimpleBox(new Point(4000, 7600), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox);
        BoxTriangle triangleBox = new BoxTriangle(new Point(4000, 7800), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(triangleBox);
        ContainerOpenHorizontal containerOpenHorizontal = new ContainerOpenHorizontal(new Point(4000, 8100), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenHorizontal);
        ContainerOpenVertical containerOpenVertical = new ContainerOpenVertical(new Point(3300, 8300), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenVertical);
        
        RockMediumOne rockMediumOne = new RockMediumOne(new Point(5000, 7900), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne);
        RockMediumTwo rockMediumTwo = new RockMediumTwo(new Point(5200, 7900), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo);
        RockMediumThree rockMediumThree = new RockMediumThree(new Point(5400, 7900), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree);
        RockLargeOne rockLargeOne = new RockLargeOne(new Point(5000, 7600), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeOne);
        RockLargeTwo rockLargeTwo = new RockLargeTwo(new Point(5300, 7600), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo);
        
        Satelite satelite = new Satelite(new Point(4228,6600), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(satelite);
        
        DinosaurusSkeleton dinosaurusSkeleton = new DinosaurusSkeleton(new Point(2600,8100), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(dinosaurusSkeleton);
        
        DroneBarracks droneBarracks = new DroneBarracks(new Point(3000, 7600), 256, 384, graphicsContext, monitorWindow, playerRobot, enemyContainer, enemyGraphicsContext, this.gridTable);
        gridTable.insertGameObjectIntoGridCell(droneBarracks);
    }
    
    private void generatedObjectsForGameWithoutCollision(GridTable gridTable){
        CraterBig craterBig = new CraterBig(new Point(5000,8500), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig);
        CraterSmallOne craterSmallOne = new CraterSmallOne(new Point(5000,8250), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne);
        CraterSmallTwo craterSmallTwo = new CraterSmallTwo(new Point(5100,8250), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo);
        CraterSmallThree craterSmallThree = new CraterSmallThree(new Point(5200,8250), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree);
        CraterMedium craterMedium = new CraterMedium(new Point(5200,8350), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium);
    }

    public void generateGameMapBorders(GridTable gridTable) {
        CliffBottomLeft cliffBottomLeft = new CliffBottomLeft(new Point(2015, 12072), 256, 256, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(cliffBottomLeft);
        CliffBottomRight cliffBottomRight = new CliffBottomRight(new Point(8400, 12072), 256, 256, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(cliffBottomRight);
        CliffTopRight cliffTopRight = new CliffTopRight(new Point(8400, 2600), 256, 256, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(cliffTopRight);
        CliffTopLeft cliffTopLeft = new CliffTopLeft(new Point(2015, 2600), 256, 256, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(cliffTopLeft);

        for (int i = 0; i < 6; i++) {
            CliffHorizontal cliffHorizontal = new CliffHorizontal(new Point(i * 1024 + 2256, 12087), 1024, 256, graphicsContext, monitorWindow);
            gridTable.insertGameObjectIntoGridCell(cliffHorizontal);
        }
        
        for (int i = 0; i < 6; i++) {
            CliffHorizontalReversed cliffHorizontalReversed = new CliffHorizontalReversed(new Point(i * 1024 + 2271, 2590), 1024, 256, graphicsContext, monitorWindow);
            gridTable.insertGameObjectIntoGridCell(cliffHorizontalReversed);
        }

        for (int i = 1; i < 10; i++) {
            CliffVertical cliffVertical = new CliffVertical(new Point(2000, 12072 - i * 1024), 256, 1024, graphicsContext, monitorWindow);
            gridTable.insertGameObjectIntoGridCell(cliffVertical);
        }
        
        for (int i = 1; i < 10; i++) {
            CliffVerticalReversed cliffVerticalReversed = new CliffVerticalReversed(new Point(8415, 12072 - i * 1024), 256, 1024, graphicsContext, monitorWindow);
            gridTable.insertGameObjectIntoGridCell(cliffVerticalReversed);
        }
    }

    public void generateBackground(GridTable gridTable) {
        Image image;
        Random random = new Random();
        for (int i = 0; i < 38; i++) {
            for (int j = 0; j < 60; j++) {
                switch (random.nextInt(14)) {
                    case 0:
                        image = LoadAllResources.getMapOfAllImages().get("terrain1");
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
                        image = LoadAllResources.getMapOfAllImages().get("terrain11");
                        break;
                    case 11:
                        image = LoadAllResources.getMapOfAllImages().get("terrain12");
                        break;
                    case 12:
                        image = LoadAllResources.getMapOfAllImages().get("terrain13");
                        break;
                    case 13:
                        image = LoadAllResources.getMapOfAllImages().get("terrain14");
                        break;
                    default:
                        image = LoadAllResources.getMapOfAllImages().get("terrain1");
                        break;
                }
                BackgroundHex backgroundHex = new BackgroundHex(new Point(i * 256, j * 256), 256, 256, image, graphicsContext, monitorWindow);
                gridTable.insertBackgroundIntoGridCell(backgroundHex);
            }
        }
    }

}
