package main.java.com.example.Arkanoid.Utlis.Animations;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Particle {
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