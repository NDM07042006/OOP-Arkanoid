package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;

import main.java.arkanoid.engine.Define;

public class MultiBall extends PowerUp {

    private static Image image = new Image(Define.MULTI_BALL_IMAGE_PATH);
    public static final PowerUpType TYPE = PowerUpType.MULTI_BALL;

    public MultiBall(double x, double y) {
        super(x, y, image);
    }

    @Override
    public PowerUpType getType() {
        return TYPE;
    }

}