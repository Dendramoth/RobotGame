/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.GameStaticObject;
import java.util.HashSet;

/**
 *
 * @author Dendra
 */
public class GridCell {
    private final HashSet<GameStaticObject> objectInCell = new HashSet<GameStaticObject>();
    private final HashSet<GameStaticObject> objectsVisibleFromCell = new HashSet<GameStaticObject>();
    
    private final HashSet<GameStaticObject> backgroundInCell = new HashSet<GameStaticObject>();
    private final HashSet<GameStaticObject> backgroundVisibleFromCell = new HashSet<GameStaticObject>();

    public GridCell() {
    }
    
    public void addGameObject(GameStaticObject gameObject){
        objectInCell.add(gameObject);
    }
    
    public void addObjectVisibleFromCell(GameStaticObject gameObject){
        objectsVisibleFromCell.add(gameObject);
    }
    
    public void addGameBackgroundHex(GameStaticObject gameObject){
        backgroundInCell.add(gameObject);
    }
    
    public void addBackgroundHexVisibleFromCell(GameStaticObject gameObject){
        backgroundVisibleFromCell.add(gameObject);
    }
    

    public HashSet<GameStaticObject> getObjectInCell() {
        return objectInCell;
    }

    public HashSet<GameStaticObject> getObjectsVisibleFromCell() {
        return objectsVisibleFromCell;
    }

    public HashSet<GameStaticObject> getBackgroundInCell() {
        return backgroundInCell;
    }

    public HashSet<GameStaticObject> getBackgroundVisibleFromCell() {
        return backgroundVisibleFromCell;
    }

    
    
 
    
    
    
    
}
