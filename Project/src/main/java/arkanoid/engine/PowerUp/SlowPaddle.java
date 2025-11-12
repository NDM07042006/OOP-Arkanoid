package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;
import main.java.arkanoid.engine.Define;

public class SlowPaddle extends PowerUp {
    private static Image image = new Image(Define.SLOW_PADDLE_IMAGE_PATH);
    public static final PowerUpType TYPE = PowerUpType.SLOW_PADDLE;

    public SlowPaddle(double x, double y){
        super(x, y, image);
    }

    @Override
    public PowerUpType getType() {
        return TYPE;
    }

    @Override
    public void applyEffect(){
        System.out.println(TYPE);
        gameEngine.setPaddleSpeed(-2);
    }
}