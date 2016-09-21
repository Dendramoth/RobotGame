/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Craters;

import GameObject.GamePrimitiveObject;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author styma01
 */
public class CraterSmallTwo extends GamePrimitiveObject {
    private final Image craterImage = LoadAllResources.getMapOfAllImages().get("crater64_2");

    public CraterSmallTwo(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(possition, 64, 64, graphicsContext, monitorWindow);
    }
    
    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(craterImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }
}
