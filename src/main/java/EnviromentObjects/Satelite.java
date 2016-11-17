/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.GameStaticObject;
import GameObject.Point;
import Projectiles.Projectile;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class Satelite extends GameStaticObject {

    private final List<Point> pointsForDetection = new ArrayList<>();
    private final List<MinigunHitIntoStaticObject> allHitsIntoSpaceShip = new ArrayList<>();
    private int sateliteAngle = 0;

    public Satelite(Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(getPoints(possition), getPoints(possition), getPoints(possition), possition, width, heigh, 0, graphicsContext, monitorWindow, LoadAllResources.getMapOfAllImages().get("sateliteBase"));
    }

    private static List<Point> getPoints(Point possition) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0 + possition.getCoordX(), 0 + possition.getCoordY()));
        pointList.add(new Point(0 + possition.getCoordX(), 256 + possition.getCoordY()));
        pointList.add(new Point(256 + possition.getCoordX(), 256 + possition.getCoordY()));
        pointList.add(new Point(256 + possition.getCoordX(), 0 + possition.getCoordY()));
        return pointList;
    }

    @Override
    public void paintGameObject() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(staticObjectImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
        paintRotatingSatelite(monitorPossition);
        paintAllMinigunHitsIntoSpaceShip();
    }

    private void paintRotatingSatelite(Point monitorPossition) {
        Image rotatingSateliteImage = LoadAllResources.getMapOfAllImages().get("sateliteTower");
        sateliteAngle = (sateliteAngle + 1) % 360;
        graphicsContext.save();
        graphicsContext.translate(worldPossition.getCoordX() - monitorPossition.getCoordX() + 128, worldPossition.getCoordY() - monitorPossition.getCoordY() + 128);
        graphicsContext.rotate(sateliteAngle);
        graphicsContext.drawImage(rotatingSateliteImage, -rotatingSateliteImage.getWidth() / 2, -rotatingSateliteImage.getHeight() / 2);
        graphicsContext.restore();
    }

    private void paintAllMinigunHitsIntoSpaceShip() {
        Iterator<MinigunHitIntoStaticObject> iterator = allHitsIntoSpaceShip.iterator();
        while (iterator.hasNext()) {
            MinigunHitIntoStaticObject minigunHitIntoStaticObject = iterator.next();
            minigunHitIntoStaticObject.paint();
            if (minigunHitIntoStaticObject.getNumberOfFramesBeingDisplayed() < 1) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean detectCollision(Shape shape) {
        Shape intersect = Shape.intersect(shape, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public void doOnCollision(Point collisionPoint) {
    }

    @Override
    public void doOnBeingHitByMinigun(Point intersectionPoint) {
        allHitsIntoSpaceShip.add(new MinigunHitIntoStaticObject(intersectionPoint, graphicsContext, monitorWindow));
    }

    @Override
    public void doOnBeingHitByProjectile(Point intersectionPoint, Projectile projectile) {
    }

}
