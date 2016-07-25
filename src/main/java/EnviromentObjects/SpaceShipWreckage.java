/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GameStaticObject;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
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

    public SpaceShipWreckage(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow);
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
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(spaceShipWreckageImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        //    createPolygonForDetection(playerWorldPosition.getCoordX(), playerWorldPosition.getCoordY());
    }

    @Override
    public boolean detectCollision(Shape shape, Point playerWorldPosition) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(77 + worldPossition.getCoordX(), 275 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(77 + worldPossition.getCoordX(), 346 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(163 + worldPossition.getCoordX(), 433 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(285 + worldPossition.getCoordX(), 404 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(400 + worldPossition.getCoordX(), 210 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(380 + worldPossition.getCoordX(), 188 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(292 + worldPossition.getCoordX(), 274 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(236 + worldPossition.getCoordX(), 218 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(320 + worldPossition.getCoordX(), 128 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(300 + worldPossition.getCoordX(), 105 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(112 + worldPossition.getCoordX(), 220 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }
}
