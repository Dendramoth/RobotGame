/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import com.mycompany.robotgame.GameMainInfrastructure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private ShieldBar shieldBar;
    private static double STANDARD_BAR_HEIGHT = 36;
    private HashSet<PlayerInterfaceBar> allBars = new HashSet<>();

    public BarInterfaceHandler(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        barWrapperBottom = new BarWrapperBottom(graphicsContext);
        barWrapperTop = new BarWrapperTop(graphicsContext);
        hullIntegrityBar = new HullIntegrityBar(graphicsContext, true);
        shieldBar = new ShieldBar(graphicsContext, true);
        allBars.add(hullIntegrityBar);
        allBars.add(shieldBar);
    }

    public void paintInterface() {
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        barWrapperBottom.paintBottomWrapper();
        barWrapperTop.paintTopWrapper();
        int barPosition = 1;

        for (PlayerInterfaceBar playerInterfaceBar : allBars) {
            if (playerInterfaceBar.shouldBeDisplayed) {
                playerInterfaceBar.paintBar(barWrapperBottom.getBarYCoord() - STANDARD_BAR_HEIGHT * barPosition);
                if (playerInterfaceBar.displayedStage > 0) {
                    barPosition++;
                }
            }
        }
    }

    public void showInterface() {

    }

    public void hideInterface() {

    }

    /**
     * *******************************************************
     * ADD BAR TO INTERFACE
     */
    public void addBar() {
        if (findCurrentPanelToDisplay() != null) {
            moveTopWrapperUpAndAddNewBar();
            gameLoop.start();
        }
    }

    private void moveTopWrapperUpAndAddNewBar() {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
                barWrapperTop.setCurrentOffset(barWrapperTop.getCurrentOffset() - 1);
                paintInterface();
                if (animationCounter == 40) {
                    animationCounter = 0;
                    gameLoop.stop();
                    addBarAnimation(findCurrentPanelToDisplay());
                    gameLoop.start();
                }
            }
        });
    }

    private void addBarAnimation(final PlayerInterfaceBar interfaceBar) {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
                if (animationCounter % 5 == 0) {
                    interfaceBar.showBiggerPartOfBar(graphicsContext);
                    paintInterface();
                }
                if (interfaceBar.isBarIscompletelyVisible()) {
                    animationCounter = 0;
                    gameLoop.stop();
                    addBar();
                }
            }
        });
    }

    /**
     * *************************************************************************
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
                PlayerInterfaceBar barToBeRemoved = findCurrentPanelToRemove();
                if (animationCounter % 5 == 0) {
                    barToBeRemoved.showSmallerPartOfBar(graphicsContext);
                    paintInterface();
                }
                if (barToBeRemoved.isBarIscompletelyHidden()) {
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
                if (animationCounter == 40) {
                    animationCounter = 0;
                    gameLoop.stop();
                    if (findCurrentPanelToRemove() != null){
                        removeBarAndCloseGapAnimation();
                        gameLoop.start();
                    }
                }
            }
        });
    }

    /**
     * ************************************************************************
     * Other Methods
     */
    private PlayerInterfaceBar findCurrentPanelToDisplay() {
        for (PlayerInterfaceBar interfaceBar : allBars) {
            if (!interfaceBar.barIscompletelyVisible && interfaceBar.shouldBeDisplayed) {
                return interfaceBar;
            }
        }
        return null; // all bars are already displayed
    }
    
    private PlayerInterfaceBar findCurrentPanelToRemove() {
        PlayerInterfaceBar barToBeRemoved = null;
        for (PlayerInterfaceBar interfaceBar : allBars) {
            if (interfaceBar.barIscompletelyVisible) {
                barToBeRemoved = interfaceBar;
            }
        }
        return barToBeRemoved; // all bars are already displayed
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
