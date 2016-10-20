/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerRobot;

/**
 *
 * @author Dendra
 */
import com.mycompany.robotgame.GameMainInfrastructure;
import com.mycompany.robotgame.LoadAllResources;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class PlayerRobotTurret {

    private final Image turretIdleImage;
    private final Image turretShootingImage;
    private final GraphicsContext robotGraphicsContext;
//    private final AudioClip minigunSound = LoadAllResources.getMapOfAllSounds().get("minigunSound");

    private Image turretCurrentImage;
    private double turretAngle = 0;
    private int shootingCounter = 0;
    private Image shootingMinigunFireImage;
    private ArrayList<ShotsFromMinigun> allShotsFromMinigun = new ArrayList<>();
    private int minigunImageCounter = 0;
    private boolean turretIsShooting = false;

    public PlayerRobotTurret(GraphicsContext robotGraphicsContext) {
        this.robotGraphicsContext = robotGraphicsContext;
        turretIdleImage = LoadAllResources.getMapOfAllImages().get("towerPassive");
        turretShootingImage = LoadAllResources.getMapOfAllImages().get("towerShooting");
        turretCurrentImage = turretIdleImage;
    }

    public void paintTurret(GameObject.Point screenPossition) {
        robotGraphicsContext.save();
        robotGraphicsContext.translate(screenPossition.getCoordX(), screenPossition.getCoordY());
        moveToMouseCursor(screenPossition);
        robotGraphicsContext.rotate(turretAngle);
        robotGraphicsContext.drawImage(turretCurrentImage, -turretCurrentImage.getWidth() / 2, -turretCurrentImage.getHeight() / 2);
        paintMinigunFireOnTurret();
        robotGraphicsContext.restore();
    }

    private void paintMinigunFireOnTurret() {
        if (turretIsShooting) {
        /*    if (!minigunSound.isPlaying()) {
                minigunSound.play();
            }*/
            minigunImageCounter++;
            if (minigunImageCounter <= 4) {
                shootingMinigunFireImage = LoadAllResources.getMapOfAllImages().get("minigunFire1");
            } else if (minigunImageCounter > 4 && minigunImageCounter <= 8) {
                shootingMinigunFireImage = LoadAllResources.getMapOfAllImages().get("minigunFire2");
            } else if (minigunImageCounter > 8 && minigunImageCounter <= 12) {
                shootingMinigunFireImage = LoadAllResources.getMapOfAllImages().get("minigunFire3");
            } else if (minigunImageCounter > 12 && minigunImageCounter <= 16) {
                shootingMinigunFireImage = LoadAllResources.getMapOfAllImages().get("minigunFire4");
            } else {
                shootingMinigunFireImage = LoadAllResources.getMapOfAllImages().get("minigunFire1");
                minigunImageCounter = 0;
            }
            robotGraphicsContext.drawImage(shootingMinigunFireImage, -shootingMinigunFireImage.getWidth() / 2, -shootingMinigunFireImage.getHeight() / 2);
        } else {
      /*      if (minigunSound.isPlaying()) {
                minigunSound.stop();
            }*/
            minigunImageCounter = 0;
        }
    }

    public void moveTurretToLeft() {
        turretAngle = turretAngle - 5;
    }

    public void moveTurretToRight() {
        turretAngle = turretAngle + 5;
    }

    public double getTurretAngle() {
        return turretAngle;
    }

    public void shootTurret(boolean shoot, GameObject.Point startPositionOfShooting) {
        turretIsShooting = shoot;
        if (shoot) {
            shootingCounter++;
            if (shootingCounter == 6) {
                shootMinigunProjectile(startPositionOfShooting);
            }
            if (shootingCounter > 8) {
                if (turretCurrentImage == turretIdleImage) {
                    turretCurrentImage = turretShootingImage;
                    shootingCounter = 0;
                } else {
                    turretCurrentImage = turretIdleImage;
                    shootingCounter = 3;
                }
            }
        } else {
            turretCurrentImage = turretIdleImage;
        }
    }

    private void shootMinigunProjectile(GameObject.Point worldPositionOfRobot) {
        allShotsFromMinigun.add(new ShotsFromMinigun(new GameObject.Point(worldPositionOfRobot.getCoordX(), worldPositionOfRobot.getCoordY()), turretAngle));
    }

    public void moveToMouseCursor(GameObject.Point screenPosition) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        mouseLocation.getX();
        mouseLocation.getY();

        double xMovement = mouseLocation.getX() - screenPosition.getCoordX() - GameMainInfrastructure.windowPositionX - turretCurrentImage.getWidth() / 2;
        double yMovement = mouseLocation.getY() - screenPosition.getCoordY() - GameMainInfrastructure.windowPositionY - turretCurrentImage.getHeight() / 2;

        double angleToMouse = calculateAngleForDrawingRotatedTurret(xMovement, yMovement);
        angleToMouse = (angleToMouse + 360) % 360;

        turretAngle = angleToMouse;
    }

    private double calculateAngleForDrawingRotatedTurret(double x, double y) {
        double angle = 0;
        x = x + 25; // i dont know why :( its just heruestics constant to center mouse into middle of tank

        if (y == 0 && x == 0) {
            angle = 0;
        } else if (y > 0) {
            angle = Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        } else {
            angle = -Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        }
        return angle;
    }

    public ArrayList<ShotsFromMinigun> getAllShotsFromMinigun() {
        return allShotsFromMinigun;
    }
}
