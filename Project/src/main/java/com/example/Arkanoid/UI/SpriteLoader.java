package main.java.com.example.Arkanoid.UI;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.arkanoid.engine.Define;

public class SpriteLoader {
    private static Image spriteImage;

    static {
        spriteImage = new Image(Define.BACKGROUND);
    }

    public static ImageView getSprite(double x, double y, double width, double height) {
        ImageView imageView = new ImageView(spriteImage);
        imageView.setViewport(new Rectangle2D(x, y, width, height));
        return imageView;
    }
}