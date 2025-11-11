package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;
import main.java.arkanoid.engine.Define;

public class RandomPowerUp extends PowerUp {
    private static Image image = new Image(Define.RANDOM_POWER_UP_IMAGE_PATH);
    public static final PowerUpType TYPE = PowerUpType.RANDOM_POWER_UP;

    public RandomPowerUp(double x, double y) {
        super(x, y, image);
    }

    @Override
    public PowerUpType getType() {
        return TYPE;
    }

    @Override
    public void applyEffect() {
        
    }
}
