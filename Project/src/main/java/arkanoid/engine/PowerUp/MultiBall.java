package main.java.arkanoid.engine.PowerUp;

import javafx.scene.image.Image;

public class MultiBall extends PowerUp {

    private static Image image = new Image("/com/Arkanoid/img/stone.png");

    public MultiBall(double x, double y) {
        super(x, y, image);
    }

}