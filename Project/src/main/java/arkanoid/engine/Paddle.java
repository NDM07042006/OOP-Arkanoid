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
    int speed = 5;
    ImageView sprite;
    Scene scene;
    public void setSence(Scene scene) {
        this.scene = scene;
    }

    public void getX() {
        System.out.println(pos_X);
    }

    public  void getY() {
        System.out.println(pos_Y);
    }


    public Paddle(double startX, double startY, String imagePath) {
        pos_X = startX;
        pos_Y = startY;


        Image image = new Image(imagePath);
        sprite = new ImageView(image);
        sprite.setViewport(new Rectangle2D(0, 0, 96, 16));

        sprite.setX(pos_X);
        sprite.setY(pos_Y);
        sprite.setFitWidth(100);  // chỉnh kích thước nếu cần
        sprite.setFitHeight(30);
    }

    public ImageView getNode() {
        return sprite;
    }



    public void movingSideway() {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case A : vel_X = -1;

                        getX();
                        getY();
                        break;
                    case D : vel_X = 1;
                        getX();
                        getY();
                        break;

                }

            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case A, D, W, S:
                        vel_X = 0;
                        vel_Y = 0;
                        break;
                }
            }
        });

    }

    public void update() {
        pos_X += vel_X * speed;
        pos_Y += vel_Y * speed;
    }

    public void draw() {
        sprite.setX(pos_X);
        sprite.setY(pos_Y);
    }


}
