package main.java.arkanoid.engine.CheckCollision;

import java.util.Iterator;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.application.Platform;

import main.java.arkanoid.engine.Ball;
import main.java.arkanoid.engine.Map;
import main.java.arkanoid.engine.Bricks;

public class CheckBallAndBrick extends CheckCollision {
    private List<Ball> balls = gameEngine.getBalls();
    private Map map = gameEngine.getMap();
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

                }
            }
            Iterator<Bricks> iterator = Map.brickGroup.iterator();
            while (iterator.hasNext()) {
                Bricks brick = iterator.next();
                if (brick.isDestroyed()) {
                    iterator.remove(); // safely removes current element
                    brick = null;
                }
            }



            /*
            Iterator<Bricks> bricksIterator = map.getBrickGroup().iterator();

            while (bricksIterator.hasNext()) {
                Bricks brick = bricksIterator.next();
                if (ball.getSprite().getBoundsInParent().intersects(brick.getNode().getBoundsInParent())) {
                    Bounds ballBounds = ball.getSprite().getBoundsInParent();
                    Bounds brickBounds = brick.getSprite().getBoundsInParent();

                      Đổi hướng bóng

                    if(ballBounds.getMinY()<=brickBounds.getMaxY()
                            && ballBounds.getMaxY()> brickBounds.getMaxY()){
                        ball.setVel_Y(-ball.getVel_Y());

                    }
                    else if(ballBounds.getMinX()<=brickBounds.getMaxX()
                            && ballBounds.getMaxX()> brickBounds.getMaxX()){
                        ball.setVel_X(-ball.getVel_X());
                    }
                    else if(ballBounds.getMaxX()>=brickBounds.getMinX()
                            && ballBounds.getMinX()< brickBounds.getMinX()){
                        ball.setVel_X(-ball.getVel_X());
                    }
                    else if(ballBounds.getMaxY()>=brickBounds.getMinY()
                            && ballBounds.getMinY()< brickBounds.getMinY()){
                        ball.setVel_Y(-ball.getVel_Y());
                    }
                    brick.setCurrrentPoints(1);

                      Xóa gạch

                    if (brick.isDestroyed()) {
                        gameEngine.remove(brick.getNode());
                        bricksIterator.remove(); // safely removes current element
                        brick = null;

                    }

                }
            }

             */




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