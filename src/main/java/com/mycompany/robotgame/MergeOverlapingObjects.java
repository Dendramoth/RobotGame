/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import GameObject.GameStaticObject;
import GameObject.Point;
import MapGridTable.GridTable;
import Pathfinding.PathFindingStaticObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class MergeOverlapingObjects {

    private final GridTable gridTable;
    private final int enemySize;

    public MergeOverlapingObjects(GridTable gridTable, int enemySize) {
        this.gridTable = gridTable;
        this.enemySize = enemySize;
        checkAllSectionsOfTheGrid();
    }

    private void checkAllSectionsOfTheGrid() {
        for (int x = 0; x < gridTable.getCellCountX(); x++) {
            for (int y = 0; y < gridTable.getCellCountY(); y++) {
                HashSet<GameStaticObject> gameStaticObjects = gridTable.getStaticObjectsInGridCell(x, y);
                mergeOverlapingObjects(gameStaticObjects, enemySize);
            }
        }
    }

    private void mergeOverlapingObjects(HashSet<GameStaticObject> gameStaticObjects, int enemySize) {

        Iterator<GameStaticObject> iterator = gameStaticObjects.iterator();
        while (iterator.hasNext()) {
            GameStaticObject gameStaticObject = iterator.next();
            compareOverlapOffAllObjectInListAgainstOne(gameStaticObjects, gameStaticObject);
            iterator.remove();
        }

    }

    private void compareOverlapOffAllObjectInListAgainstOne(HashSet<GameStaticObject> gameStaticObjects, GameStaticObject comperatorObject) {
        for (GameStaticObject gameStaticObject : gameStaticObjects) {
            if ((gameStaticObject != comperatorObject) && !(gameStaticObject.getPointsList(enemySize).equals(comperatorObject.getPointsList(enemySize)))) {
                Shape intersect = Polygon.intersect(gameStaticObject.getGameObjectPolygon(enemySize), comperatorObject.getGameObjectPolygon(enemySize));
                if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
                    // NO intersection
                } else {
                    // Intersection found
                    System.out.println("INTERSECTION");
                    mergeTwoObjectsIntoOne(gameStaticObject, comperatorObject);
                }
            }
        }
    }

    private void mergeTwoObjectsIntoOne(GameStaticObject gameStaticObjectA, GameStaticObject gameStaticObjectB) {
        if (gameStaticObjectA.getPointsList(enemySize) != gameStaticObjectB.getPointsList(enemySize)) {
            PathFindingStaticObject pathFindingStaticObject = new PathFindingStaticObject(enemySize, gameStaticObjectA, gameStaticObjectB);

            System.out.println("ObjectA: ");
            for (Point point : gameStaticObjectA.getPointsList(enemySize)) {
                System.out.println(point.getCoordX() + " " + point.getCoordY());
            }

            System.out.println();
            System.out.println("ObjectB: ");
            for (Point point : gameStaticObjectB.getPointsList(enemySize)) {
                System.out.println(point.getCoordX() + " " + point.getCoordY());
            }

            System.out.println();
            System.out.println("Union Object: ");
            for (Point point : pathFindingStaticObject.getFinalUnionObjectPointList()) {
                System.out.println(point.getCoordX() + " " + point.getCoordY());
            }

            List<Point> finalUnionPointList  = new ArrayList<>(pathFindingStaticObject.getFinalUnionObjectPointList());
            
            
            gameStaticObjectA.setPointsList(finalUnionPointList, enemySize);
            gameStaticObjectA.getObjectToUpdateWhenUpdated().add(gameStaticObjectB);
            gameStaticObjectA.getObjectToUpdateWhenUpdated().addAll(gameStaticObjectB.getObjectToUpdateWhenUpdated());
            for (GameStaticObject gameStaticObject : gameStaticObjectA.getObjectToUpdateWhenUpdated()) {
                gameStaticObject.getObjectToUpdateWhenUpdated().addAll(gameStaticObjectA.getObjectToUpdateWhenUpdated());
                gameStaticObject.setPointsList(finalUnionPointList, enemySize);
            }
            gameStaticObjectA.getObjectToUpdateWhenUpdated().remove(gameStaticObjectA);
            
            gameStaticObjectB.setPointsList(finalUnionPointList, enemySize);
            gameStaticObjectB.getObjectToUpdateWhenUpdated().add(gameStaticObjectA);
            gameStaticObjectB.getObjectToUpdateWhenUpdated().addAll(gameStaticObjectA.getObjectToUpdateWhenUpdated());
            for (GameStaticObject gameStaticObject : gameStaticObjectB.getObjectToUpdateWhenUpdated()) {
                gameStaticObject.getObjectToUpdateWhenUpdated().addAll(gameStaticObjectB.getObjectToUpdateWhenUpdated());
                gameStaticObject.setPointsList(finalUnionPointList, enemySize);
            }
            gameStaticObjectB.getObjectToUpdateWhenUpdated().remove(gameStaticObjectB);

            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

}
