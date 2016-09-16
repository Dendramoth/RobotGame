/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import com.mycompany.robotgame.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import playerRobot.PlayerRobot;

/**
 *
 * @author styma01
 */
public class ShieldBar extends PlayerInterfaceBar {

    private PlayerRobot playerRobot;
    private final Image shieldEnergyTextImage = LoadAllResources.getMapOfAllImages().get("TextShieldEnergy");
    private int currentlyDisplayedPercentage = 10;

    public ShieldBar(GraphicsContext graphicsContext, boolean shouldBeDisplayed, PlayerRobot playerRobot) {
        super(graphicsContext, shouldBeDisplayed);
        this.playerRobot = playerRobot;
        currentlyDisplayedPercentage = shieldPercentageStatus();
    }

    @Override
    public void paintBar(double coordY) {
        if (displayedStage > 0) {
            if (barIscompletelyVisible){
                selectProperBarNumber();
            }
            graphicsContext.drawImage(barImage, 20, coordY);
            if (barIscompletelyVisible) {
                graphicsContext.drawImage(shieldEnergyTextImage, 20, coordY);
            }
        }
    }

    @Override
    public boolean haveBarStatusChanged() {
        if (currentlyDisplayedPercentage != shieldPercentageStatus()){
            return true;
        }
        return false;
    }
    
    

    public int shieldPercentageStatus() {
        double percentage = 0;
        int newPercentageStatus = 0;
        if (playerRobot.getPlayerRobotShield().getShieldHitPoints() != 0) {
            percentage = (playerRobot.getPlayerRobotShield().getShieldHitPoints() / playerRobot.getPlayerRobotShield().getShieldMaximumHitPoints()) * 100;
        }
        
        if (percentage <= 0) {
            newPercentageStatus = 0;
        } else if (percentage > 0 && percentage <= 10) {
            newPercentageStatus = 1;
        } else if (percentage > 10 && percentage <= 20) {
            newPercentageStatus = 2;
        } else if (percentage > 20 && percentage <= 30) {
            newPercentageStatus = 3;
        } else if (percentage > 30 && percentage <= 40) {
            newPercentageStatus = 4;
        } else if (percentage > 40 && percentage <= 50) {
            newPercentageStatus = 5;
        } else if (percentage > 50 && percentage <= 60) {
            newPercentageStatus = 6;
        } else if (percentage > 60 && percentage <= 70) {
            newPercentageStatus = 7;
        } else if (percentage > 70 && percentage <= 80) {
            newPercentageStatus = 8;
        } else if (percentage > 80 && percentage <= 90) {
            newPercentageStatus = 9;
        } else if (percentage > 90 && percentage <= 100) {
            newPercentageStatus = 10;
        }
        
        return newPercentageStatus;
    }
    
    private void selectProperBarNumber(){
        int percentage = shieldPercentageStatus();
        switch (percentage){
            case 0: 
                barImage = LoadAllResources.getMapOfAllImages().get("displayZero");
                break;
            case 1:
                barImage = LoadAllResources.getMapOfAllImages().get("displayOne");
                break;
            case 2:
                barImage = LoadAllResources.getMapOfAllImages().get("displayTwo");
                break;
            case 3:
                barImage = LoadAllResources.getMapOfAllImages().get("displayThree");
                break;
            case 4:
                barImage = LoadAllResources.getMapOfAllImages().get("displayFour");
                break;
            case 5:
                barImage = LoadAllResources.getMapOfAllImages().get("displayFive");
                break;
            case 6:
                barImage = LoadAllResources.getMapOfAllImages().get("displaySix");
                break;
            case 7:
                barImage = LoadAllResources.getMapOfAllImages().get("displaySeven");
                break;
            case 8:
                barImage = LoadAllResources.getMapOfAllImages().get("displayEight");
                break;
            case 9:
                barImage = LoadAllResources.getMapOfAllImages().get("displayNine");
                break;
            case 10:
                barImage = LoadAllResources.getMapOfAllImages().get("displayTen");
                break;
        }
    }

}
/*

*/