/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerInterface;

import com.mycompany.robotgame.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author styma01
 */
public abstract class PlayerInterfaceBar {
    
    protected boolean barIscompletelyVisible = false;
    protected boolean barIscompletelyHidden = true;
    protected int displayedStage = 0;
    protected GraphicsContext graphicsContext;
    protected Image barImage; 
    protected boolean shouldBeDisplayed;
    
    public abstract boolean haveBarStatusChanged();

    public PlayerInterfaceBar(GraphicsContext graphicsContext, boolean shouldBeDisplayed) {
        this.graphicsContext = graphicsContext;
        this.shouldBeDisplayed = shouldBeDisplayed;
    }
        
    public void paintBar(double coordY){
        if (displayedStage > 0){
            graphicsContext.drawImage(barImage, 20, coordY);
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
