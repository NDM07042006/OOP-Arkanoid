package main.java.arkanoid.engine.PowerUp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import main.java.arkanoid.engine.*;

public class Main extends Application {
    private GameEngine gameEngine = new GameEngine();
    private AnimationTimer game;
    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, Define.SCREEN_WIDTH, Define.SCREEN_HEIGHT);
        Map map = new Map(0);
        gameEngine.setGame(root,map);
        map.loadMap(Define.SCREEN_WIDTH, Define.SCREEN_HEIGHT);
        for (Bricks b : map.getBrickGroup()) {
            b.setSence(scene);
            root.getChildren().add(b.getNode());
        }

        Paddle player = gameEngine.getPaddle();
        player.setScene(scene);
        root.getChildren().add(player.getNode());

        // Thêm một số PowerUp để kiểm tra
        // SlowPaddle powerUp1 = new SlowPaddle(300, 100);
        MultiBall powerUp2 = new MultiBall(400, 150);
        // gameEngine.addPowerUp(powerUp1);
        gameEngine.addPowerUp(powerUp2);

        game = new AnimationTimer() {
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
        };
        game.start();
        primaryStage.setTitle("Arkanoid");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            try {
                stop(); // Gọi lại phương thức stop() để dừng AnimationTimer và shutdown GameEngine
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void stop() throws Exception {
        if (gameEngine != null) {
            gameEngine.shutdown(); // shutdown ExecutorService
        }
        if (game != null ) {
            game.stop();
        }
        Platform.exit();
        System.exit(0);
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}