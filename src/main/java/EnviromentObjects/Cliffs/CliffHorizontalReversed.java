/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects.Cliffs;

import GameObject.GameStaticObject;
import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class CliffHorizontalReversed extends GameStaticObject{
    private final Image cliffHorizontalReversedImage = LoadAllResources.getMapOfAllImages().get("cliffHorizontalReversed");
    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffHorizontalReversed(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow);
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0 + possition.getCoordX(), 150 + possition.getCoordY()));
        pointList.add(new Point(1024 + possition.getCoordX(), 150 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 322 + possition.getCoordY()));
        pointList.add(new Point(1024 + possition.getCoordX(), 322 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(cliffHorizontalReversedImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
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
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 150 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(1024 + worldPossition.getCoordX(), 150 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(1024 + worldPossition.getCoordX(), 322 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 322 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }
}