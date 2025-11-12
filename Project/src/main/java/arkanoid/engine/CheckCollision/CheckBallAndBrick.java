package main.java.arkanoid.engine.CheckCollision;

import java.util.Iterator;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.application.Platform;

import main.java.arkanoid.engine.Ball;
import main.java.arkanoid.engine.Map;
import main.java.arkanoid.engine.Bricks;
import main.java.com.example.Arkanoid.Utlis.Animations.ParticleSystem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class CheckBallAndBrick extends CheckCollision {
    private List<Ball> balls = gameEngine.getBalls();
    private Map map = gameEngine.getMap();
    @Override
    protected void check() {
        Pane gamePane = new Pane();
        ParticleSystem particleSystem = new ParticleSystem(gamePane);
        for (Ball ball : balls) {
            Iterator<Bricks> bricksIterator = map.getBrickGroup().iterator();
            while (bricksIterator.hasNext()) {
                Bricks brick = bricksIterator.next();
                if (ball.getSprite().getBoundsInParent().intersects(brick.getNode().getBoundsInParent())) {
                    if(!ball.brickCollision()){
                        //Set tình trạng va chạm là true, tránh kẹt bóng ở lần check sau
                        ball.setBrickCollision(true);

                        Bounds ballBounds = ball.getSprite().getBoundsInParent();
                        Bounds brickBounds = brick.getSprite().getBoundsInParent();
                        /*
                         * Đổi hướng bóng
                         */

                        // Chạm bên dưới gạch

                        if(ballBounds.getMinY()<=brickBounds.getMaxY()
                                && ballBounds.getMaxY()> brickBounds.getMaxY()){
                            ball.setVel_Y(Math.abs(ball.getVel_Y()));
                        }

                        // Chạm bên trên gạch

                        else if(ballBounds.getMaxY()>=brickBounds.getMinY()
                                && ballBounds.getMinY()< brickBounds.getMinY()){
                            ball.setVel_Y(-Math.abs(ball.getVel_Y()));
                        }

                        // Chạm bên phải gạch

                        else if(ballBounds.getMinX()<=brickBounds.getMaxX()
                                && ballBounds.getMaxX()> brickBounds.getMaxX()){
                            ball.setVel_X(Math.abs(ball.getVel_X()));
                        }

                        // Chạm bên trái gạch

                        else if(ballBounds.getMaxX()>=brickBounds.getMinX()
                                && ballBounds.getMinX()< brickBounds.getMinX()){
                            ball.setVel_X(-Math.abs(ball.getVel_X()));
                        }


                        brick.setCurrrentPoints(1);
                        System.out.println("Collision detected");
                        particleSystem.createExplosion(brick.getPos_X(), brick.getPos_Y(), Color.ORANGE, 50);


                    }
                }
                //Set lại khi không còn va chạm nữa
                else ball.setBrickCollision(false);
            }
        }
    }
    private void safeRemove(Bricks brick){
        Platform.runLater(() -> {
            gameEngine.remove(brick.getSprite());
        });
    }

    @Override
    public void run() {
        check();
    }
}