package main.java.com.example.Arkanoid.UI;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteLoader {
    private static Image spriteImage;

    static {
        spriteImage = new Image("/com/Arkanoid/images/background_game.png");
    }

    public static ImageView getSprite(double x, double y, double width, double height) {
        ImageView imageView = new ImageView(spriteImage);
        imageView.setViewport(new Rectangle2D(x, y, width, height));
        return imageView;
    }
}