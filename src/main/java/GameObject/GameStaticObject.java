/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author styma01
 */
public abstract class GameStaticObject extends GameObjectWithDistanceDetection{
    protected final Polygon gameObjectPolygon = new Polygon();
    private List<Point> listOfPathPoints = new ArrayList<Point>();
    private List<Line> polygonLineList = new ArrayList<Line>();

    public GameStaticObject(List<Point> pointsList, Point possition, double width, double heigh, GraphicsContext graphicsContext) {
        super(possition, width, heigh, graphicsContext);
        createPolygon(pointsList);
        createLinesFromPolygonPoints(pointsList);
    }

    protected void createPolygon(List<Point> pointsList) {
        gameObjectPolygon.getPoints().clear();
        for (Point point : pointsList) {
            gameObjectPolygon.getPoints().add(point.getCoordX());
            gameObjectPolygon.getPoints().add(point.getCoordY());
        }
    }

    @Override
    public void paintGameObject() {};
    
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
