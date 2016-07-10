/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.CornerPointsOfObject;
import GameObject.GameObject;
import java.util.HashMap;
import java.util.HashSet;
import javafx.scene.canvas.GraphicsContext;

/**
 * cellSize: size of cell in pixels cellVisibility: number of cells next to
 * yours where u can still see objects gridCellMap: map containing all the cells
 * visibleObjectsFromPossition: contains all visible objects from a specific
 * possition
 *
 * @author Dendra
 */
public class GridTable {

    private final int cellSize = 512;
    private final int cellVisibility = 1;
    private final int mapWidth = 5120;
    private final int mapHeigh = 10240;
    private GridCell gridCellField[][];
    private GraphicsContext graphicsContext;
    private HashMap<GridCellPossition, HashSet<GameObject>> visibleObjectsFromPossition = new HashMap<>();

    public GridTable(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

        gridCellField = new GridCell[Math.round(mapWidth / cellSize)][mapHeigh / cellSize];
        createGrid();
    }

    public void paintAllObjectsVisibleFromCoord(double playerWorldCoordX, double playerWorldCoordY) {
        graphicsContext.clearRect(0, 0, 2000, 2000);
        
        int x = indexInGrid(playerWorldCoordX);
        int y = indexInGrid(playerWorldCoordY);

        //   System.out.println("coordx: " + x + " coordy: " + y);
        HashSet<GameObject> visibleObjects = gridCellField[x][y].getObjectsVisibleFromCell();
        //      System.out.println(visibleObjects.size());
        for (GameObject gameObject : visibleObjects) {
            gameObject.paintStaticGameObject(playerWorldCoordX, playerWorldCoordY);
        }
    }

    private void createGrid() {
        for (int indexX = 0; indexX < mapWidth / cellSize; indexX++) {
            for (int indexY = 0; indexY < mapHeigh / cellSize; indexY++) {
                gridCellField[indexX][indexY] = new GridCell();
            }
        }
    }

    public void insertGameObjectIntoGrid(GameObject gameObject) {
        int switchValue = 0;
        CornerPointsOfObject cornerPointsOfObject = gameObject.getCornerPointsOfObject();

        int x1 = indexInGrid(cornerPointsOfObject.getTopLeft().getCoordX());
        int x2 = indexInGrid(cornerPointsOfObject.getTopRight().getCoordX());
        if (x1 > x2) {
            switchValue = x2;
            x2 = x1;
            x1 = switchValue;
        }

        int y1 = indexInGrid(cornerPointsOfObject.getTopLeft().getCoordY());
        int y2 = indexInGrid(cornerPointsOfObject.getBottomLeft().getCoordY());
        if (y1 > y2) {
            switchValue = y2;
            y2 = y1;
            y1 = switchValue;
        }

        addObjectToCell(gameObject, x1, x2, y1, y2);
        addVisibleObjectToCell(gameObject, x1, x2, y1, y2);
    }

    private void addObjectToCell(GameObject gameObject, int x1, int x2, int y1, int y2) {
        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addGameObject(gameObject);
            }
        }
    }

    private void addVisibleObjectToCell(GameObject gameObject, int cellX1, int cellX2, int cellY1, int cellY2) {
        int x1 = cellX1 - cellVisibility;
        if (x1 < 0) {
            x1 = 0;
        }

        int x2 = cellX2 + cellVisibility + 1;
        if (x2 > Math.round(mapWidth / cellSize - 1)) {
            x2 = Math.round(mapWidth / cellSize - 1);
        }

        int y1 = cellY1 - cellVisibility;
        if (y1 < 0) {
            y1 = 0;
        }

        int y2 = cellY2 + cellVisibility;
        if (y2 > Math.round(mapHeigh / cellSize - 1)) {
            y2 = Math.round(mapHeigh / cellSize - 1);
        }

        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                //         System.out.println("x: " + indexX + " y:" + indexY);
                gridCellField[indexX][indexY].addObjectVisibleFromCell(gameObject);
            }
        }
    }

    private int indexInGrid(double coordination) {
        return Math.floorDiv((int) (coordination), cellSize);
    }

}
