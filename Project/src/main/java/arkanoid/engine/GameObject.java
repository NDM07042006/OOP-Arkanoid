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
    Node node;
    boolean destroyed = false;

    public double getPos_X() {
        return pos_X;
    }

    public double getPos_Y() {
        return pos_Y;
    }
    public void update() {}



}



