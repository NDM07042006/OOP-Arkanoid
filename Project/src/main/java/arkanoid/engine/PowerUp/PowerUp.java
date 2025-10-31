package main.java.arkanoid.engine.PowerUp;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import main.java.arkanoid.engine.GameObject;

public abstract class PowerUp extends GameObject {

    protected static final double FALL_SPEED = 1;
    protected static final int WIDTH = 30;// độ to của power up khi hiển thị
    protected static final int HEIGHT = 30;
    protected double x;
    protected double y;
    protected ImageView sprite;
    protected Scene scene;

    public PowerUp(double x, double y, Image image) {
        this.x = x;
        this.y = y;
        this.sprite = new ImageView(image);
        this.sprite.setX(this.x);
        this.sprite.setY(this.y);
        this.sprite.setFitWidth(WIDTH);
        this.sprite.setFitHeight(HEIGHT);
        this.sprite.setPreserveRatio(true);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageView getSprite() {
        return sprite;
    }

    @Override
    public void update() {
        y += FALL_SPEED;
        this.sprite.setY(y);
    }

    public abstract PowerUpType getType();

}