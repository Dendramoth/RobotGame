/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.GamePrimitiveObject;
import GameObject.GameStaticObject;
import java.util.HashSet;

/**
 *
 * @author Dendra
 */
public class GridCell {
    private final HashSet<GameStaticObject> objectInCell = new HashSet<GameStaticObject>();
    private final HashSet<GameStaticObject> objectsVisibleFromCell = new HashSet<GameStaticObject>();
    
    private final HashSet<GamePrimitiveObject> objectInCellWithoutColision = new HashSet<GamePrimitiveObject>();
    private final HashSet<GamePrimitiveObject> objectVisibleFromCellWithoutColision = new HashSet<GamePrimitiveObject>();
    
    private final HashSet<GamePrimitiveObject> backgroundInCell = new HashSet<GamePrimitiveObject>();
    private final HashSet<GamePrimitiveObject> backgroundVisibleFromCell = new HashSet<GamePrimitiveObject>();

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
    

    public HashSet<GameStaticObject> getObjectInCell() {
        return objectInCell;
    }

    public HashSet<GameStaticObject> getObjectsVisibleFromCell() {
        return objectsVisibleFromCell;
    }

    public HashSet<GamePrimitiveObject> getBackgroundInCell() {
        return backgroundInCell;
    }

    public HashSet<GamePrimitiveObject> getBackgroundVisibleFromCell() {
        return backgroundVisibleFromCell;
    }

    public HashSet<GamePrimitiveObject> getObjectInCellWithoutColision() {
        return objectInCellWithoutColision;
    }

    public HashSet<GamePrimitiveObject> getObjectVisibleFromCellWithoutColision() {
        return objectVisibleFromCellWithoutColision;
    }
}
