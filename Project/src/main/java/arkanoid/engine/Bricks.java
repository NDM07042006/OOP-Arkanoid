package main.java.arkanoid.engine;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Bricks extends GameObject {
    int Point_given;
    double currrentPoints;
    double Max_HP;
    int width = 50;
    int height = 25;
    boolean givePowerUp = false;
    int PowerUp_Type;

    public int getPowerUp_Type() {
        return PowerUp_Type;
    }

    public void setPowerUp_Type(int powerUp_Type) {
        PowerUp_Type = powerUp_Type;
    }

    public boolean isGivePowerUp() {
        return givePowerUp;
    }

    public void setGivePowerUp(boolean givePowerUp) {
        this.givePowerUp = givePowerUp;
    }

    ImageView sprite;
    Scene scene;


    public int getPoint_given() {
        return Point_given;
    }

    public void setPoint_given(int point_given) {
        Point_given = point_given;
    }

    public double getCurrrentPoints() {
        return currrentPoints;
    }

    public void setCurrrentPoints(double minusPoint) {
        this.currrentPoints -= minusPoint;
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
                  int X, int Y, int  screenWidth, int screenHeight, int row, int column, double HP) {

        currrentPoints = HP;
        Max_HP = HP;
        this.pos_X = posX;
        this.pos_Y = posY;

        Image image = new Image(imagePath);
        sprite = new ImageView(image);
        sprite.setViewport(new Rectangle2D(X, Y, 32, 16));

        // Tính kích thước viên gạch theo số hàng/cột và kích thước màn hình
        this.width = screenWidth / column;
        this.height = screenWidth/ 2 / column ;

        sprite.setX(pos_X);
        sprite.setY(pos_Y);
        sprite.setFitWidth(width);
        sprite.setFitHeight(height);
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


    public void update() {
        if (currrentPoints <= 0) {
            destroyed = true;
        }

        if (currrentPoints <= Max_HP/3 ) {
            sprite.setViewport(new Rectangle2D(176, sprite.getViewport().getMinY(), sprite.getViewport().getWidth(), sprite.getViewport().getHeight()));
        }
        else if (currrentPoints <= 2*Max_HP/3 && currrentPoints >= Max_HP/3) {
            sprite.setViewport(new Rectangle2D(176-32, sprite.getViewport().getMinY(), sprite.getViewport().getWidth(), sprite.getViewport().getHeight()));
        }

    }
}