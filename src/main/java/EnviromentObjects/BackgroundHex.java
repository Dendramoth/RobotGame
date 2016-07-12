/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GameObject;
import GameObject.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class BackgroundHex extends GameObject{
    private Image hexImage;
    private GraphicsContext graphicsContext;

    public BackgroundHex(Point possition, double width, double heigh, Image image, GraphicsContext graphicsContext) {
        super(possition, width, heigh);
        this.hexImage = image;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void paintGameObject() {
    }

    @Override
    public void paintStaticGameObject(double worldPossitionOfPlayerX, double worldPossitionOfPlayerY) {
        graphicsContext.drawImage(hexImage, worldPossitionOfPlayerX - worldPossition.getCoordX(), worldPossitionOfPlayerY - worldPossition.getCoordY());
    }
    
}
