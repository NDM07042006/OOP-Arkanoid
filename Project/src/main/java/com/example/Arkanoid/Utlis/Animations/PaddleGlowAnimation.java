package main.java.com.example.Arkanoid.Utlis.Animations;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static class Particle {
        private final Node shape;
        private double velX, velY;
        private double lifeSpan;
        private double currentLife;

        public Particle(double x, double y, double size, Color color, double velX, double velY, double lifeSpan) {
            this.shape = new Rectangle(size, size, color);
            this.shape.setTranslateX(x);
            this.shape.setTranslateY(y);
            this.velX = velX;
            this.velY = velY;
            this.lifeSpan = lifeSpan;
            this.currentLife = 0;
        }

        public Node getShape() {
            return shape;
        }

        /**
         * Cập nhật vị trí và độ mờ của hạt.
         * @param deltaTime Thời gian trôi qua giữa hai khung hình (nên dùng trong GameEngine)
         * @return true nếu hạt đã hết thời gian tồn tại (cần bị xóa)
         */
        public boolean update(double deltaTime) {
            currentLife += deltaTime;

            // Cập nhật vị trí: Tăng thêm độ trượt nhẹ (hoặc trọng lực)
            shape.setTranslateX(shape.getTranslateX() + velX * deltaTime);
            shape.setTranslateY(shape.getTranslateY() + velY * deltaTime);

            // Giảm độ trong suốt (Opacity) để tạo hiệu ứng mờ dần
            shape.setOpacity(1 - (currentLife / lifeSpan));

            return currentLife >= lifeSpan;
        }
    }

    public static class ParticleSystem {
        private List<Particle> particles;
        private Pane gamePane;
        private Random random;

        public ParticleSystem(Pane gamePane) {
            this.particles = new ArrayList<>();
            this.gamePane = gamePane;
            this.random = new Random();
        }

        /**
         * Tạo hiệu ứng nổ tại vị trí (x, y)
         */
        public void createExplosion(double x, double y, Color color, int particleCount) {
            for (int i = 0; i < particleCount; i++) {
                // Random velocity cho mỗi hạt
                double angle = random.nextDouble() * 2 * Math.PI; // 0 đến 360 độ
                double speed = 50 + random.nextDouble() * 100; // Tốc độ 50-150
                double velX = Math.cos(angle) * speed;
                double velY = Math.sin(angle) * speed;

                // Kích thước hạt ngẫu nhiên
                double size = 3 + random.nextDouble() * 5; // 3-8 pixels

                // Thời gian sống: 0.5 - 1.5 giây
                double lifeSpan = 0.5 + random.nextDouble();

                Particle particle = new Particle(x, y, size, color, velX, velY, lifeSpan);
                particles.add(particle);
                gamePane.getChildren().add(particle.getShape());
            }
        }

        /**
         * Cập nhật tất cả particles
         * Gọi trong GameLoop (mỗi frame)
         */
        public void update(double deltaTime) {
            List<Particle> toRemove = new ArrayList<>();

            for (Particle particle : particles) {
                boolean isDead = particle.update(deltaTime);

                if (isDead) {
                    toRemove.add(particle);
                    gamePane.getChildren().remove(particle.getShape());
                }
            }

            particles.removeAll(toRemove);
        }

        /**
         * Tạo trail effect cho bóng
         */
        public void createTrail(double x, double y, Color color) {
            double size = 4 + random.nextDouble() * 3;
            double lifeSpan = 0.3 + random.nextDouble() * 0.2;
            double velX = (random.nextDouble() - 0.5) * 10;
            double velY = (random.nextDouble() - 0.5) * 10;

            Particle particle = new Particle(x, y, size, color, velX, velY, lifeSpan);
            particles.add(particle);
            gamePane.getChildren().add(particle.getShape());
        }
        /**
         * Xóa tất cả particles
         */
        public void clear() {
            for (Particle particle : particles) {
                gamePane.getChildren().remove(particle.getShape());
            }
            particles.clear();
        }

        /**
         * Số lượng particles đang active
         */
        public int getParticleCount() {
            return particles.size();
        }
    }
}