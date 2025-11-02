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

    public int getPoint_given() {
        return Point_given;
    }

    public void setPoint_given(int point_given) {
        Point_given = point_given;
    }

    public int getCurrrentPoints() {
        return currrentPoints;
    }

    public void setCurrrentPoints(int currrentPoints) {
        this.currrentPoints = currrentPoints;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public Bricks(int posX, int posY, String imagePath,
                  int X, int Y, int screenHeight, int screenWidth, int row, int column, int HP) {

        currrentPoints = HP;
        Point_given = HP * 10; // Mỗi HP cho 10 điểm

        this.pos_X = posX;
        this.pos_Y = posY;

        Image image = new Image(imagePath);
        sprite = new ImageView(image);
        sprite.setViewport(new Rectangle2D(X, Y, 32, 16));

        // Tính kích thước viên gạch theo số hàng/cột và kích thước màn hình
        this.width = screenWidth / column;
        this.height = screenWidth / 2 / column;

        sprite.setX(pos_X);
        sprite.setY(pos_Y);
        sprite.setFitWidth(width);
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

    public void setPos_X(double x) {
        pos_X = x;
        sprite.setX(x); // Cập nhật vị trí visual
    }

    public void setPos_Y(double y) {
        pos_Y = y;
        sprite.setY(y); // Cập nhật vị trí visual
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
        if (currrentPoints == 0) {
            destroyed = true;
        }
    }
}
