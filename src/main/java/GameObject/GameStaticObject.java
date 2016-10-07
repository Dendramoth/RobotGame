/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import EnviromentObjects.MinigunHitIntoStaticObject;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public abstract class GameStaticObject extends GameObjectWithDistanceDetection {

    protected Image staticObjectImage;
    protected final Polygon gameObjectPolygon = new Polygon();
    private List<Point> listOfPathPoints = new ArrayList<Point>();
    private List<Line> polygonLineList = new ArrayList<Line>();

    public GameStaticObject(List<Point> pointsList, Point possition, double width, double heigh, GraphicsContext graphicsContext, MonitorWindow monitorWindow, Image staticObjectImage) {
        super(possition, width, heigh, graphicsContext, monitorWindow);
        this.staticObjectImage = staticObjectImage;
        createPolygon(pointsList);
        createLinesFromPolygonPoints(pointsList);
    }

    protected final void createPolygon(List<Point> pointsList) {
        gameObjectPolygon.getPoints().clear();
        for (Point point : pointsList) {
            gameObjectPolygon.getPoints().add(point.getCoordX());
            gameObjectPolygon.getPoints().add(point.getCoordY());
        }
    }

    private void createLinesFromPolygonPoints(List<Point> pointsList) {
        Line line;
        for (int i = 0; i < pointsList.size(); i++) {
            if (i < pointsList.size() - 1) {
                line = new Line(pointsList.get(i).getCoordX(), pointsList.get(i).getCoordY(), pointsList.get(i + 1).getCoordX(), pointsList.get(i + 1).getCoordY());
            } else {
                line = new Line(pointsList.get(i).getCoordX(), pointsList.get(i).getCoordY(), pointsList.get(0).getCoordX(), pointsList.get(0).getCoordY());
            }
            polygonLineList.add(line);
        }
    }

    public void moveGameObject(Point moveToPoint) {
        double deltaX = moveToPoint.getCoordX() - getWorldPossition().getCoordX();
        double deltaY = moveToPoint.getCoordY() - getWorldPossition().getCoordY();
        double angle = calculateAngleBetweenPoints(deltaX, deltaY);

        worldPossition.setCoordX(worldPossition.getCoordX() - Math.cos(Math.toRadians(angle + 90)) * 1);
        worldPossition.setCoordY(worldPossition.getCoordY() - Math.sin(Math.toRadians(angle + 90)) * 1);
    }

    private double calculateAngleBetweenPoints(double x, double y) {
        double angle;
        if (y == 0 && x == 0) {
            angle = 0;
        } else if (y > 0) {
            angle = Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        } else {
            angle = -Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        }
        angle = (angle + 360) % 360;
        return angle;
    }

    public abstract void createPolygonForDetection();

    @Override
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape projectileTrajectoryLine, Point trajectoryStartPosition) {
        Point intersectionPoint = new Point(0, 0);

        createPolygonForDetection();
        Shape intersect = Shape.intersect(projectileTrajectoryLine, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return new ResultOfDetectColisionWithProjectile(false, new Point(0,0));
        }

        Point possibleIntersectionOne = new Point(intersect.getLayoutBounds().getMaxX(), intersect.getLayoutBounds().getMaxY());
        Point possibleIntersectionTwo = new Point(intersect.getLayoutBounds().getMinX(), intersect.getLayoutBounds().getMaxY());
        Point possibleIntersectionThree = new Point(intersect.getLayoutBounds().getMaxX(), intersect.getLayoutBounds().getMinY());
        Point possibleIntersectionFour = new Point(intersect.getLayoutBounds().getMinX(), intersect.getLayoutBounds().getMinY());
        
        double distancePointOne = Math.abs(trajectoryStartPosition.getCoordX() - possibleIntersectionOne.getCoordX()) + Math.abs(trajectoryStartPosition.getCoordY() - possibleIntersectionOne.getCoordY());
        double distancePointTwo = Math.abs(trajectoryStartPosition.getCoordX() - possibleIntersectionTwo.getCoordX()) + Math.abs(trajectoryStartPosition.getCoordY() - possibleIntersectionTwo.getCoordY());
        double distancePointThree = Math.abs(trajectoryStartPosition.getCoordX() - possibleIntersectionThree.getCoordX()) + Math.abs(trajectoryStartPosition.getCoordY() - possibleIntersectionThree.getCoordY());
        double distancePointFour = Math.abs(trajectoryStartPosition.getCoordX() - possibleIntersectionFour.getCoordX()) + Math.abs(trajectoryStartPosition.getCoordY() - possibleIntersectionFour.getCoordY());

        if (distancePointOne <= distancePointTwo && distancePointOne <= distancePointThree && distancePointOne <= distancePointFour) {
            intersectionPoint = possibleIntersectionOne;
        } else if(distancePointTwo <= distancePointOne && distancePointTwo <= distancePointThree && distancePointTwo <= distancePointFour){
            intersectionPoint = possibleIntersectionTwo;
        } else if (distancePointThree <= distancePointOne && distancePointThree <= distancePointTwo && distancePointThree <= distancePointFour){
            intersectionPoint = possibleIntersectionThree;
        }else{
            intersectionPoint = possibleIntersectionFour;
        }
        
        intersectionPoint.setCoordX(intersectionPoint.getCoordX() - MinigunHitIntoStaticObject.explosionImageSize / 2);
        intersectionPoint.setCoordY(intersectionPoint.getCoordY() - MinigunHitIntoStaticObject.explosionImageSize / 2);
        return new ResultOfDetectColisionWithProjectile(true, intersectionPoint);
    }

    public Shape detectIntersection(Shape lineDetection) {
        return Shape.intersect(gameObjectPolygon, lineDetection);
    }

    public Polygon getGameObjectPolygon() {
        return gameObjectPolygon;
    }

    public List<Line> getPolygonLineList() {
        return polygonLineList;
    }
}
