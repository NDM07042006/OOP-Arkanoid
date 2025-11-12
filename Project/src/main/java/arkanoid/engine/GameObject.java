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
    double pos_X;
    double pos_Y;
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

    public double getPos_X() {
        return pos_X;
    }

    public void setPos_X(double pos_X) {
        this.pos_X = pos_X;
    }

    public double getPos_Y() {
        return pos_Y;
    }

    public void setPos_Y(double pos_Y) {
        this.pos_Y = pos_Y;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(double width) {
        Width = width;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void update() {}
}



