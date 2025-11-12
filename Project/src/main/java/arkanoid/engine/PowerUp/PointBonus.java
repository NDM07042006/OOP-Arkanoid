package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;
import main.java.arkanoid.engine.Define;

public class PointBonus extends PowerUp {
    private static Image image = new Image(Define.POINT_BONUS_IMAGE_PATH);
    public static final PowerUpType TYPE = PowerUpType.POINT_BONUS;

    public PointBonus(double x, double y){
        super(x, y, image);
    }

    private int randomPoint() {
        return (int)(Math.random() * 101);
    }

    @Override
    public PowerUpType getType() {
        return TYPE;
    }

    @Override
    public void applyEffect(){
        System.out.println(TYPE);
        //Thêm hàm Cộng điểm của gameEngine ở đây
        gameEngine.addScore(randomPoint());
    }
}
