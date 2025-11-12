package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;
import main.java.arkanoid.engine.Define;

public class ExtraLife extends PowerUp {
    private static Image image = new Image( Define.EXTRA_LIFE_IMAGE_PATH);
    public static final PowerUpType TYPE = PowerUpType.EXTRA_LIFE;

    public ExtraLife(double x, double y){
        super(x, y, image);
    }

    @Override
    public PowerUpType getType() {
        return TYPE;
    }

    @Override
    public void applyEffect(){

        System.out.println(TYPE);
        gameEngine.addLife();
    }
}
