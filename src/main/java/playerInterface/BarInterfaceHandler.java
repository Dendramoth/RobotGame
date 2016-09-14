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
    private HashSet<InterfaceBar> allBars = new HashSet<>();

    public BarInterfaceHandler(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        barWrapperBottom = new BarWrapperBottom(graphicsContext);
        barWrapperTop = new BarWrapperTop(graphicsContext);
        hullIntegrityBar = new HullIntegrityBar(graphicsContext, true);
        shieldBar = new ShieldBar(graphicsContext, true);
        allBars.add(shieldBar);
        allBars.add(hullIntegrityBar);
    }

    public void paintInterface() {
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        barWrapperBottom.paintBottomWrapper();
        barWrapperTop.paintTopWrapper();
        int barPosition = 1;
        if (hullIntegrityBar.shouldBeDisplayed) {
            hullIntegrityBar.paintBar(barWrapperBottom.getBarYCoord() - STANDARD_BAR_HEIGHT * barPosition);
            barPosition++;
        }
        if (shieldBar.shouldBeDisplayed) {
            shieldBar.paintBar(barWrapperBottom.getBarYCoord() - STANDARD_BAR_HEIGHT * barPosition);
            barPosition++;
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
                    System.out.println(findCurrentPanelToDisplay().getClass());
                    addBarAnimation(findCurrentPanelToDisplay());
                    gameLoop.start();
                }
            }
        });
    }

    private void addBarAnimation(final InterfaceBar interfaceBar) {
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
                if (animationCounter == 40) {
                    animationCounter = 0;
                    gameLoop.stop();
                }
            }
        });
    }

    /**
     * ************************************************************************
     * Other Methods
     */
    private InterfaceBar findCurrentPanelToDisplay() {
        for (InterfaceBar interfaceBar : allBars) {
            if (!interfaceBar.barIscompletelyVisible && interfaceBar.shouldBeDisplayed) {
                return interfaceBar;
            }
        }
        return null; // all bars are already displayed
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
