package main.java.arkanoid.engine;

import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
public class Main extends Application {
    public static ArrayList<Ball> ballsGroup = new ArrayList<>();



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        Parent parent = loader.load();
        ArrayList<Bricks> ListofBricks = new ArrayList<>();


        int baseWidth = 800;
        int baseHeight = 600;

        Group root = new Group();
        Scene scene = new Scene(root, baseWidth, baseHeight);

        /*

// Giữ tỉ lệ chuẩn, scale toàn bộ root khi đổi kích thước
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            double scale = Math.min(newVal.doubleValue() / baseWidth, scene.getHeight() / baseHeight);
            root.setScaleX(scale);
            root.setScaleY(scale);

            // Căn giữa để không bị dồn góc trái
            root.setLayoutX((scene.getWidth() - baseWidth * scale) / 2);
            root.setLayoutY((scene.getHeight() - baseHeight * scale) / 2);
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            double scale = Math.min(scene.getWidth() / baseWidth, newVal.doubleValue() / baseHeight);
            root.setScaleX(scale);
            root.setScaleY(scale);

            // Căn giữa dọc
            root.setLayoutX((scene.getWidth() - baseWidth * scale) / 2);
            root.setLayoutY((scene.getHeight() - baseHeight * scale) / 2);
        });

        */



        Map map = new Map(1);

        map.loadMap(baseWidth, baseHeight);
        for (Bricks b : Map.brickGroup) {
            b.setSence(scene);
            root.getChildren().add(b.getNode());
        }



        Paddle player = new Paddle(200, 500, "main/resources/com/Arkanoid/paddles_and_balls.png");
        player.setScene(scene);
        root.getChildren().add(player.getNode());


        Ball ball = new Ball(200, 500, "main/resources/com/Arkanoid/paddles_and_balls.png");
        ball.setSence(scene);
        root.getChildren().add(ball.getNode());
        ball.setMainBall(true);
        ballsGroup.add(ball);


        new AnimationTimer() {
            @Override
            public void handle(long now) {

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case A : player.setVel_X(-1);;
                                break;
                            case D : player.setVel_X(1);;
                                break;
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
                for (Ball b: ballsGroup) {
                    if (ball.getNode().getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                        // Handle bounced
                        ball.vel_Y *= -1;
                    }
                    ball.update();

                }
                
                for (Bricks brick : Map.brickGroup) {
                    for (Ball ball : ballsGroup) {
                        brick.update();
                        if (ball.getNode().getBoundsInParent().intersects(brick.getNode().getBoundsInParent())) {
                            ball.vel_Y *= -1;
                            brick.currrentPoints -= 1;
                        }
                        if (brick.isDestroyed()) {
                            root.getChildren().remove(brick.getNode());
                        }
                    }
                }

                Iterator<Bricks> iterator = Map.brickGroup.iterator();

                while (iterator.hasNext()) {
                    Bricks brick = iterator.next();
                    if (brick.isDestroyed()) {
                        iterator.remove(); // safely removes current element
                        brick = null;
                    }
                }



                player.update();
                System.out.println(Map.brickGroup.size());

            }
        }.start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Paddle Test");
        primaryStage.show();
        root.requestFocus();




    }

    public static void main(String[] args) {
        launch(args);
    }
}