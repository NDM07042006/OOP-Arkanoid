package main.java.arkanoid.engine.PowerUp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import main.java.arkanoid.engine.*;
import main.java.com.example.Arkanoid.Utlis.Animations.PaddleGlowAnimation;
import main.java.com.example.Arkanoid.Utlis.SoundManager;
import java.util.ArrayList;

public class Main extends Application {
    private GameEngine gameEngine = new GameEngine();
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameEngine gameEngine = new GameEngine();

        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, Define.SCREEN_WIDTH, Define.SCREEN_HEIGHT);
        Map map = new Map(10);
        gameEngine.setGame(anchorPane,map);
        map.loadMap(Define.SCREEN_WIDTH, Define.SCREEN_HEIGHT);


        for (Bricks b : map.getBrickGroup()) {
            b.setSence(scene);
            anchorPane.getChildren().add(b.getNode());
        }

        Paddle player = gameEngine.getPaddle();
        player.setScene(scene);
        anchorPane.getChildren().add(player.getNode());




        // Thêm một số PowerUp để kiểm tra
        //MultiBall powerUp1 = new MultiBall(300, 100);
        //MultiBall powerUp2 = new MultiBall(400, 150);
        //gameEngine.addPowerUp(powerUp1);
        //gameEngine.addPowerUp(powerUp2);
        gameEngine.addBall();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case A : gameEngine.moveLeft();
                                break;
                            case D : gameEngine.moveRight();
                                break;
                            default:
                                break;

                            case SPACE:
                                gameEngine.MoveBall();
                        }

                    }

                });

                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case A, D:
                                gameEngine.notMove();
                                break;
                            default:
                                break;
                        }
                    }
                });

                gameEngine.update();
                gameEngine.CheckAllCollision();
            }
        }.start();
        primaryStage.setTitle("Arkanoid");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        if (gameEngine != null) {
            gameEngine.shutdown(); // shutdown ExecutorService
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}