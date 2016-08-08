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
public class CliffBottomRight extends GameStaticObject{
    private final Image cliffBottomRightImage = LoadAllResources.getMapOfAllImages().get("cliffBottomRightCorner");
    private final List<Point> pointsForDetection = new ArrayList<>();

    public CliffBottomRight(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), possition, width, heigh, graphicsContext, monitorWindow);
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(170 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(143 + possition.getCoordX(), 124 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 158 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 273 + possition.getCoordY()));
        pointList.add(new Point(150 + possition.getCoordX(), 265 + possition.getCoordY()));
        pointList.add(new Point(258 + possition.getCoordX(), 182 + possition.getCoordY()));
        pointList.add(new Point(275 + possition.getCoordX(), 0 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(cliffBottomRightImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }

    @Override
    public boolean detectCollision(Shape shape, Point point) {
        createPolygonForDetection();
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    private void createPolygonForDetection() {
        pointsForDetection.clear();
        pointsForDetection.add(new Point(170 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(143 + worldPossition.getCoordX(), 124 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 158 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(0 + worldPossition.getCoordX(), 273 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(150 + worldPossition.getCoordX(), 265 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(258 + worldPossition.getCoordX(), 182 + worldPossition.getCoordY()));
        pointsForDetection.add(new Point(275 + worldPossition.getCoordX(), 0 + worldPossition.getCoordY()));
        createPolygon(pointsForDetection);
    }
    
    @Override
    public void doOnCollision() {
    }

    @Override
    public void doOnBeingHitByMinigun() {
    }
}
