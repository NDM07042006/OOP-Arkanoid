package main.java.arkanoid.engine;

import main.java.com.example.Arkanoid.Utlis.SoundManager;
import java.util.ArrayList;

/**
 * Collision Handler với âm thanh tích hợp
 */
public class CollisionHandler {
    private int screenWidth;
    private int screenHeight;
    private SoundManager soundManager;

    public void CollisionHandler(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.soundManager = SoundManager.getInstance();
    }

    /**
     * Kiểm tra và xử lý tất cả va chạm
     * @return true nếu ball bị mất (rơi xuống đáy)
     */
    public boolean handleAllCollisions(Ball ball, Paddle paddle, ArrayList<Bricks> bricks) {
        // 1. Kiểm tra ball vs walls
        if (checkBallVsWalls(ball)) {
            return true; // Ball lost
        }

        // 2. Kiểm tra ball vs paddle
        checkBallVsPaddle(ball, paddle);

        // 3. Kiểm tra ball vs bricks
        checkBallVsBricks(ball, bricks);

        return false;
    }

    /**
     * Kiểm tra va chạm ball với tường
     * @return true nếu ball rơi xuống đáy
     */
    private boolean checkBallVsWalls(Ball ball) {
        double ballX = ball.pos_X;
        double ballY = ball.pos_Y;
        double ballWidth = ball.getSprite().getFitWidth();
        double ballHeight = ball.getSprite().getFitHeight();

        // Va chạm tường trái
        if (ballX <= 0) {
            ball.pos_X = 0;
            ball.vel_X = -ball.vel_X;
            soundManager.playWallHit(); // ⭐ Phát âm thanh
        }

        // Va chạm tường phải
        if (ballX + ballWidth >= screenWidth) {
            ball.pos_X = screenWidth - ballWidth;
            ball.vel_X = -ball.vel_X;
            soundManager.playWallHit(); // ⭐ Phát âm thanh
        }

        // Va chạm tường trên
        if (ballY <= 0) {
            ball.pos_Y = 0;
            ball.vel_Y = -ball.vel_Y;
            soundManager.playWallHit(); // ⭐ Phát âm thanh
        }

        // Va chạm đáy (mất ball)
        if (ballY >= screenHeight) {
            soundManager.playLifeLost(); // ⭐ Phát âm thanh mất mạng
            return true; // Ball lost
        }

        return false;
    }

    /**
     * Kiểm tra va chạm ball với paddle
     */
    private void checkBallVsPaddle(Ball ball, Paddle paddle) {
        if (isCollidingBallPaddle(ball, paddle)) {
            // Tính vị trí va chạm trên paddle (0 = trái cùng, 0.5 = giữa, 1 = phải cùng)
            double ballCenterX = ball.pos_X + ball.getSprite().getFitWidth() / 2;
            double hitPosition = (ballCenterX - paddle.pos_X) / paddle.getSprite().getFitWidth();

            // Điều chỉnh góc phản xạ dựa trên vị trí chạm
            double angle = (hitPosition - 0.5) * Math.PI * 0.8; // -72° đến 72°

            // Giữ nguyên tốc độ hiện tại của ball
            double currentSpeed = Math.sqrt(ball.vel_X * ball.vel_X + ball.vel_Y * ball.vel_Y);

            // Áp dụng góc mới
            ball.vel_X = Math.sin(angle) * currentSpeed;
            ball.vel_Y = -Math.abs(Math.cos(angle) * currentSpeed); // Luôn đi lên

            // Đẩy ball lên trên paddle để tránh bị stuck
            ball.pos_Y = paddle.pos_Y - ball.getSprite().getFitHeight();

            soundManager.playPaddleHit(); // ⭐ Phát âm thanh va chạm paddle
        }
    }

    /**
     * Kiểm tra va chạm ball với bricks
     */
    private void checkBallVsBricks(Ball ball, ArrayList<Bricks> bricks) {
        for (int i = bricks.size() - 1; i >= 0; i--) {
            Bricks brick = bricks.get(i);

            // Bỏ qua brick đã bị phá hủy
            if (brick.isDestroyed()) continue;

            if (isCollidingBallBrick(ball, brick)) {
                // Xác định hướng va chạm
                CollisionSide side = getCollisionSide(ball, brick);

                // Phản xạ ball theo hướng va chạm
                if (side == CollisionSide.TOP || side == CollisionSide.BOTTOM) {
                    ball.vel_Y = -ball.vel_Y;
                } else { // LEFT hoặc RIGHT
                    ball.vel_X = -ball.vel_X;
                }

                // Xử lý damage brick
                int currentHP = brick.getCurrrentPoints();

                if (currentHP != -1) { // Không phải unbreakable brick
                    brick.setCurrrentPoints(currentHP - 1);

                    if (brick.getCurrrentPoints() <= 0) {
                        // Brick bị phá hủy
                        brick.setDestroyed(true);
                        brick.getSprite().setVisible(false);
                        soundManager.playBrickBreak(); // ⭐ Phát âm thanh vỡ gạch
                    } else {
                        // Brick bị hit nhưng chưa vỡ - có thể thêm sound khác
                        soundManager.playWallHit(); // Tạm dùng wall hit
                    }
                } else {
                    // Unbreakable brick
                    soundManager.playWallHit(); // Âm thanh bật lại
                }

                // Chỉ xử lý 1 brick mỗi frame để tránh bug
                break;
            }
        }
    }

    /**
     * Kiểm tra va chạm giữa ball và paddle (AABB - Axis-Aligned Bounding Box)
     */
    private boolean isCollidingBallPaddle(Ball ball, Paddle paddle) {
        double ballX = ball.pos_X;
        double ballY = ball.pos_Y;
        double ballW = ball.getSprite().getFitWidth();
        double ballH = ball.getSprite().getFitHeight();

        double paddleX = paddle.pos_X;
        double paddleY = paddle.pos_Y;
        double paddleW = paddle.getSprite().getFitWidth();
        double paddleH = paddle.getSprite().getFitHeight();

        // Chỉ va chạm khi ball đang rơi xuống (tránh bug khi ball đi lên từ dưới paddle)
        if (ball.vel_Y <= 0) return false;

        // AABB collision detection
        return ballX < paddleX + paddleW &&
                ballX + ballW > paddleX &&
                ballY < paddleY + paddleH &&
                ballY + ballH > paddleY;
    }

    /**
     * Kiểm tra va chạm giữa ball và brick (AABB)
     */
    private boolean isCollidingBallBrick(Ball ball, Bricks brick) {
        double ballX = ball.pos_X;
        double ballY = ball.pos_Y;
        double ballW = ball.getSprite().getFitWidth();
        double ballH = ball.getSprite().getFitHeight();

        double brickX = brick.getPos_X();
        double brickY = brick.getPos_Y();
        double brickW = brick.getWidth();
        double brickH = brick.getHeight();

        // AABB collision detection
        return ballX < brickX + brickW &&
                ballX + ballW > brickX &&
                ballY < brickY + brickH &&
                ballY + ballH > brickY;
    }

    /**
     * Xác định hướng va chạm giữa ball và brick
     * Dựa trên SAT (Separating Axis Theorem)
     */
    private CollisionSide getCollisionSide(Ball ball, Bricks brick) {
        // Tính tâm của ball và brick
        double ballCenterX = ball.pos_X + ball.getSprite().getFitWidth() / 2;
        double ballCenterY = ball.pos_Y + ball.getSprite().getFitHeight() / 2;

        double brickCenterX = brick.getPos_X() + brick.getWidth() / 2;
        double brickCenterY = brick.getPos_Y() + brick.getHeight() / 2;

        // Tính khoảng cách từ tâm ball đến tâm brick
        double dx = ballCenterX - brickCenterX;
        double dy = ballCenterY - brickCenterY;

        // Tính kích thước "mở rộng"
        double width = (ball.getSprite().getFitWidth() + brick.getWidth()) / 2;
        double height = (ball.getSprite().getFitHeight() + brick.getHeight()) / 2;

        // Tính crossWidth và crossHeight để xác định hướng va chạm
        double crossWidth = width * dy;
        double crossHeight = height * dx;

        // Xác định collision side
        if (Math.abs(dx) <= width && Math.abs(dy) <= height) {
            if (crossWidth > crossHeight) {
                return (crossWidth > -crossHeight) ? CollisionSide.BOTTOM : CollisionSide.LEFT;
            } else {
                return (crossWidth > -crossHeight) ? CollisionSide.RIGHT : CollisionSide.TOP;
            }
        }

        // Default (không nên xảy ra)
        return CollisionSide.TOP;
    }

    /**
     * Enum định nghĩa các hướng va chạm
     */
    private enum CollisionSide {
        TOP,    // Va chạm từ trên
        BOTTOM, // Va chạm từ dưới
        LEFT,   // Va chạm từ trái
        RIGHT   // Va chạm từ phải
    }
}