/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GamePrimitiveObject;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class DeadSpiderRobot extends GamePrimitiveObject{
    private Image deadStaticRocketTurretBaseImage = LoadAllResources.getMapOfAllImages().get("spiderCorpse");

    public DeadSpiderRobot(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(possition, width, heigh, graphicsContext, monitorWindow);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(deadStaticRocketTurretBaseImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }
    
}
