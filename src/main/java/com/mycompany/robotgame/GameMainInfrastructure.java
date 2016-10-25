/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotgame;

import CollisionDetection.DetectCollisions;
import Encounter.Encounter;
import Enemy.BomberAirplane;
import Enemy.EnemyContainer;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import playerInterface.BarInterfaceHandler;
import playerRobot.PlayerRobot;

public class GameMainInfrastructure {

    public static double WINDOW_WIDTH;
    public static double WINDOW_HEIGH;
    public static double windowPositionX = 0.0;
    public static double windowPositionY = 0.0;

    private static AnimationTimer gameLoop;

    private boolean mousePressed = false;
    private boolean keyAPressed = false;
    private boolean keySPressed = false;
    private boolean keyWPressed = false;
    private boolean keyDPressed = false;
    private boolean keyXPressed = false;
    private boolean keySpacePressed = false;

    private PlayerRobot playerRobot;
    private GridTable gridTable;
    private EnemyContainer enemyContainer;
    private GameDynamicEnviroment gameDynamicEnviroment;
    private DetectCollisions detectCollisions;
    private ProjectileContainer projectileContainer;
    private BarInterfaceHandler barInterfaceHandler;
    private boolean openInterfaceBar = true;
    private GraphicsContext gameGraphicsContext;
    
    private Encounter encounter;

    public GameMainInfrastructure(Stage stage, VBox gamePanel) throws Exception {
        StackPane gameCanvasPanel = new StackPane();
        changeCanvasWidthAndHeighToFullSize();

        final Canvas gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        gameGraphicsContext = gameCanvas.getGraphicsContext2D();
        final Canvas interfaceCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext interfaceGraphicsContext = interfaceCanvas.getGraphicsContext2D();

        gameCanvasPanel.getChildren().add(gameCanvas);
        gameCanvasPanel.getChildren().add(interfaceCanvas);

        Point startMonitorWindowPos = new Point(4000, 8000);
        MonitorWindow monitorWindow = new MonitorWindow(startMonitorWindowPos);
        gridTable = new GridTable(monitorWindow);
        playerRobot = new PlayerRobot(gameGraphicsContext, new Point(startMonitorWindowPos.getCoordX() + WINDOW_WIDTH / 2, startMonitorWindowPos.getCoordY() + WINDOW_HEIGH / 2), gridTable, monitorWindow);
        enemyContainer = new EnemyContainer(gameGraphicsContext, gridTable, monitorWindow);
        projectileContainer = new ProjectileContainer();

        gameDynamicEnviroment = new GameDynamicEnviroment(gameGraphicsContext, monitorWindow);
        detectCollisions = new DetectCollisions(playerRobot, gameDynamicEnviroment, gridTable, enemyContainer, projectileContainer);
        encounter = new Encounter(enemyContainer, gridTable, monitorWindow, gameGraphicsContext);

        barInterfaceHandler = new BarInterfaceHandler(interfaceGraphicsContext, playerRobot);

        //    enemyContainer.addEnemy(new EvilDroneMarkOne(new Point(1800, 8000), 64, 64, 3, 20, 30, enemyGraphicsContext, gridTable, monitorWindow));
     //   enemyContainer.addEnemy(new SpiderRobot(new Point(4000, 8500), 2.3, 20, 30, gameGraphicsContext, gridTable, monitorWindow, projectileContainer, gameDynamicEnviroment)); //2.3
    //    enemyContainer.addEnemy(new EvilDroneMarkTwo(new Point(2000, 8500), 64, 64, 2, 15, 20, gameGraphicsContext, gridTable, monitorWindow));
     //   enemyContainer.addEnemy(new StaticRocketTurret(new Point(4175, 7466), 2, 20, 30, false, gameGraphicsContext, gridTable, monitorWindow, projectileContainer));
     //   enemyContainer.addEnemy(new StaticRocketTurret(new Point(4515, 7466), 2, 20, 30, false, gameGraphicsContext, gridTable, monitorWindow, projectileContainer));
     //   enemyContainer.addEnemy(new BomberAirplane(new Point(3700,8500), 7, 10, 20, gameGraphicsContext, gridTable, monitorWindow, projectileContainer));

        //    enemyContainer.addEnemy(new StaticRocketTurret(new Point(2515, 7600), 64, 64, 2, 20, 30, enemyGraphicsContext, gridTable, monitorWindow, projectileContainer));
        CreateMap1 createMap1 = new CreateMap1(gameGraphicsContext, monitorWindow, playerRobot, enemyContainer, gridTable);
        createMap1.generatedObjectForGame(gridTable);
        createMap1.generateGameMapBorders(gridTable);
        createMap1.generateBackground(gridTable);
        
        MergeOverlapingObjects mergeOverlapingObjects = new MergeOverlapingObjects(gridTable, 64);

        VBox gameVerticalPanel = new VBox();
        gameVerticalPanel.getChildren().add(gameCanvasPanel);
        gamePanel.getChildren().add(gameVerticalPanel);

        setUpMouseListeners(stage);
        setUpKeyboardListeners(stage);
        setUpResizeListeners(stage);

        buildAndSetGameLoop(stage);
    }

    private void changeCanvasWidthAndHeighToFullSize() {
        WINDOW_WIDTH = Screen.getPrimary().getVisualBounds().getMaxX();
        WINDOW_HEIGH = Screen.getPrimary().getVisualBounds().getMaxY();
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
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setUpKeyAsPressed(true, event);
            }
        });

        stage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setUpKeyAsPressed(false, event);
            }
        });
    }

    private void setUpResizeListeners(Stage stage) {
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
            case "X":
                keyXPressed = pressed;
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
                
                // clear the screen
                gameGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

                movePlayerRobot();
                showHideInterface();
                
                gridTable.paintAllObjectsInMonitorWindow(0);
                gridTable.paintAllObjectsInMonitorWindow(1);
                gameDynamicEnviroment.paintAllMinigunsHitsOnGround();
                gameDynamicEnviroment.paintAllLaserHitsOnGround();

                enemyContainer.testEnemiesAlive();
                enemyContainer.moveEnemies(new Point(playerRobot.getWorldPossition().getCoordX(), playerRobot.getWorldPossition().getCoordY()));
                //paint enemies under player robot
                enemyContainer.paintEnemies(playerRobot.getWorldPossition(), 0); //paint enemies in layer 0 -> under player
                enemyContainer.paintAllDiingEnemies(0);
                                
                playerRobot.paintGameObject();
                playerRobot.shootFromRobotTurret(mousePressed);
                
                //paint enemies above player robot
                enemyContainer.paintEnemies(playerRobot.getWorldPossition(), 3); //paint enemies in layer 3 -> above player e.g. planes,..
                enemyContainer.paintAllDiingEnemies(3);

                projectileContainer.moveAllProjectiles();
                projectileContainer.paintAllProjectiles();
                projectileContainer.testAllProjectilesForReachingMaximumRange();
                projectileContainer.paintAllProjectilesExplosions();

                detectCollisions.detectCollisionsWithPlayerMinigunShots();
                detectCollisions.detectCollisionOfAllDronesWithPlayerRobot();
                detectCollisions.detectCollisionOfRocketWithStaticObjectsAndOtherEnemies();
                
            //    encounter.generateNewEncounter();
            //    encounter.continuousSummoningOfAddsForAlreadyGeneratedEncounters();
                
                barInterfaceHandler.checkChangesInBarsAndPaintThemIfNecessary();
            }

        });
    }

    private void movePlayerRobot() {
        // sound section was causing lags, commented for now
        if (keyAPressed == true || keySPressed == true || keyWPressed == true || keyDPressed == true) {
        //    playerRobot.playRobotMovingSound();
            playerRobot.moveTracks();
        } else {
         //   playerRobot.playRobotIdleSound();
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

    private void showHideInterface() {
        if (keyXPressed == true) {
            keyXPressed = false;
            if (!barInterfaceHandler.isAnimationInProgress()) {
                barInterfaceHandler.setAnimationInProgress(true);
                if (openInterfaceBar) {
                    openInterfaceBar = false;
                    barInterfaceHandler.addBar();
                } else {
                    openInterfaceBar = true;
                    barInterfaceHandler.removeBar();
                }
            }
        }
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

    public void paintInterface() {
        barInterfaceHandler.paintInterface();
    }

}
