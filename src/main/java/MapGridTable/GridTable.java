/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.BackgroundObject;
import GameObject.CornerPointsOfObject;
import GameObject.GameObject;
import GameObject.GameStaticObject;
import com.mycompany.robotgame.GameMainInfrastructure;
import java.util.HashSet;
import javafx.scene.canvas.GraphicsContext;

/**
 * cellSize: size of cell in pixels cellVisibility: number of cells next to
 * yours where u can still see objects gridCellMap: map containing all the cells
 * visibleObjectsFromPossition: contains all visible objects from a specific
 * position
 *
 * @author Dendra
 */
public class GridTable {

    private final int cellSize = 512;
    private final int cellVisibility = 1;
    private final int mapWidth = 5120;
    private final int mapHeigh = 10240;
    private final int cellCountX;
    private final int cellCountY;
    private final GridCell gridCellField[][];
    private final GraphicsContext graphicsContext;

    public GridTable(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        cellCountX = Math.round(mapWidth / cellSize);
        cellCountY = Math.round(mapHeigh / cellSize);
        gridCellField = new GridCell[cellCountX][cellCountY];

        createGrid();
    }

    public void paintAllObjectsVisibleFromCoord(double playerWorldCoordX, double playerWorldCoordY) {
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

        int x = indexInGrid(playerWorldCoordX);
        int y = indexInGrid(playerWorldCoordY);

        if (x < cellCountX - 1 && x >= 0 && y < cellCountY - 1 && y >= 0) {
            HashSet<BackgroundObject> visibleBackground = gridCellField[x][y].getBackgroundVisibleFromCell();
            for (BackgroundObject gameObject : visibleBackground) {
                gameObject.paintStaticGameObject(playerWorldCoordX, playerWorldCoordY);
            }

            HashSet<GameStaticObject> visibleObjects = gridCellField[x][y].getObjectsVisibleFromCell();
            for (GameStaticObject gameObject : visibleObjects) {
                gameObject.paintStaticGameObject(playerWorldCoordX, playerWorldCoordY);
            }
        }
    }

    private void createGrid() {
        for (int indexX = 0; indexX < cellCountX; indexX++) {
            for (int indexY = 0; indexY < cellCountY; indexY++) {
                gridCellField[indexX][indexY] = new GridCell();
            }
        }
    }

    public void insertGameObjectIntoGridCell(GameStaticObject gameObject) {
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

    public void insertBackgroundIntoGridCell(BackgroundObject gameObject) {
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

        addBackgroundToCell(gameObject, x1, x2, y1, y2);
        addVisibleBackgroundToCell(gameObject, x1, x2, y1, y2);
    }

    private void addObjectToCell(GameStaticObject gameObject, int x1, int x2, int y1, int y2) {
        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addGameObject(gameObject);
            }
        }
    }

    private void addBackgroundToCell(BackgroundObject gameObject, int x1, int x2, int y1, int y2) {
        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addGameBackgroundHex(gameObject);
            }
        }
    }

    private void addVisibleObjectToCell(GameStaticObject gameObject, int cellX1, int cellX2, int cellY1, int cellY2) {
        int x1 = cellX1 - cellVisibility;
        if (x1 < 0) {
            x1 = 0;
        }

        int x2 = cellX2 + cellVisibility + 1;
        if (x2 > cellCountX - 1) {
            x2 = cellCountX - 1;
        }

        int y1 = cellY1 - cellVisibility;
        if (y1 < 0) {
            y1 = 0;
        }

        int y2 = cellY2 + cellVisibility;
        if (y2 > cellCountY - 1) {
            y2 = cellCountY- 1;
        }

        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addObjectVisibleFromCell(gameObject);
            }
        }
    }

    private void addVisibleBackgroundToCell(BackgroundObject gameObject, int cellX1, int cellX2, int cellY1, int cellY2) {
        int x1 = cellX1 - cellVisibility;
        if (x1 < 0) {
            x1 = 0;
        }

        int x2 = cellX2 + cellVisibility + 2;
        if (x2 > cellCountX - 1) {
            x2 = cellCountX - 1;
        }

        int y1 = cellY1 - cellVisibility;
        if (y1 < 0) {
            y1 = 0;
        }

        int y2 = cellY2 + cellVisibility;
        if (y2 > cellCountY - 1) {
            y2 = cellCountY - 1;
        }

        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addBackgroundHexVisibleFromCell(gameObject);
            }
        }
    }

    private int indexInGrid(double coordination) {
        return (int) Math.floor(coordination / cellSize);
    }

}
