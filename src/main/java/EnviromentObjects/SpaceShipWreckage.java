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
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class SpaceShipWreckage extends GameStaticObject {

    private final GraphicsContext graphicsContext;
    private final Image spaceShipWreckageImage = LoadAllResources.getMapOfAllImages().get("spaceShipWreckage");
    private final List<Point> pointsForDetection = new ArrayList<>();

    public SpaceShipWreckage(Point possition, double width, double heigh, GraphicsContext graphicsContext) {
        super(getPoints(possition), possition, width, heigh, graphicsContext);
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
    }

    @Override
    public boolean detectCollision(Shape shape, Point playerWorldPosition) {
        createPolygonForDetection(playerWorldPosition.getCoordX(), playerWorldPosition.getCoordY());
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    private void createPolygonForDetection(double worldPossitionOfPlayerX, double worldPossitionOfPlayerY) {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(77 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 275 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(77 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 346 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(163 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 433 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(285 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 404 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(400 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 210 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 188 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(292 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 274 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(236 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 218 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(320 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 128 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(300 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 105 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        pointsForDetection.add(new Point(112 + worldPossitionOfPlayerX - worldPossition.getCoordX(), 220 + worldPossitionOfPlayerY - worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }

    @Override
    public void paintStaticGameObject(Point playerWorldPossition, Point playerScreenPosition) {
        graphicsContext.drawImage(spaceShipWreckageImage, playerWorldPossition.getCoordX() - worldPossition.getCoordX() + playerScreenPosition.getCoordX(), playerWorldPossition.getCoordY() - worldPossition.getCoordY() + playerScreenPosition.getCoordY());

        createPolygonForDetection(playerWorldPossition.getCoordX(), playerWorldPossition.getCoordY());
        graphicsContext.setFill(Color.RED);
        for (int i = 0; i < pointsForDetection.size(); i++) {
            graphicsContext.fillOval(pointsForDetection.get(i).getCoordX() - 5, pointsForDetection.get(i).getCoordY() - 5, 10, 10);
        }
    }
    
    
}
