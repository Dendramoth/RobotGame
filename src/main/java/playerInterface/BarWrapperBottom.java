/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import com.mycompany.robotgame.GameMainInfrastructure;
import com.mycompany.robotgame.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class BarWrapperBottom {

    private static double CLOSED_OFFSET = -130;
    private final Image bottomBarWrapperImage = LoadAllResources.getMapOfAllImages().get("barWrapperBottom");
    private GraphicsContext graphicsContext;
    private double barYCoord = 0;

    public BarWrapperBottom(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void paintBottomWrapper() {
        barYCoord = GameMainInfrastructure.WINDOW_HEIGH + CLOSED_OFFSET;
        graphicsContext.drawImage(bottomBarWrapperImage, 20, barYCoord);
    }

    public double getBarYCoord() {
        return barYCoord;
    }
    
}
