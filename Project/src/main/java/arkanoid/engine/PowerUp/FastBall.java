package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;

import main.java.arkanoid.engine.Define;

public class FastBall extends PowerUp {
    private static Image image = new Image(Define.FAST_BALL_IMAGE_PATH);
    public static final PowerUpType TYPE = PowerUpType.FAST_BALL;

    public FastBall(double x, double y){
        super(x, y, image);
    }

    @Override
    public PowerUpType getType() {
        return TYPE;
    }

    @Override
    public void applyEffect(){
        gameEngine.setAllBallSpeed(1);
    }
}
