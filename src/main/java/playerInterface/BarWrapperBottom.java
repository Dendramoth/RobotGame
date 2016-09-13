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
 * @author styma01
 */
public class BarWrapperBottom {

    private static double CLOSED_OFFSET = -80;
    private final Image bottomBarWrapperImage = LoadAllResources.getMapOfAllImages().get("barWrapperBottom");
    private GraphicsContext graphicsContext;

    public BarWrapperBottom(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void paintBottomWrapper() {
        graphicsContext.drawImage(bottomBarWrapperImage, 20, GameMainInfrastructure.WINDOW_HEIGH + CLOSED_OFFSET);
    }
}
