package main.java.arkanoid.engine.PowerUp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import main.java.arkanoid.engine.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        int baseWidth = 800;
        int baseHeight = 600;

        Group root = new Group();
        Scene scene = new Scene(root, baseWidth, baseHeight);

        Paddle player = new Paddle(200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
        player.setScene(scene);
        root.getChildren().add(player.getNode());

        MultiBall powerUp = new MultiBall(300, 100);
        root.getChildren().add(powerUp.getSprite());

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                powerUp.update();
            }
        }.start();
        primaryStage.setTitle("JavaFX với FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}