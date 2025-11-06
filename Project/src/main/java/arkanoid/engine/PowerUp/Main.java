package main.java.arkanoid.engine.PowerUp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
        Map map = new Map(0);
        gameEngine.setGame(root,map);
        map.loadMap(baseHeight, baseHeight);
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