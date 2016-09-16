/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import com.mycompany.robotgame.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author styma01
 */
public class HullIntegrityBar extends PlayerInterfaceBar{
    
    private Image hullIntegrityTextImage = LoadAllResources.getMapOfAllImages().get("TextHullIntergrity");

    public HullIntegrityBar(GraphicsContext graphicsContext, boolean shouldBeDisplayed) {
        super(graphicsContext, shouldBeDisplayed);
    }
    
    @Override
    public void paintBar(double coordY){
        if (displayedStage > 0){
            graphicsContext.drawImage(barImage, 20, coordY);
            if (barIscompletelyVisible){
                graphicsContext.drawImage(hullIntegrityTextImage, 20, coordY);
            }
        }
    }

    @Override
    public boolean haveBarStatusChanged() {
        return false;
    }
    
    
    
}
