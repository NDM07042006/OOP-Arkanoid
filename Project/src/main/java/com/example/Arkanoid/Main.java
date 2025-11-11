package main.java.com.example.Arkanoid;

import javafx.application.Application;
import javafx.stage.Stage;

import main.java.com.example.Arkanoid.UI.*;
import main.java.com.example.Arkanoid.Utlis.SceneCache;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        System.out.println("Main start() with stage: " + stage);

        // Load tất cả âm thanh khi khởi động
        SoundManager.getInstance().loadAllSounds();

        // Preload scenes trong background để lần sau mở nhanh hơn
        System.out.println("🚀 Preloading scenes...");
        SceneCache.getInstance().preloadAll();

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