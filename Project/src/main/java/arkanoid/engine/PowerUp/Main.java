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
        GameEngine gameEngine = new GameEngine();

        Paddle player = gameEngine.paddle;
        player.setScene(scene);
        root.getChildren().add(player.getNode());

        // Thêm một số PowerUp để kiểm tra
        MultiBall powerUp1 = new MultiBall(300, 100);
        MultiBall powerUp2 = new MultiBall(400, 150);
        gameEngine.addPowerUp(powerUp1);
        gameEngine.addPowerUp(powerUp2);
        for (PowerUp powerUp : gameEngine.powerUps){
            root.getChildren().add(powerUp.getSprite());
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (PowerUp powerUp : gameEngine.powerUps) {
                    powerUp.update();
                }
            }
        }.start();
        primaryStage.setTitle("Arkanoid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}