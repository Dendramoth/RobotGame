/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import com.mycompany.robotgame.GameMainInfrastructure;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class EvilDroneMarkOne extends Enemy {

    private int blinkCounter = 0;
    private int explodingTimer = 0;
    private double angleOfDrone = 0;
    private double lastAngleToAvoidCollision = 0;
    private boolean collisionDetectedInLastTest = false;

    private double x1 = 0;
    private double x2 = 0;
    private double y1 = 0;
    private double y2 = 0;

    public EvilDroneMarkOne(Point possitionInWorld, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        super(possitionInWorld, width, heigh, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, monitorWindow);

        enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
        hitPoints = 30;
        damagedStateTreshold = 25;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        double deltaX = playerPossitionX - worldPossition.getCoordX();
        double deltaY = playerPossitionY - worldPossition.getCoordY();
        angleOfDrone = calculateAngleBetweenPlayerAndDrone(deltaX, deltaY);

        worldPossition.setCoordX(worldPossition.getCoordX() - Math.cos(Math.toRadians(angleOfDrone + 90)) * movementSpeed);
        worldPossition.setCoordY(worldPossition.getCoordY() - Math.sin(Math.toRadians(angleOfDrone + 90)) * movementSpeed);
    }

    private double calculateAngleBetweenPlayerAndDrone(double x, double y) {
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
    public boolean detectCollision(Shape shape, Point playerWorldPosition) {
        return false;
    }
    
    

    /*   @Override
     public boolean detectCollision(Shape shape) {
     if (alive) {
     //   Circle meteorPolygon = new Circle(possitionOnCanvasX + enemyImage.getWidth() / 2, possitionOnCanvasY + enemyImage.getHeight() / 2, (enemyImage.getHeight() / 2));
     Rectangle r = new Rectangle();
     r.setX(possitionX);
     r.setY(possitionY);
     r.setWidth(64);
     r.setHeight(64);
     Shape intersect = Shape.intersect(shape, r);
     if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
     return false;
     }
     } else {
     return false;
     }
     return true;
     }*/
    @Override
    public void paintAllExplosionsEnemy(GraphicsContext enemyGraphicsContext) {
        /*   Iterator<Explosion> iterator = allExplosionsOnEnemy.iterator();
         while (iterator.hasNext()) {
         Explosion explosion = iterator.next();
         explosion.paint(possitionX, possitionY, enemyGraphicsContext);
         if (explosion.getNumberOfFramesBeingDisplayed() < 1) {
         iterator.remove();
         }
         }*/
    }

    @Override
    protected boolean paintDyingEnemyAnimation(GraphicsContext enemyGraphicsContext) {
        if (explodingTimer < 4) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death1");
        } else if (explodingTimer <= 5) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death2");
        } else if (explodingTimer > 5 && explodingTimer <= 10) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death3");
        } else if (explodingTimer > 10 && explodingTimer <= 15) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death4");
        } else if (explodingTimer > 15 && explodingTimer <= 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death5");
        } else {
            return false;
        }

        enemyGraphicsContext.drawImage(enemyImage, worldPossition.getCoordX(), worldPossition.getCoordY());
        explodingTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {

    }

    public double getAngleOfDrone() {
        return angleOfDrone;
    }

    public void setAngleOfDrone(double angleOfDrone) {
        this.angleOfDrone = angleOfDrone;
    }

    public double getLastAngleToAvoidCollision() {
        return lastAngleToAvoidCollision;
    }

    public void setLastAngleToAvoidCollision(double lastAngleToAvoidCollision) {
        this.lastAngleToAvoidCollision = lastAngleToAvoidCollision;
    }

    public boolean isCollisionDetectedInLastTest() {
        return collisionDetectedInLastTest;
    }

    public void setCollisionDetectedInLastTest(boolean collisionDetectedInLastTest) {
        this.collisionDetectedInLastTest = collisionDetectedInLastTest;
    }

    @Override
    public void paintGameObject() {
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

        blinkCounter++;
        if (hitPoints >= damagedStateTreshold) {
            if (blinkCounter <= 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
            }
            if (blinkCounter > 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle2");
            }
            if (blinkCounter == 30) {
                blinkCounter = 0;
            }
        }
        if (hitPoints < damagedStateTreshold) {
            if (blinkCounter <= 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1Damaged");
            }
            if (blinkCounter > 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle2Damaged");
            }
            if (blinkCounter == 30) {
                blinkCounter = 0;
            }
        }
        
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(enemyImage, worldPossition.getCoordX() - monitorPossition.getCoordX(), worldPossition.getCoordY() - monitorPossition.getCoordY());
    }

    
    

}
