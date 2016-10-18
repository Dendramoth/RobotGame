/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.GamePrimitiveObject;
import GameObject.GameStaticObject;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 *
 * @author Dendra
 */
public class GridCell {
    private final LinkedHashSet<GameStaticObject> objectInCell = new LinkedHashSet<>();
    private final LinkedHashSet<GameStaticObject> objectsVisibleFromCell = new LinkedHashSet<>();
    
    private final LinkedHashSet<GamePrimitiveObject> objectInCellWithoutColision = new LinkedHashSet<>();
    private final LinkedHashSet<GamePrimitiveObject> objectVisibleFromCellWithoutColision = new LinkedHashSet<>();
    
    private final LinkedHashSet<GamePrimitiveObject> backgroundInCell = new LinkedHashSet<>();
    private final LinkedHashSet<GamePrimitiveObject> backgroundVisibleFromCell = new LinkedHashSet<>();

    public GridCell() {
    }
    
    public void addGameObject(GameStaticObject gameObject){
        objectInCell.add(gameObject);
    }
    
    public void addObjectVisibleFromCell(GameStaticObject gameObject){
        objectsVisibleFromCell.add(gameObject);
    }
    
    public void addGameObjectWithoutColision(GamePrimitiveObject gameObject){
        objectInCellWithoutColision.add(gameObject);
    }
    
    public void addGameObjectWithoutColisionVisibleFromCell(GamePrimitiveObject gameObject){
        objectVisibleFromCellWithoutColision.add(gameObject);
    }
    
    public void addGameBackgroundHex(GamePrimitiveObject gameObject){
        backgroundInCell.add(gameObject);
    }
    
    public void addBackgroundHexVisibleFromCell(GamePrimitiveObject gameObject){
        backgroundVisibleFromCell.add(gameObject);
    }
    

    public LinkedHashSet<GameStaticObject> getObjectInCell() {
        return objectInCell;
    }

    public LinkedHashSet<GameStaticObject> getObjectsVisibleFromCell() {
        return objectsVisibleFromCell;
    }

    public LinkedHashSet<GamePrimitiveObject> getBackgroundInCell() {
        return backgroundInCell;
    }

    public LinkedHashSet<GamePrimitiveObject> getBackgroundVisibleFromCell() {
        return backgroundVisibleFromCell;
    }

    public LinkedHashSet<GamePrimitiveObject> getObjectInCellWithoutColision() {
        return objectInCellWithoutColision;
    }

    public LinkedHashSet<GamePrimitiveObject> getObjectVisibleFromCellWithoutColision() {
        return objectVisibleFromCellWithoutColision;
    }
}
