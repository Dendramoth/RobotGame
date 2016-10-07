/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import com.mycompany.robotgame.GameMainInfrastructure;
import java.util.HashMap;
import java.util.HashSet;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import playerRobot.PlayerRobot;

/**
 *
 * @author Dendra
 */
public class BarInterfaceHandler {

    private BarWrapperBottom barWrapperBottom;
    private BarWrapperTop barWrapperTop;
    private GraphicsContext graphicsContext;
    private static AnimationTimer gameLoop;
    private int animationCounter = 0;
    private final HullIntegrityBar hullIntegrityBar;
    private final ShieldBar shieldBar;
    private static final double STANDARD_BAR_HEIGHT = 36;
    private HashMap<Integer, PlayerInterfaceBar> allBars = new HashMap <Integer, PlayerInterfaceBar>();
    private boolean animationInProgress = false;
    private PlayerRobot playerRobot;

    public BarInterfaceHandler(GraphicsContext graphicsContext, PlayerRobot playerRobot) {
        this.graphicsContext = graphicsContext;
        this.playerRobot = playerRobot;
        
        barWrapperBottom = new BarWrapperBottom(graphicsContext);
        barWrapperTop = new BarWrapperTop(graphicsContext);
        hullIntegrityBar = new HullIntegrityBar(graphicsContext, true, playerRobot);
        shieldBar = new ShieldBar(graphicsContext, true, playerRobot);
        allBars.put(0, hullIntegrityBar);
        allBars.put(1, shieldBar);
    }

    public void paintInterface() {
        graphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        barWrapperBottom.paintBottomWrapper();
        barWrapperTop.paintTopWrapper();
        int barPosition = 1;

        for (int i = 0; i < allBars.size(); i++) {
            PlayerInterfaceBar playerInterfaceBar = allBars.get(i);
            if (playerInterfaceBar.shouldBeDisplayed) {
                playerInterfaceBar.paintBar(barWrapperBottom.getBarYCoord() - STANDARD_BAR_HEIGHT * barPosition);
                if (playerInterfaceBar.displayedStage > 0) {
                    barPosition++;
                }
            }
        }
    }

    /**
     * *******************************************************
     * ADD BAR TO INTERFACE
     */
    public void addBar() {
        if (findCurrentPanelToDisplay() != null) {
            moveTopWrapperUpAndAddNewBar();
            gameLoop.start();
        } else {
            animationInProgress = false;
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
        if (findCurrentPanelToRemove() != null) {
            removeBarAndCloseGapAnimation(findCurrentPanelToRemove());
            gameLoop.start();
        } else {
            animationInProgress = false;
        }
    }

    private void removeBarAndCloseGapAnimation(final PlayerInterfaceBar barToBeRemoved) {
        setGameLoop(new AnimationTimer() {
            @Override
            public void handle(long now) {
                animationCounter++;
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
                    removeBar();
                }
            }
        });
    }

    /**
     * ************************************************************************
     * Other Methods
     */
    
    public void checkChangesInBarsAndPaintThemIfNecessary(){
        boolean repaintBars = false;
        for (int i = 0; i < allBars.size(); i++){
            if (allBars.get(i).haveBarStatusChanged()){
                repaintBars = true;
            }
        }
        if (repaintBars){
            paintInterface();
        }
    }
    
    private PlayerInterfaceBar findCurrentPanelToDisplay() {
        for (int i = 0; i < allBars.size(); i++){
            if (!allBars.get(i).barIscompletelyVisible && allBars.get(i).shouldBeDisplayed) {
                return allBars.get(i);
            }
        }
        return null; // all bars are already displayed
    }

    private PlayerInterfaceBar findCurrentPanelToRemove() {
        for (int i = allBars.size() - 1; i >= 0; i--){
            if (allBars.get(i).barIscompletelyVisible) {
                return allBars.get(i);
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

    public boolean isAnimationInProgress() {
        return animationInProgress;
    }

    public void setAnimationInProgress(boolean animationInProgress) {
        this.animationInProgress = animationInProgress;
    }

}
