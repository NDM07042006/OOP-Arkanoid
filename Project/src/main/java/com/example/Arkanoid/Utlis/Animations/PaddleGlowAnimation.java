package main.java.com.example.Arkanoid.Utlis.Animations;

import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Animation chớp sáng cho Paddle khi bóng chạm vào
 * Hiệu ứng: Glow + Scale + Shadow
 */
public class PaddleGlowAnimation implements Animation {

    private ParallelTransition animation;
    private boolean playing;

    // Cấu hình hiệu ứng
    private static final Duration GLOW_DURATION = Duration.millis(150);
    private static final double SCALE_AMOUNT = 1.1;
    private static final double GLOW_LEVEL = 0.8;

    public PaddleGlowAnimation() {
        this.playing = false;
    }

    @Override
    public void play(Node node) {
        play(node, null);
    }

    @Override
    public void play(Node node, Runnable onFinished) {
        if (playing) {
            return; // Không chạy nếu đang có animation
        }

        playing = true;

        // Lưu effect gốc
        var originalEffect = node.getEffect();

        // Tạo Glow effect
        Glow glow = new Glow(GLOW_LEVEL);

        // Tạo DropShadow cho hiệu ứng chớp sáng
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setRadius(20);
        shadow.setSpread(0.5);

        // Kết hợp Glow + Shadow
        glow.setInput(shadow);
        node.setEffect(glow);

        // Scale animation - phóng to nhẹ
        ScaleTransition scaleUp = new ScaleTransition(GLOW_DURATION, node);
        scaleUp.setToX(SCALE_AMOUNT);
        scaleUp.setToY(SCALE_AMOUNT);

        // Scale về lại bình thường
        ScaleTransition scaleDown = new ScaleTransition(GLOW_DURATION, node);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        // Chạy scale up -> scale down
        SequentialTransition scaleSequence = new SequentialTransition(scaleUp, scaleDown);

        // Kết hợp tất cả
        animation = new ParallelTransition(scaleSequence);

        animation.setOnFinished(e -> {
            // Khôi phục effect gốc
            node.setEffect(originalEffect);
            node.setScaleX(1.0);
            node.setScaleY(1.0);
            playing = false;

            if (onFinished != null) {
                onFinished.run();
            }
        });

        animation.play();
    }

    @Override
    public void stop() {
        if (animation != null && playing) {
            animation.stop();
            playing = false;
        }
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Tạo animation với màu tùy chỉnh
     */
    public void playWithColor(Node node, Color glowColor) {
        if (playing) return;

        playing = true;
        var originalEffect = node.getEffect();

        // Tạo effect với màu tùy chỉnh
        Glow glow = new Glow(GLOW_LEVEL);
        DropShadow shadow = new DropShadow();
        shadow.setColor(glowColor);
        shadow.setRadius(20);
        shadow.setSpread(0.5);
        glow.setInput(shadow);
        node.setEffect(glow);

        // Scale animation
        ScaleTransition scaleUp = new ScaleTransition(GLOW_DURATION, node);
        scaleUp.setToX(SCALE_AMOUNT);
        scaleUp.setToY(SCALE_AMOUNT);

        ScaleTransition scaleDown = new ScaleTransition(GLOW_DURATION, node);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        SequentialTransition scaleSequence = new SequentialTransition(scaleUp, scaleDown);
        animation = new ParallelTransition(scaleSequence);

        animation.setOnFinished(e -> {
            node.setEffect(originalEffect);
            node.setScaleX(1.0);
            node.setScaleY(1.0);
            playing = false;
        });

        animation.play();
    }
}
