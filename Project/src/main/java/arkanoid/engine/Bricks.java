package main.java.arkanoid.engine;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bricks extends GameObject {
    static int HitPoints = 1;
    int Point_given;
    int currrentPoints = HitPoints;
    int width = 50;
    int height = 25;

    ImageView sprite;
    Scene scene;

    public Bricks(int posX, int posY, String imagePath, int X, int Y) {


        Image image = new Image(imagePath);
        sprite = new ImageView(image);
        sprite.setViewport(new Rectangle2D(X, Y, 32, 16));

        sprite.setX(pos_X);
        sprite.setY(pos_Y);
        sprite.setFitWidth(width);  // chỉnh kích thước nếu cần
        sprite.setFitHeight(height);
    }

    public void setHitPoints(int hitPoints) {
        this.HitPoints = hitPoints;
    }



    public void setSence(Scene scene) {
        this.scene = scene;
    }

    public ImageView getNode() {
        return sprite;
    }

    public void setPos_X(int x) {
        pos_X = x;
    }

    public void setPos_Y(int y) {
        pos_Y = y;
    }

    public double getPos_X() {
        return pos_X;
    }

    public double getPos_Y() {
        return pos_Y;
    }

    public void Bricks_destroyed() {
        if (HitPoints == 0) {
            destroyed = true;
        }
    }

    public void update() {

    }







}
