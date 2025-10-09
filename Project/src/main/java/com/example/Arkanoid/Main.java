package main.java.com.example.Arkanoid;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.UI.MenuScene;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        MenuScene menu = new MenuScene(stage);
        menu.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
