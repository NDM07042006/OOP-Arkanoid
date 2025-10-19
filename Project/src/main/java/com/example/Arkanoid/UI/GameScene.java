package main.java.com.example.Arkanoid.UI;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.arkanoid.engine.Paddle;

public class GameScene {
    private Stage stage;

    private Label scLabel;
    private Label lvLabel;
    private Label liLabel;
    private Button pause;

    public Label getScLabel() {
        scLabel = new Label("SCORE: 0");
        return scLabel;
    }

    public Label getLvLabel() {
        lvLabel = new Label("LEVEL: 0");
        return lvLabel;
    }

    public Label getLiLabel() {
        liLabel = new Label("LIVES: 0");
        return liLabel;
    }

    public Button getPause() {
        pause = new Button("PAUSE");
        return pause;
    }

    public GameScene(Stage stage) {
        System.out.println("GameScene constructor with stage: " + stage);
        this.stage = stage;
    }

    private Stage newStage;

    public void show() {
        Paddle paddle = new Paddle(200, 300, "/com/Arkanoid/images/Screenshot from 2025-09-24 00-47-22.png");
        Label l1 = getLiLabel();
        Label l2 = getLvLabel();
        Label l3 = getScLabel();
        Button b1 = getPause();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                paddle.update();
                paddle.draw();
            }
        };

        b1.setOnAction(e -> {
            if (newStage != null && newStage.isShowing()) {
                newStage.toFront();
                return;
            }

            gameLoop.stop();

            newStage = new Stage();
            newStage.initOwner(stage);
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.setResizable(false);

            newStage.setOnHidden(ev -> {
                if (stage.getScene() != null) {
                    gameLoop.start();
                }
            });

            PauseScene pauseScene = new PauseScene(newStage);
            pauseScene.show();
        });

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(l1, l2, l3, paddle.getNode(), b1);
        AnchorPane.setTopAnchor(l1, 20.0);
        AnchorPane.setLeftAnchor(l2, 100.0);
        AnchorPane.setTopAnchor(b1, 350.0);
        AnchorPane.setLeftAnchor(b1, 250.0);

        Scene scene = new Scene(root, 600, 400);
        paddle.setSence(scene);
        paddle.movingSideway();

        gameLoop.start();
        stage.setTitle("Game");
        stage.setScene(scene);
        System.out.println("GameScene show() using stage: " + stage);
        stage.show();

    }
}