package main.java.arkanoid.engine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.Node;


public class GameObject {
    public double pos_X;
    public double pos_Y;
    double Width = 48;
    double Height = 50;
    Image image;

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    Node node;
    boolean destroyed = false;



    public void update() {}
}



