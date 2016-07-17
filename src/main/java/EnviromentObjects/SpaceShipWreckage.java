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

    private final GraphicsContext graphicsContext;
    private final Image spaceShipWreckageImage = LoadAllResources.getMapOfAllImages().get("spaceShipWreckage");
    private final List<Point> pointsForDetection = new ArrayList<>();

    public SpaceShipWreckage(Point possition, double width, double heigh, GraphicsContext graphicsContext) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, Color.BLACK);
        this.graphicsContext = graphicsContext;
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
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

    private void createPolygonForDetection(double worldPossitionOfPlayerX, double worldPossitionOfPlayerY, Point playerScreenPossition) {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(77 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 275 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(77 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 346 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(163 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 433 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(285 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 404 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(400 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 210 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 188 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(292 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 274 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(236 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 218 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(320 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 128 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(300 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 105 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
        pointsForDetection.add(new Point(112 + worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), 220 + worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY()));
    }

    @Override
    public void paintStaticGameObject(double worldPossitionOfPlayerX, double worldPossitionOfPlayerY, Point playerScreenPossition) {
        //    graphicsContext.drawImage(spaceShipWreckageImage, worldPossitionOfPlayerX - worldPossition.getCoordX(), worldPossitionOfPlayerY - worldPossition.getCoordY());
        graphicsContext.drawImage(spaceShipWreckageImage, worldPossitionOfPlayerX - worldPossition.getCoordX() + playerScreenPossition.getCoordX(), worldPossitionOfPlayerY - worldPossition.getCoordY() + playerScreenPossition.getCoordY());

        createPolygonForDetection(worldPossitionOfPlayerX, worldPossitionOfPlayerY, playerScreenPossition);
        graphicsContext.setFill(Color.RED);
        for (int i = 0; i < pointsForDetection.size(); i++) {
            graphicsContext.fillOval(pointsForDetection.get(i).getCoordX() - 5, pointsForDetection.get(i).getCoordY() - 5, 10, 10);
        }
    }

}
