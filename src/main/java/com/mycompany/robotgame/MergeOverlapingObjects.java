/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import EnviromentObjects.Containers.SimpleBox;
import GameObject.GameObjectWithDistanceDetection;
import GameObject.GameStaticObject;
import GameObject.ResultOfDetectColisionWithProjectile;
import MapGridTable.GridTable;
import java.util.HashSet;
import java.util.Iterator;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class MergeOverlapingObjects {

    private GridTable gridTable;

    public MergeOverlapingObjects(GridTable gridTable) {
        this.gridTable = gridTable;
        checkAllSectionsOfTheGrid();
    }

    private void checkAllSectionsOfTheGrid() {
        for (int x = 0; x < gridTable.getCellCountX(); x++) {
            for (int y = 0; y < gridTable.getCellCountY(); y++) {
                HashSet<GameStaticObject> gameStaticObjects = gridTable.getStaticObjectsInGridCell(x, y);
                mergeOverlapingObjects(gameStaticObjects, 64);
            }
        }
    }

    private void mergeOverlapingObjects(HashSet<GameStaticObject> gameStaticObjects, int enemySize) {

        Iterator<GameStaticObject> iterator = gameStaticObjects.iterator();
        while (iterator.hasNext()) {
            GameStaticObject gameStaticObject = iterator.next();
            compareOverlapOffAllObjectInListAgainstOne(gameStaticObjects, gameStaticObject, enemySize);
            iterator.remove();
        }

    }

    private void compareOverlapOffAllObjectInListAgainstOne(HashSet<GameStaticObject> gameStaticObjects, GameStaticObject comperatorObject, int enemySize) {
        for (GameStaticObject gameStaticObject : gameStaticObjects) {
            if (gameStaticObject != comperatorObject) {
                Shape intersect = Polygon.intersect(gameStaticObject.getGameObjectPolygon(enemySize), comperatorObject.getGameObjectPolygon(enemySize));
                if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
                    System.out.println("-----------------");
                    
                    // intersection
                } else {
                    System.out.println("INTERSECTION");
                    if (gameStaticObject instanceof SimpleBox) {
                        System.out.println("Simple BOX!");
                    }
                    // no intersection
                }
            }
        }
    }

}
