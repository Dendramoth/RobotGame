/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import com.mycompany.robotgame.GameMainInfrastructure;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

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
    private HullIntegrityBar hullIntegrityBar;
    private static double STANDARD_BAR_HEIGHT = 36;

    public BarInterfaceHandler(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        barWrapperBottom = new BarWrapperBottom(graphicsContext);
        barWrapperTop = new BarWrapperTop(graphicsContext);
        hullIntegrityBar = new HullIntegrityBar();
    }

    public void paintInterface() {
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        barWrapperBottom.paintBottomWrapper();
        barWrapperTop.paintTopWrapper();
        hullIntegrityBar.paintBar(barWrapperBottom.getBarYCoord() - STANDARD_BAR_HEIGHT);
    }

    /*********************************************************
     * ADD BAR TO INTERFACE
     */
    public void addBar() {
        moveTopWrapperUpAndAddNewBar();
        gameLoop.start();
    }

    private void moveTopWrapperUpAndAddNewBar() {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
                barWrapperTop.setCurrentOffset(barWrapperTop.getCurrentOffset() - 1);
                paintInterface();
                if (animationCounter == 42) {
                    animationCounter = 0;
                    gameLoop.stop();
                    addBarAnimation();
                    gameLoop.start();
                }
            }
        });
    }

    private void addBarAnimation() {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
                if (animationCounter % 5 == 0) {
                    hullIntegrityBar.showBiggerPartOfBar(graphicsContext);
                    paintInterface();
                }
                if (hullIntegrityBar.isBarIscompletelyVisible()) {
                    animationCounter = 0;
                    gameLoop.stop();
                }
            }
        });
    }

    
    /***************************************************************************
     * REMOVE BAR FROM INTERFACE
     */
    
    public void removeBar() {
        removeBarAndCloseGapAnimation();
        gameLoop.start();
    }
    
    private void removeBarAndCloseGapAnimation() {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
                if (animationCounter % 5 == 0) {
                    hullIntegrityBar.showSmallerPartOfBar(graphicsContext);
                    paintInterface();
                }
                if (hullIntegrityBar.isBarIscompletelyHidden()) {
                    animationCounter = 0;
                    gameLoop.stop();
                    moveTopWrapperDown();
                    gameLoop.start();
                }
            }
        });
    }

    private void moveTopWrapperDown() {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
                barWrapperTop.setCurrentOffset(barWrapperTop.getCurrentOffset() + 1);
                paintInterface();
                if (animationCounter == 42) {
                    animationCounter = 0;
                    gameLoop.stop();
                }
            }
        });
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /**************************************************************************
     * Other Methods
     */

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
