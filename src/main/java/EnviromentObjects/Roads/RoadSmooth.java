/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Roads;

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
public class RoadSmooth extends GamePrimitiveObject {
    private final Image roadImage = LoadAllResources.getMapOfAllImages().get("Road_end_north_smooth");

    public RoadSmooth(Point possition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(possition, 424, 424, 0, graphicsContext, monitorWindow);
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(roadImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }
}
