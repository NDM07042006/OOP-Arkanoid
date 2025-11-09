package main.java.arkanoid.engine.PowerUp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import main.java.arkanoid.engine.*;

public class Main extends Application {
    private GameEngine gameEngine = new GameEngine();
    @Override
    public void start(Stage primaryStage) throws Exception {

        int baseWidth = 800;
        int baseHeight = 600;

        Group root = new Group();
        Scene scene = new Scene(root, baseWidth, baseHeight);
        Map map = new Map(1);
        gameEngine.setGame(root,map, baseWidth, baseHeight);
        //map.loadMap(baseHeight, baseHeight);
        for (Bricks b : map.getBrickGroup()) {
            b.setSence(scene);
            root.getChildren().add(b.getNode());
        }

        Paddle player = gameEngine.getPaddle();
        player.setScene(scene);
        root.getChildren().add(player.getNode());

        // Thêm một số PowerUp để kiểm tra
        MultiBall powerUp1 = new MultiBall(300, 100);
        MultiBall powerUp2 = new MultiBall(400, 150);
        gameEngine.addPowerUp(powerUp1);
        gameEngine.addPowerUp(powerUp2);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case A : player.setVel_X(-1);;
                                System.out.println("moving");
                                break;
                            case D : player.setVel_X(1);;
                                System.out.println("moving");
                                break;
                                /*
                            case SPACE:
                                for (Ball ball: ballsGroup) {
                                    if (!ball.isMoving() && ball.isMainBall()) { // 🔹 chỉ kích hoạt lần đầu
                                        ball.setMoving(true);
                                        ball.setSpeed(6);
                                        ball.setVel_Y(-1);
                                        ball.setVel_X(-1);
                                        System.out.println("moving");
                                    }
                                    break;
                                }

                                 */

                        }

                    }

                });

                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case A, D:
                                player.setVel_X(0);

                                break;
                        }
                    }
                });


                gameEngine.update();
                gameEngine.CheckAllCollision();

                System.out.println(gameEngine.getMap().getBrickGroup().size());


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