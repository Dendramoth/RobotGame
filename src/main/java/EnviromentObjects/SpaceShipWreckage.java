/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GameStaticObject;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author Dendra
 */
public class SpaceShipWreckage extends GameStaticObject {

    GraphicsContext graphicsContext;
    Image spaceShipWreckageImage = LoadAllResources.getMapOfAllImages().get("spaceShipWreckage");

    public SpaceShipWreckage(Point possition, double width, double heigh, GraphicsContext graphicsContext) {
        super(getPoints(possition),possition, width, heigh,graphicsContext,Color.BLACK);
        this.graphicsContext = graphicsContext;
    }
    
    private static List<Point> getPoints(Point possition){
        List<Point> pointList = new ArrayList<Point>();
        pointList.add(new Point(600 + possition.getCoordX(), 100 + possition.getCoordY()));
        pointList.add(new Point(800 + possition.getCoordX(), 100 + possition.getCoordY()));
        pointList.add(new Point(800 + possition.getCoordX(), 600 + possition.getCoordY()));
        pointList.add(new Point(600 + possition.getCoordX(), 600 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        graphicsContext.drawImage(spaceShipWreckageImage, worldPossition.getCoordX(), worldPossition.getCoordY());
    }

    @Override
    public void paintStaticGameObject(double worldPossitionOfPlayerX, double worldPossitionOfPlayerY) {
        graphicsContext.drawImage(spaceShipWreckageImage, worldPossitionOfPlayerX - worldPossition.getCoordX(), worldPossitionOfPlayerY - worldPossition.getCoordY());
    }

}
