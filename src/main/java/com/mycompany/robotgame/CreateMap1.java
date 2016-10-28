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
import EnviromentObjects.DryLand.DryLandLargeOne;
import EnviromentObjects.DryLand.DryLandLargeTwo;
import EnviromentObjects.DryLand.DryLandHorizontal;
import EnviromentObjects.DryLand.DryLandVertical;
import EnviromentObjects.DryLand.DryLandSmallOne;
import EnviromentObjects.DryLand.DryLandSmallTwo;
import EnviromentObjects.DryLand.DryLandSmallThree;
import GameObject.Point;
import MapGridTable.GridTable;
import TitanRemains.TitanHead;
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
    private GridTable gridTable;

    public CreateMap1(GraphicsContext graphicsContext, MonitorWindow monitorWindow, PlayerRobot playerRobot, EnemyContainer enemyContainer, GridTable gridTable) {
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;
        this.playerRobot = playerRobot;
        this.enemyContainer = enemyContainer;
        this.gridTable = gridTable;
    }

    public void generatedObjectForGame(GridTable gridTable) {
        generatedObjectsForGameWithoutCollision(gridTable);
        
    /*    SpaceShipWreckage spaceShipWreckage = new SpaceShipWreckage(new Point(6431, 8040), 512, 512, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(spaceShipWreckage);
        
        OuterFortressWallsWest outerFortressWallsWest = new OuterFortressWallsWest(new Point(4447,3496), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(outerFortressWallsWest);
        OuterFortressWallsEast outerFortressWallsEast = new OuterFortressWallsEast(new Point(5343,3496), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(outerFortressWallsEast);
        InnerFortressWallNorth innerFortressWallNorth = new InnerFortressWallNorth(new Point(4959,3880), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallNorth);
        InnerFortressWallSouth innerFortressWallSouth = new InnerFortressWallSouth(new Point(4959,4520), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallSouth);
        
        OuterFortressWallsWest outerFortressWallsWest1 = new OuterFortressWallsWest(new Point(2847,6568), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(outerFortressWallsWest1);
        InnerFortressWallNorth innerFortressWallNorth1 = new InnerFortressWallNorth(new Point(3615,6696), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallNorth1);
        InnerFortressWallSouth innerFortressWallSouth1 = new InnerFortressWallSouth(new Point(3615,7848), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(innerFortressWallSouth1);*/
        
   //     SimpleBox simpleBox1 = new SimpleBox(new Point(4703, 4648), graphicsContext, monitorWindow);
   //     gridTable.insertGameObjectIntoGridCell(simpleBox1);
        SimpleBox simpleBox2 = new SimpleBox(new Point(3551, 7016), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox2);
        SimpleBox simpleBox3 = new SimpleBox(new Point(3551, 7144), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox3);
   /*     SimpleBox simpleBox4 = new SimpleBox(new Point(3551, 7272), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox4);
        SimpleBox simpleBox5 = new SimpleBox(new Point(3551, 7400), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox5);
        SimpleBox simpleBox6 = new SimpleBox(new Point(3807, 7016), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox6);
        SimpleBox simpleBox7 = new SimpleBox(new Point(3807, 7144), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox7);
        SimpleBox simpleBox8 = new SimpleBox(new Point(3807, 7272), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox8);
        SimpleBox simpleBox9 = new SimpleBox(new Point(3807, 7400), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(simpleBox9);
        BoxTriangle triangleBox1 = new BoxTriangle(new Point(4575,3624), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(triangleBox1);
        BoxTriangle triangleBox2 = new BoxTriangle(new Point(2975,6696), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(triangleBox2);
        BoxTriangle triangleBox3 = new BoxTriangle(new Point(3935,7016), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(triangleBox3);
        ContainerOpenHorizontal containerOpenHorizontal1 = new ContainerOpenHorizontal(new Point(5599,3880), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenHorizontal1);
        ContainerOpenHorizontal containerOpenHorizontal2 = new ContainerOpenHorizontal(new Point(2975,7848), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenHorizontal2);
        ContainerOpenHorizontal containerOpenHorizontal3 = new ContainerOpenHorizontal(new Point(3679,7720), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenHorizontal3);
        ContainerOpenVertical containerOpenVertical = new ContainerOpenVertical(new Point(5727,4520), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(containerOpenVertical);
        
        //rockmediumone
        RockMediumOne rockMediumOne1 = new RockMediumOne(new Point(2655,10856), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne1);
        RockMediumOne rockMediumOne2 = new RockMediumOne(new Point(2847,10280), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne2);
        RockMediumOne rockMediumOne3 = new RockMediumOne(new Point(4319,11048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne3);
        RockMediumOne rockMediumOne4 = new RockMediumOne(new Point(5663,10344), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne4);
        RockMediumOne rockMediumOne5 = new RockMediumOne(new Point(6751,9704), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne5);
        RockMediumOne rockMediumOne6 = new RockMediumOne(new Point(7711,7976), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne6);
        RockMediumOne rockMediumOne7 = new RockMediumOne(new Point(2527,9512), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne7);
        RockMediumOne rockMediumOne8 = new RockMediumOne(new Point(3871,8296), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne8);
        RockMediumOne rockMediumOne9 = new RockMediumOne(new Point(3295,5672), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne9);
        RockMediumOne rockMediumOne10 = new RockMediumOne(new Point(2591,3880), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne10);
        RockMediumOne rockMediumOne12 = new RockMediumOne(new Point(7583,2984), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne12);
        RockMediumOne rockMediumOne13 = new RockMediumOne(new Point(7775,5544), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumOne13);
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
        RockMediumTwo rockMediumTwo6 = new RockMediumTwo(new Point(5855,10536), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo6);
        RockMediumTwo rockMediumTwo7 = new RockMediumTwo(new Point(6367,9896), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo7);
        RockMediumTwo rockMediumTwo8 = new RockMediumTwo(new Point(6943,8872), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo8);
        RockMediumTwo rockMediumTwo9 = new RockMediumTwo(new Point(7903,7720), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo9);
        RockMediumTwo rockMediumTwo10 = new RockMediumTwo(new Point(3231,9064), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo10);
        RockMediumTwo rockMediumTwo11 = new RockMediumTwo(new Point(2975,9896), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo11);
        RockMediumTwo rockMediumTwo12 = new RockMediumTwo(new Point(2847,5416), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo12);
        RockMediumTwo rockMediumTwo13 = new RockMediumTwo(new Point(2655,2984), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo13);
        RockMediumTwo rockMediumTwo15 = new RockMediumTwo(new Point(7391,3688), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo15);
        RockMediumTwo rockMediumTwo16 = new RockMediumTwo(new Point(7071,5224), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumTwo16);
        //rockmediumthree
        RockMediumThree rockMediumThree1 = new RockMediumThree(new Point(3295,10216), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree1);
        RockMediumThree rockMediumThree2 = new RockMediumThree(new Point(4447,10728), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree2);
        RockMediumThree rockMediumThree3 = new RockMediumThree(new Point(5663,11304), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree3);
        RockMediumThree rockMediumThree4 = new RockMediumThree(new Point(5919,10152), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree4);
        RockMediumThree rockMediumThree5 = new RockMediumThree(new Point(6495,9384), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree5);
        RockMediumThree rockMediumThree6 = new RockMediumThree(new Point(7903,8232), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree6);
        RockMediumThree rockMediumThree7 = new RockMediumThree(new Point(3231,8808), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree7);
        RockMediumThree rockMediumThree8 = new RockMediumThree(new Point(2591,5992), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree8);
        RockMediumThree rockMediumThree9 = new RockMediumThree(new Point(2399,4776), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree9);
        RockMediumThree rockMediumThree10 = new RockMediumThree(new Point(3295,2920), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree10);
        RockMediumThree rockMediumThree11 = new RockMediumThree(new Point(4511,2920), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree11);
        RockMediumThree rockMediumThree12 = new RockMediumThree(new Point(6879,2859), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree12);
        RockMediumThree rockMediumThree13 = new RockMediumThree(new Point(7647,4328), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree13);
        RockMediumThree rockMediumThree14 = new RockMediumThree(new Point(7199,5608), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockMediumThree14);
        //rock large one
        RockLargeOne rockLargeOne1 = new RockLargeOne(new Point(2911,10664), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeOne1);
        RockLargeOne rockLargeOne2 = new RockLargeOne(new Point(5599,10792), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeOne2);
        RockLargeOne rockLargeOne3 = new RockLargeOne(new Point(4255,9384), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeOne3);
        RockLargeOne rockLargeOne4 = new RockLargeOne(new Point(3423,3880), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeOne4);
        RockLargeOne rockLargeOne5 = new RockLargeOne(new Point(7327,4840), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeOne5);
        //rock large two
        RockLargeTwo rockLargeTwo1 = new RockLargeTwo(new Point(4319,11496), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo1);
        RockLargeTwo rockLargeTwo2 = new RockLargeTwo(new Point(6047,9448), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo2);
        RockLargeTwo rockLargeTwo3 = new RockLargeTwo(new Point(7519,8680), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo3);
        RockLargeTwo rockLargeTwo4 = new RockLargeTwo(new Point(2719,8360), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo4);
        RockLargeTwo rockLargeTwo5 = new RockLargeTwo(new Point(2463,9896), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo5);
        RockLargeTwo rockLargeTwo6 = new RockLargeTwo(new Point(3103,4584), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo6);
        RockLargeTwo rockLargeTwo7 = new RockLargeTwo(new Point(3871,5736), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo7);
        RockLargeTwo rockLargeTwo8 = new RockLargeTwo(new Point(6943,3496), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(rockLargeTwo8);
        
        Satelite satelite = new Satelite(new Point(5087,4136), 1536, 1536, graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(satelite);
        
        DinosaurusSkeleton dinosaurusSkeleton = new DinosaurusSkeleton(new Point(4063,9768), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(dinosaurusSkeleton);
        
        DroneBarracks droneBarracks = new DroneBarracks(new Point(3103, 7144), 256, 384, graphicsContext, monitorWindow, playerRobot, enemyContainer, this.gridTable);
        gridTable.insertGameObjectIntoGridCell(droneBarracks);
        
        TitanHead titanHead = new TitanHead(new Point(6175,5800), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(titanHead);*/
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
        CraterSmallOne craterSmallOne7 = new CraterSmallOne(new Point(7647,11240), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne7);
        CraterSmallOne craterSmallOne8 = new CraterSmallOne(new Point(4575,6120), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne8);
        CraterSmallOne craterSmallOne9 = new CraterSmallOne(new Point(7327,8296), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne9);
        CraterSmallOne craterSmallOne10 = new CraterSmallOne(new Point(4319,8808), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne10);
        CraterSmallOne craterSmallOne11 = new CraterSmallOne(new Point(5471,8680), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne11);
        CraterSmallOne craterSmallOne12 = new CraterSmallOne(new Point(4447,5544), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne12);
        CraterSmallOne craterSmallOne13 = new CraterSmallOne(new Point(4191,4072), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne13);
        CraterSmallOne craterSmallOne14 = new CraterSmallOne(new Point(4063,3368), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne14);
        CraterSmallOne craterSmallOne15 = new CraterSmallOne(new Point(4767,2984), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne15);
        CraterSmallOne craterSmallOne16 = new CraterSmallOne(new Point(5727,3112), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne16);
        CraterSmallOne craterSmallOne17 = new CraterSmallOne(new Point(6239,5160), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallOne17);
        //cratersmalltwo
        CraterSmallTwo craterSmallTwo1 = new CraterSmallTwo(new Point(2719,11560), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo1);
        CraterSmallTwo craterSmallTwo2 = new CraterSmallTwo(new Point(3103,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo2);
        CraterSmallTwo craterSmallTwo3 = new CraterSmallTwo(new Point(3487,11048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo3);
        CraterSmallTwo craterSmallTwo4 = new CraterSmallTwo(new Point(3807,10664), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo4);
        CraterSmallTwo craterSmallTwo5 = new CraterSmallTwo(new Point(6239,10856), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo5);
        CraterSmallTwo craterSmallTwo6 = new CraterSmallTwo(new Point(4703,6056), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo6);
        CraterSmallTwo craterSmallTwo7 = new CraterSmallTwo(new Point(7455,8232), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo7);
        CraterSmallTwo craterSmallTwo8 = new CraterSmallTwo(new Point(5727,5544), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo8);
        CraterSmallTwo craterSmallTwo9 = new CraterSmallTwo(new Point(5599,8744), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo9);
        CraterSmallTwo craterSmallTwo10 = new CraterSmallTwo(new Point(4511,5224), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo10);
        CraterSmallTwo craterSmallTwo11 = new CraterSmallTwo(new Point(3999,4904), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo11);
        CraterSmallTwo craterSmallTwo12 = new CraterSmallTwo(new Point(3871,4008), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo12);
        CraterSmallTwo craterSmallTwo13 = new CraterSmallTwo(new Point(4319,3432), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo13);
        CraterSmallTwo craterSmallTwo14 = new CraterSmallTwo(new Point(5343,3240), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo14);
        CraterSmallTwo craterSmallTwo15 = new CraterSmallTwo(new Point(6239,3304), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo15);
        CraterSmallTwo craterSmallTwo16 = new CraterSmallTwo(new Point(6175,4136), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo16);
        CraterSmallTwo craterSmallTwo17 = new CraterSmallTwo(new Point(5919,5160), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallTwo17);
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
        CraterSmallThree craterSmallThree8 = new CraterSmallThree(new Point(6303,11112), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree8);
        CraterSmallThree craterSmallThree9 = new CraterSmallThree(new Point(4895,5992), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree9);
        CraterSmallThree craterSmallThree10 = new CraterSmallThree(new Point(6751,9128), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree10);
        CraterSmallThree craterSmallThree11 = new CraterSmallThree(new Point(4447,9000), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree11);
        CraterSmallThree craterSmallThree12 = new CraterSmallThree(new Point(5791,8552), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterSmallThree12);
        //cratermedium(one)
        CraterMedium craterMedium1 = new CraterMedium(new Point(3039,11688), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium1);
        CraterMedium craterMedium2 = new CraterMedium(new Point(3743,11176), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium2);
        CraterMedium craterMedium3 = new CraterMedium(new Point(3679,10472), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium3);
        CraterMedium craterMedium4 = new CraterMedium(new Point(4255,11240), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium4);
        CraterMedium craterMedium5 = new CraterMedium(new Point(7327,11432), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium5);
        CraterMedium craterMedium6 = new CraterMedium(new Point(6623,10664), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium6);
        CraterMedium craterMedium7 = new CraterMedium(new Point(6879,9128), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium7);
        CraterMedium craterMedium8 = new CraterMedium(new Point(5535,5736), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium8);
        CraterMedium craterMedium9 = new CraterMedium(new Point(4511,8680), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium9);
        CraterMedium craterMedium10 = new CraterMedium(new Point(5471,8872), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium10);
        CraterMedium craterMedium11 = new CraterMedium(new Point(4191,5032), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium11);
        CraterMedium craterMedium12 = new CraterMedium(new Point(3743,4520), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium12);
        CraterMedium craterMedium13 = new CraterMedium(new Point(4063,3688), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium13);
        CraterMedium craterMedium14 = new CraterMedium(new Point(5023,3112), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium14);
        CraterMedium craterMedium15 = new CraterMedium(new Point(5919,3240), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium15);
        CraterMedium craterMedium16 = new CraterMedium(new Point(6303,4520), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMedium16);
        //cratermediumtwo
        CraterMediumTwo craterMediumTwo1 = new CraterMediumTwo(new Point(2911,11304), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo1);
        CraterMediumTwo craterMediumTwo2 = new CraterMediumTwo(new Point(3679,11496), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo2);
        CraterMediumTwo craterMediumTwo3 = new CraterMediumTwo(new Point(3935,10792), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo3);
        CraterMediumTwo craterMediumTwo4 = new CraterMediumTwo(new Point(4767,10728), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo4);
        CraterMediumTwo craterMediumTwo5 = new CraterMediumTwo(new Point(7391,9064), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo5);
        CraterMediumTwo craterMediumTwo6 = new CraterMediumTwo(new Point(4703,5800), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo6);
        CraterMediumTwo craterMediumTwo7 = new CraterMediumTwo(new Point(5471,5416), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo7);
        CraterMediumTwo craterMediumTwo8 = new CraterMediumTwo(new Point(4831,8552), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo8);
        CraterMediumTwo craterMediumTwo9 = new CraterMediumTwo(new Point(4063,8872), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo9);
        CraterMediumTwo craterMediumTwo10 = new CraterMediumTwo(new Point(4063,4712), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo10);
        CraterMediumTwo craterMediumTwo11 = new CraterMediumTwo(new Point(6303,3880), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo11);
        CraterMediumTwo craterMediumTwo12 = new CraterMediumTwo(new Point(6111,4840), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterMediumTwo12);
        //craterbig
        CraterBig craterBig1 = new CraterBig(new Point(3615,10856), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig1);
        CraterBig craterBig2 = new CraterBig(new Point(6559,10856), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig2);
        CraterBig craterBig3 = new CraterBig(new Point(4703,8744), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig3);
        CraterBig craterBig4 = new CraterBig(new Point(4063,5352), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig4);
        CraterBig craterBig5 = new CraterBig(new Point(3935,4264), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig5);
        CraterBig craterBig6 = new CraterBig(new Point(3679,3560), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig6);
        CraterBig craterBig7 = new CraterBig(new Point(4447,3112), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig7);
        CraterBig craterBig8 = new CraterBig(new Point(6431,3560), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig8);
        CraterBig craterBig9 = new CraterBig(new Point(6367,4840), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(craterBig9);
        
        
        //DryLandSmallOne
        DryLandSmallOne DryLandSmallOne1 = new DryLandSmallOne(new Point(5535,11624), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallOne1);
        DryLandSmallOne DryLandSmallOne2 = new DryLandSmallOne(new Point(7903,10408), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallOne2);
        DryLandSmallOne DryLandSmallOne3 = new DryLandSmallOne(new Point(2847,4200), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallOne3);
        DryLandSmallOne DryLandSmallOne4 = new DryLandSmallOne(new Point(7775,3496), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallOne4);
        //DryLandSmallTwo
        DryLandSmallTwo DryLandSmallTwo1 = new DryLandSmallTwo(new Point(3551,8872), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallTwo1);
        DryLandSmallTwo DryLandSmallTwo2 = new DryLandSmallTwo(new Point(7647,10024), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallTwo2);
        DryLandSmallTwo DryLandSmallTwo3 = new DryLandSmallTwo(new Point(7775,4968), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallTwo3);
        //DryLandSmallThree
        DryLandSmallThree DryLandSmallThree1 = new DryLandSmallThree(new Point(7071,11048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandSmallThree1);
        //DryLandHorizontal
        DryLandHorizontal DryLandHorizontal1 = new DryLandHorizontal(new Point(3103,9448), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandHorizontal1);
        DryLandHorizontal DryLandHorizontal2 = new DryLandHorizontal(new Point(6559,10216), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandHorizontal2);
        DryLandHorizontal DryLandHorizontal3 = new DryLandHorizontal(new Point(6559,3048), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandHorizontal3);
        //DryLandVertical
        DryLandVertical DryLandVertical1 = new DryLandVertical(new Point(5983,11304), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandVertical1);
        DryLandVertical DryLandVertical2 = new DryLandVertical(new Point(3999,8104), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandVertical2);
        //DryLandLargeOne
        DryLandLargeOne DryLandLargeOne1 = new DryLandLargeOne(new Point(7647,11368), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandLargeOne1);
        DryLandLargeOne DryLandLargeOne2 = new DryLandLargeOne(new Point(7647,9256), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandLargeOne2);
        DryLandLargeOne DryLandLargeOne3 = new DryLandLargeOne(new Point(3359,4968), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandLargeOne3);
        DryLandLargeOne DryLandLargeOne4 = new DryLandLargeOne(new Point(6687,4008), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandLargeOne4);
        //DryLandLargeTwo
        DryLandLargeTwo DryLandLargeTwo1 = new DryLandLargeTwo(new Point(7199,10344), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandLargeTwo1);
        DryLandLargeTwo DryLandLargeTwo2 = new DryLandLargeTwo(new Point(2463,8808), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandLargeTwo2);
        DryLandLargeTwo DryLandLargeTwo3 = new DryLandLargeTwo(new Point(2975,3176), graphicsContext, monitorWindow);
        gridTable.insertGameObjectIntoGridCell(DryLandLargeTwo3);
        
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
    /*    CliffBottomLeft cliffBottomLeft = new CliffBottomLeft(new Point(2015, 12072), 256, 256, graphicsContext, monitorWindow);
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
        }*/
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
