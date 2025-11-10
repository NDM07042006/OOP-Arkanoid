package main.java.arkanoid.engine;

import java.util.List;
import java.util.ArrayList;
import main.java.arkanoid.engine.PowerUp.*;
//test phần engine sẽ sửa sau
public class GameEngine {
    public Paddle paddle = new Paddle( 200, 500, Define.PADDLES_AND_BALLS_IMAGE_PATH);
    public List<Ball> balls = new ArrayList<>();
    public List<PowerUp> powerUps = new ArrayList<>();
    public Bricks bricks = new Bricks(0,0,Define.BRICKS_IMAGE_PATH,
                                112,0,800,600,
                                10,10,2);
    public void addBall() {
        Ball ball = new Ball(200, 500,
                Define.PADDLES_AND_BALLS_IMAGE_PATH);
        balls.add(ball);
    }

    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }

    
}
