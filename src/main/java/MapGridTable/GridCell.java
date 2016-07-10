/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGridTable;

import GameObject.GameObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Dendra
 */
public class GridCell {
    private HashSet<GameObject> objectInCell = new HashSet<GameObject>();
    private HashSet<GameObject> objectsVisibleFromCell = new HashSet<GameObject>();
   // private HashSet<GameObject> backgroundVisibleFromCell = new HashSet<GameObject>();

    public GridCell() {
    }
    
    public void addGameObject(GameObject gameObject){
        objectInCell.add(gameObject);
    }
    
    public void addObjectVisibleFromCell(GameObject gameObject){
        objectsVisibleFromCell.add(gameObject);
    }

    public HashSet<GameObject> getObjectInCell() {
        return objectInCell;
    }

    public HashSet<GameObject> getObjectsVisibleFromCell() {
        return objectsVisibleFromCell;
    }
    
    
    
    
}
