package main.java.arkanoid.engine.PowerUp;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import main.java.arkanoid.engine.GameObject;

public abstract class PowerUp extends GameObject {

    protected static final int FALL_SPEED = 2;
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
    }

    public ImageView getSprite() {
        return sprite;
    }

    @Override
    public void update() {
        y += FALL_SPEED;
        this.sprite.setY(y);
    }

}