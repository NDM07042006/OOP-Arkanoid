package main.java.arkanoid.engine.CheckCollision;

import java.util.Iterator;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.application.Platform;

import main.java.arkanoid.engine.Ball;
import main.java.arkanoid.engine.Map;
import main.java.arkanoid.engine.Bricks;
import main.java.com.example.Arkanoid.Utlis.Animations.PaddleGlowAnimation;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

public class CheckBallAndBrick extends CheckCollision {
    private List<Ball> balls = gameEngine.getBalls();
    private Map map = gameEngine.getMap();

    private PaddleGlowAnimation paddleGlow = new PaddleGlowAnimation();



    @Override
    protected void check() {
        for (Ball ball : balls) {
            for (Bricks brick : Map.brickGroup) {
                if (ball.getSprite().getBoundsInParent().intersects(brick.getNode().getBoundsInParent())) {
                    if(!ball.brickCollision()){
                        ball.setBrickCollision(true);
                        Bounds ballBounds = ball.getSprite().getBoundsInParent();
                        Bounds brickBounds = brick.getSprite().getBoundsInParent();
                        /*
                         * Đổi hướng bóng
                         */
                        /*
                         * Chạm bên dưới gạch
                         */
                        if(ballBounds.getMinY()<=brickBounds.getMaxY()
                                && ballBounds.getMaxY()> brickBounds.getMaxY()){
                            ball.setVel_Y(-ball.getVel_Y());
                        }
                        /*
                         * Chạm bên trên gạch
                         */
                        else if(ballBounds.getMaxY()>=brickBounds.getMinY()
                                && ballBounds.getMinY()< brickBounds.getMinY()){
                            ball.setVel_Y(-ball.getVel_Y());
                        }
                        /*
                         * Chạm bên phải gạch
                         */
                        else if(ballBounds.getMinX()<=brickBounds.getMaxX()
                                && ballBounds.getMaxX()> brickBounds.getMaxX()){
                            ball.setVel_X(-ball.getVel_X());
                        }
                        /*
                         * Chạm bên trái gạch
                         */
                        else if(ballBounds.getMaxX()>=brickBounds.getMinX()
                                && ballBounds.getMinX()< brickBounds.getMinX()){
                            ball.setVel_X(-ball.getVel_X());
                        }

                        brick.setCurrrentPoints(1);
                        System.out.println("Collision detected");



                    }
                    paddleGlow.play(brick.getNode());
                    SoundManager.getInstance().playSoundEffect("paddle_hit", 0.8); // 80% volume

                }

                else ball.setBrickCollision(false);;


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
