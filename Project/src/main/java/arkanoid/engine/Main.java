package main.java.arkanoid.engine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import main.java.com.example.Arkanoid.UI.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


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

        // Thêm handler để đóng app đúng cách khi close window
        stage.setOnCloseRequest(event -> {
            System.out.println("🛑 Closing application...");
            // Stop all sounds
            SoundManager.getInstance().stopAllSounds();
            // Exit JavaFX Platform
            Platform.exit();
            // Force exit JVM
            System.exit(0);
        });

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

/*

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource(Define.GAME_SCENE)
        );
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Arkanoid");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


 */