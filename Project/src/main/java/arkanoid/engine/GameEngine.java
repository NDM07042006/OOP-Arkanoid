package main.java.arkanoid.engine;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.geometry.Bounds;
import javafx.scene.Group;
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

    /*
     * Cài đặt luồng
     */
    private final ExecutorService collisionExecutor = Executors.newFixedThreadPool(3);// Cài đặt số luồng kiểm tra
    private List<CheckCollision> collisionTasks = new ArrayList<>();

    public GameEngine() {
    }

    /*
     * Getter setter game
     */
    public void setGame(Group root, Map map) {
        this.root = root;
        this.map = map;
        PowerUp.setGameEngine(this);
        CheckCollision.setCheckCollision(this);
    }

    public void remove(ImageView node) {
        root.getChildren().remove(node);
    }

    /*
     * Thêm luồng
     */
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
    /*
     * Tạo vector vận tốc
     * Dữ liệu đầu vào là góc tính theo phương thẳng đứng
     */
    public double setVelBall_X(double degrees) {
        double vel_X = Define.DEFAULF_BALL_SPEED * Math.sin(Math.toRadians(degrees));
        return vel_X;
    }

    public double setVelBall_y(double degrees) {
        double vel_Y = -1 * Define.DEFAULF_BALL_SPEED * Math.cos(Math.toRadians(degrees));
        return vel_Y;
    }

    /*
     * Tạo ball mặc định không di chuyển
     */
    public void addBall() {
        Bounds paddleBounds = paddle.getSprite().getBoundsInParent();
        double start_x = (paddleBounds.getMaxX() - paddleBounds.getMinX()) / 2
                + paddleBounds.getMinX();
        double start_y = paddleBounds.getMinY() - 30;
        Ball ball = new Ball(start_x, start_y,
                Define.PADDLES_AND_BALLS_IMAGE_PATH);
        ball.setSpeed(10);
        ball.setVel_X(setVelBall_X(0));
        ball.setVel_Y(setVelBall_y(0));
        balls.add(ball);
        root.getChildren().add(ball.getNode());
    }

    /*
     * Thêm ball tự động di chuyển có hướng
     */
    public void addBall(double degrees) {
        Bounds paddleBounds = paddle.getSprite().getBoundsInParent();
        double start_x = (paddleBounds.getMaxX() - paddleBounds.getMinX()) / 2
                + paddleBounds.getMinX();
        double start_y = paddleBounds.getMinY() - 30;
        Ball ball = new Ball(start_x, start_y,
                Define.PADDLES_AND_BALLS_IMAGE_PATH);
        ball.setMoving(true);
        ball.setSpeed(10);
        ball.setVel_X(setVelBall_X(degrees));
        ball.setVel_Y(setVelBall_y(degrees));
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

        for (Bricks brick : map.getBrickGroup()) {
            brick.update();
            if (brick.isDestroyed()) {
                switch (brick.getPowerUp_Type()) {
                    case 1:
                        MultiBall powerUp = new MultiBall(brick.pos_X, brick.pos_Y);
                        addPowerUp(powerUp);

                }
                root.getChildren().remove(brick.getNode());

            }
        }
        Iterator<Bricks> iterator = Map.brickGroup.iterator();
        while (iterator.hasNext()) {
            Bricks b = iterator.next();
            if (b.isDestroyed()) {
                iterator.remove(); // safely removes current element
                b = null;
            }
        }
        paddle.update();
    }

    /*
     * Di chuyển paddle
     */
    public void moveLeft() {
        paddle.setVel_X(-2);
        for (Ball b : balls) {
            if (!b.isMoving() && b.isAttached()) {
                b.setVel_X(-2);
            }
        }
    }

    public void moveRight() {
        paddle.setVel_X(2);
        for (Ball b : balls) {
            if (!b.isMoving() && b.isAttached()) {
                b.setVel_X(2);

            }
        }
    }

    public void notMove() {
        paddle.setVel_X(0);
        for (Ball b : balls) {
            if (!b.isMoving() && b.isAttached()) {
                b.setVel_X(0);
            }
        }

    }

    public void MoveBall() {
        for (Ball ball : balls) {
            if (!ball.isMoving() && ball.isAttached()) { // 🔹 chỉ kích hoạt lần đầu
                ball.setMoving(true);
                ball.setSpeed(5);
                ball.setVel_Y(-1);
                ball.setVel_X(0);
                System.out.println("moving");
            }
            break;
        }
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

    // Điều chỉnh speed bóng
    public void setAllBallSpeed(int extraSpeed) {
        for (Ball ball : balls) {
            if (ball.getSpeed() + extraSpeed >= Define.MIN_BALL_SPEED
                    && ball.getSpeed() + extraSpeed <= Define.MAX_BALL_SPEED) {
                ball.setSpeed(ball.getSpeed() + extraSpeed);
            }
        }
    }

    public void setPaddleSpeed(int extraSpeed) {
        if (paddle.getSpeed() + extraSpeed >= Define.MIN_PADDLE_SPEED
                && paddle.getSpeed() + extraSpeed <= Define.MAX_PADDLE_SPEED) {
            paddle.setSpeed(paddle.getSpeed() + extraSpeed);
        }
    }

}