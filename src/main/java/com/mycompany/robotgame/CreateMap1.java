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
import EnviromentObjects.Craters.CraterMediumTwo;
import EnviromentObjects.Craters.CraterSmallOne;
import EnviromentObjects.Craters.CraterSmallThree;
import EnviromentObjects.Craters.CraterSmallTwo;
import EnviromentObjects.DroneBarracks;
import EnviromentObjects.InnerFortressWallNorth;
import EnviromentObjects.InnerFortressWallSouth;
import EnviromentObjects.OuterFortressWallsEast;
import EnviromentObjects.OuterFortressWallsWest;
import EnviromentObjects.Roads.RoadSmooth;
import EnviromentObjects.Roads.RoadSouthBridge;
import EnviromentObjects.Roads.RoadStraight;
import EnviromentObjects.Roads.RoadStraightBreaks;
import EnviromentObjects.Roads.RoadStraightBurns;
import EnviromentObjects.Roads.RoadStraightCracks;
import EnviromentObjects.Roads.RoadStraightCrater;
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
        
        SpaceShipWreckage spaceShipWreckage = new SpaceShipWreckage(new Point(6431, 8040), 512, 512, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(spaceShipWreckage);
        
        OuterFortressWallsWest outerFortressWallsWest = new OuterFortressWallsWest(new Point(3600,6000), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(outerFortressWallsWest);
        OuterFortressWallsEast outerFortressWallsEast = new OuterFortressWallsEast(new Point(4450,6000), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(outerFortressWallsEast);
        InnerFortressWallNorth innerFortressWallNorth = new InnerFortressWallNorth(new Point(4100,6400), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallNorth);
        InnerFortressWallSouth innerFortressWallSouth = new InnerFortressWallSouth(new Point(4100,7000), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallSouth);
        
        SimpleBox simpleBox = new SimpleBox(new Point(5215, 4200), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox);
        BoxTriangle triangleBox = new BoxTriangle(new Point(4000, 7800), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(triangleBox);
        ContainerOpenHorizontal containerOpenHorizontal = new ContainerOpenHorizontal(new Point(4000, 8100), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenHorizontal);
        ContainerOpenVertical containerOpenVertical = new ContainerOpenVertical(new Point(3300, 8300), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenVertical);
        
        //rockmediumone
        RockMediumOne rockMediumOne1 = new RockMediumOne(new Point(2655,10856), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne1);
        RockMediumOne rockMediumOne2 = new RockMediumOne(new Point(2847,10280), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne2);
        RockMediumOne rockMediumOne3 = new RockMediumOne(new Point(4319,11048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne3);
        //rockmediumtwo
        RockMediumTwo rockMediumTwo1 = new RockMediumTwo(new Point(2463,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo1);
        RockMediumTwo rockMediumTwo2 = new RockMediumTwo(new Point(3295,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo2);
        RockMediumTwo rockMediumTwo3 = new RockMediumTwo(new Point(3999,11368), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo3);
        RockMediumTwo rockMediumTwo4 = new RockMediumTwo(new Point(4575,10984), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo4);
        RockMediumTwo rockMediumTwo5 = new RockMediumTwo(new Point(4703,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo5);
        //rockmediumthree
        RockMediumThree rockMediumThree1 = new RockMediumThree(new Point(3295,10216), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree1);
        RockMediumThree rockMediumThree2 = new RockMediumThree(new Point(4447,10728), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree2);
        //rock large one
        RockLargeOne rockLargeOne1 = new RockLargeOne(new Point(2911,10664), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeOne1);
        //rock large two
        RockLargeTwo rockLargeTwo1 = new RockLargeTwo(new Point(4319,11496), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo1);
        
        Satelite satelite = new Satelite(new Point(4228,6600), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(satelite);
        
        DinosaurusSkeleton dinosaurusSkeleton = new DinosaurusSkeleton(new Point(4063,9768), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(dinosaurusSkeleton);
        
        DroneBarracks droneBarracks = new DroneBarracks(new Point(3000, 7600), 256, 384, graphicsContext, monitorWindow, playerRobot, enemyContainer, enemyGraphicsContext, this.gridTable);
        gridTable.insertGameObjectIntoGridCell(droneBarracks);
    }
    
    private void generatedObjectsForGameWithoutCollision(GridTable gridTable){
        //cratersmallone
        CraterSmallOne craterSmallOne1 = new CraterSmallOne(new Point(2591,11304), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne1);
        CraterSmallOne craterSmallOne2 = new CraterSmallOne(new Point(2463,10984), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne2);
        CraterSmallOne craterSmallOne3 = new CraterSmallOne(new Point(3231,11048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne3);
        CraterSmallOne craterSmallOne4 = new CraterSmallOne(new Point(3423,11688), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne4);
        CraterSmallOne craterSmallOne5 = new CraterSmallOne(new Point(3679,10728), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne5);
        CraterSmallOne craterSmallOne6 = new CraterSmallOne(new Point(3871,10344), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne6);
        //cratersmalltwo
        CraterSmallTwo craterSmallTwo1 = new CraterSmallTwo(new Point(2719,11560), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo1);
        CraterSmallTwo craterSmallTwo2 = new CraterSmallTwo(new Point(3103,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo2);
        CraterSmallTwo craterSmallTwo3 = new CraterSmallTwo(new Point(3487,11048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo3);
        CraterSmallTwo craterSmallTwo4 = new CraterSmallTwo(new Point(3807,10664), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo4);
        //cratersmallthree
        CraterSmallThree craterSmallThree1 = new CraterSmallThree(new Point(2719,11816), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree1);
        CraterSmallThree craterSmallThree2 = new CraterSmallThree(new Point(2847,10536), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree2);
        CraterSmallThree craterSmallThree3 = new CraterSmallThree(new Point(3487,11304), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree3);
        CraterSmallThree craterSmallThree4 = new CraterSmallThree(new Point(3551,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree4);
        CraterSmallThree craterSmallThree5 = new CraterSmallThree(new Point(3999,10600), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree5);
        CraterSmallThree craterSmallThree6 = new CraterSmallThree(new Point(3999,11048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree6);
        CraterSmallThree craterSmallThree7 = new CraterSmallThree(new Point(4767,11368), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree7);
        //cratermedium(one)
        CraterMedium craterMedium1 = new CraterMedium(new Point(3039,11688), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium1);
        CraterMedium craterMedium2 = new CraterMedium(new Point(3743,11176), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium2);
        CraterMedium craterMedium3 = new CraterMedium(new Point(3679,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium3);
        CraterMedium craterMedium4 = new CraterMedium(new Point(4255,11240), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium4);
        //cratermediumtwo
        CraterMediumTwo craterMediumTwo1 = new CraterMediumTwo(new Point(2911,11304), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo1);
        CraterMediumTwo craterMediumTwo2 = new CraterMediumTwo(new Point(3679,11496), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo2);
        CraterMediumTwo craterMediumTwo3 = new CraterMediumTwo(new Point(3935,10792), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo3);
        CraterMediumTwo craterMediumTwo4 = new CraterMediumTwo(new Point(4767,10728), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo4);
        //craterbig
        CraterBig craterBig1 = new CraterBig(new Point(3615,10856), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig1);
        
        RoadSmooth roadSmooth = new RoadSmooth(new Point(4995,5182), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadSmooth);
        RoadStraightCrater roadStraightCrater2 = new RoadStraightCrater(new Point(4995,5606), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightCrater2);
        RoadStraight roadStraight7 = new RoadStraight(new Point(4995,6030), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraight7);
        RoadStraightBurns roadStraightBurns3 = new RoadStraightBurns(new Point(4995,6454), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightBurns3);
        RoadStraightBreaks roadStraightBreaks2 = new RoadStraightBreaks(new Point(4995,6878), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightBreaks2);
        RoadStraight roadStraight6 = new RoadStraight(new Point(4995,7302), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraight6);
        RoadStraightCracks roadStraightCracks2 = new RoadStraightCracks(new Point(4995,7726), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightCracks2);
        RoadStraight roadStraight5 = new RoadStraight(new Point(4995,8150), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraight5);
        RoadStraightCrater roadStraightCrater = new RoadStraightCrater(new Point(4995,8574), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightCrater);
        RoadStraight roadStraight4 = new RoadStraight(new Point(4995,8998), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraight4);
        RoadStraight roadStraight3 = new RoadStraight(new Point(4995,9422), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraight3);
        RoadStraightCracks roadStraightCracks = new RoadStraightCracks(new Point(4995,9846), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightCracks);
        RoadStraightBreaks roadStraightBreaks = new RoadStraightBreaks(new Point(4995,10270), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightBreaks);
        RoadStraight roadStraight2 = new RoadStraight(new Point(4995,10694), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraight2);
        RoadStraight roadStraight = new RoadStraight(new Point(4995,11118), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraight);
        RoadStraightBurns roadStraightBurns = new RoadStraightBurns(new Point(4995,11542), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadStraightBurns);
        RoadSouthBridge roadSouthBridge = new RoadSouthBridge(new Point(4995,11966), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(roadSouthBridge);
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
