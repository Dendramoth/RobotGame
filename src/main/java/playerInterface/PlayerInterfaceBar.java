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
public abstract class PlayerInterfaceBar {
    
    protected boolean barIscompletelyVisible = false;
    protected boolean barIscompletelyHidden = true;
    protected int displayedStage = 0;
    protected GraphicsContext graphicsContext;
    protected Image barImage; 
    protected boolean shouldBeDisplayed;
    protected int currentlyDisplayedPercentage = 10;
    protected PlayerRobot playerRobot;
    protected Image barTextImage;
    
    public abstract boolean haveBarStatusChanged();
    public abstract int barPercentageStatus();
    
    public PlayerInterfaceBar(GraphicsContext graphicsContext, boolean shouldBeDisplayed, PlayerRobot playerRobot, Image barTextImage) {
        this.graphicsContext = graphicsContext;
        this.shouldBeDisplayed = shouldBeDisplayed;
        this.playerRobot = playerRobot;
        this.barTextImage = barTextImage;
    }
        
    public void paintBar(double coordY){
        if (displayedStage > 0) {
            if (barIscompletelyVisible){
                selectProperBarNumberImage();
            }
            graphicsContext.drawImage(barImage, 20, coordY);
            if (barIscompletelyVisible) {
                graphicsContext.drawImage(barTextImage, 20, coordY);
            }
        }
    }
    
    public void showBiggerPartOfBar(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
        displayedStage++;
        chooseCorrectBarFrameForOpeningClosingAnimation();
        
        if (displayedStage >= 10){
            barIscompletelyVisible = true;
        }
        if (displayedStage > 0){
            barIscompletelyHidden = false;
        }
        
    }
    
    public void showSmallerPartOfBar(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
        displayedStage--;
        chooseCorrectBarFrameForOpeningClosingAnimation();
        
        if (displayedStage <= 0){
            barIscompletelyHidden = true;
        }
        if (displayedStage < 10){
            barIscompletelyVisible = false;
        }
    }
    
    public void chooseCorrectBarFrameForOpeningClosingAnimation(){
        switch (displayedStage){
            case 1:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro1");
                break;
            case 2:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro2");
                break;
            case 3:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro3");
                break;
            case 4:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro4");
                break;
            case 5:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro5");
                break;
            case 6:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro6");
                break;
            case 7:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro7");
                break;
            case 8:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro8");
                break;
            case 9:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro9");
                break;
            case 10:
                barImage = LoadAllResources.getMapOfAllImages().get("displayTen");
                break;
            default:
                barImage = LoadAllResources.getMapOfAllImages().get("barIntro10");
        }
    }
    
    protected void selectProperBarNumberImage(){
        int percentage = barPercentageStatus();
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

    public boolean isBarIscompletelyVisible() {
        return barIscompletelyVisible;
    }

    public boolean isBarIscompletelyHidden() {
        return barIscompletelyHidden;
    }

    public int getDisplayedStage() {
        return displayedStage;
    }
    
    
}
