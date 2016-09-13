/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import GameObject.Point;
import com.mycompany.robotgame.GameMainInfrastructure;
import static com.mycompany.robotgame.GameMainInfrastructure.windowPositionX;
import static com.mycompany.robotgame.GameMainInfrastructure.windowPositionY;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 *
 * @author styma01
 */
public class BarInterfaceHandler {
    private BarWrapperBottom barWrapperBottom;
    private BarWrapperTop barWrapperTop;
    private GraphicsContext graphicsContext;
    private static AnimationTimer gameLoop;
    private int animationCounter = 0;

    public BarInterfaceHandler(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        barWrapperBottom = new BarWrapperBottom(graphicsContext);
        barWrapperTop = new BarWrapperTop(graphicsContext);
    }
    
    public void paintInterface(){
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        barWrapperBottom.paintBottomWrapper();
        barWrapperTop.paintTopWrapper();
    }
    
    public void addBar(){
        addBarGameLoop();
        gameLoop.start();
    }
    
    public void removeBar(){
        
    }
    
    private void addBarGameLoop() {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
                barWrapperTop.setCurrentOffset(barWrapperTop.getCurrentOffset() - 2);
                paintInterface();
                if (animationCounter == 20){
                    animationCounter = 0;
                    gameLoop.stop();
                }
            }
        });
    }
    
    protected static void setGameLoop(AnimationTimer gameLoop) {
        BarInterfaceHandler.gameLoop = gameLoop;
    }
    
    public void beginGameLoop() {
        gameLoop.start();
    }

    public void stopGameLoop() {
        gameLoop.stop();
    }
    
}
