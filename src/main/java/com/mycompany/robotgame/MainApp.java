package com.mycompany.robotgame;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class MainApp extends Application {
    
    public LoadAllResources loadAllImages = new LoadAllResources();

    @Override
    public void start(Stage stage) throws Exception {
        VBox mainPanel = new VBox();
        
        GameMainInfrastructure gameMainInfrastructure = new GameMainInfrastructure(stage, mainPanel);
        gameMainInfrastructure.beginGameLoop();

        Scene scene = new Scene(mainPanel);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.setCursor(new ImageCursor(LoadAllResources.getMapOfAllImages().get("cursorTarget"),
                                LoadAllResources.getMapOfAllImages().get("cursorTarget").getWidth() / 2,
                                LoadAllResources.getMapOfAllImages().get("cursorTarget").getHeight() /2));

        stage.setTitle("Robot Awesome Game");
        stage.setScene(scene);
        stage.show();
        
        gameMainInfrastructure.paintInterface(); //show player interface
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
