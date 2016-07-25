/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GamePrimitiveObject;
import GameObject.Point;
import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class BackgroundHex extends GamePrimitiveObject {

    private Image hexImage;
    private GraphicsContext graphicsContext;

    public BackgroundHex(Point possition, double width, double heigh, Image image, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(possition, width, heigh, graphicsContext, monitorWindow);
        this.hexImage = image;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(hexImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }

}
