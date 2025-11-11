package main.java.com.example.Arkanoid.Utlis.Animations;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSystem {
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