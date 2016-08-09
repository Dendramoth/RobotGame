/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class MinigunHitIntoStaticObject {

    private Point worldPosition;
    private Image explosionImage;
    private GraphicsContext graphicsContext;
    private MonitorWindow monitorWindow;
    private int numberOfFramesBeingDisplayed = 20;
    public static int explosionImageSize = 32;

    public MinigunHitIntoStaticObject(Point worldPosition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        this.worldPosition = worldPosition;
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;

        Random random = new Random();
        switch (random.nextInt(5)) {
            case 0:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("blockedMinigunShot1");
                break;
            case 1:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("blockedMinigunShot2");
                break;
            case 2:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("blockedMinigunShot3");
                break;
            case 3:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("blockedMinigunShot4");
                break;
            case 4:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("blockedMinigunShot5");
                break;
        }
    }

    public void paint() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(explosionImage, worldPosition.getCoordX() - monitorPossition.getCoordX(), worldPosition.getCoordY() - monitorPossition.getCoordY());
        numberOfFramesBeingDisplayed--;
    }

    public int getNumberOfFramesBeingDisplayed() {
        return numberOfFramesBeingDisplayed;
    }
}
