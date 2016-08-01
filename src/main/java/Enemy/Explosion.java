/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import GameObject.Point;
import com.mycompany.robotgame.LoadAllResources;
import com.mycompany.robotgame.MonitorWindow;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author styma01
 */
public class Explosion {

    private double possitionX = 0;
    private double possitionY = 0;
    private Image explosionImage;
    private int numberOfFramesBeingDisplayed = 10;
    private MonitorWindow monitorWindow;
    
    public Explosion(MonitorWindow monitorWindow) {
        Random random = new Random();
        this.possitionX = random.nextDouble() * 32;
        this.possitionY = random.nextDouble() * 32;
        this.monitorWindow = monitorWindow;

        switch (random.nextInt(3)) {
            case 0:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("droneHit1");
                break;
            case 1:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("droneHit2");
                break;
            case 2:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("droneHit3");
                break;
        }
    }
    
    
    
    public void paint(Point worldPosition, GraphicsContext graphicsContext) {
        Point monitorPossition = monitorWindow.getPositionInWorld();
        graphicsContext.drawImage(explosionImage, worldPosition.getCoordX() - possitionX - monitorPossition.getCoordX(), worldPosition.getCoordY() - possitionY - monitorPossition.getCoordY());
        numberOfFramesBeingDisplayed--;
    }

    public int getNumberOfFramesBeingDisplayed() {
        return numberOfFramesBeingDisplayed;
    }
}
