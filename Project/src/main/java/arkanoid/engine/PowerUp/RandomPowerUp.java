package main.java.arkanoid.engine.PowerUp;

import java.util.Random;

import javafx.scene.image.Image;
import main.java.arkanoid.engine.Define;

public class RandomPowerUp extends PowerUp {
    private static Image image = new Image(Define.RANDOM_POWER_UP_IMAGE_PATH);
    public static final PowerUpType TYPE = PowerUpType.RANDOM_POWER_UP;

    private static MultiBall  effectMultiBall  = new MultiBall(0, 0);
    private static FastBall   effectFastBall   = new FastBall(0, 0);
    private static SlowBall   effectSlowBall   = new SlowBall(0, 0);
    private static FastPaddle effectFastPaddle = new FastPaddle(0, 0);
    private static SlowPaddle effectSlowPaddle = new SlowPaddle(0, 0);

    public RandomPowerUp(double x, double y) {
        super(x, y, image);
    }

    @Override
    public PowerUpType getType() {
        return TYPE;
    }

    @Override
    public void applyEffect() {
        PowerUpType[] types = PowerUpType.values();
        int randomIndex = new Random().nextInt(types.length - 1); // Loại bỏ phần tử cuối
        PowerUpType randomType = types[randomIndex];

        // Tùy vào loại random được chọn, tạo và áp dụng hiệu ứng tương ứng
        switch (randomType) {
            case MULTI_BALL:
                // Gọi hiệu ứng MULTI_BALL
                effectMultiBall.applyEffect();
                break;
            case FAST_BALL:
                // Gọi hiệu ứng FAST_BALL
                effectFastBall.applyEffect();
                break;
            case SLOW_BALL:
                // Gọi hiệu ứng SLOW_BALL
                effectSlowBall.applyEffect();
                break;
            case FAST_PADDLE:
                // Gọi hiệu ứng FAST_PADDLE
                effectFastPaddle.applyEffect();
                break;
            case SLOW_PADDLE:
                // Gọi hiệu ứng SLOW_PADDLE
                effectSlowPaddle.applyEffect();
                break;
            default:
                break;
        }
    }
}
