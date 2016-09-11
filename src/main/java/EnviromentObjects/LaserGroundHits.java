/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObject.Point;
import com.mycompany.robotgame.GameDynamicEnviroment;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class LaserGroundHits {
    
    private Point worldPosition;
    private Image laserImage;
    private GraphicsContext graphicsContext;
    private MonitorWindow monitorWindow;
    private int numberOfFramesBeingDisplayed = 150;

    public LaserGroundHits(Point worldPosition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        this.worldPosition = new Point (worldPosition.getCoordX(), worldPosition.getCoordY());
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;
        
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 0:
                this.laserImage = LoadAllResources.getMapOfAllImages().get("laserBurnedGround1");
                break;
            case 1:
                this.laserImage = LoadAllResources.getMapOfAllImages().get("laserBurnedGround2");
                break;
            case 2:
                this.laserImage = LoadAllResources.getMapOfAllImages().get("laserBurnedGround3");
                break;
            case 3:
                this.laserImage = LoadAllResources.getMapOfAllImages().get("laserBurnedGround4");
                break;
            default:
                this.laserImage = LoadAllResources.getMapOfAllImages().get("laserBurnedGround1");
        }
    }
    
    public void paint() {
        
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(laserImage, worldPosition.getCoordX() - monitorPossition.getCoordX() - 16, worldPosition.getCoordY() - monitorPossition.getCoordY() - 16);
        System.out.println("painting " + (worldPosition.getCoordX() - monitorPossition.getCoordX() - 16) + " " + (worldPosition.getCoordY() - monitorPossition.getCoordY() - 16));
        numberOfFramesBeingDisplayed--;
    }

    public int getNumberOfFramesBeingDisplayed() {
        return numberOfFramesBeingDisplayed;
    }
    
}
