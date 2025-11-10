package main.java.arkanoid.engine;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Ball extends GameObject {
    double vel_Y;
    double vel_X;
    int speed = 0;
    ImageView sprite;
    Scene scene;
    boolean isMoving = false;
    boolean paddleCollision = false;
    boolean brickCollision = false;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public double getVel_X() {
        return vel_X;
    }

    public void setVel_X(double vel_X) {
        this.vel_X = vel_X;
    }

    public double getVel_Y() {
        return vel_Y;
    }

    public void setVel_Y(double vel_Y) {
        this.vel_Y = vel_Y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean brickCollision(){
        return brickCollision;
    }

    public void setBrickCollision(boolean collision){
        brickCollision = collision;
    }
    public boolean paddleCollision(){
        return paddleCollision;
    }

    public void setPaddleCollision(boolean collision){
        paddleCollision = collision;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setSence(Scene scene) {
        this.scene = scene;
    }

    public Ball(double startX, double startY, String imagePath) {
        pos_X = startX;
        pos_Y = startY;

        Image image = new Image(imagePath);
        sprite = new ImageView(image);
        sprite.setViewport(new Rectangle2D(176, 16, 16, 16));

        sprite.setX(pos_X);
        sprite.setY(pos_Y);
        sprite.setFitWidth(30);  // chỉnh kích thước nếu cần
        sprite.setFitHeight(30);
    }


    public ImageView getNode() {
        return sprite;
    }

    @Override
    public void update() {
        if (!isMoving) return;
        pos_X += vel_X * speed;
        pos_Y += vel_Y * speed;
        sprite.setX(pos_X);
        sprite.setY(pos_Y);

        if (pos_X <= 0 || pos_X >= Define.SCREEN_WIDTH - 16) {vel_X = - vel_X;}
        if (pos_Y <= 0 || pos_Y >= Define.SCREEN_HEIGHT - 16) {vel_Y = - vel_Y;}
    }

}

