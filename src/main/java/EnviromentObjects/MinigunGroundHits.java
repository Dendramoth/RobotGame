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
public class MinigunGroundHits {
    
    private Point worldPosition;
    private Image explosionImage;
    private GraphicsContext graphicsContext;
    private MonitorWindow monitorWindow;
    private int numberOfFramesBeingDisplayed = 100;

    public MinigunGroundHits(Point worldPosition, GraphicsContext graphicsContext, MonitorWindow monitorWindow) {
        this.worldPosition = worldPosition;
        this.graphicsContext = graphicsContext;
        this.monitorWindow = monitorWindow;
        
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss1");
                break;
            case 1:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss2");
                break;
            case 2:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss3");
                break;
            case 3:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss4");
                break;
            case 4:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss5");
                break;
            case 5:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss6");
                break;
            case 6:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss7");
                break;
        }
    }
    
    public void paint() {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(explosionImage, worldPosition.getCoordX() - monitorPossition.getCoordX() - 64, worldPosition.getCoordY() - monitorPossition.getCoordY() - 64);
        numberOfFramesBeingDisplayed--;
    }

    public int getNumberOfFramesBeingDisplayed() {
        return numberOfFramesBeingDisplayed;
    }
}
