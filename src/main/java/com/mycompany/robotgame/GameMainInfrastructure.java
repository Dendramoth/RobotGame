/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import CollisionDetection.DetectCollisions;
import Enemy.EnemyContainer;
import Enemy.EvilDroneMarkOne;
import Enemy.EvilDroneMarkTwo;
import Enemy.SpiderRobot;
import Enemy.StaticRocketTurret;
import GameObject.Point;
import MapGridTable.GridTable;
import Projectiles.ProjectileContainer;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import playerRobot.PlayerRobot;

public class GameMainInfrastructure {

    public static double WINDOW_WIDTH = 1024;
    public static double WINDOW_HEIGH = 720;
    //public static int FRAMERATE = 60;
    public static double windowPositionX = 0.0;
    public static double windowPositionY = 0.0;

    //private static Timeline gameLoop;
    private static AnimationTimer gameLoop;

    private boolean mousePressed = false;
    private boolean keyAPressed = false;
    private boolean keySPressed = false;
    private boolean keyWPressed = false;
    private boolean keyDPressed = false;
    private boolean keySpacePressed = false;

    private Label robotHpValueLabel;
    private Label shieldHpValueLabel;
    private Label playerWorldPossitionValueLabel;
    private Label gameOverLabel = new Label("");

    private PlayerRobot playerRobot;
    private GridTable gridTable;
    private EnemyContainer enemyContainer;
    private GameDynamicEnviroment gameDynamicEnviroment;
    private DetectCollisions detectCollisions;
    private ProjectileContainer projectileContainer;

    public GameMainInfrastructure(Stage stage, VBox gamePanel) throws Exception {
        StackPane gameCanvasPanel = new StackPane();
        changeCanvasWidthAndHeighToFullSize();

        final Canvas baseCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext enviromentGraphicsContext = baseCanvas.getGraphicsContext2D();
        final Canvas enemiesCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext enemyGraphicsContext = enemiesCanvas.getGraphicsContext2D();
        final Canvas robotCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext robotGraphicsContext = robotCanvas.getGraphicsContext2D();

        gameCanvasPanel.getChildren().add(baseCanvas);
        gameCanvasPanel.getChildren().add(enemiesCanvas);
        gameCanvasPanel.getChildren().add(robotCanvas);

        Point startMonitorWindowPos = new Point(2000, 8000);
        MonitorWindow monitorWindow = new MonitorWindow(startMonitorWindowPos);
        gridTable = new GridTable(enviromentGraphicsContext, monitorWindow);
        playerRobot = new PlayerRobot(robotGraphicsContext, new Point(startMonitorWindowPos.getCoordX() + WINDOW_WIDTH / 2, startMonitorWindowPos.getCoordY() + WINDOW_HEIGH / 2), gridTable, monitorWindow);
        enemyContainer = new EnemyContainer(enemyGraphicsContext, gridTable, enviromentGraphicsContext, monitorWindow);
        projectileContainer = new ProjectileContainer();
    //    enemyContainer.addEnemy(new EvilDroneMarkOne(new Point(1800, 8000), 64, 64, 3, 20, 30, enemyGraphicsContext, gridTable, monitorWindow));
        enemyContainer.addEnemy(new SpiderRobot(new Point(2000, 8500), 256, 256, 2.3, 20, 30, enemyGraphicsContext, gridTable, monitorWindow)); //2.3
    //    enemyContainer.addEnemy(new EvilDroneMarkTwo(new Point(2000, 8500), 64, 64, 2, 15, 20, enemyGraphicsContext, gridTable, monitorWindow));
        enemyContainer.addEnemy(new StaticRocketTurret(new Point(2175, 7466), 64, 64, 2, 20, 30, enemyGraphicsContext, gridTable, monitorWindow, projectileContainer));
        enemyContainer.addEnemy(new StaticRocketTurret(new Point(2515, 7466), 64, 64, 2, 20, 30, enemyGraphicsContext, gridTable, monitorWindow, projectileContainer));
        
    //    enemyContainer.addEnemy(new StaticRocketTurret(new Point(2515, 7600), 64, 64, 2, 20, 30, enemyGraphicsContext, gridTable, monitorWindow, projectileContainer));
        
        gameDynamicEnviroment = new GameDynamicEnviroment(enviromentGraphicsContext, monitorWindow);
        detectCollisions = new DetectCollisions(playerRobot, gameDynamicEnviroment, gridTable, enemyContainer);

        CreateMap1 createMap1 = new CreateMap1(enviromentGraphicsContext, monitorWindow);
        createMap1.generatedObjectForGame(gridTable);
        createMap1.generateGameMapBorders(gridTable);
        createMap1.generateBackground(gridTable);

        HBox userProfilePanel = new HBox();
        Label robotHpLabel = new Label("Robot HP:");
        robotHpValueLabel = new Label(String.valueOf(playerRobot.getHitPoints()));
        userProfilePanel.getChildren().add(robotHpLabel);
        userProfilePanel.getChildren().add(robotHpValueLabel);
        userProfilePanel.getChildren().add(gameOverLabel);

        HBox playerShiedInformation = new HBox();
        Label shieldHpLabel = new Label("Shield Energy:");
        shieldHpValueLabel = new Label(String.valueOf(playerRobot.getPlayerRobotShield().getShieldHitPoints()));
        playerShiedInformation.getChildren().add(shieldHpLabel);
        playerShiedInformation.getChildren().add(shieldHpValueLabel);

        HBox playerWorldPossition = new HBox();
        Label playerWorldPossitionLabel = new Label("World Possition:");
        playerWorldPossitionValueLabel = new Label(String.valueOf(playerRobot.getWorldPossition().getCoordX() + " " + playerRobot.getWorldPossition().getCoordY()));
        playerWorldPossition.getChildren().add(playerWorldPossitionLabel);
        playerWorldPossition.getChildren().add(playerWorldPossitionValueLabel);

        VBox gameVerticalPanel = new VBox();
        gameVerticalPanel.getChildren().add(gameCanvasPanel);
        gameVerticalPanel.getChildren().add(playerShiedInformation);
        gameVerticalPanel.getChildren().add(userProfilePanel);
        gameVerticalPanel.getChildren().add(playerWorldPossition);

        gamePanel.getChildren().add(gameVerticalPanel);

        setUpMouseListeners(stage);
        setUpKeyboardListeners(stage);
        setUpResizeListeners(stage, baseCanvas, robotCanvas, enemiesCanvas);

        buildAndSetGameLoop(stage);
    }

    private void changeCanvasWidthAndHeighToFullSize() {
        WINDOW_WIDTH = Screen.getPrimary().getVisualBounds().getMaxX();
        WINDOW_HEIGH = Screen.getPrimary().getVisualBounds().getMaxY() - 100;
    }

    private void setUpMouseListeners(Stage stage) {
        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setUpMouseAsPressed(true);
            }
        });
        stage.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setUpMouseAsPressed(false);
            }
        });
    }

    private void setUpKeyboardListeners(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setUpKeyAsPressed(true, event);
            }
        });

        stage.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setUpKeyAsPressed(false, event);
            }
        });
    }

    private void setUpResizeListeners(Stage stage, final Canvas baseCanvas, final Canvas robotCanvas, final Canvas enemyCanvas) {
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                WINDOW_WIDTH = (double) newValue;
            }
        });

        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                WINDOW_HEIGH = (double) newSceneHeight;
            }
        });
    }

    private void setUpMouseAsPressed(final boolean pressed) {
        mousePressed = pressed;
    }

    private void setUpKeyAsPressed(final boolean pressed, final KeyEvent event) {
        switch (event.getCode().toString().toUpperCase()) {
            case "A":
                keyAPressed = pressed;
                break;
            case "S":
                keySPressed = pressed;
                break;
            case "W":
                keyWPressed = pressed;
                break;
            case "D":
                keyDPressed = pressed;
                break;
            case "SPACE":
                keySpacePressed = pressed;

        }
    }

    private void buildAndSetGameLoop(final Stage stage) {
        setGameLoop(new AnimationTimer() {
            /**
             * Everything inside this handle is what will be repeated in every
             * game loop. Move objects here, detect collisions etc.
             */
            @Override
            public void handle(long now) {
                windowPositionX = stage.getX();
                windowPositionY = stage.getY();

                movePlayerRobot();
                playerRobot.paintGameObject();
                playerRobot.shootFromRobotTurret(mousePressed);
                gridTable.paintAllObjectsInMonitorWindow();
                gameDynamicEnviroment.paintAllMinigunsHitsOnGround();

                enemyContainer.testEnemiesAlive();
                enemyContainer.moveEnemies(new Point(playerRobot.getWorldPossition().getCoordX(), playerRobot.getWorldPossition().getCoordY()));
                enemyContainer.paintEnemies(playerRobot.getWorldPossition());
                enemyContainer.paintAllDiingEnemies();
                
                projectileContainer.moveAllProjectiles();
                projectileContainer.paintAllProjectiles();
                projectileContainer.testAllProjectilesForReachingMaximumRange();
                projectileContainer.paintAllProjectilesExplosions();
                
                detectCollisions.detectCollisionsWithPlayerMinigunShots();
                
                playerWorldPossitionValueLabel.setText(String.valueOf(playerRobot.getWorldPossition().getCoordX() + " " + playerRobot.getWorldPossition().getCoordY()));
            }

        });
    }

    private void movePlayerRobot() {
        if (keyAPressed == true || keySPressed == true || keyWPressed == true || keyDPressed == true) {
            playerRobot.playRobotMovingSound();
            playerRobot.moveTracks();
        } else {
            playerRobot.playRobotIdleSound();
        }

        if (keyAPressed == true) {
            playerRobot.moveRobotLeft();
        }
        if (keySPressed == true) {
            playerRobot.moveRobotBackward();
        }
        if (keyWPressed == true) {
            playerRobot.moveRobotForward();
        }
        if (keyDPressed == true) {
            playerRobot.moveRobotRight();
        }

        playerRobot.setShieldActive(keySpacePressed);
    }

    protected static void setGameLoop(AnimationTimer gameLoop) {
        GameMainInfrastructure.gameLoop = gameLoop;
    }

    public void beginGameLoop() {
        gameLoop.start();
    }

    public void stopGameLoop() {
        gameLoop.stop();
    }

}
