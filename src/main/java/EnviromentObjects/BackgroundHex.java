/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GamePrimitiveObject;
import GameObject.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class BackgroundHex extends GamePrimitiveObject{
    private Image hexImage;
    private GraphicsContext graphicsContext;

    public BackgroundHex(Point possition, double width, double heigh, Image image, GraphicsContext graphicsContext) {
        super(possition, width, heigh, graphicsContext);
        this.hexImage = image;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void paintStaticGameObject(Point playerWorldPosition, Point playerScreenPosition) {
        graphicsContext.drawImage(hexImage, playerWorldPosition.getCoordX() - worldPossition.getCoordX(), playerWorldPosition.getCoordY() - worldPossition.getCoordY());
    }

    @Override
    public void paintGameObject() {
        // not supported
    }
   
}
