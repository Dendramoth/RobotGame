/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import com.mycompany.robotgame.GameMainInfrastructure;
import com.mycompany.robotgame.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    public EvilDroneMarkOne(Point possitionInWorld, double width, double heigh, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext) {
        super(possitionInWorld, width, heigh, movementSpeed, damagedStateTreshold, hitPoints, graphicsContext);

        enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
        hitPoints = 30;
        damagedStateTreshold = 25;
    }

 /*   @Override
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        return paintDyingEnemyAnimation(enemyGraphicsContext);
    }

    @Override
    public void doOnBeingHit(String weaponType) {
        switch (weaponType) {
            case "rocket":
                hitPoints = hitPoints - 20;
                break;
            case "minigun":
                hitPoints = hitPoints - 1;
                allExplosionsOnEnemy.add(new Explosion());
                break;
            default:
                hitPoints--;
        }
        if (hitPoints < damagedStateTreshold) {
            movementSpeed = 0.5;
        }
    }*/

    @Override
    public void paintEnemy(double playerPossitionX, double playerPossitionY) {
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

        System.out.println("----------------------------");
        System.out.println((playerPossitionX - worldPossition.getCoordX()) + " " + (playerPossitionY - worldPossition.getCoordY()));
        System.out.println((playerPossitionX) + " " + (playerPossitionY));
        graphicsContext.drawImage(enemyImage, playerPossitionX - worldPossition.getCoordX(), playerPossitionY - worldPossition.getCoordY());
    }

    public void paintDetectionParagraph() {
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.strokeLine(x1, y1, x2, y2);
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.strokeLine(x1 + 64, y1 + 64, x2 + 64, y2 + 64);
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.strokeLine(x1 + 64, y1, x2 + 64, y2);
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.strokeLine(x1, y1 + 64, x2, y2 + 64);
    }

    public void setUpXYForDetectionParagraph(double x, double y, double x2, double y2) {
        this.x1 = x;
        this.x2 = x2;
        this.y1 = y;
        this.y2 = y2;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        playerPossitionX = playerPossitionX + 64;
        playerPossitionY = playerPossitionY + 96;
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
    }

}
