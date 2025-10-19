package main.java.com.example.Arkanoid;

import javafx.application.Application;
import javafx.stage.Stage;

import main.java.com.example.Arkanoid.UI.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        System.out.println("Main start() with stage: " + stage);
        MenuScene menu = new MenuScene(stage);
        menu.show();
        // EndScene endScene = new EndScene(stage);
        // endScene.show();
        // SettingScene settingScene = new SettingScene(stage);
        // settingScene.show();
        // PauseScene pauseScene = new PauseScene(stage);
        // pauseScene.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
