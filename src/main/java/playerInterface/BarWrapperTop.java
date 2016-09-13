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
public class BarWrapperTop {

    private static double CLOSED_OFFSET = -102;
    private Image topBarWrapperImage = LoadAllResources.getMapOfAllImages().get("barWrapperTop");
    private GraphicsContext graphicsContext;

    public BarWrapperTop(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void paintTopWrapper() {
        graphicsContext.drawImage(topBarWrapperImage, 20, GameMainInfrastructure.WINDOW_HEIGH + CLOSED_OFFSET);
    }

}
