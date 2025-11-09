package main.java.arkanoid.engine.CheckCollision;

import java.util.Iterator;
import java.util.List;

import main.java.arkanoid.engine.Ball;
import main.java.arkanoid.engine.Paddle;

public class CheckBallAndPaddle extends CheckCollision {
    private List<Ball> balls = gameEngine.getBalls();
    private Paddle paddle = gameEngine.getPaddle();

    @Override
    protected void check() {
        Iterator<Ball> iterator = balls.iterator();

        while (iterator.hasNext()) {
            Ball ball = iterator.next();
            if (ball.getNode().getBoundsInParent().intersects(paddle.getNode().getBoundsInParent())) {
                ball.setVel_Y(-ball.getVel_Y());
            }
        }
    }


    @Override
    public void run() {
        check();
    }

}