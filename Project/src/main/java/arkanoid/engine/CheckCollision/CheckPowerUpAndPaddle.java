package main.java.arkanoid.engine.CheckCollision;

import java.util.List;
import javafx.application.Platform;
import java.util.Iterator;

import main.java.arkanoid.engine.PowerUp.PowerUp;
import main.java.arkanoid.engine.Paddle;

public class CheckPowerUpAndPaddle extends CheckCollision {
    private List<PowerUp> powerUps = gameEngine.getPowerUps();
    private Paddle paddle = gameEngine.getPaddle();

    @Override
    protected void check() {
        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            if (powerUp.getSprite().getBoundsInParent().intersects(paddle.getSprite().getBoundsInParent())) {
                /*
                 * Xóa power up
                 */
                safeRemove(powerUp);
                powerUpIterator.remove();
            }
        }
    }

    private void safeRemove(PowerUp powerUp) {
        Platform.runLater(() -> {
            gameEngine.remove(powerUp.getSprite());
            powerUp.applyEffect();
        });
    }

    @Override
    public void run() {
        check();
    }

}