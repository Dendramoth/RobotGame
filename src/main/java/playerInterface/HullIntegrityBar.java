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
 * @author Dendra
 */
public class HullIntegrityBar extends PlayerInterfaceBar{

    public HullIntegrityBar(GraphicsContext graphicsContext, boolean shouldBeDisplayed, PlayerRobot playerRobot) {
        super(graphicsContext, shouldBeDisplayed, playerRobot, LoadAllResources.getMapOfAllImages().get("TextHullIntergrity"));
    }
    
    @Override
    public boolean haveBarStatusChanged() {
        int actualStatusOfHitPoints = barPercentageStatus();
        if (currentlyDisplayedPercentage != actualStatusOfHitPoints){
            currentlyDisplayedPercentage = actualStatusOfHitPoints;
            return true;
        }
        return false;
    }
    
    @Override
    public int barPercentageStatus() {
        double percentage = 0;
        int newPercentageStatus = 0;
        if (playerRobot.getHitPoints() != 0) {
            percentage = ((double) playerRobot.getHitPoints() / (double) playerRobot.getMaxHitPoints()) * 100;
        }
        if (percentage <= 3) {
            newPercentageStatus = 0;
        } else if (percentage > 3 && percentage <= 10) {
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
    
}
