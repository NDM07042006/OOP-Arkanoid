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

        // Tạo scene từ FXML

        int baseWidth = 800;
        int baseHeight = 600;

        Group root = new Group();
        Scene scene = new Scene(root, baseWidth, baseHeight);

        Paddle player = new Paddle(200, 500, "/com/Arkanoid/img/paddles_and_balls.png");
        player.setScene(scene);
        root.getChildren().add(player.getNode());

        MultiBall powerUp = new MultiBall(300, 100);
        root.getChildren().add(powerUp.getSprite());

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                powerUp.update(); // gọi hàm update() để di chuyển
            }
        }.start();

        // Cấu hình và hiển thị cửa sổ
        primaryStage.setTitle("JavaFX với FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
