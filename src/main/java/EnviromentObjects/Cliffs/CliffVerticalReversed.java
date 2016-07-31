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
public class CliffVerticalReversed extends GameStaticObject{
    private final Image cliffVerticalImage = LoadAllResources.getMapOfAllImages().get("cliffVerticalReversed");
    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffVerticalReversed(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow);
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(150 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(150 + possition.getCoordX(), 1024 + possition.getCoordY()));
        pointList.add(new Point(322 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(322 + possition.getCoordX(), 1024 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(cliffVerticalImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }

    @Override
    public boolean detectCollision(Shape shape) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(150 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(150 + worldPossition.getCoordX(), 1024 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(322 + worldPossition.getCoordX(), 1024 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(322 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }
}
