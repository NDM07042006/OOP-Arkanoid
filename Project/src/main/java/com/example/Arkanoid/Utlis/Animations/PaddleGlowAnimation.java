package main.java.com.example.Arkanoid.Utlis.Animations;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Animation chớp sáng cho Paddle khi bóng chạm vào
 * Chỉ dùng Glow + Shadow, KHÔNG scale để tránh ảnh hưởng collision
 */
public class PaddleGlowAnimation implements Animation {

    private Timeline animation;
    private boolean playing;

    // Cấu hình hiệu ứng
    private static final Duration GLOW_DURATION = Duration.millis(150);
    private static final double GLOW_LEVEL = 0.8;
    private static final double SHADOW_RADIUS = 25;

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
        Glow glow = new Glow(0); // Bắt đầu từ 0

        // Tạo DropShadow cho hiệu ứng chớp sáng
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setRadius(0); // Bắt đầu từ 0
        shadow.setSpread(0.5);

        // Kết hợp Glow + Shadow
        glow.setInput(shadow);
        node.setEffect(glow);

        // Tạo timeline để animate glow level và shadow radius
        animation = new Timeline(
                // Frame 1: Glow lên MAX
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.levelProperty(), 0),
                        new KeyValue(shadow.radiusProperty(), 0)
                ),
                new KeyFrame(GLOW_DURATION,
                        new KeyValue(glow.levelProperty(), GLOW_LEVEL),
                        new KeyValue(shadow.radiusProperty(), SHADOW_RADIUS)
                ),
                // Frame 2: Glow xuống về 0
                new KeyFrame(GLOW_DURATION.multiply(2),
                        new KeyValue(glow.levelProperty(), 0),
                        new KeyValue(shadow.radiusProperty(), 0)
                )
        );

        animation.setOnFinished(e -> {
            // Khôi phục effect gốc
            node.setEffect(originalEffect);
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
        Glow glow = new Glow(0);
        DropShadow shadow = new DropShadow();
        shadow.setColor(glowColor);
        shadow.setRadius(0);
        shadow.setSpread(0.5);
        glow.setInput(shadow);
        node.setEffect(glow);

        // Timeline animation
        animation = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.levelProperty(), 0),
                        new KeyValue(shadow.radiusProperty(), 0)
                ),
                new KeyFrame(GLOW_DURATION,
                        new KeyValue(glow.levelProperty(), GLOW_LEVEL),
                        new KeyValue(shadow.radiusProperty(), SHADOW_RADIUS)
                ),
                new KeyFrame(GLOW_DURATION.multiply(2),
                        new KeyValue(glow.levelProperty(), 0),
                        new KeyValue(shadow.radiusProperty(), 0)
                )
        );

        animation.setOnFinished(e -> {
            node.setEffect(originalEffect);
            playing = false;
        });

        animation.play();
    }
}