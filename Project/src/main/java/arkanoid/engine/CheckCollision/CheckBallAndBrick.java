package main.java.arkanoid.engine.CheckCollision;

import java.util.Iterator;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.application.Platform;

import main.java.arkanoid.engine.Ball;
import main.java.arkanoid.engine.Map;
import main.java.arkanoid.engine.Bricks;
import main.java.com.example.Arkanoid.Utlis.Animations.PaddleGlowAnimation;

public class CheckBallAndBrick extends CheckCollision {
    private List<Ball> balls = gameEngine.getBalls();
    private Map map = gameEngine.getMap();

    private PaddleGlowAnimation paddleGlow = new PaddleGlowAnimation();

    @Override
    protected void check() {
        for (Ball ball : balls) {
            for (Bricks brick : Map.brickGroup) {
                if (ball.getSprite().getBoundsInParent().intersects(brick.getNode().getBoundsInParent())) {
                    Bounds ballBounds = ball.getSprite().getBoundsInParent();
                    Bounds brickBounds = brick.getSprite().getBoundsInParent();
                    /*
                     * Đổi hướng bóng
                     */
                    if (ballBounds.getMinY() <= brickBounds.getMaxY()
                            && ballBounds.getMaxY() > brickBounds.getMaxY()) {
                        ball.setVel_Y(-ball.getVel_Y());

                    } else if (ballBounds.getMinX() <= brickBounds.getMaxX()
                            && ballBounds.getMaxX() > brickBounds.getMaxX()) {
                        ball.setVel_X(-ball.getVel_X());
                    } else if (ballBounds.getMaxX() >= brickBounds.getMinX()
                            && ballBounds.getMinX() < brickBounds.getMinX()) {
                        ball.setVel_X(-ball.getVel_X());
                    } else if (ballBounds.getMaxY() >= brickBounds.getMinY()
                            && ballBounds.getMinY() < brickBounds.getMinY()) {
                        ball.setVel_Y(-ball.getVel_Y());
                    }
                    brick.setCurrrentPoints(1);

                    paddleGlow.play(brick.getNode());


                }
            }


        }
    }
    private void safeRemove(Bricks brick){
        Platform.runLater(() -> {
            gameEngine.remove(brick.getNode());
        });
    }

    @Override
    public void run() {
        check();
    }
}

/*

 */