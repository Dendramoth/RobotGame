/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.GameObject;
import java.util.HashSet;

/**
 *
 * @author Dendra
 */
public class GridCell {
    private final HashSet<GameObject> objectInCell = new HashSet<GameObject>();
    private final HashSet<GameObject> objectsVisibleFromCell = new HashSet<GameObject>();
    
    private final HashSet<GameObject> backgroundInCell = new HashSet<GameObject>();
    private final HashSet<GameObject> backgroundVisibleFromCell = new HashSet<GameObject>();

    public GridCell() {
    }
    
    public void addGameObject(GameObject gameObject){
        objectInCell.add(gameObject);
    }
    
    public void addObjectVisibleFromCell(GameObject gameObject){
        objectsVisibleFromCell.add(gameObject);
    }
    
    public void addGameBackgroundHex(GameObject gameObject){
        backgroundInCell.add(gameObject);
    }
    
    public void addBackgroundHexVisibleFromCell(GameObject gameObject){
        backgroundVisibleFromCell.add(gameObject);
    }
    

    public HashSet<GameObject> getObjectInCell() {
        return objectInCell;
    }

    public HashSet<GameObject> getObjectsVisibleFromCell() {
        return objectsVisibleFromCell;
    }

    public HashSet<GameObject> getBackgroundInCell() {
        return backgroundInCell;
    }

    public HashSet<GameObject> getBackgroundVisibleFromCell() {
        return backgroundVisibleFromCell;
    }

    
    
 
    
    
    
    
}
