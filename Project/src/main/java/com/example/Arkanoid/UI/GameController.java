package main.java.com.example.Arkanoid.UI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.arkanoid.engine.*;
import javafx.animation.AnimationTimer;
import main.java.com.example.Arkanoid.Data.Lives;

import java.sql.SQLOutput;


public class GameController {

    public ImageView backgroundPlaceholder;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView pauseButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label livesLabel;

    @FXML
    private Stage stage;

    private AnimationTimer mainLoop;
    private GameEngine gameEngine;
    private Map map;

    public int levelNumber = 1; // Default level
    private ImageView backgroundView;
    private boolean needsBackgroundLoad = false; // Flag để track khi cần load background
    private Map currentMap; // Lưu map hiện tại
    private Scene scene;

    public AnimationTimer getMainLoop() {
        return mainLoop;
    }

    public void setMainLoop(AnimationTimer mainLoop) {
        this.mainLoop = mainLoop;
    }

    @FXML
    public void initialize() {
        System.out.println("🔧 GameController.initialize() called");
        System.out.println("   - anchorPane: " + (anchorPane != null ? "OK" : "NULL"));
        System.out.println("   - needsBackgroundLoad: " + needsBackgroundLoad);
        System.out.println("   - levelNumber: " + levelNumber);

        // FXML components đã được inject, anchorPane sẵn sàng
        // Nếu level đã được set trước, load background ngay
        if (needsBackgroundLoad && anchorPane != null) {
            loadBackgroundForLevel(levelNumber);
            needsBackgroundLoad = false;
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        System.out.println("GameController: Level set to " + levelNumber);
        System.out.println("   - anchorPane: " + (anchorPane != null ? "OK" : "NULL"));

        // Nếu anchorPane đã sẵn sàng, load ngay
        if (anchorPane != null) {
            System.out.println("   - Loading background immediately...");
            loadBackgroundForLevel(levelNumber);
            System.out.println("   - Loading map immediately...");
            loadMapForLevel(levelNumber);
        } else {
            // Nếu chưa, đợi initialize() gọi
            System.out.println("   - Waiting for initialize() to load background...");
            needsBackgroundLoad = true;
        }
    }

    private void loadMapForLevel(int level) {
        try {

            if (anchorPane == null) {
                System.err.println("⚠️ AnchorPane not initialized yet, skipping map load");
                return;
            }

            // Xóa map cũ nếu có
            Paddle player = new Paddle(200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
            levelNumber = level;
            gameEngine = new GameEngine();
            gameEngine.setLives(10);
            scene = anchorPane.getScene();
            map = new Map(level);
            gameEngine.setGame(anchorPane, map, player);
            map.loadMap(Define.BACKGROUND_WIDTH, Define.BACKGROUND_HEIGHT);

            for (Bricks b : map.getBrickGroup()) {
                b.setSence(scene);
                anchorPane.getChildren().add(b.getNode());
            }

            player.setScene(scene);
            anchorPane.getChildren().add(player.getNode());

            gameEngine.addBall();
            gameEngine.setLives(10);



// only register input ONCE
            scene.setOnKeyPressed(keyEvent -> {
                if (gameEngine != null ) {
                }
                switch (keyEvent.getCode()) {
                    case A :
                        gameEngine.moveLeft();
                        break;

                    case D :
                        gameEngine.moveRight();
                        break;

                    case SPACE :
                        gameEngine.MoveBall();
                        break;

                    case R:
                        gameEngine.destroyAll();
                        break;

                    case P:
                        Paddle newplayer = new Paddle(200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
                        gameEngine.setLives(10);
                        map = new Map(levelNumber);
                        System.out.println(levelNumber + " level typeshit");


                        gameEngine.setGame(anchorPane, map, newplayer);
                        map.loadMap(Define.SCREEN_WIDTH-150, Define.SCREEN_HEIGHT);

                        for (Bricks b : map.getBrickGroup()) {
                            b.setSence(scene);
                            anchorPane.getChildren().add(b.getNode());
                        }

                        newplayer.setScene(scene);
                        anchorPane.getChildren().add(newplayer.getNode());
                        loadBackgroundForLevel(levelNumber);
                        gameEngine.addBall();
                        gameEngine.setLives(10);
                        break;

                    case ESCAPE:
                        pause();
                        break;






                }
            });

            scene.setOnKeyReleased(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case A, D : gameEngine.notMove();


                }
            });

// create main loop
            mainLoop = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (gameEngine != null) {
                    gameEngine.update();
                    gameEngine.CheckAllCollision();

                    }

                }
            };
            mainLoop.start();

        } catch (Exception e) {
            System.err.println("❌ Error loading map for level " + level);
            e.printStackTrace();
        }
    }

    private void loadBackgroundForLevel(int level) {
        try {
            // Kiểm tra anchorPane đã được khởi tạo chưa
            if (anchorPane == null) {
                System.err.println("⚠️ AnchorPane not initialized yet, skipping background load");
                return;
            }

            // Sử dụng SpriteLoader để load background
            // Ban đầu dùng kích thước mặc định hoặc prefSize
            double initialWidth = anchorPane.getPrefWidth() > 0 ? anchorPane.getPrefWidth() : 600;
            double initialHeight = anchorPane.getPrefHeight() > 0 ? anchorPane.getPrefHeight() : 400;

            ImageView newBackground = SpriteLoader.getBackgroundForLevel(level, initialWidth, initialHeight);

            // Xóa background cũ nếu có
            if (backgroundView != null) {
                anchorPane.getChildren().remove(backgroundView);
            }

            // Bind kích thước background với AnchorPane
            newBackground.fitWidthProperty().bind(anchorPane.widthProperty());
            newBackground.fitHeightProperty().bind(anchorPane.heightProperty());

            // Set vị trí background ở góc trên bên trái
            AnchorPane.setTopAnchor(newBackground, 0.0);
            AnchorPane.setLeftAnchor(newBackground, (Double) 150.0);
            AnchorPane.setRightAnchor(newBackground, 0.0);
            AnchorPane.setBottomAnchor(newBackground, 0.0);

            // Thêm background mới ở vị trí đầu tiên (phía sau tất cả)
            backgroundView = newBackground;
            anchorPane.getChildren().add(0, backgroundView);

            System.out.println("✅ GameController: Background loaded and bound to AnchorPane for Level " + level);
            System.out.println("   - AnchorPane size: " + anchorPane.getWidth() + "x" + anchorPane.getHeight());
            System.out.println("   - AnchorPane prefSize: " + anchorPane.getPrefWidth() + "x" + anchorPane.getPrefHeight());

        } catch (Exception e) {
            System.err.println("❌ Error loading background for level " + level);
            e.printStackTrace();
        }
    }

    public int getLevel() {
        return levelNumber;
    }

    /**
     * Update score display
     */
    public void updateScore(int score) {
        if (scoreLabel != null) {
            scoreLabel.setText("SCORE: " + score);
        }
    }

    /**
     * Update lives display
     */
    public void updateLives(int lives) {
        if (livesLabel != null) {
            livesLabel.setText("LIVES: " + lives);
        }
    }

    /**
     * Get score label for direct access if needed
     */
    public Label getScoreLabel() {
        return scoreLabel;
    }

    /**
     * Get lives label for direct access if needed
     */
    public Label getLivesLabel() {
        return livesLabel;
    }

    @FXML
    public void pause() {
        // Tạo một stage mới cho pause
        mainLoop.stop();
        Stage pauseStage = new Stage();
        pauseStage.initStyle(javafx.stage.StageStyle.TRANSPARENT); // Phải set TRƯỚC initOwner
        pauseStage.initOwner(stage);
        pauseStage.initModality(Modality.WINDOW_MODAL);

        PauseScene pauseScene = new PauseScene(pauseStage);
        pauseScene.show();
    }


    @FXML
    public void restartLevel() {
        gameEngine.destroyAll();

        System.out.println("Testing runing ksgdfjkhabsdfuvsdjbfhgasfdghfnadtftghaysgedfbioahsbdfuiohaksduyfgboyyesgdf");
        Paddle newplayer = new Paddle(200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
        gameEngine.setLives(10);
        map = new Map(levelNumber);
        gameEngine.setGame(anchorPane, map, newplayer);
        map.loadMap(Define.SCREEN_WIDTH-150, Define.SCREEN_HEIGHT);

        for (Bricks b : map.getBrickGroup()) {
            b.setSence(scene);
            anchorPane.getChildren().add(b.getNode());
        }

        gameEngine.update();
        newplayer.setScene(scene);
        anchorPane.getChildren().add(newplayer.getNode());
        loadBackgroundForLevel(levelNumber);
        gameEngine.addBall();
        gameEngine.setLives(10);
    }

    public void closeLevel() {
        gameEngine.destroyAll();

    }
    /*
    @FXML

    public void endGame() {
        if (live == 0){
            stage.close();
            EndScene endScene = (EndScene)
        }
    }


     */





    public void destroyAll() {
        System.out.println("🧹 Destroying everything in GameController...");

        // 1️⃣ Stop animation loop
        if (mainLoop != null) {
            mainLoop.stop();
            mainLoop = null;
        }

        // 2️⃣ Stop all engines and threads
        if (gameEngine != null) {
            gameEngine.destroyAll(); // You created this earlier in GameEngine
            gameEngine.update();
            gameEngine.shutdown();
            gameEngine = null;

        }

        // 3️⃣ Remove all nodes from the pane
        if (anchorPane != null) {
            anchorPane.getChildren().clear();
        }

        // 4️⃣ Clear map reference
        if (map != null) {
            map = null;
        }

        // 5️⃣ Reset background (optional)
        backgroundView = null;

        System.gc(); // suggest GC cleanup
        System.out.println("✅ GameController: All objects destroyed and memory cleared.");
    }

}