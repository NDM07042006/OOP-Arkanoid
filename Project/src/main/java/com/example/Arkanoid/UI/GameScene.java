package main.java.com.example.Arkanoid.UI;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import main.java.arkanoid.engine.Define;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import main.java.arkanoid.engine.Ball;
import main.java.arkanoid.engine.Bricks;
import main.java.arkanoid.engine.Paddle;
import main.java.arkanoid.engine.Map;
import main.java.com.example.Arkanoid.Data.*;

public class GameScene {
    private Stage stage;

    private Label scLabel;
    private Label lvLabel;
    private Label liLabel;
    private Button pause;
    private Score score;
    private Lives lives;

    public Label getScLabel() {
        scLabel = new Label("SCORE: 0");
        scLabel.setLayoutX(10);
        scLabel.setLayoutY(10);
        return scLabel;
    }

    public Label getLvLabel() {
        lvLabel = new Label("LEVEL: 0");
        lvLabel.setLayoutX(10);
        lvLabel.setLayoutY(30);
        return lvLabel;
    }

    public Label getLiLabel() {
        liLabel = new Label("LIVES: 0" );
        liLabel.setLayoutX(10);
        liLabel.setLayoutY(50);
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

    public void show() {
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/Sample.fxml"));
        // Parent parent = loader.load();
        // try {
        //     FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/Sample.fxml"));
        //     Parent parent = loader.load();
        // } catch (Exception e) {
        //     // TODO: handle exception
        //     e.printStackTrace();
        // }
        // ArrayList<Bricks> ListofBricks = new ArrayList<>();

        // Khởi tạo Score
        score = new Score();
        lives = new Lives();

        int baseWidth = 800;
        int baseHeight = 600;

        Group root = new Group();
        Scene scene = new Scene(root, baseWidth, baseHeight);

        

// Giữ tỉ lệ chuẩn, scale toàn bộ root khi đổi kích thước
        // scene.widthProperty().addListener((obs, oldVal, newVal) -> {
        //     double scale = Math.min(newVal.doubleValue() / baseWidth, scene.getHeight() / baseHeight);
        //     root.setScaleX(scale);
        //     root.setScaleY(scale);

        //     // Căn giữa để không bị dồn góc trái
        //     root.setLayoutX((scene.getWidth() - baseWidth * scale) / 2);
        //     root.setLayoutY((scene.getHeight() - baseHeight * scale) / 2);
        // });

        // scene.heightProperty().addListener((obs, oldVal, newVal) -> {
        //     double scale = Math.min(scene.getWidth() / baseWidth, newVal.doubleValue() / baseHeight);
        //     root.setScaleX(scale);
        //     root.setScaleY(scale);

        //     // Căn giữa dọc
        //     root.setLayoutX((scene.getWidth() - baseWidth * scale) / 2);
        //     root.setLayoutY((scene.getHeight() - baseHeight * scale) / 2);
        // });





        Map map = new Map(0);
        map.loadMap(baseHeight, baseHeight);
        
        // Dịch toàn bộ map xuống dưới thêm 50 pixels
        int offsetY = 70; // Thay đổi giá trị này để dịch map lên/xuống
        for (Bricks b : Map.brickGroup) {
            b.setSence(scene);
            b.setPos_Y(b.getPos_Y() + offsetY); // Dịch brick xuống với cast
            root.getChildren().add(b.getNode());
        }



        Paddle player = new Paddle(200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
        player.setScene(scene);
        root.getChildren().add(player.getNode());


        Ball ball = new Ball(200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
        ball.setSence(scene);
        root.getChildren().add(ball.getNode());

        // Khai báo AnimationTimer trước để có thể dừng/khởi động từ pause button
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case A : player.setVel_X(-1);
                                System.out.println("moving");
                                break;
                            case D : player.setVel_X(1);
                                System.out.println("moving");
                                break;
                            case SPACE:
                                if (!ball.isMoving()) { // 🔹 chỉ kích hoạt lần đầu
                                    ball.setMoving(true);
                                    ball.setSpeed(6);
                                    ball.setVel_Y(-1);
                                    ball.setVel_X(-1);
                                    System.out.println("moving");
                                }
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
                                player.setVel_X(0);

                                break;
                            default:
                                break;
                        }
                    }
                });

                if (ball.getNode().getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                    // Handle bounced
                    ball.setVel_Y(ball.getVel_Y() * -1);
                    // ball.vel_Y *= -1;
                }




                // Sử dụng Iterator để xóa brick an toàn
                Iterator<Bricks> iterator = Map.brickGroup.iterator();
                while (iterator.hasNext()) {
                    Bricks b = iterator.next();
                    b.update();
                    if (ball.getNode().getBoundsInParent().intersects(b.getNode().getBoundsInParent())) {
                        ball.setVel_Y(ball.getVel_Y() * -1);
                        // ball.vel_Y *= -1;
                        b.setCurrrentPoints(b.getCurrrentPoints() - 1);
                        // b.currrentPoints -= 1;
                    }
                    if (b.isDestroyed()) {
                        // Cập nhật score khi brick bị phá hủy
                        int points = b.getPoint_given();
                        score.setScore(score.getScore() + points);
                        scLabel.setText("SCORE: " + score.getScore());

                        root.getChildren().remove(b.getNode());
                        iterator.remove(); // Xóa khỏi list để không bị cộng lại
                    }
                }

                ball.update();

                
                
                // Kiểm tra nếu bóng rơi xuống dưới (game over hoặc reset)
                if (ball.getPos_Y() > baseHeight) {
                    lives.updateLives();
                    liLabel.setText("LIVES: " + lives.getLives());
                    // Reset bóng về vị trí paddle
                    ball.setMoving(false);
                    ball.setSpeed(0);
                    ball.setVel_X(0);
                    ball.setVel_Y(0);
                    // Đặt lại vị trí bóng
                    ball.setPos_X(player.getPos_X() + 45);
                    ball.setPos_Y(480);
                    System.out.println("Ball fell! Press SPACE to restart.");
                }

                player.update();

                
            }
        };

        Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(350);
        pauseButton.setLayoutY(550);
        pauseButton.setFocusTraversable(false); // ✅ Tắt focus vào button
        pauseButton.setOnAction(e -> {
            // Dừng game timer khi pause
            gameTimer.stop();
            
            // Tạo một stage mới mỗi lần pause
            Stage pauseStage = new Stage();
            pauseStage.initStyle(javafx.stage.StageStyle.TRANSPARENT); // ✅ Thêm dòng này
            pauseStage.initOwner(stage);
            pauseStage.initModality(Modality.WINDOW_MODAL);
            
            PauseScene pauseScene = new PauseScene(pauseStage);
            pauseScene.show();
            
            // Khi pause stage đóng, khởi động lại game timer và trả focus về game
            pauseStage.setOnHidden(event -> {
                gameTimer.start();
                stage.requestFocus();
                scene.getRoot().requestFocus();
            });
        });

        root.getChildren().add(pauseButton);
        root.getChildren().add(getLiLabel());
        root.getChildren().add(getLvLabel());
        root.getChildren().add(getScLabel());
        
        gameTimer.start();

        stage.setScene(scene);
        stage.setTitle("Paddle Test");
        stage.setResizable(false);
        stage.show();
        root.requestFocus();

    }
}