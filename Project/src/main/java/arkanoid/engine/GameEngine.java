package main.java.arkanoid.engine;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Iterator;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import main.java.arkanoid.engine.PowerUp.*;
import main.java.arkanoid.engine.CheckCollision.*;
import javafx.scene.layout.Pane;
import main.java.com.example.Arkanoid.Data.Lives;
import main.java.com.example.Arkanoid.Data.Score;
import main.java.com.example.Arkanoid.Utlis.Animations.ParticleSystem;
import javafx.scene.layout.AnchorPane;
import main.java.com.example.Arkanoid.Utlis.SoundManager;


//test phần engine sẽ sửa sau
public class GameEngine {
    private AnchorPane AnchorPane;
    private Paddle paddle;
    private List<Ball> balls = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private Map map;
    private Pane gamePane ;
    private ParticleSystem particleSystem;

    private Label scLabel;
    private Label liLabel;
    private Score score = new Score();
    private int lives;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Label getScLabel() {
        scLabel = new Label("SCORE: 0");
        scLabel.setLayoutX(10);
        scLabel.setLayoutY(10);
        return scLabel;
    }


    public Label getLiLabel() {
        liLabel = new Label("LIVES: 0" );
        liLabel.setLayoutX(10);
        liLabel.setLayoutY(50);
        return liLabel;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }



    public Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

    public ParticleSystem getParticleSystem() {
        return particleSystem;
    }

    public void setParticleSystem(ParticleSystem particleSystem) {
        this.particleSystem = particleSystem;
    }

    /*
     * Cài đặt luồng
     */
    private final ExecutorService collisionExecutor = Executors.newFixedThreadPool(3);// Cài đặt số luồng kiểm tra
    private List<CheckCollision> collisionTasks = new ArrayList<>();

    public GameEngine() {}
    /*
     * Getter setter game
     */
    public void setGame(AnchorPane AnchorPane, Map map, Paddle paddle) {
        Pane gamePane = new Pane();
        ParticleSystem particleSystem = new ParticleSystem(gamePane);
        this.AnchorPane = AnchorPane;
        this.map = map;
        this.paddle = paddle;
        PowerUp.setGameEngine(this);
        CheckCollision.setCheckCollision(this);
    }

    public void remove(ImageView node) {
        AnchorPane.getChildren().remove(node);
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
     * Xử lý bóng
     */
    /*
     * Tạo vector vận tốc
     * Dữ liệu đầu vào là góc của vector vận tốc hợp với phương thẳng đứng
     */
    public double setVelBall_X(double degrees){
        double vel_X = Define.DEFAULF_BALL_VECTOR_SPEED*Math.sin(Math.toRadians(degrees));
        return vel_X;
    }
    public double setVelBall_y(double degrees){
        double vel_Y = -1*Define.DEFAULF_BALL_VECTOR_SPEED*Math.cos(Math.toRadians(degrees));
        return vel_Y;
    }

    // Tạo bóng mặc định không di chuyển

    public void addBall() {
        Bounds paddleBounds = paddle.getSprite().getBoundsInParent();
        double start_x = paddleBounds.getCenterX();
        double start_y = paddleBounds.getMinY()-21;
        Ball ball = new Ball(start_x, start_y,
                Define.PADDLES_AND_BALLS_IMAGE_PATH);
        ball.setVel_X(setVelBall_X( 0));
        ball.setVel_Y(setVelBall_y( 0));
        balls.add(ball);
        AnchorPane.getChildren().add(ball.getNode());
        ball.setAttached(true);
        ball.setSpeed(0);
    }

    // Thêm bóng tự động di chuyển có hướng

    public void addBall(double degrees) {
        Bounds paddleBounds = paddle.getSprite().getBoundsInParent();
        double start_x = paddleBounds.getCenterX();
        double start_y = paddleBounds.getMinY()-30;
        Ball ball = new Ball(start_x, start_y,
                Define.PADDLES_AND_BALLS_IMAGE_PATH);
        ball.setMoving(true);
        ball.setSpeed(Define.DEFAULF_BALL_SPEED);
        ball.setVel_X(setVelBall_X( degrees));
        ball.setVel_Y(setVelBall_y( degrees));
        balls.add(ball);
        AnchorPane.getChildren().add(ball.getNode());
    }

    public void MoveBall() {
        for (Ball ball: balls) {
            if (!ball.isMoving() && ball.isAttached() ) { // 🔹 chỉ kích hoạt lần đầu
                ball.setMoving(true);
                ball.setSpeed(Define.DEFAULF_BALL_SPEED);
                ball.setVel_Y(setVelBall_y(0));
                ball.setVel_X(setVelBall_X(0));
                System.out.println("moving");
                ball.setAttached(false);
            }
            break;
        }
    }

    //Điều chỉnh speed bóng
    public void setAllBallSpeed(int extraSpeed){
        for(Ball ball:balls){
            if(ball.getSpeed() + extraSpeed >= Define.MIN_BALL_SPEED
                    && ball.getSpeed() + extraSpeed <= Define.MAX_BALL_SPEED){
                ball.setSpeed(ball.getSpeed() + extraSpeed);
            }
        }
    }
    /*
     * Thêm power up
     */
    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
        AnchorPane.getChildren().add(powerUp.getSprite());
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

        Iterator<Ball> iteratorBall = balls.iterator();
        while (iteratorBall.hasNext()) {
            Ball b = iteratorBall.next();
            if (b.isDestroyed()) {
                iteratorBall.remove(); // safely removes current element
                b = null;
            }
        }

        for (Bricks brick: map.getBrickGroup()) {
            brick.update();
            if (brick.isDestroyed()) {
                /*
                score.setScore(score.getScore() + brick.Point_given);
                scLabel.setText("SCORE: " + score.getScore());

                 */
                SoundManager.getInstance().playBrickBreak();
                switch (brick.getPowerUp_Type()) {
                    case 0:
                        break;
                    case 1:
                        MultiBall multiBall = new MultiBall(brick.pos_X, brick.pos_Y);
                        addPowerUp(multiBall);
                        break;
                    case 2:
                        FastBall fastBall= new FastBall(brick.pos_X, brick.pos_Y);
                        addPowerUp(fastBall);
                        break;
                    case 3:
                        SlowBall slowBall= new SlowBall(brick.pos_X, brick.pos_Y);
                        addPowerUp(slowBall);
                        break;
                    case 4:
                        FastPaddle fastPaddle= new FastPaddle(brick.pos_X, brick.pos_Y);
                        addPowerUp(fastPaddle);
                        break;
                    case 5:
                        SlowPaddle slowPaddle= new SlowPaddle(brick.pos_X, brick.pos_Y);
                        addPowerUp(slowPaddle);
                        break;
                    case 6:
                        RandomPowerUp randomPowerUp = new RandomPowerUp(brick.pos_X, brick.pos_Y);
                        addPowerUp(randomPowerUp);
                        break;
                    default:
                        break;
                }

                AnchorPane.getChildren().remove(brick.getNode());

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
        if (paddle != null) {
            paddle.update();
            if (paddle.isDestroyed()) {
                AnchorPane.getChildren().remove(paddle.getNode());
                paddle = null;
            }
        }

        if (balls.size() <= 0  && lives > 0) {
            lives --;
            addBall();
        }

    }

    /*
     * Di chuyển paddle
     */
    public void moveLeft(){
        if (paddle != null) {
        paddle.setVel_X(-2);
        for (Ball b: balls) {
              if (b.isAttached()) {
                  b.setSpeed(paddle.getSpeed());
                  b.setVel_Y(0);
                  b.setVel_X(-2);
                }
            }
        }
    }
    public void moveRight() {
        if (paddle != null) {
            paddle.setVel_X(2);
            for (Ball b : balls) {
                if (b.isAttached()) {
                    b.setSpeed(paddle.getSpeed());
                    b.setVel_X(paddle.getVel_X());
                    b.setVel_Y(0);
                }
            }

        }
    }
    public void notMove(){
        if (paddle != null) {
        paddle.setVel_X(0);
        for (Ball b: balls) {
            if (b.isAttached()) {
                b.setSpeed(paddle.getSpeed());
                b.setVel_X(0);
                b.setVel_Y(0);

            }
        }
    }
    }

    public void setPaddleSpeed(int extraSpeed){
        if (paddle.getSpeed() + extraSpeed >= Define.MIN_PADDLE_SPEED
                && paddle.getSpeed() + extraSpeed <= Define.MAX_PADDLE_SPEED){
            paddle.setSpeed(paddle.getSpeed() + extraSpeed);
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

    public void destroyAll() {
        try {
            System.out.println("🧹 Destroying all game objects...");

            // 1️⃣ Ngừng mọi hoạt động animation / particle
            if (particleSystem != null) {
                particleSystem.clear();
                particleSystem = null;
            }
            /*
            // 2️⃣ Tắt luồng kiểm tra va chạm
            if (collisionExecutor != null && !collisionExecutor.isShutdown()) {
                collisionExecutor.shutdownNow();
            }\
             */

            if (collisionTasks != null) {
                collisionTasks.clear();
            }

            // 3️⃣ Xóa toàn bộ node khỏi AnchorPane
            if (AnchorPane != null) {
                AnchorPane.getChildren().clear();
            }

            // 4️⃣ Xóa danh sách object
            if (balls != null) {
                for (Ball b : balls) {
                    b.setDestroyed(true); // Nếu Ball có hàm destroy() để cleanup riêng
                }
                balls.clear();
            }

            if (powerUps != null) {
                for (PowerUp p : powerUps) {
                    p.setDestroyed(true); // Nếu PowerUp có hàm destroy() riêng
                }
                powerUps.clear();
            }

            if (map != null && map.getBrickGroup() != null) {
                for (Bricks brick : map.getBrickGroup()) {
                    brick.setDestroyed(true); // Nếu có
                }
                map.getBrickGroup().clear();
            }

            // 5️⃣ Dọn player paddle
            if (paddle != null && paddle.getNode() != null) {
                paddle.setDestroyed(true); // nếu có destroy()
            }
            paddle = null;

            // 6️⃣ Dọn Map, GamePane, v.v.
            /*
            map = null;
            gamePane = null;
            AnchorPane = null;

             */



            System.out.println("✅ All game objects destroyed successfully.");

        } catch (Exception e) {
            System.err.println("❌ Error while destroying game objects:");
            e.printStackTrace();
        }
    }


}