package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Utlis.SceneCache;

public class GameScene2 {
    private Stage stage;
    private int levelNumber;

    public GameScene2(Stage stage) {
        this(stage, 1); // Default level 1
    }

    public GameScene2(Stage stage, int levelNumber) {
        this.stage = stage;
        this.levelNumber = levelNumber;
    }

    public void show() {
        try {
            long startTime = System.currentTimeMillis();

            Scene scene;
            GameController gameController;


            // Thử dùng cached scene từ SceneCache
            scene = SceneCache.getInstance().getCachedScene("game");

            if (scene != null) {
                System.out.println("⚡ Using cached GameScene");
                gameController = (GameController) SceneCache.getInstance().getCachedController("game");
            } else {
                System.out.println("🔄 Loading GameScene from FXML...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.GAME_SCENE_PATH));
                Parent root = loader.load();

                scene = new Scene(root);
                gameController = loader.getController();

                System.out.println("✅ GameScene loaded and cached");
            }

            // Set stage và level
            if (gameController != null) {
                gameController.setStage(stage);
                gameController.setLevel(levelNumber);
            }

            stage.setTitle("Game - Level " + levelNumber);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            long endTime = System.currentTimeMillis();
            System.out.println("⏱️ GameScene show() took: " + (endTime - startTime) + "ms");
            System.out.println("TFFFFF");

        } catch (Exception e) {
            System.err.println("❌ Error loading GameScene:");
            e.printStackTrace();
        }
    }

    // Method để clear cache nếu cần
    public static void clearCache() {
        SceneCache.getInstance().clearCache("game");
        System.out.println("🗑️ GameScene cache cleared");
    }
}