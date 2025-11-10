package main.java.arkanoid.engine.CheckCollision;

import java.util.Iterator;
import java.util.List;
import javafx.geometry.Bounds;

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
                if(!ball.paddleCollision()){
                    //Set tình trạng va chạm là true, tránh kẹt bóng ở lần check sau
                    ball.setPaddleCollision(true);

                    Bounds paddleBounds = paddle.getSprite().getBoundsInParent();
                    double paddleLength = paddleBounds.getWidth();
                    double paddleMidPoint = paddleBounds.getCenterX();
                    
                    Bounds ballBounds = ball.getSprite().getBoundsInParent();
                    double ballMidPoint = ballBounds.getCenterX();

                    //Tính góc vector vận tốc sau va chạm
                    double degrees = (ballMidPoint - paddleMidPoint)/(paddleLength/2)*75;
                    ball.setVel_X(gameEngine.setVelBall_X(degrees));
                    ball.setVel_Y(gameEngine.setVelBall_y(degrees));
                }
            }
            //Set lại khi không còn va chạm nữa
            else ball.setPaddleCollision(false);
        }
    }


    @Override
    public void run() {
        check();
    }

}