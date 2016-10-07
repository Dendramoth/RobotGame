/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.CornerPointsOfObject;
import GameObject.GamePrimitiveObject;
import GameObject.GameStaticObject;
import com.mycompany.robotgame.GameMainInfrastructure;
import com.mycompany.robotgame.MonitorWindow;
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

    private final int cellSize = 256;
    private final int cellVerticalVisibility = 2;
    private final int cellHorizontalVisibility = 4;
    private final int mapWidth = 10240;
    private final int mapHeigh = 18000;
    private final int cellCountX;
    private final int cellCountY;
    private final GridCell gridCellField[][];
    private final GraphicsContext graphicsContext;
    private MonitorWindow monitorWindow;

    public GridTable(GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;
        cellCountX = Math.round(mapWidth / cellSize);
        cellCountY = Math.round(mapHeigh / cellSize);
        gridCellField = new GridCell[cellCountX][cellCountY];
        createGrid();
    }

    public HashSet<GameStaticObject> getAllVisibleObjects() {
        int x = bottomIndexInGrid(monitorWindow.getPositionInWorld().getCoordX() + GameMainInfrastructure.WINDOW_WIDTH / 2 - 32);
        int y = bottomIndexInGrid(monitorWindow.getPositionInWorld().getCoordY() + GameMainInfrastructure.WINDOW_HEIGH / 2 - 32);
        HashSet<GameStaticObject> visibleObjects = gridCellField[x][y].getObjectsVisibleFromCell();
        return visibleObjects;
    }
    
    public HashSet<GamePrimitiveObject> getAllVisibleObjectsWithoutColision() {
        int x = bottomIndexInGrid(monitorWindow.getPositionInWorld().getCoordX() + GameMainInfrastructure.WINDOW_WIDTH / 2 - 32);
        int y = bottomIndexInGrid(monitorWindow.getPositionInWorld().getCoordY() + GameMainInfrastructure.WINDOW_HEIGH / 2 - 32);
        HashSet<GamePrimitiveObject> visibleObjects = gridCellField[x][y].getObjectVisibleFromCellWithoutColision();
        return visibleObjects;
    }

    public void paintAllObjectsInMonitorWindow() {
    //    graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

        int x = bottomIndexInGrid(monitorWindow.getPositionInWorld().getCoordX() + GameMainInfrastructure.WINDOW_WIDTH / 2 - 32);
        int y = bottomIndexInGrid(monitorWindow.getPositionInWorld().getCoordY() + GameMainInfrastructure.WINDOW_HEIGH / 2 - 32);

        if (x < cellCountX - 1 && x >= 0 && y < cellCountY - 1 && y >= 0) {
            HashSet<GamePrimitiveObject> visibleBackground = gridCellField[x][y].getBackgroundVisibleFromCell();
            for (GamePrimitiveObject gameObject : visibleBackground) {
                gameObject.paintGameObject();
            }
            
            HashSet<GameStaticObject> visibleObjects = gridCellField[x][y].getObjectsVisibleFromCell();
            for (GameStaticObject gameObject : visibleObjects) {
                gameObject.paintGameObject();
            }
            
            HashSet<GamePrimitiveObject> visibleObjectsWithoutColision = gridCellField[x][y].getObjectVisibleFromCellWithoutColision();
            for (GamePrimitiveObject gameObject : visibleObjectsWithoutColision) {
                gameObject.paintGameObject();
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

    public void insertGameObjectIntoGridCell(GamePrimitiveObject gameObject) {
        int switchValue = 0;
        CornerPointsOfObject cornerPointsOfObject = gameObject.getCornerPointsOfObject();

        int x1 = bottomIndexInGrid(cornerPointsOfObject.getTopLeft().getCoordX());
        int x2 = bottomIndexInGrid(cornerPointsOfObject.getTopRight().getCoordX());
        if (x1 > x2) {
            switchValue = x2;
            x2 = x1;
            x1 = switchValue;
        }

        int y1 = bottomIndexInGrid(cornerPointsOfObject.getTopLeft().getCoordY());
        int y2 = bottomIndexInGrid(cornerPointsOfObject.getBottomLeft().getCoordY());
        if (y1 > y2) {
            switchValue = y2;
            y2 = y1;
            y1 = switchValue;
        }

        if (gameObject instanceof GameStaticObject) {
            GameStaticObject gameStaticObject = (GameStaticObject) gameObject;
            addObjectToCell(gameStaticObject, x1, x2, y1, y2);
            addVisibleObjectToCell(gameStaticObject, x1, x2, y1, y2);
        }else{
            addPrimitiveObjectToCell(gameObject, x1, x2, y1, y2);
            addVisiblePrimitiveObjectToCell(gameObject, x1, x2, y1, y2);
        }
    }

    public void insertBackgroundIntoGridCell(GamePrimitiveObject gameObject) {
        int switchValue = 0;
        CornerPointsOfObject cornerPointsOfObject = gameObject.getCornerPointsOfObject();

        int x1 = bottomIndexInGrid(cornerPointsOfObject.getTopLeft().getCoordX());
        int x2 = topIndexInGrid(cornerPointsOfObject.getTopRight().getCoordX());
        if (x1 > x2) {
            switchValue = x2;
            x2 = x1;
            x1 = switchValue;
        }

        int y1 = bottomIndexInGrid(cornerPointsOfObject.getTopLeft().getCoordY());
        int y2 = topIndexInGrid(cornerPointsOfObject.getBottomLeft().getCoordY());
        if (y1 > y2) {
            switchValue = y2;
            y2 = y1;
            y1 = switchValue;
        }

        addVisibleBackgroundToCell(gameObject, x1, x2, y1, y2);
    }

    private void addObjectToCell(GameStaticObject gameObject, int x1, int x2, int y1, int y2) {
        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addGameObject(gameObject);
            }
        }
    }

    private void addPrimitiveObjectToCell(GamePrimitiveObject gameObject, int x1, int x2, int y1, int y2) {
        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addGameObjectWithoutColision(gameObject);
            }
        }
    }

    private void addBackgroundToCell(GamePrimitiveObject gameObject, int x1, int x2, int y1, int y2) {
        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addGameBackgroundHex(gameObject);
            }
        }
    }

    private void addVisibleObjectToCell(GameStaticObject gameObject, int cellX1, int cellX2, int cellY1, int cellY2) {
        int x1 = cellX1 - cellHorizontalVisibility;
        if (x1 < 0) {
            x1 = 0;
        }

        int x2 = cellX2 + cellHorizontalVisibility + 1;
        if (x2 > cellCountX - 1) {
            x2 = cellCountX - 1;
        }

        int y1 = cellY1 - cellVerticalVisibility;
        if (y1 < 0) {
            y1 = 0;
        }

        int y2 = cellY2 + cellVerticalVisibility;
        if (y2 > cellCountY - 1) {
            y2 = cellCountY - 1;
        }

        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addObjectVisibleFromCell(gameObject);
            }
        }
    }

    private void addVisiblePrimitiveObjectToCell(GamePrimitiveObject gameObject, int cellX1, int cellX2, int cellY1, int cellY2) {
        int x1 = cellX1 - cellHorizontalVisibility;
        if (x1 < 0) {
            x1 = 0;
        }

        int x2 = cellX2 + cellHorizontalVisibility + 1;
        if (x2 > cellCountX - 1) {
            x2 = cellCountX - 1;
        }

        int y1 = cellY1 - cellVerticalVisibility;
        if (y1 < 0) {
            y1 = 0;
        }

        int y2 = cellY2 + cellVerticalVisibility;
        if (y2 > cellCountY - 1) {
            y2 = cellCountY - 1;
        }

        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addGameObjectWithoutColisionVisibleFromCell(gameObject);
            }
        }
    }

    private void addVisibleBackgroundToCell(GamePrimitiveObject gameObject, int cellX1, int cellX2, int cellY1, int cellY2) {
        int x1 = cellX1 - cellHorizontalVisibility;
        if (x1 < 0) {
            x1 = 0;
        }

        int x2 = cellX2 + cellHorizontalVisibility;
        if (x2 > cellCountX - 1) {
            x2 = cellCountX - 1;
        }

        int y1 = cellY1 - cellVerticalVisibility;
        if (y1 < 0) {
            y1 = 0;
        }

        int y2 = cellY2 + cellVerticalVisibility;
        if (y2 > cellCountY - 1) {
            y2 = cellCountY - 1;
        }

        for (int indexX = x1; indexX <= x2; indexX++) {
            for (int indexY = y1; indexY <= y2; indexY++) {
                gridCellField[indexX][indexY].addBackgroundHexVisibleFromCell(gameObject);
            }
        }
    }

    private int bottomIndexInGrid(double coordination) {
        return (int) Math.floor(coordination / cellSize);
    }

    private int topIndexInGrid(double coordination) {
        return (int) Math.floor((coordination - 1) / cellSize);
    }

}
