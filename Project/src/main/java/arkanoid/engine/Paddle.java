package main.java.arkanoid.engine;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Rectangle2D;


public class Paddle extends GameObject{
    double vel_X;
    double vel_Y;
    int speed = 2;
    ImageView sprite;
    Scene scene;

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

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public Paddle(double startX, double startY, String imagePath) {
        pos_X = startX;
        pos_Y = startY;


        Image image = new Image(imagePath);
        sprite = new ImageView(image);
        sprite.setViewport(new Rectangle2D(0, 0, 96, 16));

        sprite.setX(pos_X);
        sprite.setY(pos_Y);
        sprite.setFitWidth(120);  // chỉnh kích thước nếu cần
        sprite.setFitHeight(30*1.2);
    }

    //give object to the Group
    public ImageView getNode() {
        return sprite;
    }

    @Override
    public void update() {
        pos_X += vel_X * speed;
        pos_Y += vel_Y * speed;
        sprite.setX(pos_X);
        sprite.setY(pos_Y);
    }

}
