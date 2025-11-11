package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.arkanoid.engine.GameObject;
import main.java.arkanoid.engine.GameEngine;

public abstract class PowerUp extends GameObject {

    protected static final double FALL_SPEED = 1;
    protected static final int WIDTH = 30;// độ to của power up khi hiển thị
    protected static final int HEIGHT = 30;
    protected static GameEngine gameEngine;
    protected double x;
    protected double y;
    protected ImageView sprite;

    public PowerUp(double x, double y, Image image) {
        this.x = x;
        this.y = y;
        this.sprite = new ImageView(image);
        this.sprite.setX(this.x);
        this.sprite.setY(this.y);
        this.sprite.setFitWidth(WIDTH);
        this.sprite.setFitHeight(HEIGHT);
    }
    public static void setGameEngine(GameEngine engine) {
        gameEngine = engine;
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
    public abstract void applyEffect();

}