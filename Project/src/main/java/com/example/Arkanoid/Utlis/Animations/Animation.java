package main.java.com.example.Arkanoid.Utlis.Animations;

import javafx.scene.Node;

/**
 * Interface chung cho tất cả animations trong game
 * Áp dụng Strategy Pattern
 */
public interface Animation {

    /**
     * Phát animation trên node
     * @param node Node cần áp dụng animation (paddle, brick, ball, ...)
     */
    void play(Node node);

    /**
     * Phát animation với callback khi hoàn thành
     * @param node Node cần áp dụng animation
     * @param onFinished Callback được gọi khi animation kết thúc
     */
    void play(Node node, Runnable onFinished);

    /**
     * Dừng animation
     */
    void stop();

    /**
     * Kiểm tra animation có đang chạy không
     * @return true nếu đang chạy, false nếu không
     */
    boolean isPlaying();
}