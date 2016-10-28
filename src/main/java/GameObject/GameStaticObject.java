/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import EnviromentObjects.MinigunHitIntoStaticObject;
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
    protected final Polygon gameObjectPolygon64 = new Polygon();
    private final List<Line> polygonLineList = new ArrayList<>();
    private final List<Line> polygonLineList64 = new ArrayList<>();
    
    private List<Point> pointsList;
    private List<Point> pointsList64;

    public GameStaticObject(List<Point> pointsList, List<Point> pointsList64, Point possition, double width, double heigh, int objectLayer, GraphicsContext graphicsContext, MonitorWindow monitorWindow, Image staticObjectImage) {
        super(possition, width, heigh, objectLayer, graphicsContext, monitorWindow);
        this.staticObjectImage = staticObjectImage;
        this.pointsList = pointsList;
        this.pointsList64 = pointsList64;
        
        if (pointsList != null) {
            createPolygon(pointsList);
            createLinesFromPolygonPoints(pointsList);
        }

        if (pointsList64 != null) {
            createPolygon64(pointsList64);
            createLinesFromPolygonPoints64(pointsList64);
        }
    }

    private void createPolygon(List<Point> pointsList) {
        gameObjectPolygon.getPoints().clear();
        for (Point point : pointsList) {
            gameObjectPolygon.getPoints().add(point.getCoordX());
            gameObjectPolygon.getPoints().add(point.getCoordY());
        }
    }

    private void createPolygon64(List<Point> pointsList) {
        gameObjectPolygon64.getPoints().clear();
        for (Point point : pointsList) {
            gameObjectPolygon64.getPoints().add(point.getCoordX());
            gameObjectPolygon64.getPoints().add(point.getCoordY());
        }
    }

    private void createLinesFromPolygonPoints(List<Point> pointsList) {
        polygonLineList.clear();
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

    private void createLinesFromPolygonPoints64(List<Point> pointsList) {
        polygonLineList64.clear();
        Line line;
        for (int i = 0; i < pointsList.size(); i++) {
            if (i < pointsList.size() - 1) {
                line = new Line(pointsList.get(i).getCoordX(), pointsList.get(i).getCoordY(), pointsList.get(i + 1).getCoordX(), pointsList.get(i + 1).getCoordY());
            } else {
                line = new Line(pointsList.get(i).getCoordX(), pointsList.get(i).getCoordY(), pointsList.get(0).getCoordX(), pointsList.get(0).getCoordY());
            }
            polygonLineList64.add(line);
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

    @Override
    public ResultOfDetectColisionWithProjectile detectCollisionWithProjectile(Shape projectileTrajectoryLine, Point trajectoryStartPosition) {
        Point intersectionPoint = new Point(0, 0);

        Shape intersect = Shape.intersect(projectileTrajectoryLine, gameObjectPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return new ResultOfDetectColisionWithProjectile(false, new Point(0, 0));
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
        } else if (distancePointTwo <= distancePointOne && distancePointTwo <= distancePointThree && distancePointTwo <= distancePointFour) {
            intersectionPoint = possibleIntersectionTwo;
        } else if (distancePointThree <= distancePointOne && distancePointThree <= distancePointTwo && distancePointThree <= distancePointFour) {
            intersectionPoint = possibleIntersectionThree;
        } else {
            intersectionPoint = possibleIntersectionFour;
        }

        intersectionPoint.setCoordX(intersectionPoint.getCoordX() - MinigunHitIntoStaticObject.explosionImageSize / 2);
        intersectionPoint.setCoordY(intersectionPoint.getCoordY() - MinigunHitIntoStaticObject.explosionImageSize / 2);
        return new ResultOfDetectColisionWithProjectile(true, intersectionPoint);
    }

    public Shape detectIntersection(int enemySize, Shape lineDetection) {
        switch (enemySize) {
            case 64:
                return Shape.intersect(gameObjectPolygon64, lineDetection);
            default:
                return Shape.intersect(gameObjectPolygon, lineDetection);
        }
    }

    public Polygon getGameObjectPolygon(int enemySize) {
        switch (enemySize) {
            case 64:
                return gameObjectPolygon64;
            default:
                return gameObjectPolygon;
        }

    }

    public List<Line> getPolygonLineList(int enemySize) {
        switch (enemySize) {
            case 64:
                return polygonLineList64;
            default:
                return polygonLineList;
        }
    }

    public void setPointsList(List<Point> pointsList, int enemySize) {
        switch (enemySize) {
            case 64:
                this.pointsList64.clear();
                this.pointsList64 = pointsList;
                createPolygon64(pointsList64);
                createLinesFromPolygonPoints64(pointsList64);
                break;
            default:
                this.pointsList.clear();
                this.pointsList = pointsList;
                createPolygon(pointsList);
                createLinesFromPolygonPoints(pointsList);
        }
    }
    
    public void setPointsListFake(List<Point> notUsedList,int enemySize) {
        switch (enemySize) {
            case 64:
                this.pointsList64.clear();
                pointsList64.add(new Point(3533 , 6998));
                pointsList64.add(new Point(3701, 6998));
                pointsList64.add(new Point(3701, 7294));
                pointsList64.add(new Point(3533, 7294));
                createPolygon64(pointsList64);
                createLinesFromPolygonPoints64(pointsList64);
                break;
            default:
                this.pointsList.clear();
                pointsList.add(new Point(3533 , 6998));
                pointsList.add(new Point(3701, 6998));
                pointsList.add(new Point(3701, 7294));
                pointsList.add(new Point(3533, 7294));
                createPolygon(pointsList);
                createLinesFromPolygonPoints(pointsList);
        }
    }
    
    public void setPointsListFake2(List<Point> notUsedList,int enemySize) {
        switch (enemySize) {
            case 64:
                this.pointsList64.clear();
                pointsList64.add(new Point(0 , 0));
                pointsList64.add(new Point(1, 0));
       /*         pointsList64.add(new Point(3701, 7126));
                pointsList64.add(new Point(3701, 7166));*/
                pointsList64.add(new Point(1, 1));
                pointsList64.add(new Point(0, 1));
          //      pointsList64.add(new Point(3533, 7166));
          //      pointsList64.add(new Point(3533, 7126));
                createPolygon64(pointsList64);
                createLinesFromPolygonPoints64(pointsList64);
                break;
            default:
                this.pointsList.clear();
                pointsList.add(new Point(0 , 0));
                pointsList.add(new Point(1, 0));
                pointsList.add(new Point(1, 1));
                pointsList.add(new Point(0, 1));
                createPolygon(pointsList);
                createLinesFromPolygonPoints(pointsList);
        }
    }
    
    public List<Point> getPointsList(int enemySize) {
        switch (enemySize) {
            case 64:
                return pointsList64;
            default:
                return pointsList;
        }
    }



}
