/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GameObject;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class SpaceShipWreckage extends GameObject {

    GraphicsContext graphicsContext;
    Image spaceShipWreckageImage = LoadAllResources.getMapOfAllImages().get("spaceShipWreckage");

    public SpaceShipWreckage(Point possition, double width, double heigh, GraphicsContext graphicsContext) {
        super(possition, width, heigh);
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void paintGameObject() {
        graphicsContext.drawImage(spaceShipWreckageImage, possition.getCoordX(), possition.getCoordY());
    }

    @Override
    public void paintStaticGameObject(double worldPossitionOfPlayerX, double worldPossitionOfPlayerY) {
     //   System.out.println((worldPossitionOfPlayerX - possition.getCoordX()) + "   " + (worldPossitionOfPlayerY - possition.getCoordY()));
        graphicsContext.drawImage(spaceShipWreckageImage, worldPossitionOfPlayerX - possition.getCoordX(), worldPossitionOfPlayerY - possition.getCoordY());
    }

}
