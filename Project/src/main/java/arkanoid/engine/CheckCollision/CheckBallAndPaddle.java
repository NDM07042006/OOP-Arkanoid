package main.java.arkanoid.engine.CheckCollision;

import java.util.Iterator;
import java.util.List;

import javafx.geometry.Bounds;
import main.java.arkanoid.engine.Ball;
import main.java.arkanoid.engine.Paddle;
import main.java.com.example.Arkanoid.Utlis.Animations.PaddleGlowAnimation;

public class CheckBallAndPaddle extends CheckCollision {
    private List<Ball> balls = gameEngine.getBalls();
    private Paddle paddle = gameEngine.getPaddle();
    private PaddleGlowAnimation paddleGlow = new PaddleGlowAnimation();

    @Override
    protected void check() {
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();
            if (ball.getNode().getBoundsInParent().intersects(paddle.getNode().getBoundsInParent())) {
                if(!ball.isColliding()){
                    Bounds paddleBounds = paddle.getSprite().getBoundsInParent();
                    double paddleLength = paddleBounds.getMaxX() - paddleBounds.getMinX();
                    double paddleMidPoint = paddleLength/2 +paddleBounds.getMinX();
                    ball.setCollision(true);
                    Bounds ballBounds = ball.getSprite().getBoundsInParent();
                    double ballMidPoint = (ballBounds.getMaxX() - ballBounds.getMinX())/2 +ballBounds.getMinX();
                    double degrees = (ballMidPoint - paddleMidPoint)/(paddleLength/2)*75;
                    ball.setVel_X(gameEngine.setVelBall_X(degrees));
                    ball.setVel_Y(gameEngine.setVelBall_y(degrees));

                    paddleGlow.play(paddle.getNode());

                }
                else ball.setCollision(false);
            }
        }
    }


    @Override
    public void run() {
        check();
    }

}