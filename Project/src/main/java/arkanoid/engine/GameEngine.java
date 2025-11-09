package main.java.arkanoid.engine;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import main.java.arkanoid.engine.PowerUp.*;
import main.java.arkanoid.engine.CheckCollision.*;

//test phần engine sẽ sửa sau
public class GameEngine {
    private Group root;
    private Paddle paddle = new Paddle(200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
    private List<Ball> balls = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private Map map;
    private Bounds paddleBounds = paddle.getSprite().getBoundsInParent();


    /*
     * Cài đặt luồng
     */
    private final ExecutorService collisionExecutor = Executors.newFixedThreadPool(3);// Cài đặt số luồng kiểm tra
    private List<CheckCollision> collisionTasks = new ArrayList<>();

    public GameEngine() {}
    /*
     * Getter setter gamed
     */
    public void setGame(Group root, Map map, int baseWidth, int baseHeight ) {
        this.root = root;
        this.map = map;
        map.loadMap(baseWidth, baseHeight);

        PowerUp.setGameEngine(this);
        CheckCollision.setCheckCollision(this);
    }

    public void remove(ImageView node) {
        root.getChildren().remove(node);
    }

    public void setCollisionTasks() {
        collisionTasks.add(new CheckBallAndBrick());
        collisionTasks.add(new CheckBallAndPaddle());
        collisionTasks.add(new CheckPowerUpAndPaddle());
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public Map getMap() {
        return map;
    }
    /*
     * Thêm ball
     */
    public void addBall() {
        double start_x = (paddleBounds.getMaxX()-paddleBounds.getMinX())/2
                + paddleBounds.getMinX();
        double start_y = paddleBounds.getMinY()-30;
        Ball ball = new Ball(start_x, start_y,
                Define.PADDLES_AND_BALLS_IMAGE_PATH);
        balls.add(ball);
        root.getChildren().add(ball.getNode());
    }

    public void addBall(int velX) {
        double start_x = (paddleBounds.getMaxX()-paddleBounds.getMinX())/2
                + paddleBounds.getMinX();
        double start_y = paddleBounds.getMinY()-30;
        Ball ball = new Ball(start_x, start_y,
                Define.PADDLES_AND_BALLS_IMAGE_PATH);
        ball.setMoving(true);
        ball.setSpeed(1);
        ball.setVel_X(velX);
        ball.setVel_Y(-3);
        balls.add(ball);
        root.getChildren().add(ball.getNode());
    }
    /*
     * Thêm power up
     */
    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
        root.getChildren().add(powerUp.getSprite());
    }

    /*
     * Cập nhật trạng thái của tất cả đối tượng
     */
    public void update() {
        for (PowerUp powerUp : powerUps) {
            powerUp.update();
        }
        for (Ball ball : balls) {
            ball.update();
        }

        for (Bricks brick: map.getBrickGroup()) {
            brick.update();
            if (brick.isDestroyed()) {
                root.getChildren().remove(brick.getNode());
            }

        }

        paddle.update();


    }

    /*
     * Luồng kiểm tra va chạm
     */
    public void CheckAllCollision() {
        for (CheckCollision task : collisionTasks) {
            collisionExecutor.submit(task);
        }
    }

    /*
     * Tắt luồng kiểm tra khi thoát game
     */
    public void shutdown() {
        collisionExecutor.shutdown();
    }



}